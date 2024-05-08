package kingdomino.domains;

public class Player implements Comparable<Player>{
	private final String name;
	private final String hexColor;
	final private Kingdom kingdom;
	private Score score;
	
	public static final String UNKNOW_PLAYER = "Joueur inconnu";
	public static final String UNKNOW_COLOR = "#ffffff";
	
	/**
	 * Constructeur du Player
	 * @param name un String qui est le nom du joueur
	 * @param hexColor une String qui la couleur du joueur représentée sur forme hexadécmal
	 */
	public Player(final String name, final String hexColor) {
		this.name = name == null || name.equals("") ? UNKNOW_PLAYER : name;
		this.hexColor = hexColor == null || hexColor.equals("") ? UNKNOW_COLOR : hexColor;
		this.kingdom = new Kingdom();
	}
	
	public String getHexColor() {
		return hexColor;
	}
	
	public Kingdom getKingdom() {
		return this.kingdom;
	}

	public String getName() {
		return name;
	}	
	
	public int getScore() {
		return this.score.getScore();
	}

	public void setScore(Score score) {
		this.score = score;
	}
	
	@Override
	public int compareTo(Player other) {
		int value = 0;
		if(other == null) {
			return -1;
		} else if (this.getScore() > other.getScore()) {
			value = 1;
		}else if (this.getScore() < other.getScore()) {
			value = -1;
		}
		return value;
	}	
}
