package Client.Logic;

import Client.Deck.Card;
import Client.Deck.Deck;
import Client.Enums.SuitEnum;
import Client.Players.Player;
import Client.Table.CommonCards;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EvaluatorTest {
    private static final int pot = 500;
    private static Evaluator evaluator;
    private static ArrayList<Player> players;
    private static CommonCards commonCards;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        players = new ArrayList<Player>();
        players.add(new Player("TestPlayer1", pot));
        players.add(new Player("TestPlayer2", pot));
        players.add(new Player("TestPlayer3", pot));
        players.add(new Player("TestPlayer4", pot));
        players.add(new Player("TestPlayer5", pot));

        commonCards = new CommonCards(new Deck(), 0);
    }

    @Test
    public void testRoyalFlush() {
        commonCards.clear();
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 1));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 13));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 12));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 11));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 10));

        evaluator = new Evaluator(players, commonCards);

        evaluator.doAnalyzeCards();
        assertEquals(Evaluator.ROYAL_FLUSH, evaluator.getCardPoints());
    }

    @Test
    public void testStraightFlush() {
        commonCards.clear();
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 10));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 9));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 8));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 7));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 6));

        evaluator = new Evaluator(players, commonCards);

        evaluator.doAnalyzeCards();
        assertEquals(Evaluator.STRAIGHT_FLUSH, evaluator.getCardPoints());
    }

    @Test
    public void testFour() {
        commonCards.clear();
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 6));
        commonCards.add(new Card(SuitEnum.CLUB.getSuit(), 6));
        commonCards.add(new Card(SuitEnum.SPADE.getSuit(), 6));
        commonCards.add(new Card(SuitEnum.DIAMOND.getSuit(), 6));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 12));

        evaluator = new Evaluator(players, commonCards);

        evaluator.doAnalyzeCards();
        assertEquals(Evaluator.FOUR_OF_A_KIND, evaluator.getCardPoints());
    }

    @Test
    public void testFullHouse() {
        commonCards.clear();
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 12));
        commonCards.add(new Card(SuitEnum.SPADE.getSuit(), 12));
        commonCards.add(new Card(SuitEnum.CLUB.getSuit(), 12));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 11));
        commonCards.add(new Card(SuitEnum.DIAMOND.getSuit(), 11));

        evaluator = new Evaluator(players, commonCards);

        evaluator.doAnalyzeCards();
        assertEquals(Evaluator.FULL_HOUSE, evaluator.getCardPoints());
    }

    @Test
    public void testFlush() {
        commonCards.clear();
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 2));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 3));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 12));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 11));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 10));

        evaluator = new Evaluator(players, commonCards);

        evaluator.doAnalyzeCards();
        assertEquals(Evaluator.FLUSH, evaluator.getCardPoints());
    }

    @Test
    public void testStraight() {
        commonCards.clear();
        commonCards.add(new Card(SuitEnum.CLUB.getSuit(), 4));
        commonCards.add(new Card(SuitEnum.DIAMOND.getSuit(), 6));
        commonCards.add(new Card(SuitEnum.SPADE.getSuit(), 5));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 8));
        commonCards.add(new Card(SuitEnum.DIAMOND.getSuit(), 7));

        evaluator = new Evaluator(players, commonCards);

        evaluator.doAnalyzeCards();
        assertEquals(Evaluator.STRAIGHT, evaluator.getCardPoints());
    }

    @Test
    public void testThree() {
        commonCards.clear();
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 10));
        commonCards.add(new Card(SuitEnum.SPADE.getSuit(), 10));
        commonCards.add(new Card(SuitEnum.DIAMOND.getSuit(), 10));
        commonCards.add(new Card(SuitEnum.DIAMOND.getSuit(), 3));
        commonCards.add(new Card(SuitEnum.DIAMOND.getSuit(), 13));

        evaluator = new Evaluator(players, commonCards);

        evaluator.doAnalyzeCards();
        assertEquals(Evaluator.THREE_OF_A_KIND, evaluator.getCardPoints());
    }

    @Test
    public void testTwoPair() {
        commonCards.clear();
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 4));
        commonCards.add(new Card(SuitEnum.SPADE.getSuit(), 4));
        commonCards.add(new Card(SuitEnum.DIAMOND.getSuit(), 7));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 7));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 3));

        evaluator = new Evaluator(players, commonCards);

        evaluator.doAnalyzeCards();
        assertEquals(Evaluator.TWO_PAIR, evaluator.getCardPoints());
    }

    @Test
    public void testPair() {
        commonCards.clear();
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 13));
        commonCards.add(new Card(SuitEnum.DIAMOND.getSuit(), 13));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 12));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 3));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 4));

        evaluator = new Evaluator(players, commonCards);

        evaluator.doAnalyzeCards();
        assertEquals(Evaluator.PAIR, evaluator.getCardPoints());
    }

    @Test
    public void testHigh() {
        commonCards.clear();
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 3));
        commonCards.add(new Card(SuitEnum.DIAMOND.getSuit(), 13));
        commonCards.add(new Card(SuitEnum.SPADE.getSuit(), 1));
        commonCards.add(new Card(SuitEnum.SPADE.getSuit(), 11));
        commonCards.add(new Card(SuitEnum.HEART.getSuit(), 10));

        evaluator = new Evaluator(players, commonCards);

        evaluator.doAnalyzeCards();
        assertEquals(Evaluator.HIGH_CARD, evaluator.getCardPoints());
    }
}
