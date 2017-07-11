package com.bratek.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Mateusz on 09.07.2017.
 */
public class TcpMessenger implements Messenger {

    private Socket socketPlayerOne;
    private Socket socketPlayerTwo;

    private Socket currentPlayer;

    private boolean isFirst = true;

    public TcpMessenger(Socket socketPlayerOne, Socket socketPlayerTwo) {
        this.socketPlayerOne = socketPlayerOne;
        this.socketPlayerTwo = socketPlayerTwo;

        this.currentPlayer = socketPlayerOne;
    }

    private void changePlayer() {
        if (currentPlayer == socketPlayerOne) {
            currentPlayer = socketPlayerTwo;
        } else {
            currentPlayer = socketPlayerOne;
        }
    }

    @Override
    public String printMessage(String message) {

        try {
            PrintStream printStream1 = new PrintStream(socketPlayerOne.getOutputStream());
            PrintStream printStream2 = new PrintStream(socketPlayerTwo.getOutputStream());
            printStream1.println(message);
            printStream2.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;
    }

    @Override
    public String takePlayerSymbol() {

        try {
            InputStream inputStream = socketPlayerOne.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            PrintStream printStream2 = new PrintStream(socketPlayerTwo.getOutputStream());
            String symbol = scanner.nextLine();
            symbol = symbol.toUpperCase();

            printStream2.println(String.format("Your symbol is %s", symbol.equals("X") ? "O" : "X"));

            changePlayer();
            isFirst = false;
            return symbol;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int takeDigit() {
        try {
            InputStream inputStream = currentPlayer.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            return Integer.parseInt(scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int takePlayerMove() throws NumberFormatException {
        try {
            InputStream inputStream = currentPlayer.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            int position = Integer.parseInt(scanner.nextLine());
            changePlayer();
            return position;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String takeCharacterSequence() {

        try {
            InputStream inputStream = currentPlayer.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String name = scanner.nextLine();
            name = name.toUpperCase();
            if (!isFirst) changePlayer();
            return name;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
