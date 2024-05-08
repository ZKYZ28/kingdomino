package kingdomino.domains;

public class Coordinate {
	private int row;
	private int col;
	private int direction;
	
	/**
	 *Constructeur pour les coordonnées d'une Tile
	 */
	public Coordinate() {
		this.row = 0;
		this.col = 0;
		this.direction = 0;
	}
	
	/**
	 * Utile pour les TESTS UNITAIRES
	 * @param dire la direciton
	 */
	public void setDirection(final int dire) {
		this.direction = dire;
	}
	
	/**
	 * Utile pour les TESTS UNITAIRES
	 * @param dire la ligne
	 */
	public void setRow(final int row) {
		this.row = row;
	}
	
	/**
	 * Utile pour les TESTS UNITAIRES
	 * @param dire la colonne
	 */
	public void setCol(final int col) {
		this.col = col;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public int getRow() {
		return this.row;
	}
	
	
	public int getCol() {
		return this.col;
	}
	
	/**
	 * Guetteur permettant de récupérer la position de la ligne décallée de la seconde Tile
	 * @return un entier qui est la position de la ligne de la seconde Tile
	 */
	public int getRowSecond() {
		int value = this.row;
		switch(this.direction) {
		case 1:
			value -= 1;
			break;
	
		case 3:
			value += 1;
			break;
			
		default :
			value = this.row;
			
		}	
		return value;
	}

	/**
	 * Guetteur permettant de récupérer la position de la colonne décallée de la seconde Tile
	 * @return un entier qui est la position de la colonne de la seconde Tile
	 */
	public int getColSecond() {
		int value = this.col;
		switch(this.direction) {
		case 0:
			value += 1;
			break;
			
		case 2:
			value -= 1;
			break;
		default :
			value = this.col;

		}	
		return value;
	}
	
	/**
	 * Méthode permettant de déplacer un domino de manière horizontale ou verticale 
	 * dans le plateau de jeu. 
	 * @param dr un entier . La direction horizontale
	 * @param dc un entier . La direction verticale
	 */
	public void translate(final int dr, final int dc) {
		this.row += dr;
		this.col += dc;	
		
		if(!canMoove()) {
			this.row -= dr;
			this.col -= dc;	
		}
	}
	
	/**
	 * Méthode permettant de pivoter un domino à 90° dans le sens anti-horloger dans
	 * le plateau de jeu
	 */
	public void rotate() {	
		this.direction = this.direction +1 == 4 ? 0 : this.direction +1;
			
		if(!canMoove()) {
			this.direction = this.direction -1 == -1 ? 3 : this.direction -1;
		}
	}
	
	/**
	 * Méthode permettant de déterminer si un joueur pour translanter ou tourner
	 * @return true si le joueur peut translater ou tourner, sinon false
	 */
	private boolean canMoove() {		
		final int colFirst = this.col;
		final int rowFirst = this.row;
		final int colSecond = this.getColSecond();
		final int rowSecond = this.getRowSecond();
		
		if(((colSecond < 0 || colSecond > 8) || (rowSecond < 0 || rowSecond > 8)) || ((colFirst < 0 || colFirst > 8) || (rowFirst < 0 || rowFirst > 8))) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Méthode permettant de reset les coordonnées d'un donmino en les passant toutes à 0
	 */
	public void resetCoord() {
		this.row = 0;
		this.col = 0;
		this.direction = 0;
	}
}
