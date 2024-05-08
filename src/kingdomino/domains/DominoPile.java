package kingdomino.domains;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Interface : pour l'Interface j'ai utilisé Deque parce qu'elle permet de 
 * récupérer, ajouter, supprimer des éléments qui se trouvent au début 
 * ou à la fin d'une Collection. Dans le cas de la DominoPile on ne vas pas 
 * vouloir accéder à un élément sur base de son indice mais juste récupéer ceux 
 * se trouvant en haut de la pile. 
 * 
 * Classe concrète : pour la classe concrète j'ai utilisé ArrayDeque
 * car elle propose la méthode pollFirst() qui a une CTT en O(1), c'est la méthode que 
 * je vais principalement utilisée sur vu que je veux récupéer les éléments se trouvant au début. 
 * De plus l'autre possibilité était la LinkedList mais les méthodes pour supprimer, ajouter, récupérer des élements 
 * sont plus gourmantes en CTT O(N) et se base sur des indices. Donc l' ArrayDeque était pour moi le meilleur choix.  
 * @author Mahy François
 */
public class DominoPile{
	final private Deque<Domino> collDomino;
	
	/**
	 * Constructeur de la DominoPile
	 * @param nbPlayer le un entier qui représente le nombre de joueur
	 * @param colDomino une List de Domino 
	 */
	public DominoPile(final int nbPlayer, final List<Domino> colDomino){
		Collections.shuffle(colDomino);
		this.collDomino = resizeDominoPile(nbPlayer, colDomino) ;
	}
	
	public Deque<Domino> getCollDomino() {
		return new ArrayDeque<Domino>(this.collDomino);
	}

	/**
	 * Méthode permettant d'extraire une DrawLine sur base du nombre de joueur
	 * @param nbPlayer . Un entier représentant le nombre de joueur
	 * @return List<Domino>. Une liste de Domino
	 */
	public List<Domino> extractDrawLine(final int nbPlayer){	
		int nbDominoTire = 0;
		final List<Domino> drawLine = new ArrayList<Domino>(nbDominoTire);
		
		if(nbPlayer == 2 || nbPlayer == 4) {
			nbDominoTire = 4;
		}else {
			nbDominoTire = 3;
		}
		
		for (int i = 0; i < nbDominoTire; i++) {
			drawLine.add(collDomino.pollFirst());
		}	
		Collections.sort(drawLine);
		return new ArrayList<Domino>(drawLine);
	}
	
	/**
	 * Méthode permettant de redimentionner la list de Domino sur base du nombre de joueur
	 * @param int nbPlayer . Un entier représentant le nombre de joueur
	 * @param List<Domino> dominos . La liste de Domino que la méthode doit retailler
	 * @return List<Domino> Une liste de Domino avec une taille en accord avec le nombre de joueur
	 */
	private Deque<Domino> resizeDominoPile(final int nbPlayer, final List<Domino> dominos){	
		int nbDomino = 0;	
		switch(nbPlayer) {
			case 2:
				nbDomino = 24;
				break;
			
			case 3:
				nbDomino = 36;
				break;
				
			case 4:
				nbDomino = 48;
				break;
		}		
			
		return new ArrayDeque<Domino>(dominos.subList(0, nbDomino));
	}
	
	/**
	 * Méthode permettant de savoir si il reste encore des Domino à tirer
	 * @param nbPlayer le nombre de joueur sur lequel on détermine le nombre de domino à tirer
	 * @return true si il reste des Domino à tirer, sinon false
	 */
	public boolean remainingDominos(final int nbPlayer) {
		int nbDomino = 4;
		if(nbPlayer == 3) {
			nbDomino = 3;			
		}		
		return collDomino.size() >= nbDomino; 
	}
}
