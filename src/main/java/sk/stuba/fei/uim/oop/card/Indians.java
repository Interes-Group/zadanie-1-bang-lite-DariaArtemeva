package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Indians extends Card {
    public Indians() {
        super("Indians");
    }

    @Override
    public void use(Player currentPlayer,Player targetPlayer, Board board, List<Player> players) {
        for (Player player : players) {
            if (player != currentPlayer && player.isAlive()) {
                boolean isShootCard = false;
                for (Card card : player.getHand()) {
                    if (card instanceof Bang) {
                        player.removeFromHand(card);
                        System.out.println(player.getName() + " used the bang card to prevent an attack of Indians");
                        isShootCard = true;
                        break;
                    }
                }
                if (!isShootCard) {
                    System.out.println(player.getName() + " lost 1 health point because of the Indians attack");
                    player.decreaseHealth(1);
                    if (!player.isAlive()) {
                        System.out.println(player.getName() + " is out of the game");
                    }
                }
            }
        }
    }
}
