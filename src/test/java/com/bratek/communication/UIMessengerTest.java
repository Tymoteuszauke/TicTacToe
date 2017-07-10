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

    @DataProvider
    public static Object[][] playerInputDigitsData() {
        return new Object[][] {
                {10},
                {20},
                {10000},
                {1000000}
        };
    }

    @DataProvider
    public static Object[][] playerInputData() {
        return new Object[][] {
                {"Tymek"},
                {"Remigiusz"},
                {"Rafal"},
                {"Izabela"}
        };
    }

    @BeforeTest
    public void prepare() {
        uiMessenger = new UIMessenger();
    }


    @DataProvider(name = "allowedCommands")
    public static Object[][] allowedCommands() {
        return new Object[][]{
                {"X", ""},
                {"O", ""}
        };
    }

    @Test(dataProvider = "allowedCommands")
    public void shouldReturnGivenCommand(String command, String result) {
        uiMessenger.setInputStream(new ByteArrayInputStream(command.getBytes()));

        assertEquals(uiMessenger.takePlayerSymbol(), command);
    }

    @Test
    public void shouldReturnGivenMessage() {
        String message = "Message";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        uiMessenger.setPrintStream(new PrintStream(outputStream));

        assertEquals(message, uiMessenger.printMessage(message));
    }

    @Test(dataProvider = "playerInputData")
    public void takeCharacterSequenceExpectTrue(String playerInput) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        uiMessenger.setPrintStream(new PrintStream(out));

        uiMessenger.printMessage(playerInput);

        String whatIsInConsole = out.toString();
        String whatShouldBePrinted = playerInput + System.getProperty("line.separator");

        assertEquals(whatIsInConsole, whatShouldBePrinted);
    }

    @Test(dataProvider = "playerInputDigitsData")
    public void takeDigitTestExpectTrue(int digit) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        uiMessenger.setPrintStream(new PrintStream(out));
        uiMessenger.setInputStream(new ByteArrayInputStream(String.valueOf(digit).getBytes()));
        assertEquals(uiMessenger.takeDigit(), digit);
    }

    @Test(dataProvider = "playerInputData", expectedExceptions = NumberFormatException.class)
    public void takeDigitTestExpectNumberFormatException(String digit) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        uiMessenger.setPrintStream(new PrintStream(out));
        uiMessenger.setInputStream(new ByteArrayInputStream(digit.getBytes()));
        uiMessenger.takeDigit();
    }
}