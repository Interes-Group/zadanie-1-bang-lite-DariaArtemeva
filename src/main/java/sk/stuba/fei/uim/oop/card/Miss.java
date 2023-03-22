package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Miss extends Card {
    public Miss() {
        super("Miss");
    }

    @Override
    public void use(Player currentPlayer,Player targetPlayer,  Board board, List<Player> players) {
        throw new UnsupportedOperationException("Miss card cannot be used directly by a player.");
    }

    public boolean defend() {
        return true;
    }
}