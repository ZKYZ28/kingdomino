package kingdomino.supervisers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kingdomino.domains.*;

public class GameOverSuperviser extends Superviser {
	private GameOverView view;
	private KingDominoManager manager;
	private KingDominoGame game;
	
	//TODO : modifier au besoin le constructeur 
	public GameOverSuperviser(KingDominoManager manager) {
		this.manager = manager;
	}
	
	public void setView(GameOverView view) {
		this.view = view;
	}
	
	@Override
	public void onEnter(String fromScreen) {
		//TODO : si on vient de la vue "PlayGame", calculer les scores des royaumes, trier les joueurs et les afficher
		this.game = manager.getGame();
		
		draw();
	}
	
	private void draw() {
		//TODO : mettre à jour les instruction
		view.startDraw();
		
		for (int i = 0; i < manager.getNbPlayer(); i++) {			
			Player currentPlayer = game.getPlayers().get(i);
			Score score = new Score(currentPlayer.getKingdom());
			currentPlayer.setScore(score);
		
			view.addScore(currentPlayer.getName(), currentPlayer.getScore(), currentPlayer.getHexColor());
		}
		
		//Détermine le gagnant de la partie
		List<Player> winnerPlayer = new ArrayList<Player>();
		List<Player> players = game.getPlayers();
		Collections.sort(players);
		winnerPlayer.add(players.get(players.size()-1));

		for (int i = 0; i <= players.size()-2; i++) {
			if(players.get(i).getScore() == winnerPlayer.get(0).getScore()) {
				winnerPlayer.add(players.get(i));
			}
		}
		
		
		if(winnerPlayer.size() == 1) {
			view.setWinner("Le joueur " + winnerPlayer.get(0).getName() + " remporte la partie.");
		}else {
			String winners = "";
			for (int i = 0; i < winnerPlayer.size(); i++) {
				winners += winnerPlayer.get(i).getName() + " ";
			}
			view.setWinner("Les joueurs " + winners + " sont à égalité.");
		}
		
		view.endDraw();
	}

	public void onGoToMainMenu() {
		//TODO : naviguer vers le menu principal
		view.goTo("MainMenu");
	}
}
