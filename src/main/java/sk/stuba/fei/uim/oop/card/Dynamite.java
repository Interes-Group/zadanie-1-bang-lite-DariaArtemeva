package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;
import java.util.Random;

public class Dynamite extends Card {
    public Dynamite() {
        super("Dynamite");
    }

    @Override
    public boolean requireTarget() {
        return true;
    }

    @Override
    public void use(Player user, Player target, Board board, List<Player> players) {
        target.setDynamite(this);
        System.out.println(target.getName() + " now has Dynamite in front of him.");
    }
}

