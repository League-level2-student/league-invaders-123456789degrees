import javax.swing.JFrame;

public class LeagueInvaders {
	GamePanel panel; 
	JFrame frame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	public LeagueInvaders() {
		frame = new JFrame();
		panel = new GamePanel();
	}
	public static void main (String[] args) {
		LeagueInvaders leagueGame = new LeagueInvaders();
		leagueGame.setup();
	}
	public void setup() {
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(panel);
		frame.add(panel);
	}
}
