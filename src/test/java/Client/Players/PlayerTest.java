package Client.Players;

import Client.Deck.Card;
import Client.Deck.Hand;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @BeforeClass
    void set(){
        Player gracz = new Player("Jasio", 15);
    }

    @Test
    void getName() {
        Player gracz = new Player("Jasio", 15);

        assertEquals("Jasio", gracz.getName());
    }

    @Test
    void setChips() {
        Player gracz = new Player("Jasio", 15);
        gracz.setChips(-10);

        assertEquals(5, gracz.getChips());
    }

    @Test
    void getChips() {
        Player gracz = new Player("Jasio", 15);

        assertEquals(15, gracz.getChips());
    }

    @Test
    void getSetHand() {
        Player player = new Player("Jasio", 15);

        Card card1 = new Card("HEART", 2);
        Card card2 = new Card("DIAMOND", 3);

        Hand hand = new Hand(card1, card2);
        player.setHand(hand);

        assertEquals(player.getHand().getCard1(), card1);
        assertEquals(player.getHand().getCard2(), card2);
    }

    @Test
    void playerToString() {
        Player player = new Player("Jasio", 15);
        player.setBigBlind(true);
        player.setSmallBlind(false);

        assertEquals(player.playerToString(), "Jasio 15 Has small blind? false Has big blind? true");
    }

    @Test
    void blinds() {
        Player player = new Player("Jasio", 15);
        player.setSmallBlind(true);
        player.setBigBlind(true);

        assertEquals(player.isSmallBlind(), true);
        assertEquals(player.isBigBlind(), true);

    }
}