package kingdomino.domains;

import java.util.Arrays;

/**
 * ITERATION 2 :
 * 
 * PREMIERE PARTIE :
 * Pour la représentation du Kingdom j'ai choisir un Tableau 2D, premièrement car je trouve que ça illustre assez bien la représentaion 
 * du tableau de jeu. Le Tableau 2D me permet également d'accéder à un élément précis sur base de sa position dans le tableau (avec une CTT en O(1)).
 * Je n'ai pas choisi List, Deque, Queue ni Set car elles ne me permettaient pas de lier un élément à un position de manière simple et 
 * éfficace. Cependant la Map auait pû être une solution correcte car elle me permettait de réaliser une association Clé/Valeur avec la position 
 * comme Clé et la Tile comme Valeur.
 * 
 * 
 * 
 * DEUXIEME PARTIE : 
 *  Alors ici, excepté la méthode permettant d'initialiser le tableau createKingdom() 
 *  qui est en O(N^2)(voir en dessous description), qui ne va seuelement être appelée une fois par joueur durant toute la partie,
 *  tout le reste des méthodes qui vont être utilisées plusieurs fois durant la partie ont une CTT en O(1)(voir en dessous description)
 *  
 *  createKingdom() CTT en O(N^2) avec N = le nombre de côté de mon plateau de jeu (9). 
 *  On a une première boucle sur laquelle on va boucler N fois O(N), dans laquelle il y a une autre boucle sur laquelle on va boucler N fois également O(N) 
 *  et dans ceci on a une condition et un assignation de valeur que l'on peut résumer en O(1). Ce qui nous fait donc O(N*N + 1) que l'on peut résumer en O(N^2)
 *  
 *  Si j'avais utilisé une Map pour cette méthode j'aurais également eu une CTT en O(N^2) avec N = le nombre de côté de mon plateau de jeu (9)
 *  J'aurais eu une première boucle qui m'aurait donnée la ligne sur laquelle j'aurais bouclé N fois O(N), dans laquelle j'aurais eu une autre boucle qui m'aurait 
 *  donnée la colonne sur laquelle j'aurais bouclé N fois O(N) et dans ces deux boucles j'aurais eu une condition puis la méthode put O(1). Dans cette méthode put() j'aurais ma clé (ligne&colonne) 
 *  et une tile (vide/chateau) que j'ai défini en constante ici plus bas. Ce qui nous donne donc également O(N*N + 1) que l'on peut résument en O(N^2). 
 *  
 *  Dans les deux cas j'aurais du parcourir toutes les cases de mon plateau de jeu et y assigner des valeurs. 
 *   
 *   
 *   Pour le reste des méthodes j'accède simplement à un élément sur base de sa position donc c'est en O(1)
 *   Si on applique cela à Map ça serait la méthode get() qui est elle aussi en O(1)
 *   
 *   Pour ce qui est des éventuelles conditions dans les méthodes elles ne changent en rien la CTT, elle reste CONSTANTE O(1)
 *   Et pour ce qui est des éventuelles méthodes(getCrownsCount() / getTerritory()) appelées elles ne changent en rien non plus la CTT, elle reste CONSTANTE O(1) car la seule instruction
 *   présente dans ces méthodes est le return d'un attribut. 
 *   
 * @author Mahy François
 */


/**
 * ITERATION 3 :
 * Précondition du royaume :
 * Pour que le score du royaume soit correctement calculable il faut qu' un château se trouve dans celui-ci, il ne peut pas y avoir plusieurs châteaux. Si des tuiles 
 * sont présentes, il faut qu'au moins une d'elles touchent le château et qu'elles soient toutes collées au minimum à une autre tuile ou au château, il ne peut pas y avoir une tuile qui 
 * n'est pas collée à une ou plusieurs autres ou au château. 
 * 
 * Exemples :
 * 1) Le kingdom ne possède pas de château, on ne peut donc pas partir de quelque part pour commencer à compter les points. 
 * 2) Une tuile n'est pas collée aux autres et elle ne peut donc pas être prise en compte lors du comptage des points. 
 * 
 * @author Mahy François
 *
 */
public class Kingdom {
	private Tile[][] tabTile = new Tile[9][9];
	private static final Tile EMPTY = new Tile(Terrain.EMPTY, 0);
	private static final Tile CASTLE = new Tile(Terrain.CASTLE, 0);
	
	/**
	 * Constructeur de mon Kingdom, il fait appel à la méthode createKingdom() qui permet d'initialiser le tableau avec des cases vides et un château au centre 
	 */
	public Kingdom() {
		createKingdom();
	}
	
	public Tile[][] getTabTil(){
		return Arrays.copyOf(this.tabTile, tabTile.length);
	}
	
