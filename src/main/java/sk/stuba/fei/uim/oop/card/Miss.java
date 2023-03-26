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
        System.out.println("Miss card cannot be played.");
    }

    public boolean defend() {
        return true;
    }
}