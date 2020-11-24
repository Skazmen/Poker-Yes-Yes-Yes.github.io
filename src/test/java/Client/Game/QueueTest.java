package Client.Game;

import Client.Controllers.PlayerController;
import Client.Helper;
import Client.Players.Player;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import Client.Game.Round;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueueTest {


    @Test
    void testQueue() {


        Player player1 = new Player("a", 1000);
        Player player2 = new Player("b", 1000);;
        Player player3 = new Player("c", 1000);
        Player player4 = new Player("d", 1000);

        ArrayList<Player> list = new ArrayList<>();

        list.add(player1);
        list.add(player2);
        list.add(player3);
        list.add(player4);

        LinkedList<Player> playerQueue = new LinkedList<Player>(list);
        int i;
        Player test;
        ArrayList<String> wynik = new ArrayList<String>();
        ArrayList<String> wynik2 = new ArrayList<String>();
        wynik2.add("a");
        wynik2.add("b");
        wynik2.add("c");
        wynik2.add("d");
        wynik2.add("a");

        for(i=0;i<5;i++){
            test = playerQueue.getFirst();
            playerQueue.removeFirst();
            playerQueue.addLast(test);
            wynik.add(test.getName());
        }
        assertEquals(wynik, wynik2);

    }

}