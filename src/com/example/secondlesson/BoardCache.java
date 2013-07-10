package com.example.secondlesson;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class BoardCache {
	public static final String[] squares = { "a1", "b1", "c1", "d1", "e1",
			"f1", "g1", "h1", "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
			"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3", "a4", "b4", "c4",
			"d4", "e4", "f4", "g4", "h4", "a5", "b5", "c5", "d5", "e5", "f5",
			"g5", "h5", "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6", "a7",
			"b7", "c7", "d7", "e7", "f7", "g7", "h7", "a8", "b8", "c8", "d8",
			"e8", "f8", "g8", "h8" };

	public static final String[] fenSquares = { "a8", "b8", "c8", "d8", "e8",
			"f8", "g8", "h8", "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
			"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6", "a5", "b5", "c5",
			"d5", "e5", "f5", "g5", "h5", "a4", "b4", "c4", "d4", "e4", "f4",
			"g4", "h4", "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3", "a2",
			"b2", "c2", "d2", "e2", "f2", "g2", "h2", "a1", "b1", "c1", "d1",
			"e1", "f1", "g1", "h1" };

	public static final Map<String, String> fenPieces = ImmutableMap
			.<String, String> builder().put("p", "blackPawns")
			.put("b", "blackBishops").put("n", "blackKnights")
			.put("r", "blackRooks").put("q", "blackQueens")
			.put("k", "blackKing").put("P", "whitePawn")
			.put("B", "whiteBishop").put("N", "whiteKnight")
			.put("R", "whiteRook").put("Q", "whiteQueen").put("K", "whiteKing")
			.build();

	public static final Integer[] pawnPatternsWhite = { 16, 32, 15, 17 };
	public static final Integer[] pawnPatternsBlack = { 16, 32, 15, 17 };
	public static final Integer[] bishopPattern = { -15, -17, 15, 17 };
	public static final Integer[] rookPattern = { -1, -1, -16, 16 };
	public static final Integer[] kingPattern = { -15, -17, 15, 17, -1, 1, -16,
			16 };
	public static final Integer[] queenPattern = { -17, -16, -15, -1, 1, 15,
			16, 17 };

	public static final Map<Integer, Integer[]> movePatterns = (Map<Integer, Integer[]>) ImmutableMap
			.<Integer, Integer[]> builder()
			.put(0x01, BoardCache.pawnPatternsWhite)
			.put(0x09, BoardCache.pawnPatternsBlack)
			.put(0x05, BoardCache.bishopPattern)
			.put(0x0D, BoardCache.bishopPattern)
			.put(0x06, BoardCache.rookPattern)
			.put(0x0E, BoardCache.rookPattern)
			.put(0x02, BoardCache.kingPattern)
			.put(0x0A, BoardCache.kingPattern)
			.put(0x03, BoardCache.queenPattern)
			.put(0x0B, BoardCache.queenPattern).build();

	public static final Integer[] numericMapping = { 0, 1, 2, 3, 4, 5, 6, 7,
			16, 17, 18, 19, 20, 21, 22, 23, 32, 33, 34, 35, 36, 37, 38, 39, 48,
			49, 50, 51, 52, 53, 54, 55, 64, 65, 66, 67, 68, 69, 70, 71, 80, 81,
			82, 83, 84, 85, 86, 87, 96, 97, 98, 99, 100, 101, 102, 103, 112,
			113, 114, 115, 116, 117, 118, 119 };

	public static final Map<String, Integer> numbers = ImmutableMap
			.<String, Integer> builder().put("0", 1).put("1", 1).put("2", 1)
			.put("3", 1).put("4", 1).put("5", 1).put("6", 1).put("7", 1)
			.put("8", 1).build();

	public static final Map<Integer, String> typeMapping = ImmutableMap
			.<Integer, String> builder().put(0x01, "pawn").put(0x02, "knight")
			.put(0x03, "king").put(0x05, "bishop").put(0x06, "rook")
			.put(0x07, "queen").put(0x09, "pawn").put(0x0A, "knight")
			.put(0x0B, "king").put(0x0D, "bishop").put(0x0E, "rook")
			.put(0x0F, "queen").build();

	public static final Map<Integer, String> numberToColorMapping = ImmutableMap
			.<Integer, String> builder().put(0x01, "white").put(0x02, "white")
			.put(0x03, "white").put(0x05, "white").put(0x06, "white")
			.put(0x07, "white").put(0x09, "black").put(0x0A, "black")
			.put(0x0B, "black").put(0x0D, "black").put(0x0E, "black")
			.put(0x0F, "black").build();

	public static final Map<String, String> colorMapping = ImmutableMap
			.<String, String> builder().put("p", "black").put("n", "black")
			.put("b", "black").put("r", "black").put("q", "black")
			.put("k", "black").put("P", "white").put("N", "white")
			.put("B", "white").put("R", "white").put("Q", "white")
			.put("K", "white").build();

	public static final Map<String, Integer> mapping = ImmutableMap
			.<String, Integer> builder().put("a1", 0).put("b1", 1).put("c1", 2)
			.put("d1", 3).put("e1", 4).put("f1", 5).put("g1", 6).put("h1", 7)
			.put("a2", 16).put("b2", 17).put("c2", 18).put("d2", 19)
			.put("e2", 20).put("f2", 21).put("g2", 22).put("h2", 23)
			.put("a3", 32).put("b3", 33).put("c3", 34).put("d3", 35)
			.put("e3", 36).put("f3", 37).put("g3", 38).put("h3", 39)
			.put("a4", 48).put("b4", 49).put("c4", 50).put("d4", 51)
			.put("e4", 52).put("f4", 53).put("g4", 54).put("h4", 55)
			.put("a5", 64).put("b5", 65).put("c5", 66).put("d5", 67)
			.put("e5", 68).put("f5", 69).put("g5", 70).put("h5", 71)
			.put("a6", 80).put("b6", 81).put("c6", 82).put("d6", 83)
			.put("e6", 84).put("f6", 85).put("g6", 86).put("h6", 87)
			.put("a7", 96).put("b7", 97).put("c7", 98).put("d7", 99)
			.put("e7", 100).put("f7", 101).put("g7", 102).put("h7", 103)
			.put("a8", 112).put("b8", 113).put("c8", 114).put("d8", 115)
			.put("e8", 116).put("f8", 117).put("g8", 118).put("h8", 119)
			.build();

	public static final Map<String, Integer> pieces = ImmutableMap
			.<String, Integer> builder().put("P", 0x01).put("N", 0x02)
			.put("K", 0x03).put("B", 0x05).put("R", 0x06).put("Q", 0x07)
			.put("p", 0x09).put("n", 0x0A).put("k", 0x0B).put("b", 0x0D)
			.put("r", 0x0E).put("q", 0x0F).build();

	public static final Map<Integer, String> pieceMapping = ImmutableMap
			.<Integer, String> builder().put(0x01, "P").put(0x02, "N")
			.put(0x03, "K").put(0x05, "B").put(0x06, "R").put(0x07, "Q")
			.put(0x09, "p").put(0x0A, "n").put(0x0B, "k").put(0x0D, "b")
			.put(0x0E, "r").put(0x0F, "q").build();

	public static final Map<Integer, String> numberToSquareMapping = ImmutableMap
			.<Integer, String> builder().put(0, "a1").put(1, "b1").put(2, "c1")
			.put(3, "d1").put(4, "e1").put(5, "f1").put(6, "g1").put(7, "h1")
			.put(16, "a2").put(17, "b2").put(18, "c2").put(19, "d2")
			.put(20, "e2").put(21, "f2").put(22, "g2").put(23, "h2")
			.put(32, "a3").put(33, "b3").put(34, "c3").put(35, "d3")
			.put(36, "e3").put(37, "f3").put(38, "g3").put(39, "h3")
			.put(48, "a4").put(49, "b4").put(50, "c4").put(51, "d4")
			.put(52, "e4").put(53, "f4").put(54, "g4").put(55, "h4")
			.put(64, "a5").put(65, "b5").put(66, "c5").put(67, "d5")
			.put(68, "e5").put(69, "f5").put(70, "g5").put(71, "h5")
			.put(80, "a6").put(81, "b6").put(82, "c6").put(83, "d6")
			.put(84, "e6").put(85, "f6").put(86, "g6").put(87, "h6")
			.put(96, "a7").put(97, "b7").put(98, "c7").put(99, "d7")
			.put(100, "e7").put(101, "f7").put(102, "g7").put(103, "h7")
			.put(112, "a8").put(113, "b8").put(114, "c8").put(115, "d8")
			.put(116, "e8").put(117, "f8").put(118, "g8").put(119, "h8")
			.build();

	public final Integer[] fenSquaresNumeric = { 112, 113, 114, 115, 116, 117,
			118, 119, 96, 97, 98, 99, 100, 101, 102, 103, 80, 81, 82, 83, 84,
			85, 86, 87, 64, 65, 66, 67, 68, 69, 70, 71, 48, 49, 50, 51, 52, 53,
			54, 55, 32, 33, 34, 35, 36, 37, 38, 39, 16, 17, 18, 19, 20, 21, 22,
			23, 0, 1, 2, 3, 4, 5, 6, 7

	};

	public Map<String, String> colorAbbreviations = ImmutableMap
			.<String, String> builder().put("w", "white").put("b", "black")
			.build();

	public Map<String, String> oppositeColors = ImmutableMap
			.<String, String> builder().put("white", "black")
			.put("black", "white").build();

	public final static Map<String, Integer> castle = ImmutableMap
			.<String, Integer> builder().put("-", 0).put("K", 8).put("Q", 4)
			.put("k", 2).put("q", 1).build();

	public final static Map<String, Integer> castleToNumberMapping = ImmutableMap
			.<String, Integer> builder().put("-", 0).put("q", 1).put("k", 2)
			.put("kq", 3).put("Q", 4).put("Qq", 5).put("Qk", 6).put("Qkq", 7)
			.put("K", 8).put("Kq", 9).put("Kk", 10).put("Kkq", 11)
			.put("KQ", 12).put("KQq", 13).put("KQk", 14).put("KQkq", 15)
			.build();

}
