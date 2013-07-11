package com.example.secondlesson;

public class Piece {
	
	private Integer type;
	private Integer square;
	private String color;
	
	private Integer directionToKing;
	
	public static final Integer WHITE_PAWN = 0x01;
	public static final Integer WHITE_KNIGHT = 0x02;
	public static final Integer WHITE_BISHOP = 0x05;
	public static final Integer WHITE_ROOK = 0x06;
	public static final Integer WHITE_QUEEN = 0x07;
	public static final Integer WHITE_KING = 0x03;

	
	public static final Integer BLACK_PAWN = 0x09;
	public static final Integer BLACK_KNIGHT = 0x0A;
	public static final Integer BLACK_BISHOP = 0x0D;
	public static final Integer BLACK_ROOK = 0x0E;
	public static final Integer BLACK_QUEEN = 0x0F;
	public static final Integer BLACK_KING = 0x0B;
	
	
	
	public Piece(Integer t, Integer s){
		type = t;
		square = s;
		color = BoardCache.numberToColorMapping.get(type);
	}
	
	public String getSquareAsString(){
		return (String) BoardCache.numberToSquareMapping.get(square);
	}
	
	public String getColor(){
		return color;
	}
	
	public boolean isWhite(){
		return color == "white";
	}
	
	public int getSquare(){
		return square;
	}
	
	public int getType(){
		return type;
	}
	
	public boolean isSliding(){
		return (type & 0x4) > 0;
	}
	
	public void setDirectionToKing(int dir){
		directionToKing = dir;
	}
	
	public int getDirectionToKing(){
		return directionToKing;
	}
	
	
}