	/**
	 * Méthode permettant d'initaliser le Kingdom d'un joueur
	 */
	private void createKingdom() {
		for (int i = 0; i < tabTile.length; i++) {
			for (int j = 0; j < tabTile.length; j++) {
				if(i == 4 && j == 4) {
					tabTile[i][j] = CASTLE;
				}else {
					tabTile[i][j] = EMPTY;
				}
			}
		}
	}
	
	/**
	 * Méthode permettant d'associer une Tile à une position donnée
	 * @param tile . La tile que l'on souhaite ajouter 
	 * @param row , un entier . La ligne sur laquelle on souhaite ajouter la Tile
	 * @param col , un entier . La colonne sur laquelle on souhaite ajouter la Tile
	 */
	private void associatTileToPosition(final Tile tile, final int row, final int col) {	
		tabTile[row][col] = tile;
	}
	
	
	/**
	 * QUESTION CTT :
	 * Cette méthode possède une CTT en O(1). 
	 * 
	 * On fait appel à la méthode checkIfPositionIsFree() qui est en O(1) car elle ne fait que d'acccèder 
	 * à un élément sur base de sa position et vérifier une égalité deux fois O(1 + 1) = O(1)
	 * 
	 * et checkOneSideTerritory() qui est en O(1) car elle n'a que des conditions et des appels à des méthodes en O(1), toutes les méthodes qui composent checkOneSideTerritory() 
	 * ne sont que des contions et comparaisons d'éléments récupéré sur base de leurs indices donc O(1)
	 * 
	 * Ensuite pour la méthode associatTileToPosition() elle est elle aussi en O(1) car elle affecte simplement une valeur
	 * on fait appel à la méthode 2 fois donc O(1 + 1) que l'on peut simplifier en O(1). 
	 * 
	 * Les méthodes getTerritory() et getCrownsCount() sont elles aussi en O(1) car elles return juste une valeur. 
	 * 
	 * Ce qui nous donne donc au final une méthode putDomino() en O(1) car on a O(1 + 1 ... + 1) que l'on peut simplifier en O(1). 
	 * 
	 * 
	 * 
	 * Méthode permettant d'associer un Domino dans le Kingdom tout en vérifiant si la position est valide
	 * @param current le domino qie l'on veut placer 
	 * @param coordFirst les coordonnées de la première Tile qui compose le Domino
	 * @param coordSecond les coordonnées de la seconde Tile qui compose le Domino
	 * @return true si le Domino a pu être posé, sinon false. 
	 */
	public boolean putDomino(final Domino current, final int[] coordFirst, final int[]coordSecond){
		if(checkIfCoordIsValid(coordFirst) && checkIfCoordIsValid(coordSecond)) {
			if(checkIfPositionIsFree(coordFirst, coordSecond)) {
				if(checkOneSideTerritory(current.getTerritory(0), coordFirst, current.getTerritory(1), coordSecond)) {
					associatTileToPosition(new Tile(current.getTerritory(0), current.getCrownsCount(0)), coordFirst[0], coordFirst[1]);
					associatTileToPosition(new Tile(current.getTerritory(1), current.getCrownsCount(1)), coordSecond[0], coordSecond[1]);
					return true;
				}	
			}	
		}
		return false;
	}
	
