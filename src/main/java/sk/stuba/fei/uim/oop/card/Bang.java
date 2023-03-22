package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Bang extends Card{
    public Bang() {
        super("Bang");
    }

    @Override
    public void use(Player currentPlayer, Player targetPlayer, Board board, List<Player> players) {
        targetPlayer.decreaseHealth(1);
    }
}
