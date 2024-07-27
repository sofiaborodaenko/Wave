package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class PauseMenu extends MouseAdapter {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private Color menuParticle;
    private Color myBlack = new Color(31, 31, 31);

    PauseMenu(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (Game.gameState == STATE.Game &&  Game.paused == true) {
            // mouse over main menu button
            if (mouseOver(mx, my, 250, 220, 140, 50)) {
                Game.gameState = STATE.Menu;
                Game.paused = false;

                HUD.HEALTH = 100;
                hud.bounds = 0;
                handler.speed = 5;
                handler.clearEnemy();

                for (int i = 0; i < 10; i++) {

                    menuParticle = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
                    handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), 16, 16,
                            ID.MenuParticle, handler, menuParticle, 0.02f));
                }
            }

            // mouse over quit
            if (mouseOver(mx, my, 250, 300, 140, 50)) {
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

    public void tick() {
    }

    public void render(Graphics g) {
        Font fnt = new Font("ariel", 1, 30);
        Font fnt2 = new Font("ariel", 1, 15);
        Font fnt3 = new Font("ariel", 1, 20);

        g.setColor(myBlack);
        g.fillRect(220, 80, 200, 300);
        g.setColor(new Color(75, hud.greenValue, 0));
        g.drawRect(220, 80, 200, 300);

        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Paused", 265, 130);

        g.setFont(fnt2);
        g.drawString("Click \"P\" to unpause", 242, 180);

        g.setFont(fnt3);

        g.drawString("Main Menu", 263, 250);
        g.drawString("Quit", 295, 333);

        g.drawRect(250, 220, 140, 50);
        g.drawRect(250, 300, 140, 50);

    }

}
