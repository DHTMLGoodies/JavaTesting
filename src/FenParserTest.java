import junit.framework.Assert;
import junit.framework.TestCase;

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
	
	private String defaultFen(){
		return "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	}
}
