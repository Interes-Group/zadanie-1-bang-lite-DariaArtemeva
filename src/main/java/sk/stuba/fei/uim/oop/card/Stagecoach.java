package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.board.Board;
public class Stagecoach extends Card {
    public Stagecoach() {

        super("Stagecoach");
    }

    @Override
    public void use(Player currentPlayer) {
        Board deck = new Board();
        for(int i=0; i<2;i++) {
            currentPlayer.addToHand(deck.draw());
        }
    }
}