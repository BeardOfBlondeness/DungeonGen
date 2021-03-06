package com.michaelpearcey.DungeonGen;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {

    private final int FWIDTH = 1200, FHEIGHT = 800; // Frame parameters
    private static GameHandler gh;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        try {
            display();
            gh = new GameHandler();
            update();
        } catch (LWJGLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void display() {
        Display.setTitle("Romwell");
        try {
            Display.setDisplayMode(new DisplayMode(FWIDTH, FHEIGHT));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, FWIDTH, FHEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void update() throws LWJGLException {
        while(!Display.isCloseRequested())
        {
            glClear(GL_COLOR_BUFFER_BIT);
            mainKeyListeners();
            gh.run();
            Display.update();
            Display.sync(60);
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                break;
            }
        }
        killAllGame();
    }

    public static void killAllGame() {
        Display.destroy();
        System.exit(0);
    }

    public void mainKeyListeners() {
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            gh.zoom(true);
        } else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            gh.zoom(false);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_I)) {
            gh.move(0);
        } else if(Keyboard.isKeyDown(Keyboard.KEY_K)) {
            gh.move(2);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_L)) {
            gh.move(1);
        } else if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
            gh.move(3);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
            gh.moveP(0);
        } else if(Keyboard.isKeyDown(Keyboard.KEY_S))
            gh.moveP(2);
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            gh.moveP(1);
        else if(Keyboard.isKeyDown(Keyboard.KEY_A))
            gh.moveP(3);
    }

}
