package com.tum.obj;

import com.tum.GameWin;

import java.awt.*;

public class BodyObj extends GameObj {
    // constructor
    public BodyObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
    }

    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
    }
}
