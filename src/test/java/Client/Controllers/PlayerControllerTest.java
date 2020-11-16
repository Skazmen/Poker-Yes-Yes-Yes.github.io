package Client.Controllers;

import Client.Helper;
import Client.Players.Player;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerControllerTest {
    @BeforeClass
    PlayerController setUp() {
        Player player1 = new Player(Helper.getRandomString(41), Helper.getRandom(10000));
        Player player2 = new Player(Helper.getRandomString(41), Helper.getRandom(10000));;
        Player player3 = new Player(Helper.getRandomString(41), Helper.getRandom(10000));
        Player player4 = new Player(Helper.getRandomString(41), Helper.getRandom(10000));

        ArrayList<Player> list = new ArrayList<>();

        list.add(player1);
        list.add(player2);
        list.add(player3);
        list.add(player4);

        PlayerController pC = new PlayerController(list);

        return pC;
    }

    @Test
    void addPlayer() {
        PlayerController pC = setUp();
        PlayerController pC2 = setUp();

        Player player1 = new Player(Helper.getRandomString(41), Helper.getRandom(10000));
        pC.addPlayer(player1);

        assertEquals(pC.getPlayers().size(), pC2.getPlayers().size() + 1);
    }

    @Test
    void removePlayer() {
        PlayerController pC = setUp();
        PlayerController pC2 = setUp();

        Player player1 = new Player(Helper.getRandomString(41), Helper.getRandom(10000));
        pC.removePlayer(player1);

        assertEquals(pC.getPlayers().size(), pC2.getPlayers().size());
    }
}