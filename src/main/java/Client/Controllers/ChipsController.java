package Client.Controllers;

import Client.Players.Player;

import java.util.ArrayList;

public class ChipsController {
    private ArrayList<Player> players;
    private int smallBlind;
    private int bigBlind;
    private int pot = 0;
    private int call = 0;
    private int bet = 0;
    private int raise = 0;

    public ChipsController(ArrayList<Player> players, int smallBlind, int bigBlind) {
        this.players = players;
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
    }

    public void startRound() {
        for (Player player : players) {
            if (player.isBigBlind()) {
                player.setChips(player.getChips() - bigBlind);
            } else if (player.isSmallBlind()) {
                player.setChips(player.getChips() - smallBlind);
            }
        }

        setCall(bigBlind);
        increasePot(bigBlind + smallBlind);
    }

    public void finishRound() {
        setPot(0);
    }

    private void setPot(int pot) {
        this.pot = pot;
    }

    public void increasePot(int pot) {
        this.pot += pot;
    }

    public int getPot() {
        return pot;
    }

    public void doCall(int call, Player player) {
        if (bet > 0 || raise > 0) {
            player.setChips(player.getChips() - call);
            increasePot(call);
        }
    }

    public void doBet(int bet, Player player) {
        player.setChips(player.getChips() - bet);
        setBet(bet);
        increaseRaise(bet);
        setCall(bet);
        increasePot(bet);
    }

    public void doRaise(int raise, Player player) {
        player.setChips(player.getChips() - raise);
        setRaise(raise);
        setCall(raise);
        increasePot(raise);
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getBet() {
        return bet;
    }

    public void increaseRaise(int raise) {
        this.raise += raise;
    }

    public void setRaise(int raise) {
        this.raise = raise;
    }

    public int getRaise() {
        return raise;
    }

    public void setCall(int call) {
        this.call = call;
    }

    public int getCall() {
        return call;
    }
}