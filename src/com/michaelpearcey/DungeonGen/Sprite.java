package com.michaelpearcey.DungeonGen;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {

    private Texture tex;
    private float x, y;
    private float xRes, yRes;

    public Sprite(String textureLoaction, float x, float y, int xRes, int yRes) {
        tex = loadTexture(textureLoaction);
        this.x = x;
        this.y = y;
        this.xRes = xRes;
        this.yRes = yRes;
    }

    public void draw() {
        glPushMatrix();
        tex.bind();
        glTranslatef(x, y, 0);
        glColor3f(1,1,1);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, 0);                 			 glVertex2f(0, 0);
            glTexCoord2f(0, tex.getHeight());   			 glVertex2f(0, yRes);
            glTexCoord2f(tex.getWidth(), tex.getHeight()); 	 glVertex2f(xRes,yRes);
            glTexCoord2f(tex.getWidth(), 0);				 glVertex2f(xRes,0);
        }
        glEnd();
        glPopMatrix();
    }

    public static Texture loadTexture(String path) {
        Texture tex = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try {
            tex = TextureLoader.getTexture("PNG", in);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return tex;
    }

}
