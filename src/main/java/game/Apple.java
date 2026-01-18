package game;

import java.util.Random;

public class Apple {

    private final int DOT_SIZE;
    private final int RAND_POS;
    private int appleX;
    private int appleY;

    public Apple(int dotSize, int randPos) {
        this.DOT_SIZE = dotSize;
        this.RAND_POS = randPos;
        locateApple();
    }

    public void locateApple() {
        Random random = new Random();
        appleX = random.nextInt(RAND_POS - 1) * DOT_SIZE;
        appleY = random.nextInt(RAND_POS - 1) * DOT_SIZE;
    }

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }
}
