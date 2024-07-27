package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

    private Handler handler;
    private Color color;
    private float trailLength;
    private int width, height;
    
    public BasicEnemy(int x, int y, int width, int height, ID id, Handler handler, Color color, float trailLength){
        super(x, y, id);

        this.handler = handler;
        this.color = color;
        this.trailLength = trailLength;
        this.width = width;
        this.height = height;

        velX = 5;
        velY = 5;
    }

    //draws the bounds of the rectangle for collision purposes
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    // moves the object
    public void tick(){
        x += velX;
        y += velY;


        if (y <= 0 || y >= Game.HEIGHT-32){
            //reverses the y velocity
            velY *= -1;
        }

        if (x <= 0 || x >= Game.WIDTH-16){
            //reverses the x velocity
            velX *= -1;
        }

        handler.addObject(new Trail(x, y, color, width, height, trailLength, ID.Trail, handler));

    }

    // draws the object
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }


}
