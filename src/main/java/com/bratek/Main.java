package com.bratek;



import com.bratek.communication.Messenger;
import com.bratek.communication.UIMessenger;
import com.bratek.game.Game;
import com.bratek.utils.MessageUtil;

/**
 * Created by bratek on 28.06.17.
 */
public class Main {
    public static void main(String[] args) {

        Messenger uiMessenger = new UIMessenger(System.out, System.in);
        MessageUtil.Instance.setMessenger(uiMessenger);
        Game game = new Game(uiMessenger);
        game.start();
    }

}
