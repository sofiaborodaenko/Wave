package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/*  game objects will inherit this class and each have 
 *  the methods in this class
 */

public abstract class GameObject {

    //protected means only be accessed by the object that inherits
    protected int x, y;
    protected ID id;
    protected int velX, velY;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y; 
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(ID id){
        this.id = id;
    }

    public void setvelX(int velX){
        this.velX = velX;
    }

    public void setvelY(int velY){
        this.velY = velY;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public ID getId(){
        return id;
    }

    public int getvelX(){
        return velX;
    }

    public int getvelY(){
        return velY;
    }

    


}
