package com.michaelpearcey.DungeonGen;

import java.awt.Point;

public class Game {

    private int currentLevel;
    private Level l;
    private boolean[][] grid;
    private Sprite[][] tiles;

    public Game() {
        currentLevel = 0;
        l = new Level();
        grid = l.getGrid();
        tiles = new Sprite[30][20];
        stageGrid();
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
        /*
        boolean grid[][] = l.getGrid();
        for(int j = 0; j < 20; j++) {
            for(int i = 0; i < 30; i++) {
                if(grid[i][j]) {
                    System.out.print("#");
                } else {
                    System.out.print("-");
                }
            }
            System.out.print("\n");
        }*/
        for(int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid[1].length; j++) {
                tiles[i][j].draw();
            }
        }
    }

}
