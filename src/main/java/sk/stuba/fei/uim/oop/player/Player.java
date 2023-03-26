package sk.stuba.fei.uim.oop.player;

import java.util.ArrayList;
import java.util.List;


import sk.stuba.fei.uim.oop.card.Barrel;
import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.card.Dynamite;
import sk.stuba.fei.uim.oop.card.Prison;

public class Player {
    private final String name;
    private int health;
    private final List<Card> hand;
    private Prison prison;
    private Dynamite dynamite;
    private Barrel barrel;
    public Player(String name) {
        this.name = name;
        this.health = 4;
        this.hand = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public int getHealth() {

        return health;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    public void increaseHealth(int amount) {
        health += amount;
        if (health > 3) {
            health = 3;
        }
    }

    public List<Card> getHand() {

        return hand;
    }

    public void addToHand(Card card) {

        hand.add(card);
    }

    public void removeFromHand(Card card) {

        hand.remove(card);
    }


    public void setDynamite(Dynamite dynamite) {
        this.dynamite = dynamite;
    }

    public boolean hasDynamite() {
        return dynamite != null;
    }

    public void removeDynamite() {
        dynamite = null;
    }
    public Barrel getBarrel() {
        return barrel;
    }

    public void setBarrel(Barrel barrel) {
        this.barrel = barrel;
    }

    public boolean hasBarrel() {
        return barrel != null;
    }

    public boolean isAlive() {
        return health > 0;
    }


    public void removePrison() {
        prison = null;
    }


    private boolean inPrison;

    public boolean isInPrison() {
        return inPrison;
    }

    public void setInPrison(boolean inPrison) {
        this.inPrison = inPrison;
    }
    public boolean defendAgainstIndians() {
        Card cardToDiscard = null;
        for (Card card : hand) {
            if (card.getName().equals("BANG")) {
                cardToDiscard = card;
                break;
            }
        }

        if (cardToDiscard != null) {
            hand.remove(cardToDiscard);
            return true;
        }

        return false;
    }
    @Override
    public String toString() {

        return name + " (Health: " + health + ")";
    }
    public void discardCards(Player player) {
        if (player.getHand().size() > player.getHealth()) {
            int numCardsToRemove = player.getHand().size() - player.getHealth();
            for (int i = 0; i < numCardsToRemove; i++) {
                int randomIndex = (int) (Math.random() * player.getHand().size());
                Card cardToRemove = player.getHand().get(randomIndex);
                player.removeFromHand(cardToRemove);
            }
        }
    }}
