package Client.Logic;

import Client.Deck.Card;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        int val1 = card1.getValue();
        int val2 = card2.getValue();

        if (val1 > val2)
            return 1;
        else if (val1 < val2)
            return -1;
        else
            return 0;
    }
}
