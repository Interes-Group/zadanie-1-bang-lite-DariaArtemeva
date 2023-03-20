package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;

public class Beer extends Card {

    public Beer() {
        super("Beer");
    }

    @Override
    public void use(Player currentPlayer) {
        currentPlayer.increaseHealth(1);
    }
}