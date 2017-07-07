package com.bratek;



import com.bratek.communication.Messenger;
import com.bratek.communication.UIMessenger;

import java.util.Scanner;

/**
 * Created by bratek on 28.06.17.
 */
public class Main {
    public static void main(String[] args) {

        Messenger uiMessenger = new UIMessenger(System.out, System.in);
        Game game = new Game(uiMessenger);
        game.start();
    }

}
