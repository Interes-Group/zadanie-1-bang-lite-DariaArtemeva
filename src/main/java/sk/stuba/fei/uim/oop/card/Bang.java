package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Bang extends Card {
    public Bang() {
        super("BANG");
    }
    public boolean requireTarget() {
        return true;
    }
    @Override

    public void use(Player currentPlayer, Player targetPlayer, Board board, List<Player> players) {

        boolean shotBlocked = false;
        if (targetPlayer.hasBarrel()) {
            Barrel barrel = targetPlayer.getBarrel();
            shotBlocked = barrel.blocksShot();
            if (shotBlocked) {
                System.out.println(targetPlayer.getName() + "'s Barrel card blocked the bang shot!");
            }
        }

        if (!shotBlocked) {
            Miss missCard = null;
            for (Card targetCard : targetPlayer.getHand()) {
                if (targetCard instanceof Miss) {
                    missCard = (Miss) targetCard;
                    break;
                }
            }

            if (missCard != null) {
                boolean defenseSuccessful = missCard.defend();
                if (defenseSuccessful) {
                    System.out.println("Miss card defended a player against BANG attack");
                    targetPlayer.removeFromHand(missCard);
                }
            } else {
                System.out.println(targetPlayer.getName() + " lose 1 live.");
                targetPlayer.decreaseHealth(1);
                System.out.println(currentPlayer.getName() + " shot " + targetPlayer.getName() + ".\n");
            }
        }
    }
}
