package com.bratek;



import com.bratek.socket.GameSocket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by bratek on 28.06.17.
 */
public class Main {
    public static void main(String[] args) {
//        Messenger uiMessenger = new UIMessenger(System.out, System.in);
//        MessageUtil.Instance.setMessenger(uiMessenger);
//        Game game = new Game(uiMessenger);
//        game.start();

        try (ServerSocket serverSocket = new ServerSocket(8189)){
            while(true) {

                Socket socket = serverSocket.accept();

                GameSocket g = new GameSocket(socket);
                Thread thread = new Thread(g);
                thread.start();

                socket = serverSocket.accept();

                g.setSocketPlayerTwo(socket);


                //Thread.sleep(10_000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
