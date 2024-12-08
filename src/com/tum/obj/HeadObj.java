package com.tum.obj;

import com.tum.GameWin;
import com.tum.utils.GameUtils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HeadObj extends GameObj {
    // control the direction
    private String direction = "right";

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    // constructor
    public HeadObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                changeDirection(e);
            }
        });
    }

    // change the direction by keyboard
    public void changeDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (!direction.equals("down")) {
                    direction = "up";
                    img = GameUtils.upImg;
                }
                break;
            case KeyEvent.VK_S:
                if (!direction.equals("up")) {
                    direction = "down";
                    img = GameUtils.downImg;
                }
                break;
            case KeyEvent.VK_A:
                if (!direction.equals("right")) {
                    direction = "left";
                    img = GameUtils.leftImg;
                }
                break;
            case KeyEvent.VK_D:
                if (!direction.equals("left")) {
                    direction = "right";
                    img = GameUtils.rightImg;
                }
                break;
            default:
                break;
        }
    }

    // movement
    public void move() {
        // movement of the body
        java.util.List<BodyObj> bodyObjList = this.frame.bodyObjList;
        for (int i = bodyObjList.size() - 1; i >= 1; i--) {
            bodyObjList.get(i).x = bodyObjList.get(i - 1).x;
            bodyObjList.get(i).y = bodyObjList.get(i - 1).y;
            // head and body collision judgment
            if (this.x == bodyObjList.get(i).x && this.y == bodyObjList.get(i).y) {
                // the snack eat itself
                GameWin.state = 3;
            }
        }
        bodyObjList.get(0).x = this.x;
        bodyObjList.get(0).y = this.y;

        // movement of the head
        switch (direction) {
            case "up":
                y -= height;
                break;
            case "down":
                y += height;
                break;
            case "left":
                x -= width;
                break;
            case "right":
                x += width;
                break;
            default:
                break;
        }
    }

    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);

        // eat the food
        FoodObj food = this.frame.foodObj;
        // coordinates of the last body section
        Integer newX = null;
        Integer newY = null;
        if (this.x == food.x && this.y == food.y) {
            this.frame.foodObj = food.getFood();
            BodyObj lastBody = this.frame.bodyObjList.get(this.frame.bodyObjList.size() - 1);
            newX = lastBody.x;
            newY = lastBody.y;
            this.frame.score++;
        }

        // passing judgement
        if (this.frame.score >= 10) {
            GameWin.state = 4;
        }

        // use the method move
        move();

        // add a new body at the last if the snack eat the food
        if (newX != null && newY != null) {
            this.frame.bodyObjList.add(new BodyObj(GameUtils.bodyImg, newX, newY, this.frame));
        }

        // deal with transborder
        if (x < 0) {
            x = 570;
        } else if (x > 570) {
            x = 0;
        } else if (y < 30) {
            y = 570;
        } else if (y > 570) {
            y = 30;
        }
    }
}
