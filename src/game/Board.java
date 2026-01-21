package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 400;
    private final int DOT_SIZE = 10;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private Snake snake;
    private Apple apple;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    private int score = 0;

    private Timer timer;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        snake = new Snake(B_WIDTH, B_HEIGHT, DOT_SIZE);
        apple = new Apple(DOT_SIZE, RAND_POS);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            drawObjects(g);
        } else {
            showGameOver(g);
        }
    }

    private void drawObjects(Graphics g) {
        // Draw apple as green square with border
        g.setColor(Color.green);
        g.fillRect(apple.getAppleX() + 1, apple.getAppleY() + 1, DOT_SIZE - 2, DOT_SIZE - 2);
        g.setColor(Color.darkGray);
        g.drawRect(apple.getAppleX(), apple.getAppleY(), DOT_SIZE - 1, DOT_SIZE - 1);

        int[] x = snake.getX();
        int[] y = snake.getY();

        // Draw snake with separated boxes
        for (int z = 0; z < snake.getDots(); z++) {
            if (z == 0) {
                // Draw head as red square with border
                g.setColor(Color.red);
                g.fillRect(x[z] + 1, y[z] + 1, DOT_SIZE - 2, DOT_SIZE - 2);
                g.setColor(new Color(139, 0, 0)); // Dark red border
                g.drawRect(x[z], y[z], DOT_SIZE - 1, DOT_SIZE - 1);
            } else {
                // Draw body as white squares with border
                g.setColor(Color.white);
                g.fillRect(x[z] + 1, y[z] + 1, DOT_SIZE - 2, DOT_SIZE - 2);
                g.setColor(Color.gray); // Gray border
                g.drawRect(x[z], y[z], DOT_SIZE - 1, DOT_SIZE - 1);
            }
        }

        // Draw score
        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.BOLD, 14));
        g.drawString("Score: " + score, 10, 15);

        Toolkit.getDefaultToolkit().sync();
    }

    private void showGameOver(Graphics g) {
        String msg = "Game Over";
        String scoreMsg = "Score: " + score;
        String restartMsg = "Press SPACE to Restart";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 - 20);
        g.drawString(scoreMsg, (B_WIDTH - metr.stringWidth(scoreMsg)) / 2, B_HEIGHT / 2);
        g.drawString(restartMsg, (B_WIDTH - metr.stringWidth(restartMsg)) / 2, B_HEIGHT / 2 + 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            if (snake.checkAppleCollision(apple.getAppleX(), apple.getAppleY())) {
                snake.grow();
                apple.locateApple();
                score++;
            }

            if (snake.checkCollision(B_WIDTH, B_HEIGHT)) {
                inGame = false;
            }

            snake.move(leftDirection, rightDirection, upDirection, downDirection);
        }

        repaint();
    }

    private void restartGame() {
        inGame = true;
        score = 0;
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;

        snake = new Snake(B_WIDTH, B_HEIGHT, DOT_SIZE);
        apple = new Apple(DOT_SIZE, RAND_POS);

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE && !inGame) {
                restartGame();
                return;
            }

            if (key == KeyEvent.VK_LEFT && !rightDirection) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            } else if (key == KeyEvent.VK_RIGHT && !leftDirection) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            } else if (key == KeyEvent.VK_UP && !downDirection) {
                upDirection = true;
                leftDirection = false;
                rightDirection = false;
            } else if (key == KeyEvent.VK_DOWN && !upDirection) {
                downDirection = true;
                leftDirection = false;
                rightDirection = false;
            }
        }
    }
}
