package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;
import java.util.Random;

public class CatBalou extends Card {

    public CatBalou() {
        super("Cat Balou");
    }

    @Override
    public boolean requireTarget() {
        return true;
    }

    @Override
    public void use(Player user, Player target, Board board, List<Player> players) {
        System.out.println(user.getName() + " played Cat Balou card on " + target.getName() + ".");
        List<Card> targetHand = target.getHand();

        if (!targetHand.isEmpty()) {
            Random random = new Random();
            int cardIndex = random.nextInt(targetHand.size());
            Card cardToRemove = targetHand.get(cardIndex);
            target.removeFromHand(cardToRemove);
            System.out.println(target.getName() + " lost card: " + cardToRemove.getName() + ".");
        } else {
            System.out.println(target.getName() + " has no cards in hand.");
        }
    }
}
