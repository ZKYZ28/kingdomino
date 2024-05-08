package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import kingdomino.io.CsvDataReader;

class DrawLineTests {
	private List<Domino> dominos = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
			.map(dto -> new Domino(dto.getId(), 
					new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
					new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
			.collect(Collectors.toList());
	
	private List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
			.map(dto -> new Player(dto.getName(), dto.getHexColor()))
			.collect(Collectors.toList());
	

	@Test
	void normalCase4Players() {
		DominoPile dominoPile = new DominoPile(4, dominos);
		DrawLine drawLine = new DrawLine(dominoPile.extractDrawLine(4), players);
		assertEquals(4, drawLine.getMapDominoPlayer().size());
	}
	
	@Test
	void normalCase3Players() {
		DominoPile dominoPile = new DominoPile(3, dominos);
		DrawLine drawLine = new DrawLine(dominoPile.extractDrawLine(4), players.subList(0, 3));
		assertEquals(3, drawLine.getMapDominoPlayer().size());
	}
	
	@Test
	void normalCase2Players() {
		DominoPile dominoPile = new DominoPile(2, dominos);
		DrawLine drawLine = new DrawLine(dominoPile.extractDrawLine(2), players.subList(0, 2));
		assertEquals(4, drawLine.getMapDominoPlayer().size());
	}
	
	@Test
	void getPlayerForDomino() {
		DominoPile dominoPile = new DominoPile(4, dominos);
		DrawLine drawLine = new DrawLine(dominoPile.extractDrawLine(4), players.subList(0, 1));
		
		assertEquals(players.get(0), drawLine.getPlayerForDomino());		
	}	
	
	@Test
	void keepSameOrder() {
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos, players.subList(0, 4));

		Map<Domino, Player> mapDrawLine = drawLine.getMapDominoPlayer();
		int i = 0;

		for(Map.Entry<Domino, Player> entry : mapDrawLine.entrySet()) {	
			assertEquals(0, entry.getKey().compareTo(dominos.get(i)));
			i++;
		}	
	}	
	
	
	@Test 
	void createANextDrawLine() {
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		assertEquals(4, drawLine.getDominos().size());
		assertTrue(null == drawLine.getPlayers());
	}
	
	@Test
	void resetIndexNextDomi() {
		//GIVEN
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		
		//WHEN
		drawLine.setIndexNextDomi(2);
		assertEquals(2, drawLine.getIndexNextDomi());
		
		drawLine.resetIndexNextDomi();
		//THEN
		assertEquals(0, drawLine.getIndexNextDomi());
	}
	
	@Test
	void getDominoNextLine() {
		//GIVEN
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		
		//WHEN
		drawLine.setIndexNextDomi(1);
		assertEquals(1, drawLine.getIndexNextDomi());
		
		Domino dom = drawLine.getDominoNextLine();
		//THEN
		assertEquals(dom, drawLine.getDominos().get(1));
	}
	
	@Test
	void normaleCaseaddPositionDominoNextLine(){
		//GIVEN
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		
		//WHEN
		drawLine.addPositionDominoNextLine();
		
		//THEN 
		assertEquals(1, drawLine.getIndexNextDomi());
	}
	
	@Test
	void normaleCaseaddPositionDominoNextLineWithMaxValue(){
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		
		//WHEN
		drawLine.setIndexNextDomi(3);
		drawLine.addPositionDominoNextLine();
		
		//THEN 
		assertEquals(0, drawLine.getIndexNextDomi());
	}
	
	@Test
	void getFirstDomino() {
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		
		assertEquals(drawLine.getFirstDomino(), dominos.get(0));
	}
	
	@Test
	void normalCasecheckIfNextPlayerExist() {
		//GIVEN
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		
		//WHEN
		drawLine.deletePlayerWhoComesToLay();
		drawLine.deletePlayerWhoComesToLay();

		//THEN
		assertTrue(drawLine.checkIfNextPlayerExist());
		
	}
	
	@Test
	void withoutNextPlayercheckIfNextPlayerExist() {
		//GIVEN
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		
		//WHEN
		drawLine.deletePlayerWhoComesToLay();
		drawLine.deletePlayerWhoComesToLay();
		drawLine.deletePlayerWhoComesToLay();
		drawLine.deletePlayerWhoComesToLay();

		//THEN
		assertFalse(drawLine.checkIfNextPlayerExist());
	}
	
	@Test
	void associatePlayerToNextLine() {
		//GIVEN
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		Player player = new Player("1", "#cccccc");
		Map<Domino, Player> mapDrawLine = drawLine.getMapDominoPlayer();
		
		//WHEN
		drawLine.associatePlayerToNextLine(dominos.get(2), player);
		
		
		//THEN
		for(Map.Entry<Domino, Player> entry : mapDrawLine.entrySet()) {
			if(entry.getKey() == dominos.get(2) && entry.getValue() == player)
			assertEquals(dominos.get(2), entry.getKey());
		}
	}
	
	@Test
	void resetIndexNextDomitWithFirstPosNotVoid() {
		//GIVEN
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		Player player = new Player("1", "#cccccc");
		Map<Domino, Player> mapDrawLine = drawLine.getMapDominoPlayer();
		
		//WHEN
		drawLine.associatePlayerToNextLine(dominos.get(0), player);
		drawLine.resetIndexNextDomi();
		
		//THEN
		assertEquals(1, drawLine.getIndexNextDomi());
	}
	
	@Test
	void checkIfCanSelectTrue() {
		//GIVEN
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);

		//THEN
		assertTrue(drawLine.checkIfCanSelect());
	}
	
	@Test
	void checkIfCanSelectFalse() {
		//GIVEN
		DominoPile dominoPile = new DominoPile(4, dominos);
		List<Domino> dominos = dominoPile.extractDrawLine(4);
		DrawLine drawLine = new DrawLine(dominos);
		Player player = new Player("1", "#cccccc");

		//WHEN 
		drawLine.associatePlayerToNextLine(dominos.get(0), player);
		
		//THEN
		assertFalse(drawLine.checkIfCanSelect());
	}
}
