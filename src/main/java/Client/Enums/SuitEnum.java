package Client.Enums;

public enum SuitEnum {
    HEART ("HEART"),
    DIAMOND ("DIAMOND"),
    SPADE ("SPADE"),
    CLUB ("CLUB");

    private final String suit;

    SuitEnum(final String suit) {
        this.suit = suit;
    }

    public String getSuit(){
        return suit;
    }
}
