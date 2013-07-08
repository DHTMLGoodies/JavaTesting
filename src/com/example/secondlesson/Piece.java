package com.example.secondlesson;

public class Piece {
	
	private Integer type;
	private Integer square;
	private String color;
	
	public Piece(Integer t, Integer s){
		type = t;
		square = s;
		color = BoardCache.numberToColorMapping.get(type);
	}
	
	public String getColor(){
		return color;
	}
	
	public boolean isWhite(){
		return color == "white";
	}
}
