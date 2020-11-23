package Client.Game;

import Client.Controllers.CardDealing;
import Client.Controllers.ChipsController;
import Client.Players.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Round {
    private ArrayList<Player> players;
    private Queue<Player> playerQueue;
    private ArrayList<Player> playersAbleToBet;
    private ChipsController chipsController;
    private CardDealing cardDealing;
    private int iterator = 0;
    private static int roundCount;

    public Round(
            ArrayList<Player> players,
            ChipsController chipsController,
            CardDealing cardDealing
    ) {
        this.players = players;
        this.chipsController = chipsController;
        this.cardDealing = cardDealing;

        resetRoundCount();
        this.setQueue();
    }

    private void setQueue() {
        this.playerQueue = new LinkedList<Player>(this.players);
        this.setBigBlind(true);
        this.setSmallBlind(true);
    }

    private void resetQueue() {
        this.setBigBlind(false);
        this.setSmallBlind(false);

        Player temp = this.players.get(this.players.size() - 1);
        this.players.remove(this.players.size() - 1);
        this.players.add(0, temp);
        this.playerQueue = new LinkedList<Player>(this.players);

        this.setBigBlind(true);
        this.setSmallBlind(true);
    }

    private static void resetRoundCount() {
        roundCount = 0;
    }

    public void doRound() throws IOException {
        roundCount++;
        chipsController.startRound();

        for (Player player : players) {
            System.out.println(player.toString());
        }
        while (!ifRoundShouldBeStopped()){
            Player currentPlayer = playerQueue.element(iterator)
            iterator++;
            Turn turn = new Turn(currentPlayer);


        }






        while (playerQueue.peek() != null) {
            Player currentPlayer = playerQueue.poll();

            Turn.turn(currentPlayer);
        }

        if (roundCount == 1) {
            cardDealing.dealFlop();
        } else if (roundCount == 2) {
            cardDealing.dealRiver();
        } else if (roundCount == 3) {
            cardDealing.dealTurn();
        } else if (roundCount > 3) {
            resetRoundCount();
        }

        resetQueue();
    }

    private void setSmallBlind(boolean isSmallBlind) {
        players.get(players.size() - 2).setSmallBlind(isSmallBlind);
    }

    private void setBigBlind(boolean isBigBlind) {
        players.get(players.size() - 1).setBigBlind(isBigBlind);
    }

    private boolean ifRoundShouldBeStopped() {

        int firstBet;
        boolean sameBets = true;
        boolean stop = false;
        for (Player player : players) {
            if (player.playingGame && player.playingRound)
                if (player.chips != 0)
                    playersAbleToBet.add(player);

        }
        if (playersAbleToBet.size() <= 1) {
            stop = true;
        }
        else if (playersAbleToBet.size() >= 2) {
            firstBet = playersAbleToBet.get(0).bet;
            for (Player player : players) {
                if(player.bet != firstBet)
                    sameBets = false;
                    break;
            }
            for (Player player : players) {
                if(player.isBigBlind())
                    if(player.turnsInRound > 0)
                        if(sameBets)
                            stop = true;
            }
        }
        return stop;
    }

    public void bet(int bet, Player player) {
        player.setChips(-bet);
        chipsController.increasePot(bet);
    }

    public void call(int call, Player player) {
        player.setChips(-call);
        chipsController.increasePot(call);
    }

    public static void fold(Player player) {
        players.remove(player);
    }
}