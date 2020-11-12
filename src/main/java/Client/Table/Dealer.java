package Client.Table;

import Client.Deck.Deck;

import java.util.Collections;

public class Dealer {
    private final Deck deck;

    public Dealer(Deck deck) {
        this.deck = new Deck();
    }

    public void shuffleDeck(Deck deck) {
        Collections.shuffle(deck);
    }

    public Deck getDeck() {
        return this.deck;
    }
}
