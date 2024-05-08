/**
 * Contient tout le code de l'application kingdomino.
 */
package kingdomino;

import java.util.List;
import java.util.stream.Collectors;

import kingdomino.domains.*;
import kingdomino.io.*;
import kingdomino.supervisers.*;
import kingdomino.swing.*;

/**
 * Construit l'application et lance son exécution.
 * 
 * @author Nicolas Hendrikx
 */
public class Program {
	private final static List<Domino> DOMINOS = CsvDataReader.readDominoesFromFile("resources/data/dominoes.csv").stream()
			.map(dto -> new Domino(dto.getId(), 
					new Tile(Terrain.valueOf(dto.getTerrain1()), dto.getCrownsCount1()),
					new Tile(Terrain.valueOf(dto.getTerrain2()), dto.getCrownsCount2())))
			.collect(Collectors.toList());
	
	private final static List<Player> PLAYERS = CsvDataReader.readPlayersFromFile("resources/data/players.csv").stream()
			.map(dto -> new Player(dto.getName(), dto.getHexColor()))
			.collect(Collectors.toList());
	
	
	/**
	 * Point d'entrée d'une exécution.
	 * L'application ne tient pas compte des arguments.
	 * 
	 * @param args une liste d'arguments. 
	 */
	public static void main(String[] args) {	
		final KingDominoManager manager = new KingDominoManager(PLAYERS, DOMINOS);
		MainWindow window = new MainWindow("Ai 2022 - KingDomino - Mahy François",
			new SwingMainMenuView("MainMenu", new MainMenuSuperviser(manager)),
			new SwingPlayGameView("PlayGame", new PlayGameSuperviser(manager)),
			new SwingGameOverView("GameOver", new GameOverSuperviser(manager))	
		);
		window.start("MainMenu");
	}
}

