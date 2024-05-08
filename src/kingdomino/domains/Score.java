package kingdomino.domains;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Justification INTERFACE Tuiles déjà visitée :
 * Pour l'Interface j'ai utilisé une Set car elle permet de stocker mes éléments 
 * tout en me permettant de ne pas avoir de doublons et donc que la même tuile ne soit pas visitée deux fois
 * .Ici je veux juste ajouter des éléments et vérifier si un élément est contenu dans ma Collection je vais 
 * donc pouvoir utiliser la méthode add() et contains(). 
 * 
 * Justification Classe Concrète Tuiles déjà visitée : 
 * Pour la classe concrète j'ai utilisé une HahsSet car je ne veux pas trier ni ordonner les éléments
 * présents. Je vais uniquement utilisé les méthodes add() avec une CTT en O(1) et contains() avec une CTT qui 
 * est elle aussi en O(1). 
 * 
 * 
 * 
 * Justification INTERFACE Tuiles à étendre :
 * Pour l'Interface j'ai utilisé Deque parce qu'elle permet de 
 * récupérer, ajouter et supprimer des éléments qui se trouvent aux extrémités 
 * d'une Collection. Dans le cas des tuiles à étendre on ne va pas 
 * vouloir accéder à un élément sur base de son indice mais juste récupéer celui 
 * se trouvant en première position à chaque fois. 
 * 
 * Justification Classe Concrète Tuiles à étendre : 
 * Pour la classe concrète j'ai utilisé une ArrayDeque car je dois ajouter / supprimer l'élément 
 * se trouvant à une éxtrémité. Je vais utiliser les méthodes offer() avec une CTT enO(1), pollFirst avec une CTT en O(1) et size avec une CTT en O(1). 
 * 
 * @author Mahy François
 *
 */
public class Score {
	private int score;
	private Kingdom kingdom;
	final private List<Tile> currentTerritory;
	final private Set<Tile> visitedTile;
	final private Deque<Tile> extendedTile;
	final private Deque<Tile> uncoveredTile;
	
	/**
	 * Constructeur du Score
	 * @param kingdom le Kingdom sur lequel il se base pour calculer les points
	 */
	public Score(final Kingdom kingdom) {
		this.visitedTile = new HashSet<Tile>();
		this.extendedTile = new ArrayDeque<Tile>();
		this.uncoveredTile = new ArrayDeque<Tile>();
		this.currentTerritory = new ArrayList<Tile>();
		this.kingdom = kingdom;
		this.score = 0;
		
		startCalculatingPoints();
	}
	
	
	public int getScore() {
		return this.score;
	}	
	
	/**
	 * Utilise pour les TESTS UNITAIRES
	 * @param score un entier qui est le score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	public Set<Tile> getVisitedTile() {
		return this.visitedTile;
	}	
	
	public List<Tile> getCurrentTerritory() {
		return this.currentTerritory;
	}	
	
	public Deque<Tile> getExtendedTile() {
		return this.extendedTile;
	}	
	
	public Deque<Tile> getUncoveredTile() {
		return this.uncoveredTile;
	}	
		
	public void setKingdom(final Kingdom kingdom) {
		this.kingdom = kingdom;
	}	

	/**
	 * Méthode permettant de déterminer la Tile sur laquelle on commence la comptage des points
	 */
	private void determinateFirstTile() {
		final Tile[][] tabTile = kingdom.getTabTil();
		final Tile[] arroundCastle = new Tile[] {tabTile[3][4], tabTile[4][5], tabTile[5][4], tabTile[4][3]};
		boolean findFirst = false;

		for(final Tile current : arroundCastle) {
			if(current.getTerritory() != Terrain.EMPTY) {
				if(findFirst == false) {
					visitedTile.add(current);
					extendedTile.offer(current);
					currentTerritory.add(current);
					findFirst = true;
				}else {
					uncoveredTile.offer(current);
				}	
			}
		}
	}
		
	/**
	 * Méthode permettant de savoir à quelle position est la Tile courrante 
	 */
	private void determinatePositionCurrentTile() {
		final Tile[][] tabTile = kingdom.getTabTil();
		final Tile needToBeextendedTile = extendedTile.pollFirst();
		
		for (int i = 0; i < tabTile.length; i++) {
			for (int j = 0; j < tabTile.length; j++) {
				if(needToBeextendedTile == tabTile[i][j]) {
					determinatePositionArround(needToBeextendedTile, i, j);
				}
			}
		}
	}
	
