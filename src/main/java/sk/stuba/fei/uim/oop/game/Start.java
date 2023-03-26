package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.utility.ZKlavesnice;


public class Start {

    public void startGame() {
        System.out.println("Welcome to BANG game");
        int numPlayers = ZKlavesnice.readInt("Enter number of players 2-4: ");
        while (numPlayers < 2 || numPlayers > 4) {
            System.out.println("\n" + "Invalid number of players");
            numPlayers = ZKlavesnice.readInt("Enter number of players 2-4: ");
        }

        Game game = new Game(numPlayers);
        game.dealCards();
        game.playGame();
    }
}
