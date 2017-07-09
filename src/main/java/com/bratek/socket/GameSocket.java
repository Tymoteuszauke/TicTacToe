package com.bratek.socket;

import com.bratek.communication.TcpMessenger;
import com.bratek.communication.UIMessenger;
import com.bratek.game.Game;
import com.bratek.game.TcpGame;
import com.bratek.utils.MessageUtil;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Mateusz on 09.07.2017.
 */
public class GameSocket implements Runnable {

    private Socket socketPlayer1;
    Socket socketPlayer2;

    public GameSocket(Socket socket) {
        super();
        this.socketPlayer1 = socket;
    }

    public void setSocketPlayer2(Socket socketPlayer2) {
        this.socketPlayer2 = socketPlayer2;
    }

    @Override
    public void run() {

        //Witamy w grze czekaj na 2 playera
        try {
            PrintStream printStream = new PrintStream(socketPlayer1.getOutputStream());
            printStream.println("Connected to TicTacToe game server as Player 1");
            printStream.println("Waiting for player 2...");
            //printStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        //czytaj z 1 gracza i odpowiadaj ze nie ma 2 gracza jeszcze

        // jesli 2 gracz sie pojawil to przejdz dalej

        while (socketPlayer2 == null) {
            try {
                PrintStream printStream = new PrintStream(socketPlayer1.getOutputStream());
                Thread.sleep(1000);
                printStream.println("Waiting for player 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(PrintStream printStream1 = new PrintStream(socketPlayer1.getOutputStream());
            PrintStream printStream2 = new PrintStream(socketPlayer2.getOutputStream())
            ) {

            printStream2.println("Connected to TicTacToe game server as Player 2");

            printStream1.println("Player 2 connected!");

            printStream1.println("Let's get started !!!");
            printStream2.println("Let's get started !!!");

            TcpMessenger messenger = new TcpMessenger(socketPlayer1, socketPlayer2);

            MessageUtil.Instance.setMessenger(messenger);
            TcpGame game = new TcpGame(messenger);
            game.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
