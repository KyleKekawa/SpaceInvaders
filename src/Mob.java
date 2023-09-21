import java.awt.Color;
import java.util.Random;

public class Mob {

	int mobHealth;
	int damage;
	int speed;
	EZImage picture;
	Random rg;

	Mob() {
		mobHealth = 100;
		damage = 1;
		rg = new Random();
		speed = 2;
		picture = EZ.addImage("mob.png", rg.nextInt(1024), 50);
	}

	Mob(int width, int speed, int health, int damage, Color c) {
		mobHealth = health;
		rg = new Random();
		this.speed = speed;
		this.damage = damage;
		picture = EZ.addImage("mob2.png", rg.nextInt(1024), 50);
	}

	void fall() {
		picture.translateBy(0, speed);
	}

	void die() {
		EZ.removeEZElement(picture);
	}
}
