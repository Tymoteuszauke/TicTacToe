package com.bratek.board;

import com.bratek.communication.UIMessenger;
import com.bratek.exceptions.AlreadyTakenPositionException;
import com.bratek.exceptions.DrawingBesideBoardException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mateusz on 07.07.2017.
 */
public class BoardTest {

    @DataProvider
    public static Object[][] boardSizes() {
        return new Object[][] {
                {10, 10, 5},
                {15, 10, 10},
                {20, 20, 50},
                {8, 5, 9}
        };
    }

    @DataProvider
    public static Object[][] incorrectPositions() {
        return new Object[][] {
                {10, 10, 150},
                {15, 10, 1000},
                {20, 20, 4000},
                {8, 5, -90}
        };
    }



    @Test(dataProvider = "boardSizes")
    public void sizeTestExpectTrue(int height, int width, int position) {
        Board board = new Board(height, width);
        assertEquals(board.size(), height*width);
    }

    @Test(dataProvider = "boardSizes")
    public void drawTestExpectTrue(int height, int width, int position) {

        Board board = new Board(height, width);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        board.draw("X", position);
        System.out.println(board);

        String whatIsInConsole = out.toString();
        String whatShouldBePrinted = board.toString() + System.getProperty("line.separator");

        assertEquals(whatIsInConsole, whatShouldBePrinted);

    }

    @Test(dataProvider = "boardSizes", expectedExceptions = AlreadyTakenPositionException.class)
    public void drawTestInAlreadyTakenPositionExpectException(int height, int width, int position) {

        Board board = new Board(height, width);
        board.draw("X", position);
        board.draw("O", position);
    }

    @Test(dataProvider = "incorrectPositions", expectedExceptions = DrawingBesideBoardException.class)
    public void drawTestExpectBoardException(int height, int width, int position) {

        Board board = new Board(height, width);
        board.draw("X", position);
    }

    @Test(dataProvider = "boardSizes")
    public void clearTestExpectTrue(int height, int width, int position) {
        Board board = new Board(height, width);
        board.draw("X", position);

        Board secondBoard = new Board(height, width);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        board.clear();

        System.out.println(board);

        String whatIsInConsole = out.toString();
        String whatShouldBePrinted = secondBoard.toString() + System.getProperty("line.separator");

        assertEquals(whatIsInConsole, whatShouldBePrinted);
    }





}