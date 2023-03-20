package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.player.Player;
import java.util.Random;

public class CatBalou extends Card {
    public CatBalou(){
        super("CatBalou");
    }
    @Override
    public void use(Player targetPlayer) {
        targetPlayer.getHand().remove(new Random().nextInt(targetPlayer.getHand().size()));
    }
}
