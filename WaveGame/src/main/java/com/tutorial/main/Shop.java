package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {
    Handler handler;
    HUD hud;

    public static boolean shootBullets = false;

    private int B1 = 500;
    private int B2 = 800;
    private int B3 = 1000;

    public Shop(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;

    }

    public void render(Graphics g) {
        Font fnt = new Font("ariel", 1, 50);
        Font fnt2 = new Font("ariel", 1, 20);
        Font fnt3 = new Font("ariel", 1, 15);
        Font fnt4 = new Font("ariel", 1, 10);

        g.setColor(Color.white);
        g.setFont(fnt);
        g.drawString("Shop", 255, 70);

        g.setFont(fnt2);
        g.drawString("Use your score to level up your character", 100, 120);
        g.setFont(fnt3);
        g.drawString("Click \"Space\" to exit", 240, 150);
        g.drawString("Your score: " + hud.getScore(), 70, Game.HEIGHT - 100);

        g.drawString("Upgrade Health", 57, 247);
        g.drawString("Upgrade Speed", 260, 247);
        g.drawString("Shoot Bullets", 467, 247);

        g.setFont(fnt4);
        g.drawString("Cost: " + B1, 95, 267);
        g.drawString("Cost: " + B2, 295, 267);
        g.drawString("Cost: " + B3, 495, 267);

        g.drawRect(30, 220, 180, 64);
        g.drawRect(230, 220, 180, 64);
        g.drawRect(430, 220, 180, 64);

    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // the first box, increase health
        if (mouseOver(mx, my, 30, 220, 180, 64)) {
            if (hud.getScore() >= B1) {
                hud.setScore(hud.getScore() - B1);
                B1 += 450;
                hud.bounds += 20;
                HUD.HEALTH = (100 + (hud.bounds / 2));
            }

        }

        // the second box, increases speed of player
        if (mouseOver(mx, my, 230, 220, 180, 64)) {
            if (hud.getScore() >= B2) {
                hud.setScore(hud.getScore() - B2);
                B2 += 650;
                handler.speed++;
            }

        }

        // the third box, shooting the enemies
        if (mouseOver(mx, my, 430, 220, 180, 64)) {
            if (hud.getScore() >= B3) {
                hud.setScore(hud.getScore() - B3);
                B3 += B3;
                shootBullets = true;

            }

        }
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

}
