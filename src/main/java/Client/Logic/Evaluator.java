package Client.Logic;

import Client.Deck.Card;
import Client.Deck.Hand;
import Client.Players.Player;
import Client.Table.CommonCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Evaluator {
    public static final int ROYAL_FLUSH = 10;
    public static final int STRAIGHT_FLUSH = 9;
    public static final int FOUR_OF_A_KIND = 8;
    public static final int FULL_HOUSE = 7;
    public static final int FLUSH = 6;
    public static final int STRAIGHT = 5;
    public static final int THREE_OF_A_KIND = 4;
    public static final int TWO_PAIR = 3;
    public static final int PAIR = 2;
    public static final int HIGH_CARD = 1;

    private final HashMap<Player, Hand> hands;
    private final CommonCards commonCards;
    private final ArrayList<Player> players;
    private int points = 0;

    public Evaluator(ArrayList<Player> players, CommonCards commonCards) {
        this.players = players;
        hands = new HashMap<Player, Hand>();
        for (Player player : this.players) {
            hands.put(player, player.getHand());
        }
        this.commonCards = commonCards;
    }

    public int doAnalyzeCards(Player player) {
        ArrayList<Card> cards = new ArrayList<Card>();

        ArrayList<Card> cardsCommon = new ArrayList<Card>(commonCards);
        cardsCommon.add(player.getHand().getCard1());
        cardsCommon.add(player.getHand().getCard2());

        int[] queue = new int[105];
        setQueue(queue);

        for (int i = 0; i < 105; i += 5) {
            int points = 0;

            cards.add(cardsCommon.get(queue[i]));
            cards.add(cardsCommon.get(queue[i + 1]));
            cards.add(cardsCommon.get(queue[i + 2]));
            cards.add(cardsCommon.get(queue[i + 3]));
            cards.add(cardsCommon.get(queue[i + 4]));

            points = checkRoyalFlush(cards);
            if (points != ROYAL_FLUSH) {
                points = checkStraightFlush(cards);

                if (points != STRAIGHT_FLUSH) {
                    points = checkFlush(cards);

                    if (points != FLUSH) {
                        points = checkStraight(cards);

                        if (points != STRAIGHT) {
                            points = checkValueEquality(cards);
                            setCardPoints(points);

                            if (points == 0) {
                                points = checkHighCard(cards);
                                setCardPoints(points);//HIGH_CARD
                            }

                        } else {
                            setCardPoints(points);//STRAIGHT
                        }

                    } else {
                        setCardPoints(points);//FLUSH
                    }

                } else {
                    setCardPoints(points);//STRAIGHT_FLUSH
                }

            } else {
                setCardPoints(points);//ROYAL_FLUSH
            }

            cards.remove(cardsCommon.get(queue[i]));
            cards.remove(cardsCommon.get(queue[i + 1]));
            cards.remove(cardsCommon.get(queue[i + 2]));
            cards.remove(cardsCommon.get(queue[i + 3]));
            cards.remove(cardsCommon.get(queue[i + 4]));
        }

        return this.points;
    }

    private int checkRoyalFlush(ArrayList<Card> cards) {
        int points = 0;

        Collections.sort(cards, new CardComparator());

        if (checkFlush(cards) == FLUSH) {
            if (cards.get(0).getValue() == 1) {
                if (cards.get(1).getValue() == 10) {
                    if (cards.get(2).getValue() == 11) {
                        if (cards.get(3).getValue() == 12) {
                            if (cards.get(4).getValue() == 13) {
                                points = ROYAL_FLUSH;
                            }
                        }
                    }
                }
            }
        }

        return points;
    }

    private int checkStraightFlush(ArrayList<Card> cards) {
        int points = 0;

        if (checkStraight(cards) == STRAIGHT_FLUSH) {
            points = STRAIGHT_FLUSH;
        }

        return points;
    }

    private int checkFlush(ArrayList<Card> cards) {
        ArrayList<Card> sameSuit = new ArrayList<Card>();

        boolean isFlush = false;
        if (cards.size() >= 5) {
            for (int i = 0; i < cards.size() - 1; i++) {
                String suit1 = cards.get(i).getSuit();
                for (int j = i + 1; j < cards.size(); j++) {
                    String suit2 = cards.get(j).getSuit();
                    if (suit1.equals(suit2)
                            && !sameSuit.contains(cards.get(j))) {
                        if (!sameSuit.contains(cards.get(i))) {
                            sameSuit.add(cards.get(i));
                        }
                        sameSuit.add(cards.get(j));
                    }
                }
                if (sameSuit.size() > 0 && sameSuit.size() < 5) {
                    sameSuit.clear();
                }
                if (sameSuit.size() >= 5) {
                    isFlush = true;
                    break;
                }
            }
        }

        int points = 0;

        if (isFlush) {
            if (this.points == STRAIGHT) {
                points = STRAIGHT_FLUSH;
            } else {
                points = FLUSH;
            }
        }

        return points;
    }

    private int checkStraight(ArrayList<Card> cards) {
        Collections.sort(cards, new CardComparator());
        int count = 1;
        int points = 0;

        for (int i = 0; i < cards.size() - 1; i++) {
            int firstVal = cards.get(i).getValue();
            int nextVal = cards.get(i + 1).getValue();

            if (firstVal == (nextVal - 1)) {
                count++;
            }

            if (count == 5) {
                points = STRAIGHT;
                break;
            }
        }

        if (points == STRAIGHT) {
            this.points = STRAIGHT;
            if (checkFlush(cards) == STRAIGHT_FLUSH) {
                points = STRAIGHT_FLUSH;
            }
        }

        return points;
    }

    private int checkValueEquality(ArrayList<Card> cards) {
        ArrayList<Card> rest = new ArrayList<Card>();
        ArrayList<Card> pair1 = new ArrayList<Card>();
        ArrayList<Card> pair2 = new ArrayList<Card>();
        int points = 0;

        if (cards.size() >= 4) {
            pair1 = checkPair(cards);

            if (pair1.size() == 2)
                points = PAIR;

            for (Card card : cards) {
                if (!pair1.contains(card)) {
                    rest.add(card);
                }
            }

            if (points == PAIR) {
                pair2 = checkPair(rest);

                if (pair2.size() == 2)
                    points = TWO_PAIR;

                ArrayList<Card> threeOfAKind = new ArrayList<Card>();
                for (int i = 0; i < rest.size(); i++) {
                    if (rest.get(i).getValue()
                            == pair1.get(0).getValue()) {
                        points = THREE_OF_A_KIND;
                    }

                    if (cards.size() >= 5) {
                        if (i < rest.size() - 1) {
                            if (rest.get(i).getValue()
                                    == rest.get(i + 1).getValue()) {
                                if (!threeOfAKind.contains(rest.get(i))) {
                                    threeOfAKind.add(rest.get(i));
                                }

                                if (!threeOfAKind.contains(rest.get(i + 1))) {
                                    threeOfAKind.add(rest.get(i + 1));
                                }
                            }
                        }
                    }
                }

                if (threeOfAKind.size() == 3 && pair1.size() == 2) {
                    points = FULL_HOUSE;
                    return points;
                }
            }

            for (int i = 0; i < pair1.size() && i < pair2.size(); i++) {
                if (pair1.get(i).getValue() == pair2.get(i).getValue()) {
                    points = FOUR_OF_A_KIND;
                    return points;
                }
            }
        }

        return points;
    }

    private ArrayList<Card> checkPair(ArrayList<Card> cards) {
        ArrayList<Card> pair = new ArrayList<Card>();

        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (!cards.get(i).equals(cards.get(j)) &&
                        cards.get(i).getValue() == cards.get(j).getValue()) {
                    pair.add(cards.get(i));
                    pair.add(cards.get(j));
                    break;
                }
            }
            if (pair.size() == 2) {
                break;
            }
        }

        return pair;
    }

    private int checkHighCard(ArrayList<Card> cards) {
        int max = 0;
        int points = 0;

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getValue() > max) {
                max = cards.get(i).getValue();
                points = HIGH_CARD;
            }
        }

        return points;
    }

    private void setCardPoints(int commonCardPoints) {
        if (this.points < commonCardPoints) {
            this.points = commonCardPoints;
        }
    }

    private void setQueue(int[] queue) {
        queue[0] = 0;
        queue[1] = 1;
        queue[2] = 2;
        queue[3] = 3;
        queue[4] = 4;

        queue[5] = 0;
        queue[6] = 1;
        queue[7] = 2;
        queue[8] = 3;
        queue[9] = 5;

        queue[10] = 0;
        queue[11] = 1;
        queue[12] = 2;
        queue[13] = 4;
        queue[14] = 5;

        queue[15] = 0;
        queue[16] = 1;
        queue[17] = 2;
        queue[18] = 4;
        queue[19] = 6;

        queue[20] = 0;
        queue[21] = 1;
        queue[22] = 2;
        queue[23] = 5;
        queue[24] = 6;

        queue[25] = 0;
        queue[26] = 1;
        queue[27] = 3;
        queue[28] = 4;
        queue[29] = 5;

        queue[30] = 0;
        queue[31] = 1;
        queue[32] = 3;
        queue[33] = 4;
        queue[34] = 6;

        queue[35] = 0;
        queue[36] = 1;
        queue[37] = 3;
        queue[38] = 5;
        queue[39] = 6;

        queue[40] = 0;
        queue[41] = 1;
        queue[42] = 4;
        queue[43] = 5;
        queue[44] = 6;


        queue[45] = 0;
        queue[46] = 2;
        queue[47] = 3;
        queue[48] = 4;
        queue[49] = 5;

        queue[50] = 0;
        queue[51] = 2;
        queue[52] = 3;
        queue[53] = 4;
        queue[54] = 6;

        queue[55] = 0;
        queue[56] = 2;
        queue[57] = 3;
        queue[58] = 5;
        queue[59] = 6;

        queue[60] = 0;
        queue[61] = 2;
        queue[62] = 4;
        queue[63] = 5;
        queue[64] = 6;

        queue[65] = 0;
        queue[66] = 3;
        queue[67] = 4;
        queue[68] = 5;
        queue[69] = 6;

        queue[70] = 1;
        queue[71] = 2;
        queue[72] = 3;
        queue[73] = 4;
        queue[74] = 5;

        queue[75] = 1;
        queue[76] = 2;
        queue[77] = 3;
        queue[78] = 4;
        queue[79] = 6;

        queue[80] = 1;
        queue[81] = 2;
        queue[82] = 3;
        queue[83] = 5;
        queue[84] = 6;

        queue[85] = 1;
        queue[86] = 2;
        queue[87] = 4;
        queue[88] = 5;
        queue[89] = 6;

        queue[90] = 1;
        queue[91] = 3;
        queue[92] = 4;
        queue[93] = 5;
        queue[94] = 6;

        queue[95] = 2;
        queue[96] = 3;
        queue[97] = 4;
        queue[98] = 5;
        queue[99] = 6;

        queue[100] = 0;
        queue[101] = 1;
        queue[102] = 2;
        queue[103] = 3;
        queue[104] = 6;
    }
}