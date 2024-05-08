package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import kingdomino.io.CsvDataReader;


class DominoPileTests {
	 List<Domino> dominos = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
				.map(dto -> new Domino(dto.getId(), 
						new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
						new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
				.collect(Collectors.toList());
		 
	
	 
	@Test
	void normalCaseResizeDominoPileTwoPlayers() { 
		 DominoPile dominosPile = new DominoPile(2, dominos);
		 
		 assertEquals(24, dominosPile.getCollDomino().size());
	}

	
	@Test
	void normalCaseResizeDominoPileThreePlayer() { 
		 DominoPile dominosPile = new DominoPile(3, dominos);
		 
		 assertEquals(36, dominosPile.getCollDomino().size());
	}
	
	@Test
	void normalCaseResizeDominoPileFourPlayer() { 
		 DominoPile dominosPile = new DominoPile(4, dominos);
		 
		 assertEquals(48, dominosPile.getCollDomino().size());
	}
	
	@Test
	void limitCaseResizeDominoPile() {
		 DominoPile dominosPile = new DominoPile(0, dominos);
		 
		 assertEquals(0, dominosPile.getCollDomino().size());
	}
	
	@Test
	void normalCaseExtractDrawLineTwoPlayer() {
		 DominoPile dominosPile = new DominoPile(2, dominos);
		 assertEquals(4, dominosPile.extractDrawLine(2).size());
	}
	
	@Test
	void normalCaseExtractDrawLineThreePlayer() {
		 DominoPile dominosPile = new DominoPile(3, dominos);
		 assertEquals(3, dominosPile.extractDrawLine(3).size());
	}
	
	@Test
	void normalCaseExtractDrawLineFourPlayer() {
		 DominoPile dominosPile = new DominoPile(4, dominos);
		 assertEquals(4, dominosPile.extractDrawLine(4).size());
	}
	
	
	@Test
	void sortListForDrawLine() {
		 DominoPile dominosPile = new DominoPile(4, dominos);
		 List<Domino> dominos = dominosPile.extractDrawLine(4);

		 assertTrue(dominos.get(0).getId() < dominos.get(1).getId());
		 assertTrue(dominos.get(1).getId() < dominos.get(2).getId());
		 assertTrue(dominos.get(2).getId() < dominos.get(3).getId());
	}
	
	@Test
	void remainingDominos() {
		DominoPile dominosPile = new DominoPile(4, dominos);
		assertTrue(dominosPile.remainingDominos(4));
	}
	
	@Test
	void notRemainingDominos() {
		//GIVEN
		DominoPile dominosPile = new DominoPile(2, dominos);
		
		//WHEN
		for (int i = 0; i < 6; i++) {
			dominosPile.extractDrawLine(2);
		}
		
		
		//THEN
		assertFalse(dominosPile.remainingDominos(2));
	}
}
