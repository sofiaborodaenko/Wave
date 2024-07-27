package com.tutorial.main;

import java.awt.Color;
import java.util.Random;

public class BossOne extends BasicEnemy {

    // timers for the duration of the movements
    private int timer = 160;
    private int timer2 = 50;
    private int timer3 = 100;

    private Handler handler;
    private HUD hud;
    private Color color;
    private float trailLength;
    private int width, height;

    private Random r = new Random();

    public BossOne(int x, int y, int width, int height, ID id, Handler handler, Color color, float trailLength,
            HUD hud) {
        super(x, y, width, height, id, handler, color, trailLength);

        this.handler = handler;
        this.hud = hud;
        this.color = color;
        this.trailLength = trailLength;
        this.width = width;
        this.height = height;

        // makes the boss move down slowly
        velX = 0;
        velY = 1;
    }

    // moves the object
    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0 || x >= Game.WIDTH - 96) {
            // reverses the x velocity
            velX *= -1;
        }

        // adds the trail
        handler.addObject(new Trail(x, y, color, width, height, trailLength, ID.Trail, handler));

        // if the first timer reaches zero stop the vertical movement
        if (timer <= 0) {
            velY = 0;
        } else {
            timer--;
        }

        if (timer <= 0) {
            timer2--;
        }

        if (timer2 <= 0) {
            // starts the horizontal movement
            if (velX == 0) {
                velX = 2;
            }

            // doesnt work but should speed up
            if (velX > 0) {
                velX += 0.01f;
            } else if (velX < 0) {
                velX -= 0.01f;
            }

            velX = Game.clamp(velX, -10, 10);

            int spawn = r.nextInt(10);
            // if random number is 0 shoot bullets
            if (spawn == 0) {
                handler.addObject(
                        new BossOneBullet(x + 65, y + 48, 16, 16, ID.BossOneBullet, handler, Color.red, 0.02f));
            }
        }

        // once the game reaches level 9
        if (hud.getLevel() >= 15) {

            // once in the middle move stop movement
            if (x == Game.WIDTH / 2 - 48) {
                velX = 0;
                velY = 0;

                // removes the bullets
                for (int i = 0; i < handler.object.size(); i++) {
                    // gets the id of the object
                    GameObject tempObject = handler.object.get(i);

                    if (tempObject.getId() == ID.BossOneBullet) {
                        handler.removeObject(tempObject);
                    }

                }

                // moves the boss out of screen
                if (timer3 <= 0) {
                    velY = -1;
                } else {
                    timer3--;
                }

                // once out of screen remove
                if (y <= -96) {
                    handler.removeObject(this);
                }

            }

        }

    }

}
