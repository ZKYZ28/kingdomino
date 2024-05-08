package kingdomino.supervisers;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import kingdomino.domains.*;
import kingdomino.supervisers.PlayGameView.ViewMode;

public class PlayGameSuperviser extends Superviser {
	private PlayGameView view;
	
	private KingDominoManager manager;
	private KingDominoGame game;
	
	public PlayGameSuperviser(KingDominoManager manager) {
		this.manager = manager;
	}

	public void setView(PlayGameView view) {
		this.view = view;
	}


	/**
	 * ITERATION 1 :   
	 * POSTCONDITION : 
	 * Pile de dominos : La pile domino est melangée et sa taille est adaptée en fonction 
	 * du nombre de joueur(20/2p, 33/3p, 44/4p). La taille est diminuée de la taille de la drawline. 
	 * 
	 * Ligne de tirage actuelle : La ligne de tirage est composée d'un nombre de domino déterminé 
	 * par le nombre de joueur (4/2p, 3/3p, 4/4p). Les dominos sont triés par ordre de leur ID. 
	 * Chaque joueur est associé à un Domino de manière aléatoire, pour le cas de 2p chaque joueur se voit recevoir 2 dominos. 
	 * 
	 * Ligne de tirage suivante : La ligne de tirage suivante est composée d'un nombre de domino déterminé 
	 * par le nombre de joueur (4/2p, 3/3p, 4/4p). Les dominos sont triés par ordre de leur ID. 
	 * Ici, ils ne sont associés à aucun joueur. 
	 * 
	 * 
	 * Le royaume du joueur qui a la main : Le royaume du joueur correspond au joueur qui commence la partie et affiche le premier Domino de la DrawLine en haut à gauche
	 */
	
	
	/**
	 * CTT de la méthode extractDrawLine(int nbPlayer)
	 * CTT = O(N^2 + N*log(N)) avec N = le nombre de Domino souhaité dans le DrawLine(3 ou 4)
	 * 
	 * La complexité de la première boucle est en O(N^2)
	 * Parce que nous avous une boucle qui se répète autant de 
	 * fois que l'on a de domino (3 ou 4), ensuite on a une boulce 
	 * imbriquée dans celle-ci suite à l'appel de la méthode .add
	 * et pour finir dans cette boucle on fait appel à la méhtode .pollFirst() 
	 * qui elle, est constante
	 * 
	 * Par après nous faisons appel à Collections.sort() qui a une complexité en 
	 * O(M*log(M)) 
	 */
	
	@Override
	public void onEnter(String fromScreen) {
		//TODO : créer le jeu quand l'application vient du menu principal
		this.game = manager.creatNewGame();
		for (int i = 0; i < game.getPlayers().size(); i++) {
			game.getPlayers().get(i).getKingdom().resetKingdom();
		}
		draw();
		
		Player myPlayer = game.getFirstPlayer();
		view.addMessage("Le joueur " + myPlayer.getName() + "(" + myPlayer.getHexColor() + ")" + " prend la main");
	}

	private void draw() {
		//TODO: remplacer le rendu d'exemple par le rendu réel
		view.startDraw();
		if(game.actualDrawLineNotNull()) {
			drawKingdom();
			drawCurrentDominoOnKingdom();
			drawNextLine();
			drawActualLine();
		}		
		view.endDraw();
	}

	private void drawKingdom() {
		//TODO : à remplacer par le rendu du royaume du joueur qui prend la main
		//Les coordonnées des tuiles du royaume sont centrées sur le chateau.
		//Pour éviter des coordonées négative, on fait une translation
		//Ici, le chateau occupe le centre du grille de 10x10 cases
			Kingdom kingdom = game.getKingdom();
			Tile[][] kingdomView = kingdom.getTabTil();
			for (int i = 0; i < kingdomView.length; i++) {
				for (int j = 0; j < kingdomView.length; j++) {
					if(kingdomView[i][j] != null) {
						view.addToKingdom(toString(kingdom.getTerritoryInPoisition(i, j)), kingdom.getCrownsCountInPosition(i, j), i, j);
					}
				}
		}
	}
	
	private void drawCurrentDominoOnKingdom() {
		//TODO : à remplacer par le rendu du domino courant
		Domino current = game.getFirstDomino();
		view.addToKingdom(toString(current.getTerritory(0)), current.getCrownsCount(0), game.getRow(), game.getCol(), game.getFirstPlayer().getHexColor());
		view.addToKingdom(toString(current.getTerritory(1)), current.getCrownsCount(1), game.getRowSecond(), game.getColSecond(), game.getFirstPlayer().getHexColor());
	}

