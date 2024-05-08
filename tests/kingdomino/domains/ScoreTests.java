package kingdomino.domains;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTests {

	@Test
	void determinateFirstTile() {
		Kingdom kingdom = new Kingdom();
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 2), new Tile(Terrain.FOREST, 0));
		kingdom.putDomino(dom, new int[] {3, 4}, new int[]{2, 4});
		
		Score score = new Score(kingdom);
		
		assertEquals(2, score.getVisitedTile().size());
		assertEquals(0, score.getExtendedTile().size());
	}
	
	@Test
	void calculateScore() {
		Kingdom kingdom = new Kingdom();
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 2), new Tile(Terrain.FOREST, 0));
		kingdom.putDomino(dom, new int[] {3, 4}, new int[]{2, 4});
		kingdom.putDomino(dom, new int[] {2, 5}, new int[]{2, 6});
		kingdom.putDomino(dom, new int[] {3, 6}, new int[]{4, 6});
		
		Score score = new Score(kingdom);
		
		assertEquals(6, score.getVisitedTile().size());
		assertEquals(0, score.getExtendedTile().size());
		assertEquals(36, score.getScore());
		assertEquals(0, score.getCurrentTerritory().size());
	}
	
	@Test
	void calculateScoreLimit() {
		Kingdom kingdom = new Kingdom();
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 2), new Tile(Terrain.FOREST, 0));
		kingdom.putDomino(dom, new int[] {3, 4}, new int[]{2, 4});
		kingdom.putDomino(dom, new int[] {2, 5}, new int[]{2, 6});
		
		Score score = new Score(kingdom);
		
		assertEquals(4, score.getVisitedTile().size());
		assertEquals(0, score.getExtendedTile().size());
		assertEquals(16, score.getScore());
		assertEquals(0, score.getCurrentTerritory().size());
	}
	
	@Test
	void startCalculatingPointsExtendTerritory() {
		Kingdom kingdom = new Kingdom();
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 2), new Tile(Terrain.FOREST, 0));
		Domino domTwo = new Domino(2, new Tile(Terrain.FOREST, 1), new Tile(Terrain.LAKE, 1));
		kingdom.putDomino(dom, new int[] {3, 4}, new int[]{2, 4});
		kingdom.putDomino(domTwo, new int[] {2, 5}, new int[]{2, 6});
		
		Score score = new Score(kingdom);
		
		assertEquals(4, score.getVisitedTile().size());
		assertEquals(0, score.getExtendedTile().size());
		assertEquals(0, score.getUncoveredTile().size());
		assertEquals(10, score.getScore());
	}
	
	@Test
	void startCalculatingPointsExtendTerritoryWithMutlipleArroundCastle() {
		Kingdom kingdom = new Kingdom();
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 2), new Tile(Terrain.FOREST, 0));
		Domino domTwo = new Domino(2, new Tile(Terrain.FOREST, 1), new Tile(Terrain.LAKE, 1));
		Domino domThree = new Domino(3, new Tile(Terrain.FIELD, 1), new Tile(Terrain.FIELD, 1));
		kingdom.putDomino(dom, new int[] {3, 4}, new int[]{2, 4});
		kingdom.putDomino(domTwo, new int[] {2, 5}, new int[]{2, 6});
		kingdom.putDomino(domThree, new int[] {5, 4}, new int[]{6, 4});
		
		Score score = new Score(kingdom);
		
		assertEquals(6, score.getVisitedTile().size());
		assertEquals(0, score.getExtendedTile().size());
		assertEquals(0, score.getUncoveredTile().size());
		assertEquals(14, score.getScore());
	}
	
	@Test
	void startCalculatingPointsLargeGame() {
		//GIVEN
		Kingdom kingdom = new Kingdom();
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 2), new Tile(Terrain.FOREST, 0));
		Domino domTwo = new Domino(2, new Tile(Terrain.FOREST, 1), new Tile(Terrain.LAKE, 1));
		Domino domThree = new Domino(3, new Tile(Terrain.LAKE, 2), new Tile(Terrain.FIELD, 0));
		Domino domFor = new Domino(3, new Tile(Terrain.FIELD, 1), new Tile(Terrain.FIELD, 1));
		
		kingdom.putDomino(dom, new int[] {3, 4}, new int[]{2, 4});
		kingdom.putDomino(domTwo, new int[] {2, 5}/*9*/, new int[]{2, 6});
		kingdom.putDomino(domThree, new int[] {2, 7}, new int[]{2, 8});
		kingdom.putDomino(domFor, new int[] {5, 4}, new int[]{6, 4}); 
		kingdom.putDomino(domFor, new int[] {7, 4}, new int[]{8, 4});
		
		//WHEN 
		Score score = new Score(kingdom);
		
		
		//THEN
		assertEquals(10, score.getVisitedTile().size());
		assertEquals(0, score.getExtendedTile().size());
		assertEquals(0, score.getUncoveredTile().size());
		assertEquals(31, score.getScore());
	}
	
	
	@Test
	void startCalculatingPointsLargeGameTwo() {
		//GIVEN
		Kingdom kingdom = new Kingdom();
		Domino dom = new Domino(1, new Tile(Terrain.LAKE, 0), new Tile(Terrain.LAKE, 0));
		Domino domTwo = new Domino(2, new Tile(Terrain.LAKE, 1), new Tile(Terrain.FOREST, 0));
		Domino domThree = new Domino(3, new Tile(Terrain.LAKE, 1), new Tile(Terrain.FOREST, 0));
		Domino domFor = new Domino(4, new Tile(Terrain.GRASSLANDS, 0), new Tile(Terrain.SWAMP, 0));
		Domino domFive = new Domino(5, new Tile(Terrain.MINE, 0), new Tile(Terrain.FIELD, 2));
		Domino domSix = new Domino(6, new Tile(Terrain.FIELD, 2), new Tile(Terrain.GRASSLANDS, 0));
		
		kingdom.putDomino(dom, new int[] {3, 3}, new int[]{3, 4});
		kingdom.putDomino(domTwo, new int[] {3, 5}, new int[]{3, 6});
		kingdom.putDomino(domThree, new int[] {2, 5}, new int[]{2, 6});
		kingdom.putDomino(domFor, new int[] {4, 2}, new int[]{4, 3}); 
		kingdom.putDomino(domFive, new int[] {5, 3}, new int[]{5, 4});
		kingdom.putDomino(domSix, new int[] {5, 5}, new int[]{5, 6});
		
		//WHEN 
		Score score = new Score(kingdom);
		
		
		//THEN
		assertEquals(12, score.getVisitedTile().size());
		assertEquals(0, score.getExtendedTile().size());
		assertEquals(0, score.getUncoveredTile().size());
		assertEquals(16, score.getScore());
	}
	
	@Test
	void exampleOfTheCourse() {
		//GIVEN
		Kingdom kingdom = new Kingdom();
		Domino dom = new Domino(1, new Tile(Terrain.SWAMP, 0), new Tile(Terrain.GRASSLANDS, 0));
		Domino domTwo = new Domino(2, new Tile(Terrain.GRASSLANDS, 0), new Tile(Terrain.MINE, 2));
		Domino domThree = new Domino(3, new Tile(Terrain.SWAMP, 1), new Tile(Terrain.FIELD, 0));
		Domino domFor = new Domino(4, new Tile(Terrain.SWAMP, 0), new Tile(Terrain.LAKE, 1));
		Domino domFive = new Domino(5, new Tile(Terrain.LAKE, 0), new Tile(Terrain.LAKE, 0));
		Domino domSix = new Domino(6, new Tile(Terrain.FOREST, 0), new Tile(Terrain.LAKE, 1));
		Domino domSeven = new Domino(7, new Tile(Terrain.FIELD, 2), new Tile(Terrain.LAKE, 0));
		Domino domEight = new Domino(8, new Tile(Terrain.FIELD, 0), new Tile(Terrain.FIELD, 0));
		
		kingdom.putDomino(dom, new int[] {5, 4}, new int[]{6, 4});
		kingdom.putDomino(domTwo, new int[] {7, 4}, new int[]{8, 4});
		kingdom.putDomino(domThree, new int[] {4, 5}, new int[]{4, 6});
		kingdom.putDomino(domFor, new int[] {5, 5}, new int[]{6, 5}); 
		kingdom.putDomino(domFive, new int[] {7, 5}, new int[]{7, 6});
		kingdom.putDomino(domSix, new int[] {6, 7}, new int[]{7, 7});
		kingdom.putDomino(domSeven, new int[] {5, 6}, new int[]{6, 6});
		kingdom.putDomino(domEight, new int[] {5, 7}, new int[]{5, 8});
		
		//WHEN 
		Score score = new Score(kingdom);
		
		
		//THEN
		assertEquals(16, score.getVisitedTile().size());
		assertEquals(0, score.getExtendedTile().size());
		assertEquals(0, score.getUncoveredTile().size());
		assertEquals(23, score.getScore());
	}
	
	@Test
	void equalsFasle() {
		Kingdom kingdom = new Kingdom();
		Kingdom kingdomTwo = new Kingdom();
		
		Score score = new Score(kingdom);
		Score scoreTwo = new Score(kingdomTwo);
		
		assertFalse(score.equals(scoreTwo));
	}
	
	@Test
	void equalsTrue() {
		Kingdom kingdom = new Kingdom();
		
		Score score = new Score(kingdom);
		
		assertTrue(score.equals(score));
	}
	
	@Test
	void hashCodeNotSame() {
		Kingdom kingdom = new Kingdom();
		Kingdom kingdomTwo = new Kingdom();
		
		Score score = new Score(kingdom);
		Score scoreTwo = new Score(kingdomTwo);
		
		assertNotEquals(score.hashCode(), scoreTwo.hashCode());
	}
	
	
}
