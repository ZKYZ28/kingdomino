package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTests {

	@Test
	void normalCaseGetTerritory() {
		Tile tile = new Tile(Terrain.CASTLE, 2);
		
		assertEquals(Terrain.CASTLE, tile.getTerritory());
	}
	
	@Test
	void nullTerritory() {
		Tile tile = new Tile(null, 2);
		
		assertEquals(Terrain.EMPTY, tile.getTerritory());
	}
	
	@Test
	void normalCaseGetCrownsCount() {
		Tile tile = new Tile(Terrain.CASTLE, 2);
		
		assertEquals(2, tile.getCrownsCount());
	}
	
	@Test
	void negativeCrownsCount() {
		Tile tile = new Tile(Terrain.CASTLE, -2);
		
		assertEquals(0, tile.getCrownsCount());
	}

}
