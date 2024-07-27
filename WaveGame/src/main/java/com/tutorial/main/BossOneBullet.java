package com.tutorial.main;

import java.awt.Color;
import java.util.Random;

public class BossOneBullet extends BasicEnemy{

    private Random r = new Random();

    private Handler handler;
    private Color color;
    private float trailLength;
    private int width, height;

    public BossOneBullet(int x, int y, int width, int height, ID id, Handler handler, Color color, float trailLength) {
        super(x, y, width, height, id, handler, color, trailLength);

        this.handler = handler;
        this.color = color;
        this.trailLength = trailLength;
        this.width = width;
        this.height = height;

        velX = (r.nextInt(5- -5) + -5);

        if (Game.diff == 0){
        velY = 7;
        } else {
            velY = 10;
        }
    }

    // moves the object
    public void tick(){
        x += velX;
        y += velY;

        //removes the bullet once it reaches the bottom of the screen
        if (y >= Game.HEIGHT){
            handler.removeObject(this);
        }

        handler.addObject(new Trail(x, y, color, width, height, trailLength, ID.Trail, handler));

    }

}
