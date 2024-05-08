package kingdomino.supervisers;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.*;

import kingdomino.domains.Domino;
import kingdomino.domains.KingDominoGame;
import kingdomino.domains.KingDominoManager;
import kingdomino.domains.Player;
import kingdomino.domains.Terrain;
import kingdomino.domains.Tile;
import kingdomino.fakes.FakeMainMenuScreen;
import kingdomino.io.CsvDataReader;

import static kingdomino.supervisers.MainMenuSuperviser.*;

public class MainMenuSuperviserTest {
	private final static List<Domino> DOMINOS = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
			.map(dto -> new Domino(dto.getId(), 
					new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
					new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
			.collect(Collectors.toList());
	
	private final static List<Player> PLAYERS = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
			.map(dto -> new Player(dto.getName(), dto.getHexColor()))
			.collect(Collectors.toList());
	
	private FakeMainMenuScreen fakeScreen;
	private MainMenuSuperviser superviser;
	private KingDominoManager manager = new KingDominoManager(PLAYERS, DOMINOS);

	@BeforeEach
	public void setup() {
		// GIVEN
		fakeScreen = new FakeMainMenuScreen();
		//TODO: adapter l'appel au constructeur de la classe MainMenuSuperviser
		superviser = new MainMenuSuperviser(manager);
		superviser.setView(fakeScreen);
	}
	
	@Test
	public void setsItemsOnScreenSet() {
		// EXPECT
		fakeScreen.verify("setItems", List.of(NEW_2_PLAYERS, NEW_3_PLAYERS, NEW_4_PLAYERS, QUIT));
	}

	@Test
	public void goesToPlayGameScreenOnNew2PlayersGameAsked() {
		// WHEN
		superviser.onItemSelected(0);
		// THEN
		fakeScreen.verify("goTo", "PlayGame");
	}
	
	@Test
	public void goesToPlayGameScreenOnNew3PlayersGameAsked() {
		// WHEN
		superviser.onItemSelected(1);
		// THEN
		fakeScreen.verify("goTo", "PlayGame");
	}
	
	@Test
	public void goesToPlayGameScreenOnNew4PlayersGameAsked() {
		// WHEN
		superviser.onItemSelected(2);
		// THEN
		fakeScreen.verify("goTo", "PlayGame");
	}
	
	@Test
	public void ignoresCommandUndeflow() {
		// WHEN
		superviser.onItemSelected(-1);
		// THEN
		fakeScreen.verifyNoCall("goTo", "PlayGame");
		fakeScreen.verifyNoCall("onQuitConfirmed", "MainMenu");
	}
	
	@Test
	public void ignoresCommandOverflow() {
		// WHEN
		superviser.onItemSelected(4);
		// THEN
		fakeScreen.verifyNoCall("goTo", "PlayGame");
		fakeScreen.verifyNoCall("onQuitConfirmed", "MainMenu");
	}
}
