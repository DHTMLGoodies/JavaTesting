package com.example.secondlesson;

import java.util.ArrayList;

import android.util.SparseIntArray;

public class PositionCache {

	private SparseIntArray board;
	private ArrayList<Integer> whitePieces;
	private ArrayList<Integer> blackPieces; 
	 
	public PositionCache(){ 
		clear();
	}
	
	public void clear(){
		board = new SparseIntArray();
		whitePieces = new ArrayList<Integer>();
		blackPieces = new ArrayList<Integer>();
	}
	
	public void addPiece(Integer square, Integer piece){
		board.append(square, piece);
	}
	
	public void addColoredPiece(Integer color, Integer piece){
		
	}
}
 