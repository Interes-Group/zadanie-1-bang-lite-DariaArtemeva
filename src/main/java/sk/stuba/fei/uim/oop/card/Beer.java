package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Beer extends Card {

    public Beer() {
        super("Beer");
    }

    @Override
    public void use(Player user, Player target, Board board, List<Player> players) {
        user.increaseHealth(1);
        System.out.println(user.getName() + " drank beer🍻 and got 1 health point.");
    }
}
