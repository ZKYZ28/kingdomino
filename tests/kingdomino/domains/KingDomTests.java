package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KingDomTests {
	Tile[][] tabTile = new Tile[9][9];
	Kingdom kingdom = new Kingdom();

	@Test
	void initalisation() {
	
		Tile[][] tabTileTwo = kingdom.getTabTil();
		
		for (int i = 0; i < tabTile.length; i++) {
			for (int j = 0; j < tabTile.length; j++) {
				if(i != 4 && j != 4)
				assertEquals(Terrain.EMPTY, tabTileTwo[i][j].getTerritory());
			}
		}
		
		assertNotEquals(tabTileTwo, tabTile);
	}
	
	
	@Test
	void putDominoWithCastle() {
		//GIVEN
		Kingdom kingdom = new Kingdom();
		int[] coordFirst = {3, 4};	
		int[] coordSecond = {3, 5};
		
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 0), new Tile(Terrain.FOREST, 0));
		
		//THEN
		assertTrue(kingdom.putDomino(dom, coordFirst, coordSecond));
	}
	
	@Test
	void putDominoWithGoodPosition() {
		//GIVEN
		Kingdom kingdom = new Kingdom();
		int[] coordFirst = {3, 4};	
		int[] coordSecond = {3, 5};
		
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 0), new Tile(Terrain.FOREST, 0));
		Domino domTwo = new Domino(2, new Tile(Terrain.FOREST, 0), new Tile(Terrain.FOREST, 0));
		
		//WHEN
		kingdom.putDomino(dom,  coordFirst, coordSecond);
		
		//THEN
		assertTrue(kingdom.putDomino(domTwo, new int[]{3,6}, new int[]{3,7}));
	}
	
	@Test
	void putDominoBadPosition() {
		//GIVEN
		Kingdom kingdom = new Kingdom();
		int[] coordFirst = {0, 0};	
		int[] coordSecond = {0, 1};
		
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 0), new Tile(Terrain.FOREST, 0));
		
		//THEN
		assertFalse(kingdom.putDomino(dom, coordFirst, coordSecond));
	}
	
	@Test
	void putDominoBadCoordRowFirst() {
		//GIVEN
		Kingdom kingdom = new Kingdom();
		int[] coordFirst = {-1, 0};	
		int[] coordSecond = {0, 1};
		
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 0), new Tile(Terrain.FOREST, 0));
		
		//THEN
		assertFalse(kingdom.putDomino(dom, coordFirst, coordSecond));
	}
	
	@Test
	void putDominoBadCoordRowSecond() {
		//GIVEN
		Kingdom kingdom = new Kingdom();
		int[] coordFirst = {0, 0};	
		int[] coordSecond = {-1, 1};
		
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 0), new Tile(Terrain.FOREST, 0));
		
		//THEN
		assertFalse(kingdom.putDomino(dom, coordFirst, coordSecond));
	}
	
	@Test
	void putDominoBadCoordColFirst() {
		//GIVEN
		Kingdom kingdom = new Kingdom();
		int[] coordFirst = {0, -1};	
		int[] coordSecond = {0, 1};
		
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 0), new Tile(Terrain.FOREST, 0));
		
		//THEN
		assertFalse(kingdom.putDomino(dom, coordFirst, coordSecond));
	}
	
	@Test
	void putDominoBadCoordColSecond() {
		//GIVEN
		Kingdom kingdom = new Kingdom();
		int[] coordFirst = {0, 0};	
		int[] coordSecond = {1, -1};
		
		Domino dom = new Domino(1, new Tile(Terrain.FOREST, 0), new Tile(Terrain.FOREST, 0));
		
		//THEN
		assertFalse(kingdom.putDomino(dom, coordFirst, coordSecond));
	}

}
