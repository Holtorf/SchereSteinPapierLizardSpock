import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GameController implements KeyListener {

	private GameView view;
	private Random ran; 
	private Timer t;
	
	private int showCoinCounter = 0;
	private int s1, s2;
	
	private boolean[] p1Choice = {false, false, false, false, false};
	private boolean[] p2Choice = {false, false, false, false, false};
	private boolean multi = false;
	private boolean gameEnded = false;
	private ImageIcon coins[] = {new ImageIcon("src/images/Coin-P1.gif"),  new ImageIcon("src/images/Coin-P2.gif")};
	
	public GameController() {
		this.view = new GameView();
		this.view.addKeyListener(this);
		
		this.view.getRefresh().addActionListener(listener -> resetGame());
		this.view.getToMenu().addActionListener(listener -> toggleMulti());
		this.view.getP1Name().addActionListener(listener -> renameP1());
		this.view.getP2Name().addActionListener(listener -> renameP2());
		
		this.ran = new Random();
		
		t = new Timer(10, listener -> update());
		t.start();
	}
	
	private void renameP1() {
		this.view.setP1(JOptionPane.showInputDialog("Enter Player Name: "));
	}
	
	private void renameP2() {
		this.view.setP2(JOptionPane.showInputDialog("Enter Player Name: "));
	}

	private void toggleMulti() {
		this.multi = !this.multi;
	}

	private void resetGame() {
		s1 = 0;
		s2 = 0;
		this.view.setP1("Player");
		this.view.setP2("Player");
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_Q && !(gameEnded)) {
			p1Choice[0] = true;
			match();
		}
		if(e.getKeyCode() == KeyEvent.VK_W && !(gameEnded)) {
			p1Choice[1] = true;
			match();
		}
		if(e.getKeyCode() == KeyEvent.VK_E && !(gameEnded)) {
			p1Choice[2] = true;
			match();
		}
		if(e.getKeyCode() == KeyEvent.VK_R && !(gameEnded)) {
			p1Choice[3] = true;
			match();
		}
		if(e.getKeyCode() == KeyEvent.VK_T && !(gameEnded)) {
			p1Choice[4] = true;
			match();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_J && multi && !(gameEnded)) {
			p2Choice[0] = true;
			match();
		}
		if(e.getKeyCode() == KeyEvent.VK_K && multi && !(gameEnded)) {
			p2Choice[1] = true;
			match();
		}
		if(e.getKeyCode() == KeyEvent.VK_L && multi && !(gameEnded)) {
			p2Choice[2] = true;
			match();
		}
		if(e.getKeyCode() == KeyEvent.VK_N && multi && !(gameEnded)) {
			p2Choice[3] = true;
			match();
		}
		if(e.getKeyCode() == KeyEvent.VK_M && multi && !(gameEnded)) {
			p2Choice[4] = true;
			match();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	private void match() {
		if(!multi) {
			int x = ran.nextInt(4);
			for (int i = 0; i < p2Choice.length; i++) {
				if (i == x) p2Choice[i] = true;
			}
		}
		
		if (p1Choice[0]) {
			if (p2Choice[1] || p2Choice[4]) lose();
			else if (p2Choice[2] || p2Choice[3]) win();
			else if (p2Choice[0]) JOptionPane.showMessageDialog(this.view, "Draw");
		} else if (p1Choice[1]) {
			if (p2Choice[0] || p2Choice[3]) win();
			else if(p2Choice[2] || p2Choice[4]) lose();
			else if (p2Choice[1]) JOptionPane.showMessageDialog(this.view, "Draw");
		} else if (p1Choice[2]) {
			if (p2Choice[1] || p2Choice[4]) win();
			else if (p2Choice[0] || p2Choice[3]) lose();
			else if (p2Choice[2]) JOptionPane.showMessageDialog(this.view, "Draw");
		} else if (p1Choice[3]) {
			if(p2Choice[2] || p2Choice[4]) win();
			else if(p2Choice[0] || p2Choice[1]) lose();
			else if (p2Choice[3]) JOptionPane.showMessageDialog(this.view, "Draw");
		} else if (p1Choice[4]) {
			if(p2Choice[0] || p2Choice[1]) win();
			else if(p2Choice[2] || p2Choice[3]) lose();
			else if (p2Choice[4])  JOptionPane.showMessageDialog(this.view, "Draw");
		}
	}

	private void win() {
		this.view.getPanel().setCoin(coins[0]);
		s1 = this.view.getS1() + 1;
		showCoinCounter = 300;
		gameEnded = true;
		resetChoices();
	}
	
	private void lose() {
		this.view.getPanel().setCoin(coins[1]);
		s2 = this.view.getS2() + 1;
		showCoinCounter = 300;
		gameEnded = true;
		resetChoices();
	}
	
	private void resetChoices() {
		for (int i = 0; i < p2Choice.length; i++) {
			p1Choice[i] = false;
			p2Choice[i] = false;
		}
	}
	
	private void update() {
		if (showCoinCounter > 0) showCoinCounter--;
		else if(showCoinCounter == 0) gameEnded = false;
		this.view.getPanel().updatePanel(showCoinCounter);
		this.view.update(s1, s2);
	}
}
