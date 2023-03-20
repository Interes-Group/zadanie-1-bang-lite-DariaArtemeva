package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.player.Player;
public abstract class Card {
    private final String name;

    public Card(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public abstract void use(Player player);
}

