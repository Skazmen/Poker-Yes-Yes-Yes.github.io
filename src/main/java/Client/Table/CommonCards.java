package Client.Table;

import Client.Deck.Card;
import Client.Deck.Deck;

import java.util.ArrayList;

public class CommonCards extends ArrayList<Card> {
    private Deck deck;
    private int deckIndex;
    private int index = 0;
    private ArrayList<Card> flop;
    private Card turn;
    private Card river;

    public CommonCards(Deck deck, int index) {
        this.deck = deck;
        this.deckIndex = index;
        this.setFlop();
        this.setTurn();
        this.setRiver();
    }

    private void setFlop() {
        flop = new ArrayList<Card>();
        int i;

        for (i = deckIndex; i < deckIndex + 3; i++) {
            this.add(deck.get(i));
            index++;
            flop.add(deck.get(i));
        }
        deckIndex = i;
    }

    private void setTurn() {
        turn = deck.get(deckIndex);
        this.add(turn);
        index++;
        deckIndex++;
    }

    private void setRiver() {
        river = deck.get(deckIndex);
        this.add(river);
    }
}