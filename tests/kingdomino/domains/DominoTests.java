package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DominoTests {
	
	@Test
	void getNullTilePos0() {
		Tile part1 = null;
		Tile part2 = new Tile(Terrain.CASTLE, 2);
		
		Domino domino = new Domino(1, part1, part2);
		
		assertEquals(Terrain.EMPTY, domino.getTerritory(0));
		assertEquals(0, domino.getCrownsCount(0));
	}
	
	@Test
	void getNullTilePos1() {
		Tile part1 = new Tile(Terrain.CASTLE, 2);
		Tile part2 = null;
		
		Domino domino = new Domino(1, part1, part2);
		
		assertEquals(Terrain.EMPTY, domino.getTerritory(1));
		assertEquals(0, domino.getCrownsCount(1));
	}

	@Test
	void normalCaseGetID() {
		Tile part1 = new Tile(Terrain.CASTLE, 2);
		Tile part2 = new Tile(Terrain.CASTLE, 2);
		
		Domino domino = new Domino(1, part1, part2);
		
		assertEquals(1, domino.getId());
	}
	
	@Test
	void normalCaseGetCrownsCountZero() {
		Tile part1 = new Tile(Terrain.CASTLE, 2);
		Tile part2 = new Tile(Terrain.CASTLE, 3);
		
		Domino domino = new Domino(1, part1, part2);
		
		assertEquals(2, domino.getCrownsCount(0));
	}
	
	@Test
	void normalCaseGetCrownsCountOne() {
		Tile part1 = new Tile(Terrain.CASTLE, 2);
		Tile part2 = new Tile(Terrain.CASTLE, 3);
		
		Domino domino = new Domino(1, part1, part2);
		
		assertEquals(3, domino.getCrownsCount(1));
	}
	
	
	@Test
	void normalCaseGetTerritoryZero() {
		Tile part1 = new Tile(Terrain.CASTLE, 2);
		Tile part2 = new Tile(Terrain.FOREST, 3);
		
		Domino domino = new Domino(1, part1, part2);
		
		assertEquals(Terrain.CASTLE, domino.getTerritory(0));
	}
	
	@Test
	void normalCaseGetTerritoryOne() {
		Tile part1 = new Tile(Terrain.CASTLE, 2);
		Tile part2 = new Tile(Terrain.FOREST, 3);
		
		Domino domino = new Domino(1, part1, part2);
		
		assertEquals(Terrain.FOREST, domino.getTerritory(1));
	}
	
	
	@Test
	void compareToWithFirstBig() {
		Tile part1 = new Tile(Terrain.CASTLE, 2);
		Tile part2 = new Tile(Terrain.CASTLE, 2);
		
		Domino domino1 = new Domino(5, part1, part2);
		Domino domino2 = new Domino(1, part1, part2);
		
		assertEquals(1, domino1.compareTo(domino2));
	}
	
	
	@Test
	void compareToWithSecondBig() {
		Tile part1 = new Tile(Terrain.CASTLE, 2);
		Tile part2 = new Tile(Terrain.CASTLE, 2);
		
		Domino domino1 = new Domino(1, part1, part2);
		Domino domino2 = new Domino(5, part1, part2);
		
		assertEquals(-1, domino1.compareTo(domino2));
	}
	
	@Test
	void compareToEquals() {
		Tile part1 = new Tile(Terrain.CASTLE, 2);
		Tile part2 = new Tile(Terrain.CASTLE, 2);
		
		Domino domino1 = new Domino(1, part1, part2);
		Domino domino2 = new Domino(1, part1, part2);
		
		assertEquals(0, domino1.compareTo(domino2));
	}
	
	@Test
	void compareToNull() {
		Tile part1 = new Tile(Terrain.CASTLE, 2);
		Tile part2 = new Tile(Terrain.CASTLE, 2);
		
		Domino domino1 = new Domino(1, part1, part2);
		
		assertEquals(-1, domino1.compareTo(null));
	}

}
