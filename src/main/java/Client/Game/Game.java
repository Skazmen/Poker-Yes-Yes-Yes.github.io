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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Game {

    int hand, smallBlind, bigBlind;
    int current_player = 0;
    boolean game_end = false;
    int i;
    int round = 0;
    int win;
    Random rand;
    ArrayList<Player> Players;
    ArrayList<Seat> Seats;

    public Game(ArrayList<Player> Players, int hand, int smallBlind, int bigBlind) throws IOException {
        this.hand = hand;
        this.Players = Players;
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
        if(Players.size() < 2){
            System.out.println("SOMETHING WENT WRONG");
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

            Round round1 = new Round(Players, chipsController, cardDealing);
            round1.doRound();
            cardDealing.dealFlop();
            Round round2 = new Round(Players, chipsController, cardDealing);
            round2.doRound();
            cardDealing.dealRiver();
            Round round3 = new Round(Players, chipsController, cardDealing);
            round3.doRound();
            cardDealing.dealTurn();
            Round round4 = new Round(Players, chipsController, cardDealing);
            round4.doRound();

            for(i=0;i<Players.size();i++){
                if(Players.get(i).playingGame() && Players.get(i).playingRound())
                    Scores.set(i, evaluator.doAnalyzeCards(Players.get(i)));

            }
            win = Scores.indexOf(Collections.max(Scores));
            Players.get(win).setChips(chipsController.getPot());
            chipsController.finishRound();
            // WYGRYWA TEN()

        }
    }


    public Card randomCard(ArrayList<Card> myDeck) {
        rand = new Random();
        return myDeck.get(rand.nextInt(myDeck.size()));
    }





}