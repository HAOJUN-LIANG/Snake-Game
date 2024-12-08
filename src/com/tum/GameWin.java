package com.tum;

import com.tum.obj.BodyObj;
import com.tum.obj.FoodObj;
import com.tum.obj.HeadObj;
import com.tum.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

public class GameWin extends JFrame {
    // game state: 0 (inactive), 1 (active), 2 (pause), 3 (fail), 4 (pass), 5 (restart)
    public static int state = 0;

    // score
    public int score = 0;

    // scoreboard
    int winWidth = 800;
    int winHeight = 600;

    // off screen image
    Image offScreenImage = null;

    // head object
    HeadObj headObj = new HeadObj(GameUtils.rightImg, 60, 570, this);

    // body object
    public List<BodyObj> bodyObjList = new ArrayList<>();

    // food object
    public FoodObj foodObj = new FoodObj().getFood();

    public void launch() {
        // set the window to be visible
        this.setVisible(true);
        // set the size of the window
        this.setSize(winWidth, winHeight);
        // set the window to be centered on the screen
        this.setLocationRelativeTo(null);
        // set the title of the window
        this.setTitle("Snake Game");

        // Initialize body
        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 30, 570, this));
        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 0, 570, this));

        // change the game state by keyboard
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    switch (state) {
                        case 0:
                            state = 1;
                            break;
                        case 1:
                            state = 2;
                            repaint();
                            break;
                        case 2:
                            state = 1;
                            break;
                        case 3:
                            state = 5;
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        // repaint/redraw the window and add pause when the game is active
        while (true) {
            if (state == 1) {
                repaint();
            }
            if (state == 5) {
                state = 0;
                restartGame();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // draw the window
    @Override
    public void paint(Graphics g) {
        // initialize off screen image
        if (offScreenImage == null) {
            offScreenImage = this.createImage(winWidth, winHeight);
        }
        // get the corresponding graphics object
        Graphics gImage = offScreenImage.getGraphics();

        // set the background of the window to be gray
        gImage.setColor(Color.gray);
        gImage.fillRect(0, 0, this.getWidth(), this.getHeight());

        // draw the window grid lines
        gImage.setColor(Color.black);
        for (int i = 0; i < this.getHeight(); i += 30) {
            gImage.drawLine(0, i, this.getHeight(), i);
            gImage.drawLine(i, 0, i, this.getHeight());
        }

        // draw the body
        for (int i = bodyObjList.size() - 1; i >= 0; i--) {
            bodyObjList.get(i).drawSelf(gImage);
        }

        // draw the head
        headObj.drawSelf(gImage);

        // draw the food
        foodObj.drawSelf(gImage);

        // draw the score
        GameUtils.drawText(gImage, score + " SCORE", Color.BLACK, 20, 650, 300);

        // draw the prompt
        gImage.setColor(Color.gray);
        prompt(gImage);

        // draw the off screen image
        g.drawImage(offScreenImage, 0, 0, this);
    }

    // draw the prompt
    void prompt(Graphics g) {
        if (state == 0) {
            g.fillRect(60, 240, 480, 80);
            GameUtils.drawText(g, "PRESS THE SPACE BAR TO START THE GAME", Color.BLACK, 20, 80, 280);
        }

        if (state == 2) {
            g.fillRect(60, 240, 480, 80);
            GameUtils.drawText(g, "GAME PAUSE", Color.YELLOW, 20, 250, 280);
        }

        if (state == 3) {
            g.fillRect(60, 240, 480, 80);
            GameUtils.drawText(g, "PRESS THE SPACE BAR TO RESTART THE GAME", Color.RED, 20, 70, 280);
        }

        if (state == 4) {
            g.fillRect(60, 240, 480, 80);
            GameUtils.drawText(g, "YOU WIN!!!", Color.RED, 20, 250, 280);
        }
    }

    // restart the game
    void restartGame() {
        // close the window
        this.dispose();
        // restart a new window
        String[] args = {};
        main(args);
    }

    // the main method to launch the window
    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
