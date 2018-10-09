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
        loadGrid();
    }

    private void defaultGrid() {
        for(int i = 0; i < gridX; i++) {
            for(int j = 0; j < gridY; j++) {
                grid[i][j] = false;
            }
        }
    }

    private void loadGrid() {
        int startX = 0;
        int startY = 0;
        Random ran = new Random();

        /////SETTING THE DEFAULT ROOM OF 3X3///////
        for(int i = startX; i < 3; i++) {
            for(int j = startY; j < 3; j++) {
                if(i < 30 && j < 20 && i > 0 && j > 0) {
                    grid[i][j] = true;
                    takenPoints.add(new Point(i, j));
                }
            }
        }

        /////BUILDING OUTER ROOMS OR PATHS///////
        while(takenPoints.size() < 420) {
            /////////make a path////////
            if(ran.nextInt(5) <= 3) {
                int pathWidth = ran.nextInt(6) - 3;
                int pathLength = ran.nextInt(20) - 10;
                int ranN = ran.nextInt(takenPoints.size());
                int pathX = takenPoints.get(ranN).x;
                int pathY = takenPoints.get(ran.nextInt(takenPoints.size())).y;
                int endX;
                int endY;
                int changeX, changeY;
                if(pathWidth != 0 && pathLength != 0) {
                    if(ran.nextInt(2) <= 1) {
                        endX = pathWidth + pathX;
                        endY = pathLength + pathY;
                    } else {
                        endX = pathLength + pathX;
                        endY = pathWidth + pathY;
                    }
                    if(endX < pathX) {
                        changeX = -1;
                    } else {
                        changeX = 1;
                    }
                    if(endY < pathY) {
                        changeY = -1;
                    } else {
                        changeY = 1;
                    }
                    for(int i = pathX; i < endX; i+=changeX) {
                        for(int j = pathY; j < endY; j+=changeY) {
                            if(i < 30 && j < 20 && i > 0 && j > 0) {
                                grid[i][j] = true;
                                takenPoints.add(new Point(i, j));
                            }
                        }
                    }
                }
            }
            ////////MAKE A ROOM/////////
            else {
                int roomWidth = ran.nextInt(24) - 12;
                int roomHeight = ran.nextInt(24) - 12;
                int ranN = ran.nextInt(takenPoints.size());
                int startRoomX = takenPoints.get(ranN).x;
                int startRoomY = takenPoints.get(ranN).y;
                int changeX, changeY;
                if(roomWidth != 0 && roomHeight != 0) {
                    if(roomWidth < 0) {
                        changeX = -1;
                    } else {
                        changeX = 1;
                    }
                    if(roomHeight < 0) {
                        changeY = -1;
                    } else {
                        changeY = 1;
                    }
                    for(int i = startRoomX; i < roomWidth + startRoomX; i+=changeX) {
                        for(int j = startRoomY; j < roomHeight + startRoomY; j+=changeY) {
                            if(i < 30 && j < 20 && i > 0 && j > 0) {
                                grid[i][j] = true;
                                takenPoints.add(new Point(i, j));
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean[][] getGrid() {
        return grid;
    }
}
