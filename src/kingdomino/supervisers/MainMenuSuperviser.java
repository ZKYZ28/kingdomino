package kingdomino.supervisers;

import java.util.List;

import kingdomino.domains.KingDominoGame;
import kingdomino.domains.KingDominoManager;


public final class MainMenuSuperviser extends Superviser {
	public static final String QUIT = "Quitter";
	public static final String NEW_4_PLAYERS = "Jouer à 4";
	public static final String NEW_3_PLAYERS = "Jouer à 3";
	public static final String NEW_2_PLAYERS = "Jouer à 2";
	
	private MainMenuView view;
	private KingDominoManager manager;
	
	public MainMenuSuperviser(KingDominoManager manager) {
		this.manager = manager;
	}

	public void setView(MainMenuView view) {
		//TODO : définir les items à afficher par la vue
		this.view = view;
		view.setItems(List.of(NEW_2_PLAYERS, NEW_3_PLAYERS, NEW_4_PLAYERS, QUIT));
	}

	public void onItemSelected(int selected) {
		//TODO : réagir à l'événement utilisateur item sélectionné
	
		
		switch(selected) {
			case 0:
				manager.setNbPlayer(2);
				view.goTo("PlayGame");
				break;
				
			case 1:
				manager.setNbPlayer(3);
				view.goTo("PlayGame");
				break;
				
			case 2:
				manager.setNbPlayer(4);
				view.goTo("PlayGame");
				
				break;
				
			case 3:
				view.onQuitConfirmed("MainMenu");
				break;
				
			default:
				return;				
		}		
	}
}
