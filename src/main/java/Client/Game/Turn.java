package Client.Game;

import Client.Players.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import Client.Controllers.ChipsController;

public class Turn {
    public static void turn(Player currentPlayer, ChipsController chipsController, Socket socket) throws IOException {
        currentPlayer.turnsInRound++;
        String input = "";

        PrintStream out = new PrintStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("kloc");
        input = in.readLine();
        System.out.println("lego");

        String[] parts = input.split(" ");

        switch (parts[0]) {
            case "bet":
                //bet x ----->  chipsController.doBet(x,currentPlayer);
                chipsController.doBet(Integer.parseInt(parts[1]), currentPlayer);
                break;
            case "call":
                //call x -----> chipsController.doCall(x,currentPlayer);
                chipsController.doCall(Integer.parseInt(parts[1]),currentPlayer);
                break;
            case "raise":
                //raise x -----> chipsController.doRaise(x,currentPlayer);
                chipsController.doRaise(Integer.parseInt(parts[1]), currentPlayer);
                break;
            case "fold":
                //fold ---->   Round.fold(currentPlayer);
                Round.fold(currentPlayer);
                break;
            case "check":
                //check  -----> NIC a jak sie nie da nic to chipsController.doBet(0,currentPlayer);
                break;
            default:
                break;
        }

        out.println("done");

        // value:300 type:bet --> chipsController.doBet(300,currentPlayer);




    }
}