
public class Player {

	EZImage picture;
	int playerSpeed = 10;

	Player(int x, int y) {
		picture = EZ.addImage("player.png", x, y);
	}

	void move() {
		if (EZInteraction.isKeyDown('a')) {
			picture.translateBy(-playerSpeed, 0);
		}
		if (EZInteraction.isKeyDown('d')) {
			picture.translateBy(playerSpeed, 0);
		}
	}

}