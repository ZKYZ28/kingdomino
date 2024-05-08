package kingdomino.domains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import kingdomino.io.CsvDataReader;

class KingDominoGameTests {
	
	private  List<Domino> dominos = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
			.map(dto -> new Domino(dto.getId(), 
					new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
					new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
			.collect(Collectors.toList());
	
	private List<Player> players = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
			.map(dto -> new Player(dto.getName(), dto.getHexColor()))
			.collect(Collectors.toList());
	
	@Test
	void createNewGame() {
		//GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
			
		//WHEN
		KingDominoGame game = manager.creatNewGame();
	
		assertEquals(game.getDrawLineActual(), manager.getDrawLine());
	}
	
	@Test
	void getNextDrawLine() {
		//GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
			
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		DrawLine nextLine = game.getNextDrawLine();
		
		assertEquals(4, nextLine.getDominos().size());
	}
	
	@Test
	void getNextDrawLineSort() {
		//GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
			
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		DrawLine nextDrawLine = game.getNextDrawLine();
		
		
		//THEN
		 assertTrue(nextDrawLine.getDominos().get(0).getId() < nextDrawLine.getDominos().get(1).getId());
		 assertTrue(nextDrawLine.getDominos().get(1).getId() < nextDrawLine.getDominos().get(2).getId());
		 assertTrue(nextDrawLine.getDominos().get(2).getId() < nextDrawLine.getDominos().get(3).getId());
	}
	
	@Test
	void getFirstPlayer() {
		//GIVEN
		Player p = new Player("Orange", "#666777");
		List<Player> playerList = List.of(p, p);
		KingDominoManager manager = new KingDominoManager(playerList, dominos);
		manager.setNbPlayer(2);
			
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		Player player = game.getFirstPlayer();
		
		//THEN
		assertEquals(player, playerList.get(0));
	}
	
	@Test
	void getCoordinate() {
		//GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
			
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		game.translate(0, 0);
		
		//THEN 
		assertEquals(0, game.getCol());
		assertEquals(0, game.getRow());
		assertEquals(1, game.getColSecond());
		assertEquals(0, game.getRowSecond());
		
		//WHEN
		game.resetCoord();
		
		//THEN 
		assertEquals(0, game.getCol());
		assertEquals(0, game.getRow());
	}
	
	
	@Test
	void deletePlayerWhoComesToLay() {
		//GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
			
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		game.deletePlayerWhoComesToLay();
		DrawLine drawLine = game.getDrawLineActual();
		
		assertEquals(3, drawLine.getMapDominoPlayer().size());
	}
	
	
	 @Test
	 void getDominoNextLineAndGetFirstDomino() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		Domino dom = game.getDominoNextLine();
		Domino first = game.getFirstDomino();
			
		//THEN 
		assertEquals(dom, game.getDominoNextLine());
		assertEquals(first, game.getFirstDomino());
	 }
	 
	 @Test
	 void getKingdom() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		Kingdom kingdom = game.getKingdom();
			
		//THEN 
		assertEquals(kingdom, game.getKingdom());
	 }
	 
	 @Test
	 void dominoPile() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		DominoPile domPile = game.getDominoPile();
			
		//THEN 
		assertEquals(domPile, game.getDominoPile());
	 }
	 
	 @Test
	 void playersList() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
			
		//THEN 
		assertEquals(2, game.getPlayers().size());
	 }
	 
	 @Test
	 void isGameOver() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
			
		//THEN 
		assertEquals(false, game.isGameOver());
	 }
	 
	 
	 @Test
	 void isGameOverTwo() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		game.setNextDrawLine();
		for (int i = 0; i < 4; i++) {
			game.deletePlayerWhoComesToLay();
		}
			
		//THEN 
		assertEquals(true, game.isGameOver());
	 }
	 
	 @Test
	 void actualDrawLineNotNull() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
			
		//THEN 
		assertEquals(true, game.actualDrawLineNotNull());
	 }
	 
	 @Test
	 void actualDrawLineNotNullTwo() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		game.setActualDrawLine();
			
		//THEN 
		assertEquals(false, game.actualDrawLineNotNull());
	 }
	 
	 @Test
	 void nextDrawLineNotNull() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
			
		//THEN 
		assertEquals(true, game.nextDrawLineNotNull());
	 }
	 
	 @Test
	 void nextDrawLineNotNullTwo() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		game.setNextDrawLine();
			
		//THEN 
		assertEquals(false, game.nextDrawLineNotNull());
	 }
	 
	 @Test
	 void onDominoIsPutOne() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
			
		//THEN 
		assertEquals(false, game.onDominoIsPut());
	 }
	 
	 
	 @Test
	 void swapDrawLineOne() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		for (int i = 0; i < 4; i++) {
			game.deletePlayerWhoComesToLay();
		}
		
		game.swapDrawLine();
	 }
	 
	 	 
	 @Test
	 void TEST() {
		 //GIVEN
		KingDominoManager manager = new KingDominoManager(players, dominos);
		manager.setNbPlayer(2);
				
		//WHEN
		KingDominoGame game = manager.creatNewGame();
		game.onPieceSelectedPressed();
		game.OneSelectNextPiecePressed();
		game.getMapDominoPlayerActualLineIterator();
		game.getMapDominoPlayerNextLineIterator();
		game.rotate();
	 }
}
