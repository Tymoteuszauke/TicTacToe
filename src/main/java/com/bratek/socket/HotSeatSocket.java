package com.bratek.socket;

import com.bratek.communication.UIMessenger;
import com.bratek.game.Game;
import com.bratek.locale.ApiStrings;
import com.bratek.locale.Locale;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by tymek on 10.07.17.
 */
public class HotSeatSocket implements Runnable, SocketType {

    private Socket hotSeatSocket;

    public HotSeatSocket(Socket socket) {
        this.hotSeatSocket = socket;
    }


    @Override
    public void run() {
        try(PrintStream printStream = new PrintStream(hotSeatSocket.getOutputStream());
            InputStream inputStream = hotSeatSocket.getInputStream()) {

            //printStream.println(ApiStrings.CONNECTED_TO_HOTSEAT_SERVER.getValue(firstPlayerLocale));

            UIMessenger messenger = new UIMessenger(new PrintStream(printStream), inputStream);
            messenger.printMessage("Let's get started !!!");

            Game game = new Game(messenger);
            game.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
