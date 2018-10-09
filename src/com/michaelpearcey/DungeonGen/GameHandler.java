package com.michaelpearcey.DungeonGen;

public class GameHandler {

    private boolean inGame = true;
    private Game g;
    private Menu m;

    public GameHandler() {
        g = new Game();
        m = new Menu();
    }

    public void run() {
        if(inGame) {
            g.run();
        }
    }

    public void zoom(boolean zoomIn) {
        g.zoom(zoomIn);
    }

    public void move(int direction) {
        g.move(direction);
    }

    public void moveP(int direction) {
        g.moveP(direction);
    }
}
