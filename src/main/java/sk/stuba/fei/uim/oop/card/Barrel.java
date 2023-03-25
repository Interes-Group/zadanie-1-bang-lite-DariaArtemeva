package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Barrel extends Card {
    public Barrel() {
        super("Barrel");
    }

    @Override
    public void use(Player currentPlayer, Player targetPlayer, Board board, List<Player> players) {
        if (currentPlayer.hasBarrel()) {
            System.out.println(currentPlayer.getName() + " already has a Barrel card in front of them.");
        } else {
            currentPlayer.setBarrel(this);
            System.out.println(currentPlayer.getName() + " put a Barrel card in front of them.");
        }
    }

    public boolean blocksShot() {
        int chance = (int) (Math.random() * 4);
        return chance == 0;
    }
}
