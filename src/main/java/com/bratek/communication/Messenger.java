package com.bratek.communication;

import java.io.InputStream;

/**
 * Created by bratek on 30.06.17.
 */
public interface Messenger {

    String printMessage(String message);

    String takePlayerCommand();

    InputStream getInputStream();
}
