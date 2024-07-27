package com.tutorial.main;

import java.awt.Color;

public class SmartEnemy extends BasicEnemy {
    private GameObject player;

    public SmartEnemy(int x, int y, int width, int height, ID id, Handler handler, Color color, float trailLength) {
        super(x, y, width, height, id, handler, color, trailLength);

        //sets the game object of player to player
        for (int i = 0; i < handler.object.size(); i++){
            if (handler.object.get(i).getId() == ID.Player){
                player = handler.object.get(i);
            }
        }

    }

    public void tick(){
        //extends the basicenemy tick method and just adds onto it
        super.tick();
        double diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY()) * (y-player.getY()));

        velX = (int) ((-1/distance) * diffX*3);
        velY = (int) ((-1/distance) * diffY*3);

    }

}
