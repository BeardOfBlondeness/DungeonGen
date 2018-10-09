package com.michaelpearcey.DungeonGen;

import java.awt.Point;

public class Game {

    private int currentLevel;
    private Level l;
    private boolean[][] grid;
    private Sprite[][] tiles;
    private final int speed = 5;
    private Player p;
    private float vspeed = 0;
    private float hspeed = 0;
    private final float acceleration = 0.05f;
    private final float decceleration = 0.035f;

    public Game() {
        currentLevel = 0;
        l = new Level();
        grid = l.getGrid();
        tiles = new Sprite[30][20];
        stageGrid();
        p = new Player(grid);
    }

    private void stageGrid() {
        for(int i = 0; i < grid[0].length; i++) {
            for(int j = 0; j < grid[1].length; j++) {
                if(grid[i][j])
                    tiles[i][j] = new Sprite("res/floor.png", i*40, j*40, 40, 40);
                else
                    tiles[i][j] = new Sprite("res/wall.png", i*40, j*40, 40, 40);
            }
        }
    }

    public void run() {
        for(int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid[1].length; j++) {
                tiles[i][j].draw();
            }
        }
        if(vspeed > 0)
            vspeed-=decceleration;
        else if(vspeed != 0)
            vspeed+=decceleration;
        if(hspeed > 0)
            hspeed-=decceleration;
        else if(hspeed != 0)
            hspeed+=decceleration;

        boolean collisionX = false;
        boolean collisionY = false;

        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                Sprite temp = tiles[i][j];
                if(p.getX() > temp.getX() + 1 && p.getX() < temp.getX() + temp.getxRes() - 1 && !grid[i][j]) {
                    collisionX = true;
                }
                if(p.getY() > temp.getY() + 1 && p.getY() < temp.getY() + temp.getyRes() - 1 && !grid[i][j]) {
                    collisionY = true;
                }
            }
        }
        /*
        if(!collisionX)
            hspeed = 0;
        if(!collisionY)
            vspeed = 0;*/
        p.setTempLocation(p.getX() + hspeed, p.getY() - vspeed);
        p.draw();
    }

    public void zoom(boolean zoomIn) {
        for(int i = 0; i < grid[0].length; i++) {
            for(int j = 0; j < grid[1].length; j++) {
                Sprite temp = tiles[i][j];
                int changeX = 0;
                int changeY = 0;
                int changeWidth = -1;
                int changeHeight = -1;
                if(i > 15) {
                    changeX = (i - 15)*-1;
                }
                if(i <= 15) {
                    changeX = 15 - i;
                }
                if(j <= 15) {
                    changeY = 15 - j;
                }
                if(j > 15) {
                    changeY = (j - 15)*-1;
                }
                if (!zoomIn) {
                    changeWidth = changeWidth * -1;
                    changeHeight = changeHeight * -1;
                    changeX = changeX * -1;
                    changeY = changeY * -1;
                }
                tiles[i][j].setTempLocation(temp.getX() + changeX, temp.getY() + changeY);
                tiles[i][j].setTempSize(temp.getxRes() + changeWidth, temp.getyRes() + changeHeight);
            }
        }
    }

    public void move(int dir) {
            for(int i = 0; i < grid[0].length; i++) {
                for (int j = 0; j < grid[1].length; j++) {
                    Sprite temp = tiles[i][j];
                    if(dir == 0)
                        tiles[i][j].setTempLocation(temp.getX(), temp.getY() - speed);
                    else if(dir == 2)
                        tiles[i][j].setTempLocation(temp.getX(), temp.getY() + speed);
                    if(dir == 1)
                        tiles[i][j].setTempLocation(temp.getX() + speed, temp.getY());
                    else if(dir == 3)
                        tiles[i][j].setTempLocation(temp.getX() - speed, temp.getY());
                }
            }
        if(dir == 0)
            p.setTempLocation(p.getX(), p.getY() - speed);
        else if(dir == 2)
            p.setTempLocation(p.getX(), p.getY() + speed);
        if(dir == 1)
            p.setTempLocation(p.getX() + speed, p.getY());
        else if(dir == 3)
            p.setTempLocation(p.getX() - speed, p.getY());
    }

    public void moveP(int dir) {
        if(dir == 0) {
            if(speed < 20)
                vspeed+=acceleration;
        }
        else if(dir == 2) {
            if(speed > -20)
                vspeed-=acceleration;
        }
        if(dir == 1) {
            if(hspeed < 20)
                hspeed+=acceleration;
        }
        else if(dir == 3) {
            if(hspeed > -20)
                hspeed-=acceleration;
        }

    }
}