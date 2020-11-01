package Client.Controllers;

import Client.Players.Player;

import java.util.ArrayList;
import java.util.Collections;

//TODO separate to server section?
public class PlayerController {
    private ArrayList<Player> players;

    public PlayerController(ArrayList<Player> players) {
        this.setPlayers(players);
        Collections.shuffle(players);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
