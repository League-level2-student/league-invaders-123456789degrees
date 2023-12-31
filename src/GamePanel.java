import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
    Font regularFont;
    Timer frameDraw;
    Rocketship rocket = new Rocketship(250, 700, 50, 50);
    ObjectManager manager = new ObjectManager(rocket);
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;	
    Timer alienSpawn;
    public GamePanel() {
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	regularFont = new Font("Arial", Font.PLAIN, 20);
    	frameDraw = new Timer(1000 / 60, this);
    	frameDraw.start();
    	if (needImage) {
    	    loadImage ("space.png");
    	}
    }
	@Override
	public void paintComponent(Graphics g){
		if (currentState == MENU) {
			drawMenuState(g);
		}
		else if (currentState == GAME) {
			drawGameState(g);
		}
		else if (currentState == END) {
			drawEndState(g);
		}
	}
	public void updateMenuState() { 

	}
	public void updateGameState() { 
		manager.update();
		if (!rocket.isActive) {
			currentState = END;
		}
	}
	public void updateEndState()  { 
		
	}
	public void drawMenuState(Graphics g) {  
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 22, 100);
		g.setFont(regularFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to start", 150, 360);
		g.setFont(regularFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press SPACE for instructions", 120, 540);
	}
	public void drawGameState(Graphics g) { 
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		g.setFont(regularFont);
		g.setColor(Color.RED);
		g.drawString("Score: " + manager.getScore(), 10, 25);
		manager.draw(g);
	}
	public void drawEndState(Graphics g)  { 
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 100, 100);
		g.setFont(regularFont);
		g.drawString("You killed " + manager.getScore() + " enemies.", 150, 350);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		    
		}
		else if(currentState == GAME){
		    updateGameState();
		}
		else if(currentState == END){
		    updateEndState();
		}
		System.out.println("action");
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
				rocket = new Rocketship(250, 700, 50, 50);
				manager = new ObjectManager(rocket);
			}
			else if (currentState == MENU) {
				startGame();
				currentState++;
			}
			else {
				alienSpawn.stop();
				currentState++;
			}
		}
		if (currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				if (rocket.y > 0) {
				    rocket.up();
				}
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				if (rocket.y < 750) {
					rocket.down();
				}
			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				if (rocket.x > 0) {
					rocket.left();
				}
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				if (rocket.x < 450) {
					rocket.right();
				}
			}
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				manager.addProjectile(rocket.getProjectile());
			}
		}
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void startGame() {
		alienSpawn = new Timer(1000, manager);
		alienSpawn.start();
	}
}