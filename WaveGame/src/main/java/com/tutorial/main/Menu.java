package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private Color menuParticle;

    public Menu(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        // gets the x and y coordinate once mouse is pressed
        int mx = e.getX();
        int my = e.getY();

        // mouse over start
        if (Game.gameState == STATE.Menu) {
            if (mouseOver(mx, my, 218, 150, 200, 64)) {
                Game.gameState = STATE.Select;

            }

            // if mouse over help
            if (mouseOver(mx, my, 218, 250, 200, 64)) {
                Game.gameState = STATE.Help;

            }

            // mouse over exit
            if (mouseOver(mx, my, 218, 350, 200, 64)) {
                System.exit(1);
            }

        } else if (Game.gameState == STATE.Help) {

            if (mouseOver(mx, my, 218, 350, 200, 64)) {
                Game.gameState = STATE.Menu;
            }

        } else if (Game.gameState == STATE.Select) {

            // normal button
            if (mouseOver(mx, my, 218, 150, 200, 64)) {

                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemy();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT / 2), 16, 16,
                        ID.BasicEnemy, handler, Spawn.myRed, 0.02f));


                hud.setLevel(1);
                hud.setScore(0);

               
                Game.diff = 0;

                Game.gameState = STATE.Game;

            }

            // if mouse over hard
            if (mouseOver(mx, my, 218, 250, 200, 64)) {

                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemy();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT / 2), 16, 16,
                        ID.BasicEnemy, handler, Spawn.purple, 0.02f));

                    
                hud.setLevel(1);
                hud.setScore(0);

                Game.diff = 1;

                Game.gameState = STATE.Game;
            }

            // mouse over back
            if (mouseOver(mx, my, 218, 350, 200, 64)) {
                Game.gameState = STATE.Menu;
            }

        } else if (Game.gameState == STATE.End) {

            // button over retry
            if (mouseOver(mx, my, 68, 350, 200, 64)) {
                Game.gameState = STATE.Menu;
                hud.bounds = 0;

                for (int i = 0; i < 10; i++) {

                    menuParticle = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
                    handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), 16, 16,
                            ID.MenuParticle, handler, menuParticle, 0.02f));
                }

            }

            //mouse over quit button
            if (mouseOver(mx, my, 368, 350, 200, 64)) {
                System.exit(1);
            }

        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void tick() {}

    public void render(Graphics g) {
        if (Game.gameState == STATE.Menu) {
            Font fnt = new Font("ariel", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setColor(Color.WHITE);

            g.setFont(fnt);
            g.drawString("Wave", 250, 70);

            g.setFont(fnt2);
            g.drawString("Start", 278, 190);
            g.drawString("Help", 281, 290);
            g.drawString("Quit", 283, 390);

            g.drawRect(218, 150, 200, 64);
            g.drawRect(218, 250, 200, 64);
            g.drawRect(218, 350, 200, 64);

        } else if (Game.gameState == STATE.Help) {
            Font fnt = new Font("ariel", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setColor(Color.white);
            g.setFont(fnt2);
            g.drawString("Use the WASD keys to move the player", 45, 150);
            g.drawString("Avoid getting hit by the enemies", 90, 200);
            g.drawString("If hit, your health will dicrease", 110, 250);
            g.drawString("Click \"P\" to get the pause menu", 90, 300);

            g.setColor(Color.WHITE);

            g.setFont(fnt);
            g.drawString("Help", 268, 70);

            g.setFont(fnt2);
            g.drawString("Back", 283, 390);

            g.drawRect(218, 350, 200, 64);

        } else if (Game.gameState == STATE.End) {
            Font fnt = new Font("ariel", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setColor(Color.WHITE);

            g.setFont(fnt);
            g.drawString("Game Over", 173, 70);

            g.setFont(fnt2);
            g.drawString("You lost with a score of " + hud.getScore(), 50, 190);
            g.drawString("On level " + hud.getLevel(), 50, 230);

            g.drawString("Retry", 127, 390);

            g.drawRect(68, 350, 200, 64);

            g.drawString("Quit", 433, 390);

            g.drawRect(368, 350, 200, 64);
        } else if (Game.gameState == STATE.Select) {
            Font fnt = new Font("ariel", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setColor(Color.WHITE);

            g.setFont(fnt);
            g.drawString("Select Difficulty", 120, 70);

            g.setFont(fnt2);
            g.drawString("Normal", 267, 190);
            g.drawString("Hard", 281, 290);
            g.drawString("Back", 283, 390);

            g.drawRect(218, 150, 200, 64);
            g.drawRect(218, 250, 200, 64);
            g.drawRect(218, 350, 200, 64);

        }

    }
}
