package kingdomino.domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * ITERATION 1
 * Interface : pour l'Interface j'ai utilisé Map parce qu'elle permet d'associer des clés à des valeurs,
 * dans mon cas le Domino sont les clés et les Joueurs les valeurs. Grâce à Map je vais pouvoir utiliser 
 * la méhtode entrySet() pour parcourir mes associations de clé/valeur avec une CTT constante. 
 * 
 * Classe concrète : pour la classe concrète j'ai utilisé une LinkedHashMap car elle me permet de garder les élements dans 
 * l'ordre de leur insertion, mes dominos peuvent donc rester trié par ordre d'ID. La méthode que je vais principalement 
 * utiliser est put() avec une CTT de O(1) et je pourrais aussi chercher un élément sur base de sa clé avec une CTT de O(1). 
 * 
 * @author Mahy François
 */


/**
 * ITERATION 2 : 
 * Interface : pour l'Interface j'ai utilisé Map parce qu'elle permet d'associer des clés et des valeurs,
 * dans mon cas les Domino sont les clés et les Player les valeurs. Je vais avoir besoin d'accéder à un 
 * élément précis dans ma map sur base de la clé qui lui est associée. Les méthodes put() et remove() vont 
 * me permettre d'ajouter ou de supprimer un combo Domino/Joueur en une fois. 
 * 
 * 
 * 
 * Classe concrète : pour la classe concrète je vais utiliser une LinkedHashMap pour ma CurrentDrawLine
 * car j'utilise la méthode put() avec une CTT de O(1) et remove avec une CTT de O(1). Je souhaite également garder l'ordre d'insertion des joueurs.  
 * 
 * 
 * Classe concrète : pour la classe  concrète je vais utiliser une TreeMap pour ma NextDrawLine car je souhaite trier 
 * mes dominos sur base de leurs ID et car je vais principalement utilsier get() avec CTT de O(log n) , remove() avec une CTT de O(log n) 
 * et put() avec une CTT de O(log n)
 * 
 * @author Mahy François
 *
 */
public class DrawLine{

		private Map<Domino, Player> mapDominoPlayer;
		private List<Domino> dominos;
		private List<Player> players;
		private Player currentPlayer;
		private int indexNextDomi = 0;
		
		/**
		 * Constructeur pour la current DrawLine
		 * @param dominos la liste des Domino qui vont constituer la DrawLine
		 * @param players la liste des Player qui vont constituer la DrawLine
		 */
		public DrawLine(final List<Domino> dominos, final List<Player> players) {
			this.mapDominoPlayer = new LinkedHashMap<Domino, Player>();
			this.dominos = new ArrayList<Domino>(dominos);
			this.players = new ArrayList<Player>(players);
			this.currentPlayer = getPlayerForDomino();
			setDominoToPlayer(this.dominos, this.players);
		}
		
		/**
		 * Constructeur pour la next DrawLine
		 * @param dominos la liste des Domino qui vont constiuer la nextDrawLine
		 */
		public DrawLine(final List<Domino> dominos) {
			this.mapDominoPlayer = new TreeMap<Domino, Player>();
			this.dominos = dominos;
			createNextDrawLine();
		}
		
		public Player getCurrentPlayers() {
			return currentPlayer;
		}
		
		public void setCurrentPlayers() {
			this.currentPlayer = getPlayerForDomino();
		}
		
		public List<Domino> getDominos(){
			return this.dominos;
		}

		public List<Player> getPlayers(){
			return this.players;
		}
				
		public Map<Domino, Player> getMapDominoPlayer(){
			return this.mapDominoPlayer;
		}
		
		public Iterator<Map.Entry<Domino, Player>> getIterator() {
			return this.mapDominoPlayer.entrySet().iterator();
		}
		
		public int getIndexNextDomi() {
			return this.indexNextDomi;
		}
		
