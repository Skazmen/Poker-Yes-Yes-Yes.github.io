package Client.Game;

import Client.Deck.Card;
import Client.Deck.Deck;
import Client.Deck.Hand;
import Client.Players.Player;
import Client.Table.Seat;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    int hand = 10;
    int current_player = 0;
    int i;
    int round = 0;
    Random rand;
    ArrayList<Player> Players;
    ArrayList<Seat> Seats;

    public Game(ArrayList<Player> Players, int hand) {
        this.hand = hand;
        this.Players = Players;
        if(Players.size() < 2){
            System.out.println("COŚ POSZŁO NIE TAK")
        }
        java.util.Collections.shuffle(Players);
        for(Player player : Players){
            Seat s = new Seat(current_player, player);
            Seats.add(s);
            current_player++;
        }
        for(i=0; i<Players.size(); i++){

        }

    }
    private Round(){

        Deck deck = new Deck();
        for(Player player : Players){

            Card card1;
            Card card2;
            card1 = randomCard(deck);
            deck.remove(card1);
            card2 = randomCard(deck);
            deck.remove(card2);
            Hand playerHand;
            playerHand = Hand(card1, card2);
            player.setHand(playerHand);

        }

        round++;
    }

    public Card randomCard(ArrayList<Card> mydeck) {
        rand = new Random();
        Card randomCard = mydeck.get(rand.nextInt(mydeck.size()));
        return randomCard;
    }





}