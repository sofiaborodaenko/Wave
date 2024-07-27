package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

    public static int HEALTH = 100;
    public int bounds = 0;

    public int greenValue = 255;

    private int score = 0, level = 1;

    public void tick(){

        HEALTH = Game.clamp(HEALTH, 0, 100 + bounds/2);

        //changes the colour of the health bar
        greenValue = 255 * HEALTH/100;

        //sets the min and max for health 
        greenValue = Game.clamp(greenValue, 0, 255);
        

        //increases score
        score++;
    }

    public void render(Graphics g){
        //draws the health bar
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200 + bounds, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 15, HEALTH*2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200 + bounds, 32);

        Font fnt = new Font("ariel", 1, 12);
        g.setFont(fnt);

        //draws out the level and score
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
        g.drawString("Space for Shop", 15, 96);

    }

    public void setScore(int score){
        this.score = score;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getScore(){
        return score;
    }

    public int getLevel(){
        return level;
    }


}
