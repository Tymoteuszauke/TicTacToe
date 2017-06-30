package com.bratek.communication;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

/**
 * Created by bratek on 30.06.17.
 */
@Test
public class UIMessengerTest {

    private UIMessenger uiMessenger;

    @BeforeTest
    public void prepare(){
        uiMessenger = new UIMessenger();
    }


    @DataProvider(name = "allowedCommands")
    public static Object[][] allowedCommands(){
        return new Object[][]{
                {"X", ""},
                {"O", ""}
        };
    }

    @Test(dataProvider = "allowedCommands")
    public void shouldReturnGivenCommand(String command, String result){
        uiMessenger.setInputStream(new ByteArrayInputStream(command.getBytes()));

        assertEquals(uiMessenger.takePlayerCommand(),command);
    }


    @Test
    public void shouldReturnGivenMessage(){
        String message = "Message";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        uiMessenger.setPrintStream(new PrintStream(outputStream));

        assertEquals(message, uiMessenger.printMessage(message));
    }

}