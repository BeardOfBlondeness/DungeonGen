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
}
