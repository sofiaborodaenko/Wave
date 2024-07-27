package com.tutorial.main;

import java.awt.Color;
import java.util.Random;

public class MenuParticle extends FastEnemy{
    Handler handler;
    
    Random r = new Random();
    int dir = 0;


    public MenuParticle(int x, int y, int width, int height, ID id, Handler handler, Color color, float trailLength) {
        super(x, y, width, height, id, handler, color, trailLength);
        this.handler = handler;

        dir = r.nextInt(2);
        if (dir == 0){
            velX = 2;
            velY = 5;
        } else {
            velX = 5;
            velY = 2;
        }

    }

}
