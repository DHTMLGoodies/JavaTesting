package com.example.secondlesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;

public class FenParser {

	private String fen;
	
	private Map<String, Object> cache;


	private PositionCache posCache = new PositionCache();
	
	@SuppressLint("UseSparseArrays")
	private Map<Integer, Integer> board = new HashMap<Integer, Integer>();
	
	public FenParser(String fenToSet){
		setFen(fenToSet);
	}
	
	public FenParser(){
		setFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}
	
	public String getFen(){
		return fen;
	}
		
	private void setFen(String fenToSet){
		fen = fenToSet;	
		updateFenCache();
		parseFen();
	}
	
	@SuppressLint("UseValueOf")
	private void updateFenCache(){
		String[] temp;
		temp = fen.split(" ");
		cache = new HashMap<String,Object>();
		cache.put("pieces", temp[0]);
		cache.put("color", temp[1]);
		cache.put("castleCode", BoardCache.castleToNumberMapping.get(temp[2]));
		cache.put("enPassant", temp[3]);
		cache.put("halfMoves", new Integer(temp[4]));
		cache.put("fullMoves", new Integer(temp[5]));

	}
	
	private void parseFen(){
		Integer pos = 0;
	
		String pieces = this.getFenPieces();
		
		int len = pieces.length();
		
		for(int i=0; i < len; i++){
			
			// char token = pieces.charAt(i);
			
			String token = pieces.substring(i, i+1);
			
			if(BoardCache.fenPieces.containsKey(token)){
				Integer index = BoardCache.mapping.get(BoardCache.fenSquares[pos]);
				Integer type = BoardCache.pieces.get(token);
				
				Piece piece = new Piece(type, index);		
				
				board.put(index, type);

				posCache.addColoredPiece(BoardCache.colorMapping.get(token), piece);			
				
				if(BoardCache.typeMapping.get(type) == "king"){
					posCache.storeKing(piece);
				}
				
				pos ++;
			}else if(i < (len - 1) && BoardCache.numbers.containsKey(token)){
				char token2 = pieces.charAt(i + 1);
				if(BoardCache.numbers.containsKey(token2)){
					pos +=  Integer.parseInt(pieces.substring(i, 2));					
				}else{
					pos += Integer.parseInt(token);	
				}				
			}
		}
	}
	
	@SuppressLint("UseSparseArrays")
	public Map<Integer, Piece> getPinned(String color){
		Map<Integer, Piece> ret = new HashMap<Integer, Piece>();
		
		ArrayList<Piece> pieces = getSlidingPiecesAttackingKing(color == "white" ? "black" : "white");
		
		boolean WHITE = color == "white";
		
		Piece king = posCache.getKing(color);
		
		int i = 0;
		int len = pieces.size();
		
		int kingSquare = king.getSquare();
		
		while(i < len){
			
			Piece piece = pieces.get(i);
			int square = piece.getSquare() + piece.getDirectionToKing();
			int countPieces = 0;			
			
			int pinning = 0;
			
			while(square != kingSquare && countPieces < 2){
				if(board.containsKey(square)){
					countPieces ++;
					int boardSquare = board.get(square);
					
					if((!WHITE && (boardSquare & 0x8) > 0) || (WHITE && (boardSquare & 0x08) == 0)){
						pinning = square;
					}else{
						countPieces = 3;
					}					
				}
				
				square += piece.getDirectionToKing();
			}
			
			if(countPieces == 1){
				ret.put(pinning, piece);
			}
			
			i++;
		}
		
		
		return ret;
		
	}
	
	public ArrayList<Piece> getSlidingPiecesAttackingKing(String color){
		
		ArrayList<Piece> ret = new ArrayList<Piece>();
		
		Piece king = getKing(color == "white" ? "black": "white");
		
		ArrayList<Piece> pieces = posCache.getPiecesOfAColor(color);
		
		int len = pieces.size();
		
		for(int i = 0; i < len; i++){
			Piece p = pieces.get(i);
			
			if(p.isSliding()){
				
				int numericDistance = king.getSquare() - p.getSquare();
				int boardDistance = (king.getSquare() - p.getSquare()) / getDistance(king.getSquare(), p.getSquare());
				
				switch(p.getType()){
				
					case 0x05:
					case 0x0D:
						if(numericDistance % 15 == 0 || numericDistance % 17 == 0){
							p.setDirectionToKing(boardDistance);
							ret.add(p);
						}
					break;
					
					case 0x06:
					case 0x0E:
						
						if(numericDistance % 16 == 0){
							p.setDirectionToKing(boardDistance);
							ret.add(p);
						}else if (isOnSameRank(p.getSquare(), king.getSquare())){
							p.setDirectionToKing(numericDistance > 0 ? 1 : -1);
							ret.add(p);							
						}
						break;
					case 0x07:
					case 0x0F:
						if (numericDistance % 15 == 0 || numericDistance % 17 == 0 || numericDistance % 16 == 0) {
							p.setDirectionToKing(boardDistance);
							ret.add(p);		
						}else if (isOnSameRank(p.getSquare(), king.getSquare())) {
							p.setDirectionToKing(numericDistance > 0 ? 1 : -1);
							ret.add(p);		
						}						
						break;
					default:
				
				}
			}
		}				
		
		return ret;
		
	}
	

	public Piece getKing(String color){
		return posCache.getKing(color);
	}
	
	public int getDistance(int sq1, int sq2){
		return BoardCache.distances.get(sq2 - sq1 + (sq2 | 7) - (sq1 | 7) + 240);
	}
	
	
	private String getFenPieces(){
		return (String) cache.get("pieces");
	}
	
	public int getFullMoves(){
		return (Integer) cache.get("fullMoves");
	}
	
	public int getHalfMoves(){
		return (Integer) cache.get("halfMoves");
	}
	
	public ArrayList<Piece> getPiecesOfAColor(String color){
		return color == "white" ? posCache.getWhitePieces() : posCache.getBlackPieces();
	}
	 
	public boolean isOnSameRank(int square1, int square2){
		return (square1 & 240) == (square2 & 240);
	}
	
	public boolean isOnSameFile(int square1, int square2){
		return (square1 & 15) == (square2 & 15);
	}
	
	private int getCastleCode(){
		return (Integer) cache.get("castleCode");
	}
	
	public boolean canCastleKingSide(String color){
		int code = color.equals("white") ? BoardCache.castle.get("K") : BoardCache.castle.get("k");
		return (getCastleCode() & code) == 0 ? false : true;
	}
	
	public boolean canCastleQueenSide(String color){
		int code = color.equals("white") ? BoardCache.castle.get("Q") : BoardCache.castle.get("q");
		return (getCastleCode() & code) == 0 ? false : true;
	}
	
	public String getEnPassantSquare(){
		String enPassant = (String) cache.get("enPassant");
		return enPassant != "-" ? enPassant : null;
	}
}
