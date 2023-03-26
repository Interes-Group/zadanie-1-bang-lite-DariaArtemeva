package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;


public class Barrel extends Card {

    public Barrel() {
        super("Barrel");
    }

    @Override
    public boolean requireTarget() {
        return false;
    }

    @Override
    public void use(Player user, Player target, Board board, List<Player> players) {
        user.setBarrel(this);
        System.out.println(user.getName() + " has placed a Barrel card in front of him.");
    }

    public boolean blocksShot() {
        int roll = (int) (Math.random() * 4);
        return roll == 0;
    }
}
