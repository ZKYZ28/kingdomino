package kingdomino.domains;

public class Tile {
	private final Terrain territory;
	private final int crownsCount;
	
	/**
	 * Constructeur d'une Tile
	 * @param territory un Terrain qui représente le terrain présent sur la Tile
	 * @param crownsCount un entier qui représente le nombre de couronne sur la Tile
	 */
	public Tile(final Terrain territory, final int crownsCount) {
		this.territory = territory == null ? Terrain.EMPTY : territory;
		this.crownsCount = crownsCount < 0 ? 0 : crownsCount;
	}
	
	public Terrain getTerritory(){
		return this.territory;
	}
	
	public int getCrownsCount(){
		return this.crownsCount;
	}
}
