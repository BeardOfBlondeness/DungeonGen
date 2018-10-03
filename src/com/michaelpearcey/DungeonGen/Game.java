package com.michaelpearcey.DungeonGen;

import java.awt.Point;

public class Game {

    private int currentLevel;
    private Level l;

    public Game() {
        currentLevel = 0;
        l = new Level();
    }

    public void run() {
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
        }
    }

}
