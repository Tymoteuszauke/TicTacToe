package com.bratek.board;

import com.bratek.communication.Messenger;
import com.bratek.communication.UIMessenger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

public class BoardFieldTest {

    @DataProvider
    public static Object[][] boardFieldPositions() {
        return new Object[][] {
                {10},
                {41},
                {301},
                {2001}
        };
    }

    @DataProvider
    public static Object[][] boardFieldLowerThan10Digits() {
        return new Object[][] {
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8},
                {9}
        };
    }

    @DataProvider
    public static Object[][] boardFieldBiggerThan10Digits() {
        return new Object[][] {
                {100},
                {1000},
                {2425},
                {332},
                {43},
                {55},
                {66},
                {72},
                {82},
                {97}
        };
    }

    @DataProvider
    public static Object[][] boardFieldSymbolData() {
        return new Object[][] {
                {"X"},
                {"O"},
                {"Z"},
                {"C"}
        };
    }

    @Test(dataProvider = "boardFieldPositions")
    public void testSetFieldNumber(int position) throws Exception {
        BoardField boardField = new BoardField(position);
        assertEquals(boardField.getFieldNumber(), position);
    }

    @Test(dataProvider = "boardFieldSymbolData")
    public void testSetSymbol(String symbol) throws Exception {
        BoardField boardField = new BoardField(0);
        boardField.setSymbol(symbol);
        assertEquals(boardField.getSymbol(), symbol);
    }

    @Test(dataProvider = "boardFieldLowerThan10Digits")
    public void lowerThan10toStringTestExpectTrue(int position) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BoardField boardField = new BoardField(position);
        System.out.println(boardField);

        String whatIsInConsole = out.toString();
        String whatShouldBePrinted =  boardField.toString() + System.getProperty("line.separator");

        assertEquals(whatIsInConsole, whatShouldBePrinted);
    }

    @Test(dataProvider = "boardFieldBiggerThan10Digits")
    public void biggerThan10toStringTestExpectTrue(int position) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BoardField boardField = new BoardField(position);
        System.out.println(boardField);

        String whatIsInConsole = out.toString();
        String whatShouldBePrinted =  boardField.toString() + System.getProperty("line.separator");

        assertEquals(whatIsInConsole, whatShouldBePrinted);
    }

    @Test(dataProvider = "boardFieldSymbolData")
    public void toStringTestWhenFieldHasSymbolExpectTrue(String symbol) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BoardField boardField = new BoardField(10);
        boardField.setSymbol(symbol);
        System.out.println(boardField);

        String whatIsInConsole = out.toString();
        String whatShouldBePrinted =  boardField.toString() + System.getProperty("line.separator");

        assertEquals(whatIsInConsole, whatShouldBePrinted);
    }

    @Test(dataProvider = "boardFieldSymbolData")
    public void isTakenTestExpectTrue(String symbol) {
        BoardField boardField = new BoardField(10);
        boardField.setSymbol(symbol);

        assertEquals(boardField.isTaken(), true);
    }

//    @Test
//    public void deliberateFailedTest() {
//        assertEquals(true, false);
//    }

}