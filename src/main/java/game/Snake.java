package game;

public class Snake {

    private final int ALL_DOTS;
    private final int DOT_SIZE;

    private int dots;
    private final int[] x;
    private final int[] y;

    public Snake(int boardWidth, int boardHeight, int dotSize) {
        this.ALL_DOTS = (boardWidth * boardHeight) / (dotSize * dotSize);
        this.DOT_SIZE = dotSize;
        this.x = new int[ALL_DOTS];
        this.y = new int[ALL_DOTS];
        initSnake();
    }

    private void initSnake() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 200 - z * DOT_SIZE;
            y[z] = 200;
        }
    }

    public void move(boolean leftDirection, boolean rightDirection, boolean upDirection, boolean downDirection) {
        for (int z = dots - 1; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        } else if (rightDirection) {
            x[0] += DOT_SIZE;
        } else if (upDirection) {
            y[0] -= DOT_SIZE;
        } else if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    public boolean checkCollision(int boardWidth, int boardHeight) {
        for (int z = 4; z < dots; z++) {
            if (x[0] == x[z] && y[0] == y[z]) {
                return true;
            }
        }

        if (x[0] < 0 || x[0] >= boardWidth || y[0] < 0 || y[0] >= boardHeight) {
            return true;
        }

        return false;
    }

    public boolean checkAppleCollision(int appleX, int appleY) {
        return x[0] == appleX && y[0] == appleY;
    }

    public void grow() {
        dots++;
    }

    public int getDots() {
        return dots;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }
}
