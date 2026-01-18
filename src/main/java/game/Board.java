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
    private Image ball;
    private Image appleImage;
    private Image head;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        loadImages();

        snake = new Snake(B_WIDTH, B_HEIGHT, DOT_SIZE);
        apple = new Apple(DOT_SIZE, RAND_POS);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void loadImages() {
        ball = new ImageIcon("resources/dot.png").getImage();
        appleImage = new ImageIcon("resources/apple.png").getImage();
        head = new ImageIcon("resources/head.png").getImage();
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
        g.drawImage(appleImage, apple.getAppleX(), apple.getAppleY(), this);

        int[] x = snake.getX();
        int[] y = snake.getY();

        for (int z = 0; z < snake.getDots(); z++) {
            if (z == 0) {
                g.drawImage(head, x[z], y[z], this);
            } else {
                g.drawImage(ball, x[z], y[z], this);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void showGameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
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
