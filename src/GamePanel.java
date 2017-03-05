import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private Image bg = new ImageIcon("src/images/Background.png").getImage();
	private Image coin;
	
	private boolean gameEnded = false;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(bg, 0, 0, null);
		if(gameEnded) g.drawImage(coin, 120, 85, null);
		
	}
	
	public void setCoin(ImageIcon coin) {
		this.coin = coin.getImage();
		gameEnded = true;
	}
	
	public void updatePanel(int counter) {
		if (counter == 0) {
			gameEnded = false;
		}
		repaint();
	}
}
