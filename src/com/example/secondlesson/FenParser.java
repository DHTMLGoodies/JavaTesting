package com.example.secondlesson;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.util.SparseIntArray;

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
		
		
		
		cache.put("board", new SparseIntArray(128));
		cache.put("white", new SparseIntArray(128));
		cache.put("black", new SparseIntArray(128));
		cache.put("whiteSliding", new SparseIntArray(128));
		cache.put("blackSliding", new SparseIntArray(128));

	}
	
	private void parseFen(){
		Integer pos = 0;
		Integer emptyCounter = 0;
		
		int len = this.getFenPieces().length();
		for(int i=0; i < len; i++){
			
			char token = this.getFenPieces().charAt(i);
			
			if(BoardCache.fenPieces.containsKey(token)){
				Integer index = BoardCache.mapping.get(BoardCache.squares[pos]);
				Integer type = BoardCache.pieces.get(token);
				
				Map<String, Integer> piece = new HashMap<String, Integer>();
				piece.put("t", type);
				piece.put("s", index);
				
				cache["board"].
				
				cache.get("white").add(piece);
				
				
			}
			
			
		}
		

		
	}
	
	private String getFenPieces(){
		return (String) cache.get("pieces");
	}
	
	public int getFullMoves(){
		return (Integer) cache.get("fullMoves");
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
	
	private String getColorCode(){
		return (String) cache.get("color");
	}
}
