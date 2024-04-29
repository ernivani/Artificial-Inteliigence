package fr.ernicani;


import fr.ernicani.Graphics.Game;

public class Main {
    public static void main(String[] args) {
        Game game = Game.getWindow();
        game.init();
        Thread thread = new Thread(game);
        thread.start();

    }

}