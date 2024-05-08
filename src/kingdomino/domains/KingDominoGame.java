package kingdomino.domains;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Les conditions pour vérifier qu'une partie est bien terminée sont : 
 * Qu'il ne reste plus de dominos que l'on puisse tirer et que donc la NextDrawLine soit bien vide et 
 * qu'il n'y ait plus de joueur suivant dans la Actual DrawLine, c'est à dire qu'il ne reste plus aucun 
 * domino jouable dans la ligne de tirage actuelle. 
 * 
 * @author Mahy François
 *
 */
public class KingDominoGame {

	final private List<Player> players;
	final private DominoPile dominosPile;
	private DrawLine actualLine;
	private DrawLine nextLine;
	final private Coordinate coord;
	
	/**
	 * Constructeur de mon KingDominoGame
	 * @param players la liste des joueurs de la partie
	 * @param dominos une DominoPile représentant la pile de dominos
	 * @param drawLine une DrawLine qui est la première 
	 * @param nbPlayer un entier qui représente le nombre de joueur
	 */
	public KingDominoGame(final List<Player> players,  final DominoPile dominos, final DrawLine drawLine){
		this.players = new ArrayList<Player>(players);
		this.dominosPile = dominos;
		this.actualLine = drawLine;
		this.nextLine = new DrawLine(dominosPile.extractDrawLine(players.size()));
		this.coord = new Coordinate();
	}

	public int getRow() {
		return this.coord.getRow();
	}
	
	public int getCol() {
		return this.coord.getCol();
	}
	
	public int getRowSecond() {
		return this.coord.getRowSecond();
	}
	
	public void rotate() {
		this.coord.rotate();
	}	
	
	public void deletePlayerWhoComesToLay() {
		this.actualLine.deletePlayerWhoComesToLay();
	}
	
	public void translate(final int dr, final int dc) {
		this.coord.translate(dr, dc);
	}
	
	public Iterator<Map.Entry<Domino,Player>> getMapDominoPlayerActualLineIterator() {
		return this.actualLine.getIterator();
	}
	
	public Iterator<Map.Entry<Domino,Player>> getMapDominoPlayerNextLineIterator() {
		return this.nextLine.getIterator();
	}
	
	public List<Player> getPlayers(){
		return this.players;
	}
	
	public Domino getDominoNextLine() {
		return this.nextLine.getDominoNextLine();
	}
	
	public int getColSecond() {
		return this.coord.getColSecond();
	}
	
	public Domino getFirstDomino() {
		return this.actualLine.getFirstDomino();
	}
	
	public Kingdom getKingdom() {
		return getFirstPlayer().getKingdom();
	}
	
	public void resetCoord() {
		this.coord.resetCoord();
	}
	
	/*
	 * Méthodez permettant de déterminer qui est le premier joueur à jouer. 
	 */
	public Player getFirstPlayer() {
		return actualLine.getPlayerForDomino();
	}
	
	/**
	 * Méthode qui détermine si c'est la fin de la partie
	 * @return true si la partie est finie sinon false
	 */
	public boolean isGameOver(){
		if(this.nextLine == null && !this.actualLine.checkIfNextPlayerExist()) {
			this.actualLine = null;
			return true;
		}		
		return false;
	}
	
	/**
	 * Méthode permettant de vérifier que ActualDrawLine n'est pas NULL
	 * @return true la elle n'est pas NULL, sinon false
	 */
	public boolean actualDrawLineNotNull(){
		if(this.actualLine != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Méthode permattant de vérifier que la NextDrawLine n'est pas NULL
	 * @return true si elle n'est pas NULL, sinon false
	 */
	public boolean nextDrawLineNotNull() {
		if(this.nextLine != null){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Méhtode effectuant le traitement nécessaire au passage du Domino suivant dans la NextDrawLine
	 */
	public void OneSelectNextPiecePressed() {
		if(this.nextDrawLineNotNull()) {
			
			this.nextLine.addPositionDominoNextLine();
			
			while(!this.nextLine.checkIfCanSelect()) {
				this.nextLine.addPositionDominoNextLine();
			}
		}
	}
	
	/**
	 * Méhtode effectuant le traitement nécessaire à la sélection d'un Domino dans le NextDrawLine
	 */
	public void onPieceSelectedPressed() {
		this.nextLine.associatePlayerToNextLine(getDominoNextLine(), getFirstPlayer());		
		this.deletePlayerWhoComesToLay();	
		this.nextLine.resetIndexNextDomi();
	}
	
	/**
	 * Méthode effectuant le traitement nécessaire à la pose du Domino
	 * @return
	 */
	public boolean onDominoIsPut() {
		this.resetCoord();
		
		if(this.nextLine == null) {
			this.deletePlayerWhoComesToLay();
			if(this.actualLine.checkIfNextPlayerExist()) {
				return true;
			}	
		}
		return false;
	}
	
	
	/**
	 * Méhtode permettant de changer la DrawLineActuel par la NextDrawLine et de recréer une NextDrawLine
	 */
	public void swapDrawLine() {
		if(!this.actualLine.checkIfNextPlayerExist()) {
			this.actualLine = this.nextLine;
			if(dominosPile.remainingDominos(players.size())) {
				this.nextLine = new DrawLine(dominosPile.extractDrawLine(players.size()));
			}else {
				this.nextLine = null;
			}
		}
	}

	/**
	 * Utilise pour TEST UNITAIRE
	 * @return la DrawLine actual
	 */
	public DrawLine getDrawLineActual() {
		// TODO Auto-generated method stub
		return this.actualLine;
	}
	
	/**
	 * Utilise pour TEST UNITAIRE
	 * @return la next DrawLine
	 */
	public DrawLine getNextDrawLine() {
		// TODO Auto-generated method stub
		return this.nextLine;
	}

	/**
	 * Utilise pour TEST UNITAIRE
	 * @return la DominPile
	 */
	public DominoPile getDominoPile() {
		// TODO Auto-generated method stub
		return this.dominosPile;
	}
	
	/**
	 * Utilise pour TEST UNITAIRE
	 */
	public void setActualDrawLine() {
		// TODO Auto-generated method stub
		this.actualLine = null;
	}
	
	/**
	 * Utilise pour TEST UNITAIRE
	 */
	public void setNextDrawLine() {
		// TODO Auto-generated method stub
		this.nextLine = null;
	}
}