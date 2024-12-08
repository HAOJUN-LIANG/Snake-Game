package com.tum.obj;

import com.tum.GameWin;

import java.awt.*;

public class GameObj {
    // pictures of game objects
    Image img;

    // coordinates of game objects
    int x;
    int y;

    // width and height of game objects
    // keep the width and height consistent with the small grid
    int width = 30;
    int height = 30;

    // window class reference
    GameWin frame;

    public Image getImg() {
        return img;
    }

    // getters and setters
    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameWin getFrame() {
        return frame;
    }

    public void setFrame(GameWin frame) {
        this.frame = frame;
    }

    // constructors
    public GameObj() {
    }

    public GameObj(Image img, int x, int y, GameWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.frame = frame;
    }

    public GameObj(Image img, int x, int y, int width, int height, GameWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frame = frame;
    }

    // the object draws itself
    public void drawSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }
}
