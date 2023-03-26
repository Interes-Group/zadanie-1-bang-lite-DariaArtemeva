package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.card.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class Turn {
    private final Player currentPlayer;
    private final List<Player> players;
    private final Board board;

    public Turn(Player currentPlayer, List<Player> players, Board board) {
        this.currentPlayer = currentPlayer;
        this.players = players;
        this.board = board;
    }
    public void checkPrison() {
        if (currentPlayer.isInPrison()) {
            int escapeChance = (int) (Math.random() * 4);
            if (escapeChance == 0) {
                System.out.println(currentPlayer.getName() + " has escaped from prison");
                currentPlayer.removePrison();
            } else {
                System.out.println(currentPlayer.getName() + " is still in prison and skips turn.");
            }
        }
    }
    public void drawCards() {
        for (int i = 0; i < 2; i++) {
            currentPlayer.addToHand(board.draw());
        }
    }


    public void playCards() {
        List<Card> hand = currentPlayer.getHand();

        while (!hand.isEmpty()) {
            System.out.print("Your hand: \n");
            for (int i = 0; i < hand.size(); i++) {
                System.out.print((i + 1) + "." + hand.get(i).getName() + "\n");
            }
            System.out.println();
            int cardIndex = ZKlavesnice.readInt("Select a card to play 1-" + hand.size() + " or any other number to skip: ") - 1;

            if (cardIndex < 0 || cardIndex >= hand.size()) {
                break;
            } else {
                Card card = hand.get(cardIndex);

                if (card.requireTarget()) {
                    int targetIndex = ZKlavesnice.readInt("Select a player to target 1-" + players.size() + ": ") - 1;
                    if (targetIndex < 0 || targetIndex >= players.size() || players.get(targetIndex) == currentPlayer) {
                        System.out.println("Invalid player.");
                    } else {
                        Player targetPlayer = players.get(targetIndex);
                        card.use(currentPlayer, targetPlayer, board, players);
                        currentPlayer.removeFromHand(card);
                    }
                } else {
                    card.use(currentPlayer, null, board, players);
                    currentPlayer.removeFromHand(card);
                }
            }
        }
    }





    public void endTurn(Game game) {
        currentPlayer.discardCards(currentPlayer);
        game.checkGameOver();
        game.currentPlayerIndex = (game.currentPlayerIndex + 1) % players.size();
        System.out.println("════════════════════════════════════════════════════");
    }

    public void execute(Game game) {
        checkPrison();
        if (!currentPlayer.isInPrison()) {
            drawCards();
            playCards();
        }
        endTurn(game);
    }}