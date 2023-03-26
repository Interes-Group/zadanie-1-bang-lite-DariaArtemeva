package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public abstract class Card {
    private final String name;

    public Card(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
    public boolean requireTarget() {
        return false;
    }

    public abstract void use(Player currentPlayer, Player targetPlayer, Board board, List<Player> players);}

