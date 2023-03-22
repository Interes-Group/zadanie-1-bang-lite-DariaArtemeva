package sk.stuba.fei.uim.oop;
import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Assignment1 {
    public static void main(String[] args) {
        System.out.println("Welcome to BANG game");
        int numPlayers = ZKlavesnice.readInt("Enter number of players 2-4: ");
        while (numPlayers < 2 || numPlayers > 4) {
            System.out.println("\n" + "Invalis number of players");
            numPlayers = ZKlavesnice.readInt("Enter number of players 2-4: ");
        }

        Game game = new Game(numPlayers);
        game.dealCards();
        game.playGame();
    }
    }
