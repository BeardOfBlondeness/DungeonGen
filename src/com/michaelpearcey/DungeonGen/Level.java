package com.michaelpearcey.DungeonGen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Level {

    private final int gridX = 30;
    private final int gridY = 20;
    private boolean[][] grid;
    private ArrayList<Point> takenPoints;

    public Level() {
        grid = new boolean[gridX][gridY];
        takenPoints = new ArrayList<Point>();
        defaultGrid();
    }

    private void defaultGrid() {
        for(int i = 0; i < gridX; i++) {
            for(int j = 0; j < gridY; j++) {
                grid[i][j] = false;
            }
        }
    }

    private void loadGrid() {
        int startX = gridX/2;
        int startY = gridY/2;
        Random ran = new Random();

        /////SETTING THE DEFAULT ROOM OF 3X3///////
        for(int i = startX - 1; i < startX + 1; i++) {
            for(int j = startY - 1; j < startY + 1; j++) {
                grid[i][j] =  true;
                takenPoints.add(new Point(i, j));
            }
        }

        /////BUILDING OUTER ROOMS OR PATHS///////
        while(takenPoints.size() < 420) {
            /////////make a path////////
            if(ran.nextInt(5) <= 1) {
                int pathWidth = ran.nextInt(3) + 1;
                int pathLength = ran.nextInt(10) + 1;
                int pathX = takenPoints.get(ran.nextInt(takenPoints.size())).x;
                int pathY = takenPoints.get(ran.nextInt(takenPoints.size())).y;
                int endX;
                int endY;
                if(ran.nextInt(2) <= 1) {
                    endX = pathWidth;
                    endY = pathLength;
                } else {
                    endX = pathLength;
                    endY = pathWidth;
                }
                for(int i = pathX; i < endX; i++) {
                    for(int j = pathY; j < endY; j++) {
                        grid[i][j] = true;
                        takenPoints.add(new Point(i, j));
                    }
                }
            }
            ////////MAKE A ROOM/////////
            else {

            }
        }
    }
}
