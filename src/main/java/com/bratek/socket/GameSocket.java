package com.bratek.socket;

import com.bratek.communication.TcpMessenger;
import com.bratek.game.TcpGame;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Mateusz on 09.07.2017.
 */
public class GameSocket implements Runnable {

    private Socket socketPlayerOne;
    private Socket socketPlayerTwo;

    public GameSocket(Socket socket) {
        super();
        this.socketPlayerOne = socket;
    }

    public void setSocketPlayerTwo(Socket socketPlayerTwo) {
        this.socketPlayerTwo = socketPlayerTwo;
    }

    @Override
    public void run() {

        try {
            PrintStream printStream = new PrintStream(socketPlayerOne.getOutputStream());
            printStream.println("Connected to TicTacToe game server as Player 1");
            printStream.println("Waiting for player 2...");

        }catch (IOException e) {
            e.printStackTrace();
        }

        while (socketPlayerTwo == null) {
            try {
                PrintStream printStream = new PrintStream(socketPlayerOne.getOutputStream());
                Thread.sleep(1000);
                printStream.println("Waiting for player 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (PrintStream printStreamOne = new PrintStream(socketPlayerOne.getOutputStream());
            PrintStream printStreamTwo = new PrintStream(socketPlayerTwo.getOutputStream())
            ) {

            printStreamTwo.println("Connected to TicTacToe game server as Player 2");
            printStreamOne.println("Player 2 connected!");

            TcpMessenger messenger = new TcpMessenger(socketPlayerOne, socketPlayerTwo);
            messenger.printMessage("Let's get started !!!");

            TcpGame game = new TcpGame(messenger);
            game.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
