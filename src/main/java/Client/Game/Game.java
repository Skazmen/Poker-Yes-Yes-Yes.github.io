package Client.Game;

import Client.Controllers.CardDealing;
import Client.Controllers.ChipsController;
import Client.Deck.Card;
import Client.Deck.Deck;
import Client.Deck.Hand;
import Client.Players.Player;
import Client.Table.Seat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    int hand, smallBlind, bigBlind;
    int current_player = 0;
    //int i;
    int round = 0;
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
        Deck deck = new Deck();
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
        // KTOWYGRAŁ()

    }


    public Card randomCard(ArrayList<Card> myDeck) {
        rand = new Random();
        return myDeck.get(rand.nextInt(myDeck.size()));
    }





}