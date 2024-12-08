package com.tum.obj;

import com.tum.GameWin;
import com.tum.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FoodObj extends GameObj {
    // constructor
    public FoodObj() {
        super();
    }

    public FoodObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
    }

    // random position
    Random r = new Random();

    // get the food
    public FoodObj getFood() {
        return new FoodObj(GameUtils.foodImg, r.nextInt(20) * 30, (r.nextInt(19) + 1) * 30, this.frame);
    }

    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
    }
}