	/**
	 * 
	 * JUSTIFICATION CTT :
	 * La méthode determinatePositionArround() est en  O(N + 1 ... + 1) que l'on peut simplifier en O(N) avec N = le nombre de voisin. 
	 * 
	 * Justification du O(N) :
	 * determinatePositionArround() ne fait que d'acccèder à un élément sur base de sa position, vérifier deux égalités et ajouter l'élément à la List si la condition est vraie. 
	 * Le fait d'accéder à un élément sur base de sa position dans un tableau est en O(1), vérifier une égalité est en O(1) et la méthode add d'une ArrayList est en O(1). 
	 * On peut faire ceci maximum 4 fois ce qui nous donne donc une méthode en O(1 + ... + 1) que l'on peut simplifier en O(1).
	 * La méthode getTabtile() return juste un attribut donc on est en O(1) et pour getTerritory idem. 
	 *  
	 * Ensuite on appelle la méthode lookArroundCurrentTile()  qui a une CTT en O(N). 
	 * On a une  boucle en O(N) qui va parcourir tous les voisins dans laquelle on va appeller la méthode contains() d'une HashSet qui en O(1) 
	 * puis si le contains() renvoie false on va appeler la méthodes getTirritory() 2X qui est en O(1) = O(1 +1) car elle ne return que un attibut. Si la condition est évaluée à vrai on va faire appel à la méthode add() d'une HahsSet qui est en O(1), 
	 * puis une méthode offer()  d'une ArrayDeque qui aussi en O(1) et on va faire appel à la méthode add d'une ArrayList qui est en O(1). Sinon on va faire appel à la méthode offer() d'une ArrayDeque qui est en O(1). 
	 * Ce qui nous donne donc pour la méthode lookArroundCurrentTile() une 
	 * CTT en O(N + 1 ... +1) que l'on peut simplifier en O(N).  
	 * 
	 * Méthode permettant de déterminer les Tiles autour da ma current Tile tout en restant dans les limites
	 * @param needToBeextendedTile une Tile qui est celle à étendre
	 * @param row la ligne de la current Tile
	 * @param col la colonne de la current Tile
	 */
	private void determinatePositionArround(final Tile needToBeextendedTile, final int row, final int col) {
		final List<Tile> listTile = new ArrayList<Tile>(4);
		final Tile[][] tabTile = kingdom.getTabTil();

		if(row -1 >= 0 && tabTile[row-1][col].getTerritory() != Terrain.CASTLE && tabTile[row-1][col].getTerritory() != Terrain.EMPTY) {
			listTile.add(tabTile[row-1][col]);
		}		
		if(row+1 <= 8 && tabTile[row+1][col].getTerritory() != Terrain.CASTLE && tabTile[row+1][col].getTerritory() != Terrain.EMPTY) {
			listTile.add(tabTile[row+1][col]);
		}		
		if(col-1 >= 0 && tabTile[row][col-1].getTerritory() != Terrain.CASTLE && tabTile[row][col-1].getTerritory() != Terrain.EMPTY) {
			listTile.add(tabTile[row][col-1]);
		}		
		if(col+1 <= 8 && tabTile[row][col+1].getTerritory() != Terrain.CASTLE && tabTile[row][col+1].getTerritory() != Terrain.EMPTY) {
			listTile.add(tabTile[row][col+1]);
		}
		lookArroundCurrentTile(needToBeextendedTile, listTile);
	}
		
	/**
	 * 
	 * Méthodes permettant de classifier tous les voisins d'une Tile
	 * @param needToBeextendedTile une Tile qui est celle à étendre
	 * @param listTile une List des voisins de la Tile courante 
	 */
	private void lookArroundCurrentTile(final Tile needToBeextendedTile, final List<Tile> listTile) {
		final List<Tile> arroundTile = listTile;	
		final Tile extended = needToBeextendedTile;
		
		for(final Tile current : arroundTile){
			if(!visitedTile.contains(current)){
				if(current.getTerritory() == extended.getTerritory()) {
					visitedTile.add(current);
					extendedTile.offer(current);
					currentTerritory.add(current);
				}else{
					uncoveredTile.offer(current);
				}
			}
		}
	}	
	
	/**
	 * Méthode permettant de réaliser l'ensemble de l'algorithme
	 */
	public void startCalculatingPoints() {
		//Détermine la tile où on va commencer
		determinateFirstTile();
		do{
			//Traitement jusqu'à ce que le territoire soit vide
			while(extendedTile.size() != 0) {
				determinatePositionCurrentTile();
			};
			//Calcul les points du terrain actuel
			calculatePointForCurrentTerritory();
				
			//Mets une Tile des découvertes en Tile à étendre
			if(uncoveredTile.size() != 0 && extendedTile.size() == 0) {
				while(uncoveredTile.size() != 0  && visitedTile.contains(uncoveredTile.getFirst()) ) {
					uncoveredTile.removeFirst();
				}			
				if(uncoveredTile.size() != 0) {
					swapUncoveredToExtended();
				}
			}			
		}while(extendedTile.size() != 0 || uncoveredTile.size() != 0);
	}
	
	/**
	 * Méthode permettant de calculer les points pour le territoire en cours
	 * Les points sont déterminés par le nombre de Tile * le nombre de couronnes
	 */
	private void calculatePointForCurrentTerritory() {
		int nbCrwons = 0;
		int nbTile = 0;
		for(final Tile current : currentTerritory) {
			nbCrwons += current.getCrownsCount();
			nbTile += 1;
		}
		
		this.score += nbCrwons * nbTile;
		currentTerritory.clear();
	}
	
	/**
	 * Méthode permettant de passer la première Tile découverte dans la list des tuiles à étendre 
	 */
	private void swapUncoveredToExtended() {
		final Tile swap = uncoveredTile.pollFirst();
		visitedTile.add(swap);
		extendedTile.offer(swap);
		currentTerritory.add(swap);
	}


	@Override
	public int hashCode() {
		return Objects.hash(currentTerritory, extendedTile, kingdom, score, uncoveredTile, visitedTile);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		return Objects.equals(currentTerritory, other.currentTerritory)
				&& Objects.equals(extendedTile, other.extendedTile) && Objects.equals(kingdom, other.kingdom)
				&& score == other.score && Objects.equals(uncoveredTile, other.uncoveredTile)
				&& Objects.equals(visitedTile, other.visitedTile);
	}
}
