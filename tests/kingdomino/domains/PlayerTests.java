package kingdomino.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTests {

	@Test
	void normalCasegetNamePlayer() {
		Player player = new Player("1", "#ff0000");
		
		assertEquals("1", player.getName());
	}
	
	@Test
	void nullGetPlayerName() {
		Player player = new Player(null, "#ff0000");
		
		assertEquals(Player.UNKNOW_PLAYER, player.getName());
	}
	
	@Test
	void emptyGetPlayerName() {
		Player player = new Player("", "#ff0000");
		
		assertEquals(Player.UNKNOW_PLAYER, player.getName());
	}
	
	@Test
	void normalCaseGetHexColor() {
		Player player = new Player("1", "#ff0000");
		
		assertEquals("#ff0000", player.getHexColor());
	}
	
	@Test
	void nullGetHexColor() {
		Player player = new Player("1", null);
		
		assertEquals(Player.UNKNOW_COLOR, player.getHexColor());
	}
	
	@Test
	void emptyGetHexColor() {
		Player player = new Player("1", "");
		
		assertEquals(Player.UNKNOW_COLOR, player.getHexColor());
	}
	
	@Test
	void compareToWithFirstBig() {
		//GIVEN
		Player player1 = new Player("1", "#ffffff");
		Player player2 = new Player("1", "#ffffff");
		
		//WHEN
		Score scoreP1 = new Score(player1.getKingdom());
		scoreP1.setScore(10);
		player1.setScore(scoreP1);
		Score scoreP2 = new Score(player2.getKingdom());
		scoreP2.setScore(0);
		player2.setScore(scoreP2);
		
		//THNE
		assertEquals(1, player1.compareTo(player2));
	}
	
	
	@Test
	void compareToWithSecondBig() {
		//GIVEN
		Player player1 = new Player("1", "#ffffff");
		Player player2 = new Player("1", "#ffffff");
		
		//WHEN
		Score scoreP1 = new Score(player1.getKingdom());
		scoreP1.setScore(0);
		player1.setScore(scoreP1);
		Score scoreP2 = new Score(player2.getKingdom());
		scoreP2.setScore(10);
		player2.setScore(scoreP2);
		
		//THNE
		assertEquals(-1, player1.compareTo(player2));
	}
	
	@Test
	void compareToEquals() {
		//GIVEN
		Player player1 = new Player("1", "#ffffff");
		Player player2 = new Player("1", "#ffffff");
		
		//WHEN
		Score scoreP1 = new Score(player1.getKingdom());
		scoreP1.setScore(10);
		player1.setScore(scoreP1);
		Score scoreP2 = new Score(player2.getKingdom());
		scoreP2.setScore(10);
		player2.setScore(scoreP2);
		
		//THNE
		assertEquals(0, player1.compareTo(player2));
	}
	
	@Test
	void compareToNull() {
		//GIVEN
		Player player1 = new Player("1", "#ffffff");
		
		//WHEN
		Score scoreP1 = new Score(player1.getKingdom());
		scoreP1.setScore(10);
		player1.setScore(scoreP1);
		
		//THNE
		assertEquals(-1, player1.compareTo(null));
	}

}
