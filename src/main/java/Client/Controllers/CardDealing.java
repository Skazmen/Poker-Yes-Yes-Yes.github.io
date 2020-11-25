package Client.Controllers;

import Client.Deck.Deck;
import Client.Deck.Hand;
import Client.Players.Player;
import Client.Table.CommonCards;

import java.util.ArrayList;

public class CardDealing {
    private final ArrayList<Player> players;
    private final Deck deck;
    private int index;
    private CommonCards commonCards;

    public CardDealing(ArrayList<Player> players, Deck deck) {
        this.players = players;
        this.deck = deck;
        this.index = 0;
        java.util.Collections.shuffle(deck);
    }

    public StringBuilder dealPlayerHand() {
        StringBuilder str = new StringBuilder("");

        if(index < 52) {
            for(Player player : players) {
                player.setHand(new Hand(deck.get(index), deck.get(index+1)));
                index += 2;

                System.out.println(player.getName() + ":" + player.getHand().getCard1().getSuit() + player.getHand().getCard1().getValue() + "," + player.getHand().getCard2().getSuit() + player.getHand().getCard2().getValue() + ";");
                str.append(player.getName() + ":" + player.getHand().getCard1().getSuit() + player.getHand().getCard1().getValue() + "," + player.getHand().getCard2().getSuit() + player.getHand().getCard2().getValue() + ";");
            }
        }

        commonCards = new CommonCards(deck, index);

        return str ;
    }

    public void dealFlop() {
        for(int i = 0; i < 3; i++) {
            System.out.println(commonCards.get(i).toString());
        }
    }

    public void dealRiver() {
        for(int i = 0; i < 4; i++) {
            System.out.println(commonCards.get(i).toString());
        }
    }

    public void dealTurn() {
        for(int i = 0; i < commonCards.size(); i++) {
            System.out.println(commonCards.get(i).toString());
        }
    }
}