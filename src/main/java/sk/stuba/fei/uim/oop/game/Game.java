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
    private int currentPlayerIndex;
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
    private void announceWinner() {
        for (Player player : players) {
            if (player.isAlive()) {
                System.out.println(player.getName() + " wins the game!");
                return;
            }
        }

        System.out.println("It's a draw!");
    }
    private void checkGameOver() {
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
            if (currentPlayer.hasDynamite()) {
                int explosionChance = 8;
                int roll = (int) (Math.random() * explosionChance) + 1;
                if (roll == 1) {
                    System.out.println("Dynamite explodes! " + currentPlayer.getName() + " loses 3 health points.");
                    currentPlayer.decreaseHealth(3);
                    currentPlayer.removeDynamite();
                } else {
                    System.out.println("Dynamite doesn't explode. Passing it to the next player.");
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
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            if (currentPlayer.hasPrison()) {
                int escapeChance = (int) (Math.random() * 4) + 1;
                if (escapeChance == 1) {
                    System.out.println(currentPlayer.getName() + " has escaped from prison!");
                    currentPlayer.removePrison();
                } else {
                    System.out.println(currentPlayer.getName() + " is still in prison and skips their turn.");
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                    continue;
                }
            }
            System.out.println("You have " + currentPlayer.getHealth() + " lives");
            for (int i = 0; i < 2; i++) {
                currentPlayer.addToHand(board.draw());
            }
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

                    if (card instanceof Bang) {
                        int targetIndex = ZKlavesnice.readInt("Select a player to shoot 1-" + players.size() + ": ") - 1;
                        if (targetIndex < 0 || targetIndex >= players.size() || targetIndex == currentPlayerIndex) {
                            System.out.println("Invalid player.");
                        } else {
                            Player targetPlayer = players.get(targetIndex);
                            boolean shotBlocked = false;
                            if (targetPlayer.hasBarrel()) {
                                Barrel barrel = targetPlayer.getBarrel();
                                shotBlocked = barrel.blocksShot();
                                if (shotBlocked) {
                                    System.out.println(targetPlayer.getName() + "'s Barrel card blocked the shot!");
                                }
                            }

                            if (!shotBlocked) {
                                Miss missCard = null;
                                for (Card targetCard : targetPlayer.getHand()) {
                                    if (targetCard instanceof Miss) {
                                        missCard = (Miss) targetCard;
                                        break;
                                    }
                                }

                                if (missCard != null) {
                                    boolean defenseSuccessful = missCard.defend();
                                    if (defenseSuccessful) {
                                        System.out.println("Miss card successfully defended against BANG attack!");
                                        targetPlayer.removeFromHand(missCard);
                                    }
                                } else {
                                    System.out.println(targetPlayer.getName() + " loses 1 health point.");
                                    card.use(currentPlayer, targetPlayer, board, players);
                                    currentPlayer.removeFromHand(card);
                                    System.out.println(currentPlayer.getName() + " shot " + targetPlayer.getName() + ".\n");
                                }
                            }
                        }
                    } else if (card instanceof Beer) {
                        card.use(currentPlayer, null, board, players);
                        currentPlayer.removeFromHand(card);
                        System.out.println(currentPlayer.getName() + " drank beer🍻.");

                    } else if (card instanceof Dynamite) {
                        int targetIndex = ZKlavesnice.readInt("Select a player to put in front of Dynamite 1-" + players.size() + ": ") - 1;
                        if (targetIndex < 0 || targetIndex >= players.size() || targetIndex == currentPlayerIndex) {
                            System.out.println("Invalid player.");
                        } else {
                            Player targetPlayer = players.get(targetIndex);
                            card.use(currentPlayer, targetPlayer, board, players);
                            currentPlayer.removeFromHand(card);
                        }
                    }
                    else if (card instanceof Stagecoach) {
                        card.use(currentPlayer, null, board, players);
                        currentPlayer.removeFromHand(card);
                        System.out.println(currentPlayer.getName() + " took 2 cards from deck.");

                    }else if (card instanceof Prison) {
                        int targetIndex = ZKlavesnice.readInt("Select a player to put in prison 1-" + players.size() + ": ") - 1;
                        if (targetIndex < 0 || targetIndex >= players.size() || targetIndex == currentPlayerIndex) {
                            System.out.println("Invalid player.");
                        } else {
                            Player targetPlayer = players.get(targetIndex);
                            card.use(currentPlayer, targetPlayer, board, players);
                            currentPlayer.removeFromHand(card);
                        }
                    }
                    else if (card instanceof Indians) {
                        card.use(currentPlayer,null, board, players);
                        currentPlayer.removeFromHand(card);
                        System.out.println(currentPlayer.getName() + " Indians attack, you better protect yourself!");
                    }else if (card instanceof CatBalou) {
                        int targetIndex = ZKlavesnice.readInt("Select a player to  1-" + players.size() + ": ") - 1;
                        if (targetIndex < 0 || targetIndex >= players.size() || targetIndex == currentPlayerIndex) {
                            System.out.println("Invalid player.");
                        } else {
                            Player targetPlayer = players.get(targetIndex);
                            card.use(currentPlayer,targetPlayer, board, players);
                            currentPlayer.removeFromHand(card);
                            System.out.println(currentPlayer.getName() + " removed a card from " + targetPlayer.getName() + "'s hand.\n");

                        }
                        currentPlayer.discardCards(currentPlayer);

                }

            }

        }
            checkGameOver();
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            System.out.println("════════════════════════════════════════════════════");
    }
        announceWinner();
}}




