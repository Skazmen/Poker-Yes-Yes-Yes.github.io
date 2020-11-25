package Client.Game;

import Client.Controllers.CardDealing;
import Client.Controllers.ChipsController;
import Client.Players.Player;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Round {
    private ArrayList<Player> players;
    private LinkedList<Player> playerQueue;
    private ArrayList<Player> playersAbleToBet;
    private ChipsController chipsController;
    private CardDealing cardDealing;
    private static int roundCount = 1;
    private boolean stop_game = false;

    public Round(
            ArrayList<Player> players,
            ChipsController chipsController,
            CardDealing cardDealing
    ) {
        this.players = players;
        this.chipsController = chipsController;
        this.cardDealing = cardDealing;
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

    public static void resetRoundCount() {
        roundCount = 1;
    }

    public void doRound(Socket socket) throws IOException {
        if(roundCount == 1)
            chipsController.startRound(roundCount);

        PrintStream out = new PrintStream(socket.getOutputStream());

        boolean stopDoRound = ifRoundShouldBeStopped();

        while (!stopDoRound){
            Player currentPlayer = playerQueue.getFirst();
            Turn.turn(currentPlayer, chipsController, socket);

            stopDoRound = ifRoundShouldBeStopped();

            if (!stopDoRound){
                out.println("done");
            }

            playerQueue.removeFirst();
            playerQueue.addLast(currentPlayer);
        }

        roundCount++;
        resetQueue();

        for (Player player : players) {
            player.turnsInRound = 0;
        }

        chipsController.finishRound();
    }

    private void setSmallBlind(boolean isSmallBlind) {
        players.get(players.size() - 2).setSmallBlind(isSmallBlind);
    }

    private void setBigBlind(boolean isBigBlind) {
        players.get(players.size() - 1).setBigBlind(isBigBlind);
    }

    private boolean ifRoundShouldBeStopped() {
        ArrayList<Player> playersAbleToBet = new ArrayList<>();
        int firstBet;
        boolean sameBets = true;
        boolean stop = false;
        boolean bigBlindTurn = false;

        System.out.println("Pot: " + chipsController.getPot());

        for (Player player : players) {
            if (player.playingGame() && player.playingRound())
                if (player.getChips() > 0)
                    playersAbleToBet.add(player);

        }
        if (playersAbleToBet.size() <= 1) {
            stop = true;
            bigBlindTurn = true;
            stop_game = true;
        }
        else if (playersAbleToBet.size() >= 2) {
            firstBet = playersAbleToBet.get(0).getBet();
            sameBets = true;
            for (Player player : playersAbleToBet) {
                if(player.getBet() != firstBet) {
                    sameBets = false;
                    break;
                }
            }
            System.out.println("===========");
            for (Player player : players) {
                if(player.playingRound() && player.playingGame())
                System.out.println("Player's " + player.getName() + " bet: " + player.getBet());
            }
            System.out.println("===========");
            for (Player player : players) {
                if(player.isBigBlind())
                    if(player.turnsInRound > 0)
                        bigBlindTurn = true;
            }
        }

        if(sameBets && bigBlindTurn)
            stop = true;

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
        player.setPlayingRound(false);
    }

    public boolean get_stop(){
        return stop_game;
    }
}