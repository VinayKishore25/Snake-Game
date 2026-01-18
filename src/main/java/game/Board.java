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
            drawScore(g);
        } else {
            drawObjects(g);
            showGameOver(g);
        }
    }

    private void drawScore(Graphics g) {
        int score = snake.getDots() - 3;
        String scoreText = "SCORE: " + score;

        // Draw score background
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRoundRect(5, 5, 100, 30, 10, 10);

        // Draw score text
        Font scoreFont = new Font("Arial", Font.BOLD, 16);
        g.setFont(scoreFont);
        g.setColor(Color.white);
        g.drawString(scoreText, 15, 26);
    }

    private void drawObjects(Graphics g) {
        // Draw apple with glow effect
        g.setColor(new Color(255, 50, 50));
        g.fillOval(apple.getAppleX() - 1, apple.getAppleY() - 1, DOT_SIZE + 2, DOT_SIZE + 2);
        g.setColor(Color.red);
        g.fillOval(apple.getAppleX(), apple.getAppleY(), DOT_SIZE, DOT_SIZE);

        int[] x = snake.getX();
        int[] y = snake.getY();

        // Draw snake
        for (int z = 0; z < snake.getDots(); z++) {
            if (z == 0) {
                // Draw head in dark red with border
                g.setColor(new Color(200, 0, 0));
                g.fillRect(x[z], y[z], DOT_SIZE, DOT_SIZE);
                g.setColor(new Color(255, 100, 100));
                g.drawRect(x[z], y[z], DOT_SIZE - 1, DOT_SIZE - 1);
            } else {
                // Draw body in lime green with border
                g.setColor(new Color(50, 205, 50));
                g.fillRect(x[z], y[z], DOT_SIZE, DOT_SIZE);
                g.setColor(new Color(144, 238, 144));
                g.drawRect(x[z], y[z], DOT_SIZE - 1, DOT_SIZE - 1);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void showGameOver(Graphics g) {
        int finalScore = snake.getDots() - 3;

        // Dark overlay
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);

        // Game Over box
        g.setColor(new Color(40, 40, 40));
        g.fillRoundRect(B_WIDTH / 2 - 130, B_HEIGHT / 2 - 80, 260, 160, 20, 20);
        g.setColor(new Color(102, 126, 234));
        g.drawRoundRect(B_WIDTH / 2 - 130, B_HEIGHT / 2 - 80, 260, 160, 20, 20);
        g.drawRoundRect(B_WIDTH / 2 - 131, B_HEIGHT / 2 - 81, 262, 162, 20, 20);

        // Game Over text
        Font titleFont = new Font("Arial", Font.BOLD, 32);
        Font scoreFont = new Font("Arial", Font.BOLD, 20);
        Font msgFont = new Font("Arial", Font.PLAIN, 14);

        FontMetrics titleMetrics = getFontMetrics(titleFont);
        FontMetrics scoreMetrics = getFontMetrics(scoreFont);
        FontMetrics msgMetrics = getFontMetrics(msgFont);

        String gameOverText = "GAME OVER";
        String scoreText = "Score: " + finalScore;
        String restartText = "Click START to play again";

        // Draw title
        g.setFont(titleFont);
        g.setColor(new Color(255, 100, 100));
        g.drawString(gameOverText, (B_WIDTH - titleMetrics.stringWidth(gameOverText)) / 2, B_HEIGHT / 2 - 30);

        // Draw score
        g.setFont(scoreFont);
        g.setColor(new Color(102, 126, 234));
        g.drawString(scoreText, (B_WIDTH - scoreMetrics.stringWidth(scoreText)) / 2, B_HEIGHT / 2 + 15);

        // Draw restart message
        g.setFont(msgFont);
        g.setColor(Color.lightGray);
        g.drawString(restartText, (B_WIDTH - msgMetrics.stringWidth(restartText)) / 2, B_HEIGHT / 2 + 55);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            if (snake.checkAppleCollision(apple.getAppleX(), apple.getAppleY())) {
                snake.grow();
                apple.locateApple();
            }

            if (snake.checkCollision(B_WIDTH, B_HEIGHT)) {
                inGame = false;
            }

            snake.move(leftDirection, rightDirection, upDirection, downDirection);
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

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
