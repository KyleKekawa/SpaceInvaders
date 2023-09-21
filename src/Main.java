import java.awt.Color;
import java.util.ArrayList;

/*
 * game uses ArrayLists and Inheritance.
 */

public class Main {

	public static void main(String[] args) {

		EZSound Explosion = EZ.addSound("Explosion.wav");
		// EZSound Continue = EZ.addSound("Continue_.wav");
		EZSound Hurt = EZ.addSound("hurt.wav");
		EZSound PewPew = EZ.addSound("Pew Pew.wav");
		EZSound Theme = EZ.addSound("Starfox Theme.wav");
		EZSound DontGiveUp = EZ.addSound("Wait.wav");
		Theme.loop();

		final int worldWidth = 1024;
		final int worldHeight = 768;
		// window size

		EZ.initialize(worldWidth, worldHeight);
		EZ.addImage("background.png", 512, 768 / 2);
		// creates world

		Player player = new Player(worldWidth / 2, worldHeight - 38);
		// creates player at the bottom middle of the screen

		boolean gameRunning = true;
		int gameLevel = 1;
		// needed for level management

		ArrayList<Mob> mobList = new ArrayList<>();
		ArrayList<Bullet> bulletList = new ArrayList<>();

		int health = 10;
		int score = 0;
		int bulletDamage = 100;
		int fontSize = 40;
		Color c = new Color(50, 50, 50);

		EZText lifeText = EZ.addText(200, 100, "Lives: " + health, c, fontSize);
		EZText scoreText = EZ.addText(200, 150, "Score: " + score, c, fontSize);

		while (gameRunning) {
			for (int i = 0; i < gameLevel + 5; i++) {
				mobList.add(new Mob());
				mobList.add(new Mob2());
			}
			/*
			 * creates mobs before each level is ran. for each level add a mob.
			 */

			while (!mobList.isEmpty()) {

				player.move();
				// moves the player

				for (int i = 0; i < mobList.size(); i++) {
					mobList.get(i).fall();
					if (mobList.get(i).picture.getYCenter() > worldHeight) {
						mobList.get(i).die();
						health -= mobList.remove(i).damage;
						if (Hurt.isPlaying() == false) {
							Hurt.play();
						}
					}
				}
				/*
				 * makes the mobs move towards the player. if the mobs get to the bottom, the
				 * mobs die and the player loses health.
				 */

				if (EZInteraction.wasKeyPressed('l')) {
					Bullet bullet = new Bullet(player.picture.getXCenter(), player.picture.getYCenter());
					bulletList.add(bullet);
					if (PewPew.isPlaying() == false) {
						PewPew.play();
					}
				}

				for (int i = 0; i < bulletList.size(); i++) {
					bulletList.get(i).fire();
					if (bulletList.get(i).picture.getYCenter() <= 0) {
						bulletList.remove(i).destroy();
					}
				}
				/*
				 * if the player presses L, bullets will fire from the players position and
				 * travel up. if the bullet gets to the top of the screen remove the bullet from
				 * the list.
				 */

				// mobList.get(i)
				// bulletList.get(j)
				try {
					for (int i = 0; i < mobList.size(); i++) {
						for (int j = 0; j < bulletList.size(); j++) {
							if (mobList.get(i).picture.isPointInElement(bulletList.get(j).picture.getXCenter(),
									bulletList.get(j).picture.getYCenter())) {
								bulletList.remove(j).destroy();
								j++;
								mobList.get(i).mobHealth -= bulletDamage;
								if (mobList.get(i).mobHealth <= 0) {
									mobList.remove(i).die();
									score++;
									Explosion.play();
								}
							}
						}
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println("out of bounds");
				}
				// System.out.println(bulletList.size());
				// ****************************************************
				/*
				 * if the bullet touches the mob, the mob will lose health. if the mobs health
				 * reaches 0, the mob will die.
				 */

				// if mobList is empty then the current level is no longer running and move on
				// the the next level.

				if (health <= 0) {
					gameRunning = false;
					EZ.addImage("Game Over.jpg", 512, 768 / 2);
					Theme.pause();
					DontGiveUp.play();
					Theme.getMicroSecondPosition();
					break;
				}
				// if the player health gets to 0, exit the game

				lifeText.setMsg("Health: " + health);
				scoreText.setMsg("Score: " + score);
				// refreshes the player health and score

				EZ.refreshScreen();
			}
			gameLevel++;
		}

	}
}
