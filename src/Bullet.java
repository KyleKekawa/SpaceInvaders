import java.awt.Color;

public class Bullet {

	EZRectangle picture;

	Bullet(int x, int y) {
		picture = EZ.addRectangle(x, y, 7, 15, Color.red, true);
		// fire();
	}

	void fire() {
		picture.translateBy(0, -30);
	}

	protected void destroy() {
		EZ.removeEZElement(picture);
	}

}
