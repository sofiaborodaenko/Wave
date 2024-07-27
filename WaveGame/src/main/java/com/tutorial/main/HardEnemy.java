package com.tutorial.main;

import java.awt.Color;
import java.util.Random;

public class HardEnemy extends BasicEnemy {

    private Random r = new Random();
    private Handler handler;
    private Color color;
    private int width, height;
    private float trailLength;

    public HardEnemy(int x, int y, int width, int height, ID id, Handler handler, Color color, float trailLength) {
        super(x, y, width, height, id, handler, color, trailLength);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.trailLength = trailLength;

    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) {
            if (velY <= 0) {
                velY = -(r.nextInt(7) + 3) * -1;
            } else {
                velY = (r.nextInt(7) + 3) * -1;
            }
        }

        if (x <= 0 || x >= Game.WIDTH - 16) {
            if (velX <= 0) {
                velX = -(r.nextInt(7) + 3) * -1;
            } else {
                velX = (r.nextInt(7) + 3) * -1;
            }
        }

        handler.addObject(new Trail(x, y, color, width, height, trailLength, ID.Trail, handler));

    }

}
