package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;
import java.util.Random;

public class CatBalou extends Card {
    public CatBalou(){
        super("CatBalou");
    }
    @Override
    public void use(Player currentPlayer,Player targetPlayer,  Board board, List<Player> players) {
        targetPlayer.getHand().remove(new Random().nextInt(targetPlayer.getHand().size()));
    }
}
