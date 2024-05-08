package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import kingdomino.io.CsvDataReader;

class KingDominoManagerTests {
	
	private  List<Domino> dominos = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
			.map(dto -> new Domino(dto.getId(), 
					new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
					new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
			.collect(Collectors.toList());
	
	private List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
			.map(dto -> new Player(dto.getName(), dto.getHexColor()))
			.collect(Collectors.toList());

	@Test
	void creatNewGameWith2Players() {
		//GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
		
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		
		//THEN
		assertEquals(16, game.getDominoPile().getCollDomino().size());
		assertEquals(2, game.getPlayers().size());
		assertEquals(4, game.getDrawLineActual().getDominos().size());		
	}
	
	@Test
	void creatNewGameWith3Players() {
		//GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(3);
		
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		
		//THEN
		assertEquals(30, game.getDominoPile().getCollDomino().size());
		assertEquals(3, game.getPlayers().size());
		assertEquals(3, game.getDrawLineActual().getDominos().size());	
	}
	
	@Test
	void creatNewGameWith4Players() {
		//GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(4);
		
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		
		//THEN
		assertEquals(40, game.getDominoPile().getCollDomino().size());
		assertEquals(4, game.getPlayers().size());
		assertEquals(4, game.getDrawLineActual().getDominos().size());	
	}
}
