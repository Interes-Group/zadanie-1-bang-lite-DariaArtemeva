package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.player.Player;

public class Bang extends Card{
    public Bang() {
        super("Bang");
    }

    @Override
    public void use(Player targetPlayer) {
        targetPlayer.decreaseHealth(1);
    }
}
