package sk.stuba.fei.uim.oop.game;
import sk.stuba.fei.uim.oop.card.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import sk.stuba.fei.uim.oop.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players;
    private final Board board;
    public int currentPlayerIndex;
    private boolean gameOver;

    public Game(int numPlayers) {
        players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            String playerName = ZKlavesnice.readString("Enter name of the player №" + i + ": ");
            players.add(new Player(playerName));
        }
        board = new Board();
        currentPlayerIndex = 0;
        gameOver = false;
    }

    public void dealCards() {
        board.shuffle();
        for (int i = 0; i < 4; i++) {
            for (Player player : players) {
                player.addToHand(board.draw());
            }
        }
    }
    public void announceWinner() {
        for (Player player : players) {
            if (player.isAlive()) {
                System.out.println(player.getName() + " wins the game!");
                return;
            }
        }

        System.out.println("It's a draw!");
    }
    public void checkGameOver() {
        int alivePlayers = 0;
        for (Player player : players) {
            if (player.isAlive()) {
                alivePlayers++;
            }
        }

        if (alivePlayers <= 1) {
            gameOver = true;
        }
    }
    public void playGame() {
        while (!gameOver) {
            Player currentPlayer = players.get(currentPlayerIndex);

            System.out.println("It's \u001B[31m" + currentPlayer.getName() + "\u001B[0m's turn.");
            System.out.println("You have " + currentPlayer.getHealth() + " lives");

            Turn turn = new Turn(currentPlayer, players, board);
            turn.execute(this);

            if (currentPlayer.hasDynamite()) {
                int roll = (int) (Math.random() * 8);
                if (roll == 0) {
                    System.out.println("Dynamite explodes! " + currentPlayer.getName() + " loses 3 health points.");
                    currentPlayer.decreaseHealth(3);
                    currentPlayer.removeDynamite();
                } else {
                    System.out.println("Dynamite doesn't explode. it goes to the next player.");
                    currentPlayer.removeDynamite();
                    int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
                    Player nextPlayer = players.get(nextPlayerIndex);
                    nextPlayer.setDynamite(new Dynamite());
                }
            }

            if (!currentPlayer.isAlive()) {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                continue;
            }
        }

        announceWinner();
    }}





