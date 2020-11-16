package Client.Players;

import Client.Deck.Card;
import Client.Deck.Hand;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @BeforeClass
    Player setPlayer() {
        Player player = new Player("Jasio", 15);

        return player;
    }

    @Test
    void getName() {
        Player player = setPlayer();
        assertEquals("Jasio", player.getName());
    }

    @Test
    void setChips() {
        Player player = setPlayer();
        player.setChips(-10);

        assertEquals(5, player.getChips());
    }

    @Test
    void getChips() {
        Player player = setPlayer();

        assertEquals(15, player.getChips());
    }

    @Test
    void getSetHand() {
        Player player = setPlayer();

        Card card1 = new Card("HEART", 2);
        Card card2 = new Card("DIAMOND", 3);

        Hand hand = new Hand(card1, card2);
        player.setHand(hand);

        assertEquals(player.getHand().getCard1(), card1);
        assertEquals(player.getHand().getCard2(), card2);
    }

    @Test
    void playerToString() {
        Player player = setPlayer();

        player.setBigBlind(true);
        player.setSmallBlind(false);

        assertEquals(player.playerToString(), "Jasio 15 Has small blind? false Has big blind? true");
    }

    @Test
    void blinds() {
        Player player = setPlayer();

        player.setSmallBlind(true);
        player.setBigBlind(true);

        assertEquals(player.isSmallBlind(), true);
        assertEquals(player.isBigBlind(), true);
    }
}