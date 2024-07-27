package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter{

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    

    public KeyInput(Handler handler){
        this.handler = handler;

        
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        //loops through all the objects
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //if the game object is the player, change the coordinates 
            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_W) {
                    tempObject.setvelY(-handler.speed);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setvelY(handler.speed);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_A){
                    tempObject.setvelX(-handler.speed);
                    keyDown[2] = true;
                } 
                if (key == KeyEvent.VK_D) {
                    tempObject.setvelX(handler.speed);
                    keyDown[3] = true;

                }

            }


        }

        if (key == KeyEvent.VK_P){

            if (Game.gameState == Game.STATE.Game){
                if (Game.paused){
                    Game.paused = false;
                } else {
                    Game.paused = true;
                }
            }
        } 

        if (key == KeyEvent.VK_SPACE){
            if (Game.gameState == STATE.Game){
                Game.gameState = STATE.Shop;
            } else if (Game.gameState == STATE.Shop){
                Game.gameState = STATE.Game;
            }
        }

        if (key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
        
        
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
 
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_W) keyDown[0] = false;
                if (key == KeyEvent.VK_S) keyDown[1] = false;
                if (key == KeyEvent.VK_A) keyDown[2] = false;
                if (key == KeyEvent.VK_D) keyDown[3] = false;


                //veritcal movement
                if (!keyDown[0] && !keyDown[1]) {
                    tempObject.setvelY(0);
                }
                //horizontal movement
                if (!keyDown[2] && !keyDown[3]) {
                    tempObject.setvelX(0);
                }

            }


        }

        
    }

}
