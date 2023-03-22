package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Prison extends Card {
    public Prison() {
        super("Prison");
    }

    @Override
    public void use(Player currentPlayer, Player targetPlayer, Board board, List<Player> players) {
        if (targetPlayer.hasPrison()) {
            System.out.println(targetPlayer.getName() + " already has a Prison card in front of them.");
        } else {
            targetPlayer.setPrison(this);
            System.out.println(currentPlayer.getName() + " put " + targetPlayer.getName() + " in prison.");
        }
    }
}