package com.example.secondlesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;

public class FenParser {

	private String fen;
	
	private Map<String, Object> cache;


	private PositionCache posCache = new PositionCache();
	
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

				posCache.addColoredPiece(BoardCache.colorMapping.get(token), piece);			
				
				if(BoardCache.typeMapping.get(type) == "king"){
					posCache.storeKing(piece);
				}
				
				pos ++;
			}else if(i < len && BoardCache.numbers.containsKey(token)){
				char token2 = pieces.charAt(i + 1);
				if(BoardCache.numbers.containsKey(token2)){
					pos +=  Integer.parseInt(pieces.substring(i, 2));					
				}else{
					pos += Integer.parseInt(token);	
				}				
			}
		}
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
	
	private String getColorCode(){
		return (String) cache.get("color");
	}
}