	private boolean checkIfCoordIsValid(final int[] coord) {
		if(coord[0] < 0 || coord[0] > 8) {
			return false;
		}
		if(coord[1] < 0 || coord[1] > 8) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Méhtode permettant de connaître le nombre de couronne présente sur une Tile à une position donnée dans le royaume 
	 * @param row , un entier . La ligne sur laquelle on souhaite chercher la Tile contenant le nombre de couronne
	 * @param col , un entier La colonne sur laquelle on souhaite chercher la Tile contenant le nombre de couronne
	 * @return un entier . Le nombre de couronne présente à la position donnée 
	 */
	public int getCrownsCountInPosition(final int row, final int col) {
			return tabTile[row][col].getCrownsCount();
	}
	
	/**
	 * Méhtode permettant de connaître le territoire présent sur une Tile à une position donnée dans le royaume 
	 * @param row , un entier . La ligne sur laquelle on souhaite chercher la Tile contenant le territoire
	 * @param col , un entier La colonne sur laquelle on souhaite chercher la Tile contenant le territoire
	 * @return un Terrain . Le Terrain présent à la position donnée
	 */
	public Terrain getTerritoryInPoisition(final int row, final int col) {
			return tabTile[row][col].getTerritory();
	}
	
	/**
	 * Méthode permettant de savoir si la position à laquelle on veut placer notre domino n'est pas déjà prise par un autre(check si la case est NULL)
	 * @param coordFirst un tableau qui contient les coordonnées de la première Tile
	 * @param coordSecond un tableau qui contient les coordonnées de la seconde Tile
	 * @return un boolean, true si TOUTES les coordonnées sont libres, sinon false
	 */
	private boolean checkIfPositionIsFree(final int[] coordFirst, final int[] coordSecond){
		return this.tabTile[coordFirst[0]][coordFirst[1]] == EMPTY && this.tabTile[coordSecond[0]][coordSecond[1]] == EMPTY;
	}
	
	
	/**
	 * Méthode permettant de savoir si mon domino est posable 
	 * @param territoryOne le territoire de la première Tile
	 * @param coordFirst les coordonées de ma premières Tile
	 * @param territoryTwo le territoire de la seconde Tile
	 * @param coordSecond les coordonées de la seconde Tile
	 * @return true si le domino peut ête posé, sinon false
	 */
	private boolean checkOneSideTerritory(final Terrain territoryOne, final int[] coordFirst, final Terrain territoryTwo, final int[]coordSecond){
		if(checkIfCastel(coordFirst, coordSecond)) {
			return true;
		}
		
		if(checkFirstTile(territoryOne, coordFirst)) {
			return true;
		}
		
		if(checkSecondTile(territoryTwo, coordSecond)) {
			return true;
		}
				
		return false;
	}
	
	/**
	 * Méthode permettant de vérifier si une des cases à coté de d'un domino correspond à un château 
	 * @param coordFirst un tableau contenant les coordonnées de la première Tile
	 * @param coordSecond coordFirst un tableau contenant les coordonnées de la seconde Tile
	 * @return true si une des cases à côté est un château, sinon false
	 */
	private boolean checkIfCastel(final int[] coordFirst, final int[]coordSecond) {
		return verify(coordFirst) || verify(coordSecond);
	}
	
	/**
	 * Méthode permettant de vérifier si il y a un chateau à côté d'une Til
	 * @param coord un tableau d'entier contenant les coordonnées de ma Tile
	 * @return true si un chateau se trouve à côté, sinon false
	 */
	private boolean verify(int[] coord) {
		if(coord[0]-1 > 0) {
			if(tabTile[coord[0]-1][coord[1]].getTerritory() == Terrain.CASTLE) {
				return true; 
			}
		}
		
		if(coord[0]+1 < 8) {
			if(tabTile[coord[0]+1][coord[1]].getTerritory() == Terrain.CASTLE) {
				
				return true;
			}
		}
		
		if(coord[1]-1 > 0) {
			if(tabTile[coord[0]][coord[1]-1].getTerritory() == Terrain.CASTLE) {
				return true;
			}
		}
		
		if(coord[1]+1 <8) {
			if(tabTile[coord[0]][coord[1]+1].getTerritory() == Terrain.CASTLE) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Méthode permettant de vérifier si une des cases à coté de ma Tile 1 correspond à un territoire identique 
	 * @param Terrain territoryOne un terrain qui est celui de la Tile 1
	 * @param coordFirst un tableau contenant les coordonnées de la première Tile
	 * @return true si une des cases à côté de ma Tile à un territoire identique, sinon false
	 */
	private boolean checkFirstTile(final Terrain territoryOne, final int[] coordFirst) {		
		return checkIfSameTerritoryArround(territoryOne, coordFirst);
	}
	
	/**
	 * Méthode permettant de vérifier si une des cases à coté de ma Tile 2 correspond à un territoire identique 
	 * @param Terrain territoryTwo un terrain qui est celui de la Tile 2
	 * @param coordSecond un tableau contenant les coordonnées de la seconde Tile
	 * @return true si une des cases à côté de ma Tile à un territoire identique, sinon false
	 */
	private boolean checkSecondTile(final Terrain territoryTwo, final int[]coordSecond) {
		return checkIfSameTerritoryArround(territoryTwo, coordSecond);
	}
	
	/**
	 * Métode permettant de savoir si un terrain identique se trouve à côté d'une Tile 
	 * @param territory le territoire duquel on veut vérifier la présence
	 * @param coord un tableau d'entier contenant les coordonnées de la Tile
	 * @return true si un territoire de même type se trouve à côté, sinon false
	 */
	private boolean checkIfSameTerritoryArround(final Terrain territory, final int[]coord) {	
		if(coord[0]-1 > 0) {
			if(tabTile[coord[0]-1][coord[1]].getTerritory() == territory) {		
				return true;
			} 
		}
		
		if(coord[0]+1 < 8) {
			if(tabTile[coord[0]+1][coord[1]].getTerritory() == territory) {
				return true;
			}
		}
		
		if(coord[1]-1 > 0) {
			if(tabTile[coord[0]][coord[1]-1].getTerritory() == territory) {
				return true;
			}
		}

		if(coord[1]+1 <8) {
			if(tabTile[coord[0]][coord[1]+1].getTerritory() == territory) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Méthode permettant de remettre le Kingdom à l'état de base 
	 */
	public void resetKingdom() {
		this.createKingdom();
	}
}
