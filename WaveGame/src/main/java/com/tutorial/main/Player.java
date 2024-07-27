package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

    private Random r = new Random();
    private int timer = 30;
    
    private Color bulletColor = new Color(242, 242, 220);

    Handler handler;


    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
    }

    //draws the bounds of the rectangle for collision purposes
    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }

    // moves the object
    public void tick() {

        x += velX;
        y += velY;

        //makes sure the player doesn't go out of bounds
        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);

        handler.addObject(new Trail(x, y, Color.white, 32, 32, 0.1f, ID.Trail, handler));

        collision();

        if (Shop.shootBullets == true) {

            while (timer >= 0){

                int spawn = r.nextInt(10);
                // if random number is even shoot bullets
                if (spawn % 2 ==0) {
                    handler.addObject(
                            new PlayerBullets(x, y, 8, 8, ID.PlayerBullet, handler, bulletColor, 0.02f));
                }

                timer--;
            }

            timer = 30;
            Shop.shootBullets = false;
        }

    }

    public void collision(){
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.BossOne || tempObject.getId() == ID.BossOneBullet){ 
                
                //if tempObject collides with player
                if (getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.HEALTH -= 2;
                }

            }

        }


    }

    // draws the object
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 32);
    }

}
