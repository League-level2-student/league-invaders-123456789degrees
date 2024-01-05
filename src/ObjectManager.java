import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
public class ObjectManager implements ActionListener{
	int score = 0;
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	public int getScore() {
		return score;
	}
	public ObjectManager (Rocketship rocket) {
		this.rocket = rocket;
	}
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	public void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	public void update() {
		for (Alien a  : aliens) {
			a.update();
			if (a.height > LeagueInvaders.HEIGHT) {
				a.isActive = false;
			}
		}
		for (Projectile p  : projectiles) {
			p.update();
			if (p.height < 0) {
				p.isActive = false;
			}
		}
		rocket.update();
		checkCollision();
		purgeObjects();
	}
	public void draw(Graphics g) {
		rocket.draw(g);
		for (Alien a : aliens) {
			a.draw(g);
		}
		rocket.draw(g);
		for (Projectile p : projectiles) {
			p.draw(g);
		}
	}
	public void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (!aliens.get(i).isActive) {
				aliens.remove(i);
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (!projectiles.get(i).isActive) {
				projectiles.remove(i);			
			}
		}
	}
	public void checkCollision() {
		for (Alien a : aliens) {
			if (rocket.collisionBox.intersects(a.collisionBox)) {
				rocket.isActive = false;
				a.isActive = false;
			}
			for (Projectile p : projectiles) {
				if (p.collisionBox.intersects(a.collisionBox)) {
					p.isActive = false;
					a.isActive = false;
					score++;
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
}