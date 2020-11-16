package Client.Logic;

import Client.Players.Player;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardComparatorTest {
    @BeforeClass
    void set(){
        Player gracz = new Player("Jasio", 15);
    }

    @Test
    void getName() {
        Player gracz = new Player("Jasio", 15);

        assertEquals("Jasio", gracz.getName());
    }
}