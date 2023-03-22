package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Beer extends Card {

    public Beer() {
        super("Beer");
    }

    @Override
    public void use(Player currentPlayer, Player targetPlayer, Board board, List<Player> players) {
        currentPlayer.increaseHealth(1);
    }
}