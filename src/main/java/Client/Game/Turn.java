package Client.Game;

import Client.Players.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Client.Controllers.ChipsController;

public class Turn {
    public static void turn(Player currentPlayer, ChipsController chipsController) throws IOException {
        currentPlayer.turnsInRound++;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //chwilowe uzycie docelowo server
        int input = 0;
        input = Integer.parseInt(in.readLine());

        switch (input) {
            case 1:
                //bet
                break;
            case 2:
                //call
                break;
            case 3:
                //raise
                break;
            case 4:
                //fold
//                Round.fold(currentPlayer);
                break;
            case 5:
                //check
                break;
            default:
                break;
        }

        // value:300 type:bet --> chipsController.doBet(300,currentPlayer);




    }
}
