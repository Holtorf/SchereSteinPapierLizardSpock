import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GameView extends JFrame {
	
	private String p1 = "Player";
	private String p2 = "Player";
	private int s1,s2 = 0;
	
	private JPanel score = new JPanel();
	private JPanel leftChoices = new JPanel();
	private JPanel rightChoices = new JPanel();
	
	private JLabel player1 = new JLabel(p1);
	private JLabel player2 = new JLabel(p2);
	private JLabel scoreLabel = new JLabel(s1 + " : " + s2);
	
	private JMenuBar bar = new JMenuBar();
	private JMenu menu = new JMenu("Menu");
	private JMenu rename = new JMenu("Rename");
	private JMenuItem refresh = new JMenuItem("Reset");
	private JMenuItem toggleMulti = new JMenuItem("Toggle Multiplayer");
	private JMenuItem p1Name = new JMenuItem(p1);
	private JMenuItem p2Name = new JMenuItem(p2);
	
	private GamePanel gPanel;
	
	public GameView() {
		
		setJMenuBar(bar);
		bar.add(menu);
		bar.add(rename);
		menu.add(refresh);
		menu.add(toggleMulti);
		rename.add(p1Name);
		rename.add(p2Name);
		
		JLabel scherL = new JLabel(new ImageIcon("src/images/schereL.png"));
		JLabel steinL = new JLabel(new ImageIcon("src/images/steinL.png"));
		JLabel papieL = new JLabel(new ImageIcon("src/images/papierL.png"));
		JLabel echseL = new JLabel(new ImageIcon("src/images/echseL.png"));
		JLabel spockL = new JLabel(new ImageIcon("src/images/spockL.png"));
		
		JLabel scherR = new JLabel(new ImageIcon("src/images/schereR.png"));
		JLabel steinR = new JLabel(new ImageIcon("src/images/steinR.png"));
		JLabel papieR = new JLabel(new ImageIcon("src/images/papierR.png"));
		JLabel echseR = new JLabel(new ImageIcon("src/images/echseR.png"));
		JLabel spockR = new JLabel(new ImageIcon("src/images/spockR.png"));
		
		gPanel = new GamePanel();
		
		leftChoices.setLayout(new GridLayout(5, 1));
		rightChoices.setLayout(new GridLayout(5, 1));
		
		score.add(player1);
		score.add(scoreLabel);
		score.add(player2);
		
		leftChoices.add(scherL);
		leftChoices.add(steinL);
		leftChoices.add(papieL);
		leftChoices.add(echseL);
		leftChoices.add(spockL);
		
		rightChoices.add(scherR);
		rightChoices.add(steinR);
		rightChoices.add(papieR);
		rightChoices.add(echseR);
		rightChoices.add(spockR);
		
		add(score, BorderLayout.NORTH);
		add(gPanel, BorderLayout.CENTER);
		add(leftChoices, BorderLayout.WEST);
		add(rightChoices, BorderLayout.EAST);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(550,480);
		setVisible(true);
	}
	
	public JMenuItem getToMenu() {
		return toggleMulti;
	}
	
	public JMenuItem getRefresh() {
		return refresh;
	}
	
	public JMenuItem getP1Name() {
		return p1Name;
	}
	
	public JMenuItem getP2Name() {
		return p2Name;
	}
	
	public int getS1() {
		return s1;
	}
	
	public int getS2() {
		return s2;
	}
	
	public GamePanel getPanel() {
		return gPanel;
	}
	
	public void setP1(String p1) {
		this.p1 = p1;
		this.player1.setText(this.p1);
		this.p1Name.setText(p1);
	}
	
	public void setP2(String p2) {
		this.p2 = p2;
		this.player2.setText(this.p2);
		this.p2Name.setText(p2);
	}
	
	public void update(int s1, int s2) {
		this.s1 = s1;
		this.s2 = s2;
		this.scoreLabel.setText(this.s1 + " : " + this.s2);
	}
}
