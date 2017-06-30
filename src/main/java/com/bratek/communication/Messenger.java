package com.bratek.communication;

import java.io.InputStream;

/**
 * Created by bratek on 30.06.17.
 */
public interface Messenger {

    void printMessage(String message);

    String takeUserCommand();

    InputStream getInputStream();
}
