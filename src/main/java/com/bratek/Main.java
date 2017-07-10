package com.bratek;



import com.bratek.socket.GameSocket;
import com.bratek.socket.HotSeatSocket;
import com.bratek.socket.SocketType;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by bratek on 28.06.17.
 */
public class Main {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            while(true) {

                makeSocket(serverSocket);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void makeSocket(ServerSocket serverSocket) throws Exception {
        Socket socket = serverSocket.accept();
        SocketType socketType = getGameType(socket);

        if (socketType instanceof HotSeatSocket) {
            HotSeatSocket hotSeatSocket = new HotSeatSocket(socket);
            Thread thread = new Thread(hotSeatSocket);
            thread.start();
        } else {
            GameSocket g = new GameSocket(socket);
            Thread thread = new Thread(g);
            thread.start();

            socket = serverSocket.accept();

            g.setSocketPlayerTwo(socket);
        }
    }

    public static SocketType getGameType(Socket socket) throws Exception {

        PrintStream printStream = new PrintStream(socket.getOutputStream());
        InputStream inputStream = socket.getInputStream();
        Scanner scanner = new Scanner(inputStream);

        printStream.println("Choose game type" + "\n" +
                "1. HotSeat \n" +
                "2. TCP");

        String gameType = scanner.nextLine();

        switch (gameType) {
            case "1": return new HotSeatSocket(socket);
            case "2": return new GameSocket(socket);
            default:
                printStream.println("Incorrect Number");
                getGameType(socket);

        }
        return null;
    }
}