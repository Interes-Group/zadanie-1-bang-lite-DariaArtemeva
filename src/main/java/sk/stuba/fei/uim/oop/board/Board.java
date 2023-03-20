package sk.stuba.fei.uim.oop.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sk.stuba.fei.uim.oop.card.*;
public class Board {
    private final List<Card> cards;

    public Board() {
        cards = new ArrayList<Card>();
        for (int i = 0; i < 10; i++) {
            cards.add(new Bang());
        }
        for (int i = 0; i < 8; i++) {
            cards.add(new Beer());
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new Stagecoach());
        }
        for (int i = 0; i < 5; i++) {
            cards.add(new Miss());
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Indians());
        }
        for (int i = 0; i < 6; i++) {
            cards.add(new CatBalou());
        }
    }


    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.remove(0);
    }
}
