package Client.Controllers;

import Client.Game.Round;
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

    public void startRound(int roundCount) {
        if(roundCount == 1) {

            for (Player player : players) {
                if (player.isBigBlind()) {
                    //player.setChips(player.getChips() - bigBlind);
                    doBet(bigBlind, player);
                } else if (player.isSmallBlind()) {
                    //player.setChips(player.getChips() - smallBlind);
                    doBet(smallBlind, player);
                }
            }

            setCall(bigBlind);
        }
    }

    public void finishRound() {
        for(Player player : players){
            player.setBet(0);
        }
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public void increasePot(int pot) {
        this.pot += pot;
    }

    public int getPot() {
        return pot;
    }

    public void doCall(Player player) {
        int max = findMaxBet(players);
        int chips = player.getChips() + player.getBet();
        if(chips >= max){
            player.setChips(-(max - player.getBet()));
            increasePot(max - player.getBet());
            player.setBet(max);
        }
        else{
            player.setBet(player.getBet() + player.getChips());
            increasePot(player.getChips());
            player.setChips(-(player.getChips()));
        }
    }

    public void doBet(int bet, Player player) {

        int max = findMaxBet(players);
        if(bet > player.getChips())
            bet = player.getChips() + max;
        if(max == 0){
            player.setChips(-bet);
            player.setBet(player.getBet() + bet);
            increaseRaise(bet);
            setCall(bet);
            increasePot(bet);
        }
        else {
            if(bet == max){
                player.setChips(-bet);
                player.setBet(player.getBet() + bet);
                increaseRaise(bet);
                setCall(bet);
                increasePot(bet);
            }
            else if(bet < max){
                doFold(player);
            }
            else {
                doRaise(bet + player.getBet() - max, player);
            }

        }
    }

    public void doRaise(int raise, Player player) {
        int max = findMaxBet(players);
        player.setChips( -(max + raise - player.getBet()));
        setRaise(raise);
        setCall(raise);
        increasePot(max + raise - player.getBet());
        player.setBet(max + raise);
    }

    public void doCheck(Player player) {
        int max = findMaxBet(players);
    }

    public void doFold(Player player) {
        int max = findMaxBet(players);
        if(max == 0){
            doCheck(player);
        }
        else{
            player.setPlayingRound(false);
        }
    }

    public int findMaxBet(ArrayList<Player> players) {
        int max = 0;
        for(Player player : players){
            if(player.getBet() > max)
                max = player.getBet();

        }
        return max;
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