		public void setIndexNextDomi(final int index) {
			this.indexNextDomi = index;
		}
		
		
		/**
		 * Méthode permettant de récupérer le domino de la NextLine
		 * @return
		 */
		public Domino getDominoNextLine() {
			return this.dominos.get(indexNextDomi);
		}
		
		/**
		 * Méthode permettant de remettre l'index du joueur suivant à une position libre dans la NextDrawLine
		 */
		public void resetIndexNextDomi() {
			this.indexNextDomi = 0;
			
			while(!mapDominoPlayer.get(dominos.get(indexNextDomi)).getName().equals("UNLINKED")){
				this.indexNextDomi += 1;
				if(this.indexNextDomi == 3) {
					return;
				}
			}
		}
		
		/**
		 * Méthode permettant de donner l'indice du domino selectionné dans le NextDrawLine
		 */
		public void addPositionDominoNextLine() {
			if(indexNextDomi == mapDominoPlayer.size()-1) {
				indexNextDomi = 0;
			}else {
				indexNextDomi += 1;
			}
		}
		
		/**
		 * Méthode permettant de connaître le premier Domino de la Map
		 * @return un Domino qui est le premier de la Map 
		 */
		public Domino getFirstDomino() {
			return dominos.get(0);
		}
		
		/**
		 * Méthode peremettant de créer une NextDrawLine
		 */
		private void createNextDrawLine(){
			for (int i = 0; i < this.dominos.size(); i++) {
				mapDominoPlayer.put(this.dominos.get(i), new Player("UNLINKED", "#ffffff"));
			}
		}
		
		
		/**
		 * Méthode permettant d'attribuer des dominos à des joueurs
		 * @param dominos . La list des dominos 
		 * @param players La list des joueurs à associer aux dominos
		 */
		private void setDominoToPlayer(final List<Domino> dominos, final List<Player> players) {
			Collections.shuffle(players);
			for (int i = 0; i < players.size(); i++) {
				mapDominoPlayer.put(dominos.get(i), players.get(i));
			}
			
			if(players.size() == 2) {
				final List<Player> players2 = new ArrayList<Player>(players);
				Collections.shuffle(players2);
				
				for (int i = 0; i < players.size(); i++) {
					mapDominoPlayer.put(dominos.get(i+2), players2.get(i));
				}
			}
		}
		
		/**
		 * Méthode permettant de connaître un joueur sur base du premier Domino
		 * @return Un joueur qui a comme valeur associée le domino
		 */
		public Player getPlayerForDomino(){
			Player playerFind = null;
			
			for(final Map.Entry<Domino, Player> entry : mapDominoPlayer.entrySet()) {
				if(dominos.get(0).compareTo(entry.getKey()) == 0){
					playerFind = entry.getValue();
				}
			}
			return playerFind;
		}
		
		
		/**
		 * Méthode peremttant de supprimer le premier joueur et le premier domino de la drawLine
		 */
		public void deletePlayerWhoComesToLay() {
			mapDominoPlayer.remove(dominos.get(0));
			dominos.remove(0);
		}
		
		/**
		 * Méhtode permettant d'associer un joueur à une domino dans la NextDominoLine
		 * @param domino le domino sur lequel on veut ajouter le Player dans la nextDrawLine
		 * @param current  le joueur que l'on veut associer au Domino dans la nextDrawLine
		 */
		public void associatePlayerToNextLine(final Domino domino, final Player current) {
			mapDominoPlayer.remove(domino);
			mapDominoPlayer.put(domino, current);
		}
		
		/**
		 * Méthode peremttant de vérifier si un joueur suivant existe
		 * @return true si il reste encore des gens qui peuvent jouer dans la DrawLine, sinon false
		 */
		public boolean checkIfNextPlayerExist() {
			return dominos.size() > 0;
		}
		
		/**
		 * Méthode permettant de vérifier si une position est libre dans la NextDrawLine
		 * @return true si la postion est libre, sinon false. 
		 */
		public boolean checkIfCanSelect() {			
			return mapDominoPlayer.get(dominos.get(indexNextDomi)).getName().equals("UNLINKED");
		}
}
