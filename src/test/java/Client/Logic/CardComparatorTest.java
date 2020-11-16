package Client.Logic;

import Client.Deck.Card;
import Client.Deck.Hand;
import Client.Enums.SuitEnum;
import Client.Players.Player;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

class CardComparatorTest {
    @BeforeClass
    Hand setHand(){
        Random random = new Random();
        int value1 = random.nextInt(100);
        int value2 = random.nextInt(100);

        Card card1 = new Card (SuitEnum.HEART.getSuit(), value1);
        Card card2 = new Card (SuitEnum.HEART.getSuit(), value1 - value2);
        Hand hand = new Hand(card1,card2);

        return hand;
    }

    @Test
    void testComparator() {
        Hand hand = setHand();
        CardComparator cc = new CardComparator();
        int result = cc.compare(hand.getCard1(), hand.getCard2());

        assertEquals(result, 1);
    }
}