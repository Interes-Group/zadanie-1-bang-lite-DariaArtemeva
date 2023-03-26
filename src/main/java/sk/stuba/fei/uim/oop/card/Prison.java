package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Prison extends Card {

    public Prison() {
        super("Prison");
    }

    @Override
    public boolean requireTarget() {
        return true;
    }

    @Override
    public void use(Player user, Player target, Board board, List<Player> players) {
        target.setInPrison(true);
        System.out.println(target.getName() + " is in prison.");
    }
}
