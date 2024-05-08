package kingdomino.swing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;
import kingdomino.supervisers.*;

/**
 * Implémentation de la vue pour la fin de partie en Swing.
 */
public class SwingGameOverView extends SwingView implements GameOverView {
	private static final long serialVersionUID = 7019400806587102065L;
	private final ImageIcon background = new ImageIcon("resources/images/main_menu_background.jpg");
	
	private static final int COL_COUNT = 2;
	private static final int ROW_COUNT = 2;

	private final GameOverSuperviser superviser;

	private BorderLayout mainLayout = new BorderLayout();
	private final JLabel winnerLabel = new JLabel("Appuyez sur \u23ce pour revenir au menu principal",SwingConstants.CENTER);
	{
		winnerLabel.setForeground(Theme.PRIMARY_COLOR);
		winnerLabel.setFont(winnerLabel.getFont().deriveFont(winnerLabel.getFont().getSize2D()*2.0f));		
	}
	private JPanel scorePanel = new JPanel(new GridLayout(ROW_COUNT, COL_COUNT, 2, 2));
	{
		scorePanel.setBackground(Theme.PRIMARY_BACKGROUND_COLOR_ALPHA);
	}

	JPanel winnerPanel = new JPanel();
	{
		winnerPanel.setBackground(Theme.SECONDARY_BACKGROUND_COLOR_ALPHA);
	}
	
	/**
		Initialise une nouvelle vue swing avec un {@code name} et un {@code superviser}.
		Au moment de l'initialisation, cette vue se connecte au superviser avec l'appel {@code setView(this)}.
	 */
	public SwingGameOverView(String name, GameOverSuperviser superviser) {
		super(name);
		
		this.superviser = superviser;
		this.superviser.setView(this);
		
		this.setLayout(mainLayout);				
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		drawBackground(g);
	}

	private void drawBackground(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
	}
	
	@Override
	public void onEnter(String fromView) {
		this.superviser.onEnter(fromView);
	}
	
	@Override
	public void onKeyTyped(int keyCode) {
		if(KeyEvent.VK_ENTER == keyCode) {
			superviser.onGoToMainMenu();
		}
	}
	
	/**
	Débute un nouveau rendu de la vue.
	*/
	@Override
	public void startDraw() {
		this.removeAll();
		this.scorePanel.removeAll();
	}

	/**
	Ajoute un résultat à afficher. Ce résultat est composé du nom du joueur, 
	de sa couleur et du score obtenu pour cette partie.
	 */
	@Override
	public void addScore(String playerName, int score, String color) {
		this.scorePanel.add(new SwingScoreView(playerName, score, color));
	}

	/**
	Termine le rendu de la vue ce qui provoque l'affichage à l'écran.
	 */
	@Override
	public void endDraw() {
		add(scorePanel, BorderLayout.CENTER);

		winnerPanel.add(winnerLabel);
		add(winnerPanel, BorderLayout.PAGE_END);
	}

	/**
	Affiche un message contenant le vainqueur de la partie.
	*/
	@Override
	public void setWinner(String winnerMessage) {
		this.winnerLabel.setText(winnerMessage+" Appuyez sur \u23ce pour revenir au menu principal");		
	}
	

}
