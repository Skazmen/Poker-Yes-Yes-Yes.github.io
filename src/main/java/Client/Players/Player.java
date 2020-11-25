package Client.Players;

import Client.Deck.Hand;

public class Player {
    private final String name;
    private int chips;
    private Hand hand;
    private int bet = 0;
    private boolean playingGame = true;
    private boolean playingRound = true;
    private boolean isSmallBlind = false;
    private boolean isBigBlind = false;
    public int turnsInRound = 0;

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

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }


    public void betChips(int chips) {

        if(chips <= this.chips){
            this.chips -= chips;
            this.bet += bet;
        }
        else
            System.out.printf("ZA MAŁY BILANS");

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

    public void setPlayingRound(boolean playingRound) {
        this.playingRound = playingRound;
    }

    public boolean playingRound() {
        return playingRound;
    }

    public void setPlayingGame(boolean playingGame) {
        this.playingGame = playingGame;
    }

    public boolean playingGame() {
        return playingGame;
    }


}

