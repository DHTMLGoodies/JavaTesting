package com.example.secondlesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;

public class PositionCache {

	private Map<Integer, Integer> board;
	private ArrayList<Piece> whitePieces;
	private ArrayList<Piece> blackPieces; 
	
	private Piece whiteKing;
	private Piece blackKing;
	 
	public PositionCache(){ 
		clear();
	}
	
	@SuppressLint("UseSparseArrays")
	public void clear(){
		board = new HashMap<Integer, Integer>();
		whitePieces = new ArrayList<Piece>();
		blackPieces = new ArrayList<Piece>();
	}
	
	public void addPiece(Integer square, Integer piece){
		board.put(square, piece);
	}
	
	public void addColoredPiece(String color, Piece piece){
		if(piece.isWhite()){
			whitePieces.add(piece);
		}else{
			blackPieces.add(piece);
		}
	}
	
	public void storeKing(Piece piece){
		if(piece.isWhite()){
			whiteKing = piece;
		}else{
			blackKing = piece;
		}
	}
}
 