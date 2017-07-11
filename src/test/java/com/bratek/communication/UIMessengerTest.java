package com.bratek.communication;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

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

    @DataProvider
    public static Object[][] allowedSymbols() {
        return new Object[][]{
                {"X"},
                {"O"}
        };
    }

    @BeforeTest
    public void prepare() {
        uiMessenger = new UIMessenger();
    }

    @Test(dataProvider = "allowedSymbols")
    public void shouldReturnGivenCommand(String symbol) {
        uiMessenger.setInputStream(new ByteArrayInputStream(symbol.getBytes()));

        assertEquals(uiMessenger.takePlayerSymbol(), symbol);
    }

    @Test(dataProvider = "playerInputData", expectedExceptions = InputMismatchException.class)
    public void takePlayerSymbolTestExpectInputMismatchException(String input) {
        uiMessenger.setInputStream(new ByteArrayInputStream(input.getBytes()));
        uiMessenger.takePlayerSymbol();
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