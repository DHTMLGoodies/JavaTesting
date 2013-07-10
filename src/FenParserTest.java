import junit.framework.Assert;
import junit.framework.TestCase;

import com.example.secondlesson.BoardCache;
import com.example.secondlesson.FenParser;



public class FenParserTest extends TestCase {
	
	
	/**
	 * @test
	 */
	public void testSetFen(){
		// given
		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		
		// when
		FenParser c = new FenParser(fen);
		
		// then
		Assert.assertEquals(fen,  c.getFen());		
		
	}
	
	/**
	 * @test
	 */	
	public void testShouldSetDefaultFen(){
		// given
		FenParser c = new FenParser();
		
		// then
		Assert.assertEquals(defaultFen(), c.getFen());		
	}
	
	/**
	 * @test
	 */
	public void testShouldFindFullMoves(){
		// given
		FenParser c = new FenParser("5k2/8/8/3pP3/8/8/8/7K w - d6 0 25");
		
		// then
		Assert.assertEquals(25, c.getFullMoves());
		
	}
	
	public void testShouldFindHalfMoves(){
		// given
		FenParser p = new FenParser("5k2/8/8/3pP3/8/8/8/7K w - d6 12 25");
		
		// then
		Assert.assertEquals(12, p.getHalfMoves());
	}
	
	public void testShouldFindCastle(){ 
		// given
		FenParser c = new FenParser();
		
		Assert.assertTrue(c.canCastleKingSide("white"));
		Assert.assertTrue(c.canCastleKingSide("black"));
		Assert.assertTrue(c.canCastleQueenSide("white"));
		Assert.assertTrue(c.canCastleQueenSide("black"));
		
		// given
		FenParser d = new FenParser("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w Kq - 0 1");
		
		Assert.assertTrue(d.canCastleKingSide("white"));
		Assert.assertFalse(d.canCastleKingSide("black"));
		Assert.assertFalse(d.canCastleQueenSide("white"));
		Assert.assertTrue(d.canCastleQueenSide("black"));
		
	}
	
	public void testShouldFindSquaresOnTheSameRank(){		
		// given
		FenParser c = new FenParser();
		
		// then
		Assert.assertTrue(c.isOnSameRank(getNumericSquare("a1"), getNumericSquare("h1")));		
		Assert.assertFalse(c.isOnSameRank(getNumericSquare("a2"), getNumericSquare("c4")));		
		
	}

	public void testShouldFindSquaresOnSameFile(){
		// given
		FenParser c = new FenParser();
		
		// then
		Assert.assertTrue(c.isOnSameFile(getNumericSquare("a1"), getNumericSquare("a3")));
		Assert.assertFalse(c.isOnSameFile(getNumericSquare("a1"), getNumericSquare("c3")));
	}
	
	public void testShouldFindAllPieces(){
		// given
		FenParser p = new FenParser();
		
		// then
		Assert.assertEquals(16, p.getPiecesOfAColor("white").size() );
		Assert.assertEquals(16, p.getPiecesOfAColor("black").size() );
		
		
	}

	public void testShouldGetNumberToColorMapping(){
		
		Integer[] whiteNumbers = {
			0x01,0x02,0x03,0x05,0x06,0x07	
		};		

		for(int i=0;i<whiteNumbers.length; i++){
			Assert.assertEquals("white", BoardCache.numberToColorMapping.get(whiteNumbers[i]));
		}
		
		Integer[] blackNumbers = {
			0x09,0x0A,0x0B,0x0D,0x0E,0x0F	
		};		

		for(int i=0;i<blackNumbers.length; i++){
			Assert.assertEquals("black", BoardCache.numberToColorMapping.get(blackNumbers[i]));
		}
		
		
	}
	
	
	public void testShouldGetColorMapping(){
		
		Assert.assertEquals("white", BoardCache.colorMapping.get("K"));
		Assert.assertEquals("white", BoardCache.colorMapping.get("R"));
		Assert.assertEquals("white", BoardCache.colorMapping.get("B"));
		Assert.assertEquals("white", BoardCache.colorMapping.get("N"));
		Assert.assertEquals("white", BoardCache.colorMapping.get("Q"));
		Assert.assertEquals("white", BoardCache.colorMapping.get("P"));

		Assert.assertEquals("black", BoardCache.colorMapping.get("k"));
		Assert.assertEquals("black", BoardCache.colorMapping.get("q"));
		Assert.assertEquals("black", BoardCache.colorMapping.get("r"));
		Assert.assertEquals("black", BoardCache.colorMapping.get("b"));
		Assert.assertEquals("black", BoardCache.colorMapping.get("n"));
		Assert.assertEquals("black", BoardCache.colorMapping.get("p"));
	}
	
	
	public void testFenPiecesShouldContainToken(){
		Assert.assertTrue(BoardCache.fenPieces.containsKey("K"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("Q"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("R"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("B"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("N"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("P"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("k"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("q"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("r"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("b"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("n"));
		Assert.assertTrue(BoardCache.fenPieces.containsKey("p"));
	}
	
	private int getNumericSquare(String square){
		return (Integer) BoardCache.mapping.get(square);
	}
	
	public void testShouldFindEnPassantSquares(){
		// given
		FenParser c = new FenParser("5k2/8/8/3pP3/8/8/8/7K w - d6 0 1");
		
		Assert.assertEquals("d6", c.getEnPassantSquare());
		
	}
	
	private String defaultFen(){
		return "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	}
}
