package com.tutorial.main;

import java.awt.Color;
import java.util.Random;

public class Spawn {
    Handler handler;
    private HUD hud;
    private Random r = new Random();

    static Color myRed = new Color(255, 69, 69);
    private Color myBlue = new Color(69, 202, 255);
    private Color myBeige = new Color(255, 248, 230);
    private Color darkRed = new Color(156, 0, 8);
    static Color purple = new Color(112, 0, 168);

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;

        // once score keep reaches whatever increase the level
        if (scoreKeep >= 250) {
            // sets it back to 0 to have it be infinite
            scoreKeep = 0;
            // sets the level to one higher
            hud.setLevel(hud.getLevel() + 1);

            if (Game.diff == 0) {
                if (hud.getLevel() == 2) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, myRed, 0.02f));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, myRed, 0.02f));
                } else if (hud.getLevel() == 4) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, myBlue, 0.08f));
                } else if (hud.getLevel() == 5) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.SmartEnemy, handler, myBeige, 0.1f));
                } else if (hud.getLevel() == 6) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, myRed, 0.02f));
                } else if (hud.getLevel() == 7) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, myBlue, 0.08f));
                } else if (hud.getLevel() == 8) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, myRed, 0.02f));
                } else if (hud.getLevel() == 9) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.SmartEnemy, handler, myBeige, 0.1f));
                } else if (hud.getLevel() == 10) {
                    // clears the enemies and leaves the player
                    handler.clearEnemy();
                    handler.addObject(
                            new BossOne((Game.WIDTH / 2) - 48, -150, 96, 96, ID.BossOne, handler, darkRed, 0.08f, hud));
                } else if (hud.getLevel() >= 18) {

                    if (hud.getLevel() % 1 == 0) {

                        int enemy = r.nextInt(2);

                        if (enemy == 0) {
                            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
                                    16, 16, ID.BasicEnemy, handler, myRed, 0.02f));
                        } else if (enemy == 1) {
                            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16,
                                    16, ID.BasicEnemy, handler, myBlue, 0.08f));
                        }

                        if (hud.getLevel() % 3 == 0) {
                            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
                                    16, 16, ID.SmartEnemy, handler, myBeige, 0.1f));

                        }

                    }
                }

            } else {
                if (hud.getLevel() == 2) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, purple, 0.02f));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, purple, 0.02f));
                } else if (hud.getLevel() == 4) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, myBlue, 0.08f));
                } else if (hud.getLevel() == 5) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.SmartEnemy, handler, myBeige, 0.1f));
                } else if (hud.getLevel() == 6) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, purple, 0.02f));
                } else if (hud.getLevel() == 7) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, myBlue, 0.08f));
                } else if (hud.getLevel() == 8) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.BasicEnemy, handler, purple, 0.02f));
                } else if (hud.getLevel() == 9) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16, 16,
                            ID.SmartEnemy, handler, myBeige, 0.1f));
                } else if (hud.getLevel() == 10) {
                    // clears the enemies and leaves the player
                    handler.clearEnemy();
                    handler.addObject(
                            new BossOne((Game.WIDTH / 2) - 48, -150, 96, 96, ID.BossOne, handler, darkRed, 0.08f, hud));

                } else if (hud.getLevel() >= 18) {

                    if (hud.getLevel() % 1 == 0) {

                        int enemy = r.nextInt(2);

                        if (enemy == 0) {
                            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16,
                                    16, ID.BasicEnemy, handler, purple, 0.02f));
                        } else if (enemy == 1) {
                            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 16,
                                    16, ID.BasicEnemy, handler, myBlue, 0.08f));
                        }

                        if (hud.getLevel() % 3 == 0) {
                            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),
                                    16, 16, ID.SmartEnemy, handler, myBeige, 0.1f));

                        }

                    }
                }

            }

        }

    }

}
