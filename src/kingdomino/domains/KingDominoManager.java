package kingdomino.domains;

import java.util.ArrayList;
import java.util.List;

public class KingDominoManager {
	
	private List<Player> players;
	private List<Domino> dominos;
	private DominoPile dominosPile;
	private DrawLine drawLine;
	private KingDominoGame game;
	private int nbPlayer;
	
	/**
	 * Constructeur de mon KingDominoManager
	 * @param players une liste des joueurs avec tous les joueurs du fichier csv
	 * @param dominos une liste des dominos avec tous les dominos du fichier csv
	 */
	public KingDominoManager(List<Player> players, List<Domino> dominos){
		this.players = new ArrayList<Player>(players);
		this.dominos = new ArrayList<Domino>(dominos);
		this.nbPlayer = 0;
	}
	
	/**
	 * Méthode permettant de créer une partie. 
	 * Sur base du nombre de joueur, elle va faire les traitements nécessaires et créer un nouvel Object KingDominoGame. 
	 */
	public KingDominoGame creatNewGame() {
		this.dominosPile = new DominoPile(this.nbPlayer, dominos);	
		List<Player> playersList = players.subList(0, nbPlayer);
		drawLine = new DrawLine(this.dominosPile.extractDrawLine(nbPlayer), playersList);				
		this.game =  new KingDominoGame(playersList, this.getDominoPile(), this.getDrawLine());
		return game;
	}
	
	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}
	
	
	public int getNbPlayer() {
		return nbPlayer;
	}
	
	public KingDominoGame getGame() {
		return this.game;
	}
	
	public DominoPile getDominoPile() {
		return dominosPile;
	}
	
	public DrawLine getDrawLine() {
		return this.drawLine;
	}
}
