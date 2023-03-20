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
    private final boolean gameOver;

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

    private void IndiansCard(Player currentPlayer) {
        for (Player player : players) {
            if (player != currentPlayer && player.isAlive()) {
                boolean isShootCard = false;
                for (Card card : player.getHand()) {
                    if (card instanceof Bang) {
                        player.removeFromHand(card);
                        System.out.println(player.getName() + " used the bang card to prevent an attack of Indians");
                        isShootCard = true;
                        break;
                    }
                }
                if (!isShootCard){
                    System.out.println(player.getName() + " lost 1 health point because of the Indians attack");
                    if (!player.isAlive()) {
                        System.out.println(player.getName() + " is out of the game");
                    }
                }
            }
        }
    }

    public void playGame() {
        while (!gameOver) {

            Player currentPlayer = players.get(currentPlayerIndex);
            if (!currentPlayer.isAlive()) {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                continue;
            }
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            System.out.println("You have " + currentPlayer.getHealth() + " lives");
            System.out.print("Your hand: \n");
            List<Card> hand = currentPlayer.getHand();
            for (int i = 0; i < hand.size(); i++) {
                System.out.print((i + 1) + "." + hand.get(i).getName() + "\n");
            }
            System.out.println();

            currentPlayer.addToHand(board.draw());
            boolean hasPlayedCard = false;
            while (!hasPlayedCard) {
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
                                System.out.println("Miss card not found in player's hand. And" + targetPlayer.getName() + " loses 1 health point.");
                                card.use(targetPlayer);
                                currentPlayer.removeFromHand(card);
                                System.out.println(currentPlayer.getName() + " shot " + targetPlayer.getName() + ".\n");
                                hasPlayedCard = true;
                            }

                        }
                    } else if (card instanceof Beer) {
                        card.use(currentPlayer);
                        currentPlayer.removeFromHand(card);
                        System.out.println(currentPlayer.getName() + " drank beer🍻.");
                        hasPlayedCard = true;

                    } else if (card instanceof Stagecoach) {
                        card.use(currentPlayer);
                        currentPlayer.removeFromHand(card);
                        System.out.println(currentPlayer.getName() + " took 2 cards from deck.");
                        currentPlayer.discardCards(currentPlayer);
                        hasPlayedCard = true;
                    } else if (card instanceof Indians) {
                        IndiansCard(currentPlayer);
                        currentPlayer.removeFromHand(card);
                        System.out.println(currentPlayer.getName() + " Indians attack, you better protect yourself!");
                        currentPlayer.discardCards(currentPlayer);
                        hasPlayedCard = true;
                    }else if (card instanceof CatBalou) {
                        int targetIndex = ZKlavesnice.readInt("Select a player to  1-" + players.size() + ": ") - 1;
                        if (targetIndex < 0 || targetIndex >= players.size() || targetIndex == currentPlayerIndex) {
                            System.out.println("Invalid player.");
                        } else {
                            Player targetPlayer = players.get(targetIndex);
                            card.use(targetPlayer);
                            currentPlayer.removeFromHand(card);
                            System.out.println(currentPlayer.getName() + " removed a card from " + targetPlayer.getName() + "'s hand.\n");
                            hasPlayedCard = true;
                        }
                    }

                }

            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            System.out.println("════════════════════════════════════════════════════");
        }
    }
}

//TODO: конец игры
//TODO: исправить ошибку при окончании коллоды


