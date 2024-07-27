package com.tutorial.main;

import java.awt.Color;
import java.util.Random;

public class PlayerBullets extends BasicEnemy{

    private Random r = new Random();

    private Handler handler;
    private Color color;
    private float trailLength;
    private int width, height;

    public PlayerBullets(int x, int y, int width, int height, ID id, Handler handler, Color color, float trailLength) {
        super(x, y, width, height, id, handler, color, trailLength);

        this.handler = handler;
        this.color = color;
        this.trailLength = trailLength;
        this.width = width;
        this.height = height;

        //randomizes the direction
        velX = (r.nextInt(5- -5) + -5);
        velY = (r.nextInt(5- -5) + -5);
    }

    // moves the object
    public void tick(){
        if (velX == 0){
            velX = 1;
        } 
        if (velY == 0){
            velY = 1;
        }

        x += velX;
        y += velY;

        //removes the bullet once it reaches the bottom of the screen
        if (y >= Game.HEIGHT || y <= 0 || x >= Game.WIDTH || x <= 0){
            handler.removeObject(this);
        }

        //checks the collision
        collision();

        handler.addObject(new Trail(x, y, color, width, height, trailLength, ID.Trail, handler));

    }

    public void collision(){
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.BossOneBullet){ 
                
                //if tempObject collides with an enemy
                if (getBounds().intersects(tempObject.getBounds())){
                    //removes the enemy
                    handler.removeObject(tempObject);
                }
            }
        }


    }


}
