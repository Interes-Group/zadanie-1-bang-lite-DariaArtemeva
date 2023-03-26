package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.board.Board;

import java.util.List;

public class Stagecoach extends Card {

    public Stagecoach() {
        super("Stagecoach");
    }

    @Override
    public void use(Player user, Player target, Board board, List<Player> players) {
        for (int i = 0; i < 2; i++) {
            user.addToHand(board.draw());
        }
        System.out.println(user.getName() + " used Stagecoach card and drew 2 cards from the deck.");
    }
}