	private void drawActualLine() {
			//TODO : à remplacer par le rendu de la ligne courante
			Iterator<Entry<Domino, Player>> mapDrawLine = game.getMapDominoPlayerActualLineIterator();
			Domino myDomino;
			Player myPlayer;
			
			while(mapDrawLine.hasNext()) {
				Entry<Domino,Player> tempEntry = mapDrawLine.next();
				myDomino = tempEntry.getKey();
				myPlayer = tempEntry.getValue();
				view.addToActualLine(toString(myDomino.getTerritory(0)), myDomino.getCrownsCount(0), toString(myDomino.getTerritory(1)), myDomino.getCrownsCount(1), myPlayer.getHexColor());
			}
	}

	private void drawNextLine() {
		//TODO : à remplacer par le rendu de la ligne suivante
		if(game.nextDrawLineNotNull()) {
			Iterator<Entry<Domino, Player>> mapNextDrawLine = game.getMapDominoPlayerNextLineIterator();
			Domino myNextDomino;
			Player myNextPlayer;
			Domino currentDomino = game.getDominoNextLine();
			
			while(mapNextDrawLine.hasNext()) {
				Entry<Domino,Player> tempEntry = mapNextDrawLine.next();
				myNextDomino = tempEntry.getKey();
				myNextPlayer = tempEntry.getValue();
				if(myNextDomino.getId() == currentDomino.getId()) {
					view.addToNextLine(toString(currentDomino.getTerritory(0)), currentDomino.getCrownsCount(0), toString(currentDomino.getTerritory(1)), myNextDomino.getCrownsCount(1), game.getFirstPlayer().getHexColor());
				}else {
					view.addToNextLine(toString(myNextDomino.getTerritory(0)), myNextDomino.getCrownsCount(0), toString(myNextDomino.getTerritory(1)), myNextDomino.getCrownsCount(1), myNextPlayer.getHexColor());
				}
			}
		}
	}
	
	private String toString(Terrain t) {
		return t.toString().toLowerCase();
	}

	public void onMove(int dr, int dc) {
		//TODO : Gérer les déplacement du domino courant, si ce dernier n'a pas été posé
		view.addMessage("onMove called");
		game.translate(dr, dc);
		draw();
	}

	public void onRotate() {
		//TODO : Faire pivoter le domino courant de 90°, si ce dernier n'a pas été posé
		view.addMessage("onRotate called");
		game.rotate();
		draw();
	}

	public void onConfirm() {
		
		if(game.actualDrawLineNotNull()) {
			view.addMessage("onSetDomino called");
			//TODO : vérifier que le domino est affectable au royaume au coordonnée courantes
			//TODO : affecter le domino au royaume
			final Kingdom kingdom = game.getKingdom();
			final Domino current = game.getFirstDomino();
			final int[] coordFirst = {game.getRow(), game.getCol()};
			final int[] coordSecond = {game.getRowSecond(), game.getColSecond()};
			
			if(kingdom.putDomino(current, coordFirst, coordSecond)) {
				if(game.onDominoIsPut() && !game.isGameOver()) {
					Player myPlayer = game.getFirstPlayer();
					view.addMessage("Le joueur " + myPlayer.getName() + "(" + myPlayer.getHexColor() + ")" + " prend la main");
				}			

				//Décide de la fin de partie
				if(game.isGameOver()) {
					view.addMessage("Partie terminée");				
					view.goTo("GameOver");
				}
				
				if(game.nextDrawLineNotNull()){
					view.switchMode(ViewMode.LINES);
				}
			}else {
				view.addMessage("Vous ne pouvez pas placer de domino ici !");
			}
			draw();
		}
	}

	public void onSelectNextPiece() {
		view.addMessage("onSelectNextPiece");
		//TODO : choisir le domino suivant, si le domino courant a été posé
		game.OneSelectNextPiecePressed();
		draw();
	}
	
	public void onPieceSelected() {
			view.addMessage("onPieceSelected");
			//TODO : passer au joueur suivant, si le domino courant a été posé
			//TODO : décidé de la fin de la partie
			
			//Effectue les traitements liés à la pose dans la nextDrawLine		
			game.onPieceSelectedPressed();

			view.switchMode(ViewMode.KINGDOM);	
			
			//Vérifie si c'est la fin de la DrawLine et swap si oui
			game.swapDrawLine();	
			
			final Player myPlayer = game.getFirstPlayer();
			view.addMessage("Le joueur " + myPlayer.getName() + "(" + myPlayer.getHexColor() + ")" + " prend la main");
			
			draw();
	}

	public void onPass() {
			if(game.nextDrawLineNotNull()) {
				view.addMessage("onPassSelected");		
				
				game.resetCoord();
				view.switchMode(ViewMode.LINES);

			}else {
				game.deletePlayerWhoComesToLay();
						
				if(game.isGameOver()) {
					view.addMessage("La partie est terminée");							
					view.goTo("GameOver");
					
				}else {
					Player myPlayer = game.getFirstPlayer();
					view.addMessage("Le joueur " + myPlayer.getName() + "(" + myPlayer.getHexColor() + ")" + " prend la main");	
				}				
			}	
			
			draw();
	}
}
