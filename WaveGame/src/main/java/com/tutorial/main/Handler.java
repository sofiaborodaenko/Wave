package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

/*  maintains and renders all the objects
 *  handles and processes each object
 *  loops through all the object in game, individualy update
 *  and render to screen, 
 */
public class Handler {

LinkedList<GameObject> object = new LinkedList<GameObject>();
public int speed = 5;

public void tick(){

    //loops through all the objects
    for (int i = 0; i < object.size(); i++){
        //gets the id of the object
        GameObject tempObject = object.get(i);

        tempObject.tick();
    }
}

public void render(Graphics g){
    
    //loops through all the objects
    for (int i = 0; i < object.size(); i++) {
        //gets the id of the object
        GameObject tempObject = object.get(i);

        //draws that object
        tempObject.render(g);

    }

}

public void clearEnemy(){
     //loops through all the objects
     for (int i = 0; i < object.size(); i++) {
        //gets the id of the object
        GameObject tempObject = object.get(i);

        if (tempObject.getId() == ID.Player){
           object.clear();
           if (Game.gameState != Game.STATE.End && Game.gameState != Game.STATE.Menu){
            addObject(new Player(tempObject.getX(), tempObject.getY(), ID.Player, this));
           }
        }

    }
}

//adds the object to the linked list
public void addObject( GameObject object){
    this.object.add(object);
}

//removes the object from list
public void removeObject(GameObject object){
    this.object.remove(object);
}

}
