package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;

public class Indians extends Card {
    public Indians() {
        super("Indians");
    }

    @Override
    public void use(Player players) {

        players.decreaseHealth(1);
    }

}

