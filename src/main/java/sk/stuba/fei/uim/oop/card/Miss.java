package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.player.Player;

public class Miss extends Card {
    public Miss() {
        super("Miss");
    }

    @Override
    public void use(Player targetPlayer) {
        throw new UnsupportedOperationException("Miss card cannot be used directly by a player.");
    }

    public boolean defend() {
        return true;
    }
}