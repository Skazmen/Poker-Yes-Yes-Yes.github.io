package Client.Players;

import Client.Deck.Hand;

public class Player {
    private final String name;
    private int chips;
    private Hand hand;
    private boolean isSmallBlind = false;
    private boolean isBigBlind = false;

    public Player(String name, int chips) {
        this.name = name;
        this.setChips(chips);
    }

    public String getName() {
        return name;
    }

    public void setChips(int chips) {
        this.chips += chips;
    }

    public int getChips() {
        return chips;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public String playerToString() {
        return name + " "
                + chips + " "
                + "Has small blind? " + isSmallBlind()
                + " Has big blind? " + isBigBlind();
    }

    public void setSmallBlind(boolean isSmallBlind) {
        this.isSmallBlind = isSmallBlind;
    }

    public boolean isSmallBlind() {
        return isSmallBlind;
    }

    public void setBigBlind(boolean isBigBlind) {
        this.isBigBlind = isBigBlind;
    }

    public boolean isBigBlind() {
        return isBigBlind;
    }
}