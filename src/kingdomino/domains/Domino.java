package kingdomino.domains;

public class Domino implements Comparable<Domino>{
	final private int id;
	final private Tile part1;
	final private Tile part2;
	
	
	/**
	 * Constructeur pour un domino
	 * @param id un entier qui représente l'id du Domino
	 * @param part1 une Tile qui est la première qui compose le Domino
	 * @param part2 une Tile qui est la deuxième qui compose le Domino
	 */
	public Domino(final int id, final Tile part1, final Tile part2) {
		this.id = id;
		this.part1 = part1 == null ? new Tile(Terrain.EMPTY, 0) : part1 ;
		this.part2 = part2 == null ? new Tile(Terrain.EMPTY, 0) : part2 ;
	}
	
	
	public int getId(){
		return this.id;
	}
	
	/**
	 * Méthode permettant de récupérer le territoire d'une des deux tiles qui composent un domino sur base de sa position. 0 pour la première Tile et 1 pour la deuxième Tile. 
	 * @param int pos . En entier repésentant la position de la Tile dans le Domino
	 * @return le terrain corresondant à la Tile selectionné
	 */
	public Terrain getTerritory(final int pos){
		if(pos == 0) {
			return part1.getTerritory();
		}else{
			return part2.getTerritory();
		}
	}
	
	/**
	 * Méthode permettant de récupérer le nombre de couronne d'une des deux tiles qui composent un domino sur base de sa position. 0 pour la première Tile et 1 pour la deuxième Tile. 
	 * @param int pos . En entier repésentant la position de la Tile dans le Domino
	 * @return un entier qui est le nombre de couronne associé à la Tile
	 */
	public int getCrownsCount(final int pos){
		if(pos == 0) {
			return part1.getCrownsCount();
		}else {
			return part2.getCrownsCount();
		}
	}
	
	@Override
	public int compareTo(final Domino other){
		int value = 0;
		
		if(other == null) {
			return -1;
		} else if(this.id > other.getId()) {
			value = 1;
		}else if (this.id < other.getId()){
			value =  -1;
		}
		
		return value;
	}
}
