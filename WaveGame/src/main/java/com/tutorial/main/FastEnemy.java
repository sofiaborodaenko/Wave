package com.tutorial.main;

import java.awt.Color;

public class FastEnemy extends BasicEnemy {

    public FastEnemy(int x, int y, int width, int height, ID id, Handler handler, Color color, float trailLength) {
        super(x, y, width, height, id, handler, color, trailLength);

        velX = 2;
        velY = 9;
    }

}
