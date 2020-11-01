package Client.Deck;

import java.util.ArrayList;

public class Hand {
    private final Card card1;
    private final Card card2;

    public Hand(Card card1, Card card2) {
        this.card1 = card1;
        this.card2 = card2;
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    public ArrayList<Card> getHand(){
        ArrayList<Card> Hand = new ArrayList<Card>();
        Hand.add(getCard1());
        Hand.add(getCard2());

        return Hand;
    }
}