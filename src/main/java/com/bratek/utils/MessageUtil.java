package com.bratek.utils;

import com.bratek.communication.Messenger;

/**
 * Created by Mateusz on 09.07.2017.
 */
public enum MessageUtil {
    Instance;

    private Messenger messenger;

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public String message(String message){
        if (messenger != null) {
            return messenger.printMessage(message);
        }
        else throw new NullPointerException();
    }

    public int takeDigit() {
        return messenger.takeDigit();
    }

    public Messenger getMessenger() {
        return messenger;
    }
}
