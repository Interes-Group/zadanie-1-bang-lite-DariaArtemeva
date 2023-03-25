package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Dynamite extends Card {
    public Dynamite() {
        super("Dynamite");
    }

    @Override
    public void use(Player currentPlayer, Player targetPlayer, Board board, List<Player> players) {
        if (targetPlayer.hasDynamite()) {
            System.out.println(targetPlayer.getName() + " already has a Dynamite card in front of them.");
        } else {
            targetPlayer.setDynamite(this);
            System.out.println(currentPlayer.getName() + " put " + targetPlayer.getName() + " in front of Dynamite.");
        }
    }
}
