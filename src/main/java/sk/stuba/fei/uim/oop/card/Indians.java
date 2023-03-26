package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Indians extends Card {

    public Indians() {
        super("Indians");
    }

    @Override
    public boolean requireTarget() {
        return false;
    }

    @Override
    public void use(Player user, Player target, Board board, List<Player> players) {
        System.out.println(user.getName() + " played Indians card.");

        for (Player player : players) {
            if (player != user) {
                boolean defended = player.defendAgainstIndians();

                if (!defended) {
                    System.out.println(player.getName() + " failed to defend against Indians attack and loses 1 health point.");
                    player.decreaseHealth(1);
                } else {
                    System.out.println(player.getName() + " defended against Indians attack.");
                }
            }
        }
    }
}
