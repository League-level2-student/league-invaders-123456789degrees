import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	public LeagueInvaders() {
		frame = new JFrame();
	}
	public static void main (String[] args) {
		leagueGame = new LeagueInvaders();
	}
	public void setup() {
		frame.setSize(HEIGHT, WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
