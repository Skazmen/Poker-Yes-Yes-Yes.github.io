package Client.Deck;
import Client.Enums.SuitEnum;

import java.util.ArrayList;

public class Deck extends ArrayList<Card>{
    public Deck() {
        heart();
        diamond();
        spade();
        club();
    }

    private void club() {
        for(int i = 1; i <= 13; i++) {
            this.add(new Card(SuitEnum.CLUB.getSuit(), i));
        }
    }

    private void spade() {
        for(int i = 1; i <= 13; i++) {
            this.add(new Card(SuitEnum.SPADE.getSuit(), i));
        }
    }

    private void diamond() {
        for(int i = 1; i <= 13; i++) {
            this.add(new Card(SuitEnum.DIAMOND.getSuit(), i));
        }
    }

    private void heart() {
        for(int i = 1; i <= 13; i++) {
            this.add(new Card(SuitEnum.HEART.getSuit(), i));
        }
    }
}
