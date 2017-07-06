package com.bratek.utils;

import com.bratek.player.Player;
import com.bratek.board.Board;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mateusz on 05.07.2017.
 */
public class WinUtilTest {

    private Board board;

    private final int BOARD_HEIGHT = 10;
    private final int BOARD_WIDTH = 10;


    @BeforeMethod
    public void initializeBoard() {
        board = new Board(BOARD_HEIGHT, BOARD_WIDTH);
    }

    @Test
    public void verticalWinnerExpectTrue() {
        board.clear();
        board.draw("X", 5);
        board.draw("X", 6);
        board.draw("X", 7);
        board.draw("X", 8);

        assertEquals(WinUtil.winnerFound(board, board.get(6), 4), true);
    }

    @Test
    public void horizontalWinnerExpectTrue() {

        board.draw("X", 26);
        board.draw("X", 36);
        board.draw("X", 46);
        board.draw("X", 56);

        assertEquals(WinUtil.winnerFound(board, board.get(26), 4), true);
    }

    @Test
    public void diagonalWinnerExpectTrue() {

        //TODO work on diagonal algorithm
        board.draw("X", 50);
        board.draw("X", 61);
        board.draw("X", 72);

        assertEquals(WinUtil.winnerFound(board, board.get(61), 3), false);
    }

    public void antiDiagonalWinnerExpectTrue() {

        //TODO work on antidiagonal algorithm
        board.draw("X", 16);
        board.draw("X", 27);
        board.draw("X", 38);

//        assertEquals(WinUtil.winnerFound(board, board.get));
    }

    @AfterMethod
    public void printBoard() {
        System.out.println(board);
        board = null;
    }
}