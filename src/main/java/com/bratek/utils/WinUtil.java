package com.bratek.utils;

import com.bratek.board.Board;
import com.bratek.board.BoardField;

/**
 * Created by Mateusz on 05.07.2017.
 */
public class WinUtil {

    public static boolean winnerFound(Board board, int boardFieldPosition, int winSequence) {

        BoardField boardField = board.get(boardFieldPosition);

        return checkVertical(board, boardField, winSequence)
                || checkHorizontal(board, boardField, winSequence)
                || checkDiagonal(board, boardField, winSequence)
                || checkAntiDiagonal(board, boardField, winSequence);
    }

    private static boolean checkAntiDiagonal(Board board, BoardField boardField, int winSequence) {
        int tmp = 0;
        for (int i = boardField.getFieldNumber(); i < board.size(); i = i + board.getBoardWidth() - 1) {
            if (board.get(i).getSymbol().equals(boardField.getSymbol())){
                tmp++;
            }
            if (winSequence == tmp){
                return true;
            }
        }
        for (int i = boardField.getFieldNumber() - board.getBoardWidth() + 1; i > 0; i = i - (board.getBoardWidth() - 1)) {
            if (board.get(i).getSymbol().equals(boardField.getSymbol())) {
                tmp++;
            }
            if (winSequence == tmp){
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonal(Board board, BoardField boardField, int winSequence) {
        int tmp = 0;
        for (int i = boardField.getFieldNumber(); i < board.size(); i = i + board.getBoardWidth() + 1) {
            if (board.get(i).getSymbol().equals(boardField.getSymbol())){
                if(i < board.getBoardWidth()) {
                    tmp++;
                } else {
                    if(nextRow(board, i)){
                        tmp++;
                    }
                }
            }
            if (winSequence == tmp){
                return true;
            }
        }
        for (int i = boardField.getFieldNumber() - board.getBoardWidth() - 1; i >= 0; i = i - board.getBoardWidth() - 1) {
            if (board.get(i).getSymbol().equals(boardField.getSymbol())){
                tmp++;
            }
            if (winSequence == tmp) {
                return true;
            }
        }
        return false;
    }

    private static boolean nextRow(Board board, int i) {
        return (i / board.getBoardWidth()) == ((i - (board.getBoardWidth() + 1) - 1)/board.getBoardWidth()) + 1;
    }

    private static boolean checkHorizontal(Board board, BoardField boardField, int winSequence) {

        int currentBoardFieldPosition = boardField.getFieldNumber();
        int currentLineHead = currentBoardFieldPosition % board.getBoardWidth();
        int tmp = 0;

        for (int i = currentLineHead; i < board.getBoardHeight() * board.getBoardWidth(); i += board.getBoardWidth()) {

            if (!board.get(i).getSymbol().equals(boardField.getSymbol())) {
                tmp = 0;
            } else {
                tmp ++;
            }
            if (tmp == winSequence) return true;

        }
        return false;
    }

    private static boolean checkVertical(Board board, BoardField boardField, int winSequence) {

        int currentBoardFieldPosition = boardField.getFieldNumber();
        int currentLineHead = boardField.getFieldNumber() - currentBoardFieldPosition % board.getBoardWidth();
        int tmp = 0;

        for (int i = currentLineHead; i < currentLineHead + board.getBoardWidth(); i++) {

            if (!board.get(i).getSymbol().equals(boardField.getSymbol())) {
                tmp = 0;
            } else {
                tmp ++;
            }
            if (tmp == winSequence) return true;

        }
        return false;
    }


}
