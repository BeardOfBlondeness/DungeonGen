package com.michaelpearcey.DungeonGen;

import java.awt.*;
import java.util.Random;

public class Player extends Sprite {

    private boolean[][] grid;
    private Point startPosition;

    public Player(boolean[][] grid) {
        super();
        startPosition = new Point();
        this.grid = grid;
        makeStartPos();
        init("res/wall.png", startPosition.x*40, startPosition.y*40, 10, 10);
    }

    private void makeStartPos() {
        Random r = new Random();
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                if(grid[i][j]){
                    startPosition.x = i;
                    startPosition.y = j;
                }
            }
        }
    }

}
