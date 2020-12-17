package Client.Game;

import Client.Controllers.CardDealing;
import Client.Controllers.ChipsController;
import Client.Deck.Deck;
import Client.Logic.Evaluator;
import Client.Players.Player;
import Client.Table.CommonCards;
import Client.Table.Seat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static Client.Game.Round.resetRoundCount;

public class Game {

    int chips, smallBlind, bigBlind;
    int current_player = 0;
    int i;
    int win;

    boolean game_end = false;

    ArrayList<Player> playerList = new ArrayList<>();
    ArrayList<Seat> Seats = new ArrayList<>();

    public Game(int players_count, int chips, int smallBlind, int bigBlind) throws IOException {
        this.chips = chips;

        for (int i = 0; i < players_count; i++) {
            this.playerList.add(new Player(Integer.toString(i), chips));
        }

        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;

        if(playerList.size() < 2){
            System.out.println("We lost our players to play with");
        }
        java.util.Collections.shuffle(playerList);
        for(Player player : playerList) {
            Seat s = new Seat(current_player, player);
            Seats.add(s);
            current_player++;
        }

        Socket server = new Socket("localhost", 6967);
        PrintStream out = new PrintStream(server.getOutputStream());

        while(!game_end) {
            ArrayList<Integer> Scores = new ArrayList<>(Arrays.asList(new Integer[8]));
            Collections.fill(Scores,0);
            Deck deck = new Deck();

            CommonCards commonCards = new CommonCards(deck, 0);
            Evaluator evaluator = new Evaluator(playerList, commonCards);
            ChipsController chipsController = new ChipsController(playerList, smallBlind, bigBlind );
            CardDealing cardDealing = new CardDealing(playerList, deck);

            StringBuilder message;
            chipsController.setPot(0);

            message = cardDealing.dealPlayerHand();
            out.println(message);
            System.out.println(message);

            System.out.println("1. ROUND BEGINS");
            Round round1 = new Round(playerList, chipsController, cardDealing);
            round1.doRound(server);

            if(!round1.get_stop()){
                message = cardDealing.dealFlop();
                out.println(message);
                System.out.println(message);

                System.out.println("2. ROUND BEGINS");
                Round round2 = new Round(playerList, chipsController, cardDealing);
                round2.doRound(server);

                if(!round2.get_stop()){
                    message = cardDealing.dealTurn();
                    out.println(message);
                    System.out.println(message);

                    System.out.println("3. ROUND BEGINS");
                    Round round3 = new Round(playerList, chipsController, cardDealing);
                    round3.doRound(server);

                    if(!round3.get_stop()){
                        message = cardDealing.dealRiver();
                        out.println(message);
                        System.out.println(message);

                        System.out.println("ZACZYNA SIE RUNDA 4");
                        Round round4 = new Round(playerList, chipsController, cardDealing);
                        round4.doRound(server);
                    }
                }
            }


            for(i=0; i< playerList.size(); i++) {
                if (playerList.get(i).playingGame() && playerList.get(i).playingRound()) {
                    Scores.set(i, evaluator.doAnalyzeCards(playerList.get(i)));
                    System.out.println(Scores.get(i));
                }
            }

            win = Scores.indexOf(Collections.max(Scores));
            ArrayList<Player> winners = new ArrayList<>();
            ArrayList<Integer> winnerIndexList = new ArrayList<Integer>();

            for(i=0; i< playerList.size(); i++){
                if(Scores.get(i) == Scores.get(win)){
                    System.out.println(Scores.get(i) + " " + Scores.get(win));
                    System.out.println("ktory raz");
                    winners.add(playerList.get(i));
                    winnerIndexList.add(i);
                }
            }

            for(i=0; i< winners.size(); i++){
                if(i != 0){
                    System.out.println("ORAZ");
                }

                System.out.println("WYGRAL GRACZ");
                System.out.println(playerList.get(winnerIndexList.get(i)).getName());

                winners.get(i).setChips(Math.round(chipsController.getPot()/winners.size()));
            }
            System.out.println("JAKA WYGRANA?");
            System.out.println(Math.round(chipsController.getPot()/winners.size()));

            for(Player player : playerList){
                if(player.playingGame())
                    player.setPlayingRound(true);
            }

            resetRoundCount();
            chipsController.finishRound();
        }
    }
}