package Client.Game;

import Client.Controllers.CardDealing;
import Client.Controllers.ChipsController;
import Client.Deck.Card;
import Client.Deck.Deck;
import Client.Deck.Hand;
import Client.Logic.Evaluator;
import Client.Players.Player;
import Client.Table.CommonCards;
import Client.Table.Seat;
import Client.Players.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static Client.Game.Round.resetRoundCount;

public class Game {

    int chips, smallBlind, bigBlind;
    int current_player = 0;
    int i;
    int win;

    boolean game_end = false;

    Random rand;

    ArrayList<Player> Players = new ArrayList<>();
    ArrayList<Seat> Seats = new ArrayList<>();

    public Game(int players_count, int chips, int smallBlind, int bigBlind) throws IOException {
        this.chips = chips;

        for (int i = 0; i < players_count; i++) {
            this.Players.add(new Player(Integer.toString(i), chips));
        }

        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;

        if(Players.size() < 2){
            System.out.println("We lost our players to play with");
        }
        java.util.Collections.shuffle(Players);
        for(Player player : Players) {
            Seat s = new Seat(current_player, player);
            Seats.add(s);
            current_player++;
        }

        while(!game_end) {
            ArrayList<Integer> Scores = new ArrayList<>(Arrays.asList(new Integer[8]));
            Collections.fill(Scores,0);
            Deck deck = new Deck();
            CommonCards commonCards = new CommonCards(deck, 0);
            Evaluator evaluator = new Evaluator(Players, commonCards);
            ChipsController chipsController = new ChipsController(Players, smallBlind, bigBlind );
            CardDealing cardDealing = new CardDealing(Players, deck);
            StringBuilder message;
            chipsController.setPot(0);

            Socket s = new Socket("localhost", 6967);
            PrintStream out = new PrintStream(s.getOutputStream());

            message = cardDealing.dealPlayerHand();
            // WYSLIJ INFO O KARTACH KAZDEGO GRACZA
            out.println(message);
            System.out.println(message);

            System.out.println("ZACZYNA SIE RUNDA 1");
            Round round1 = new Round(Players, chipsController, cardDealing);
            round1.doRound(s);


            message = cardDealing.dealFlop();
            // WYSLIJ INFO O FLOPIE
            out.println(message);
            System.out.println(message);

            System.out.println("ZACZYNA SIE RUNDA 2");
            Round round2 = new Round(Players, chipsController, cardDealing);
            round2.doRound(s);

            message = cardDealing.dealRiver();
            // WYSLIJ INFO O RIVERZE
            out.println(message);
            System.out.println(message);

            System.out.println("ZACZYNA SIE RUNDA 3");
            Round round3 = new Round(Players, chipsController, cardDealing);
            round3.doRound(s);


            message = cardDealing.dealTurn();
            // WYSLIJ INFO O TURNIE
            out.println(message);
            System.out.println(message);

            System.out.println("ZACZYNA SIE RUNDA 4");
            Round round4 = new Round(Players, chipsController, cardDealing);
            round4.doRound(s);

            for(i=0;i<Players.size();i++){
                if(Players.get(i).playingGame() && Players.get(i).playingRound())
                    Scores.set(i, evaluator.doAnalyzeCards(Players.get(i)));

            }

            win = Scores.indexOf(Collections.max(Scores));
            Players.get(win).setChips(chipsController.getPot());

            System.out.println("WYGRAL GRACZ");
            System.out.println(Players.get(win).getName());
            System.out.println("ILE WYGRAL?");
            System.out.println(chipsController.getPot());
            // WYSLIJ INFO KTO WYGRAL

            for(Player player : Players){
                if(player.playingGame())
                    player.setPlayingRound(true);
            }
            resetRoundCount();
            //Player tempPlayer = Players.get(0);
            //Players.remove(0);
            //Players.add(tempPlayer);

            chipsController.finishRound();
        }
    }
}