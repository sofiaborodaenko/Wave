package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    // good aspect ratio
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Shop shop;
    private PauseMenu pause;

    private Color menuParticle;

    public static boolean paused = false;
    public static int diff = 0;
    // 0 = normal, 1 = hard

    private Random r;

    public enum STATE {
        Menu,
        Select,
        Help,
        Game,
        Shop,
        End
    };

    public static STATE gameState = STATE.Menu;

    public Game() {
        r = new Random();
        // creates the handler class
        handler = new Handler();
        // creates and displays health bar
        hud = new HUD();
        // displays shop
        shop = new Shop(handler, hud);
        // creates the menu screen
        menu = new Menu(handler, hud);
        // creates pause screen
        pause = new PauseMenu(handler, hud);

        // inputs key listener
        this.addKeyListener(new KeyInput(handler));
        // inputs mouse listener
        this.addMouseListener(menu);
        this.addMouseListener(shop);
        this.addMouseListener(pause);

        // creates window
        new Window(WIDTH, HEIGHT, "Game!", this);

        // spawns the enemies
        spawner = new Spawn(handler, hud);

        if (gameState == STATE.Game) {
            // adds the player
            // handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player,
            // handler));
            // handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),
            // r.nextInt(Game.HEIGHT - 50), 16, 16, ID.BasicEnemy, handler, Spawn.myRed,
            // 0.02f));

        } else if (gameState == STATE.Menu || gameState == STATE.Help) {

            for (int i = 0; i < 10; i++) {

                menuParticle = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
                handler.addObject(new MenuParticle(r.nextInt(WIDTH - 64), r.nextInt(HEIGHT - 64), 16, 16,
                        ID.MenuParticle, handler, menuParticle, 0.02f));
            }

        }

    }

    public synchronized void start() {

        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // game loop: How it works? no clue
    public void run() {
        // dont have to click on screen to use it
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    private void tick() {

        if (gameState == STATE.Game) {

            if (!paused) {
                // sets the variables of the objects
                handler.tick();
                hud.tick();
                spawner.tick();

                if (HUD.HEALTH <= 0) {
                    gameState = STATE.End;
                    HUD.HEALTH = 100;
                    hud.bounds = 0;
                    handler.speed = 5;
                    handler.clearEnemy();
                    Game.paused = false;

                }
            }

        } else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
            menu.tick();
            // sets the variables of the objects
            handler.tick();
        }

    }

    // draws the graphics
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (gameState == STATE.Game) {

            if (paused) {
                pause.render(g);
                hud.render(g);

            } else {
                // draws the characters
                handler.render(g);
                hud.render(g);
            }

        } else if (gameState == STATE.Shop) {
            shop.render(g);

        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End
                || gameState == STATE.Select) {

            // draws the characters
            handler.render(g);
            menu.render(g);

        }

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public static void main(String[] args) {

        new Game();

    }

}
