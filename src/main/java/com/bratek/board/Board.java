package com.bratek.board;

import com.bratek.exceptions.AlreadyTakenPositionException;
import com.bratek.exceptions.BoardException;
import com.bratek.exceptions.DrawingBesideBoardException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Mateusz on 05.07.2017.
 */
public class Board {
    private List<BoardField> board;
    private int boardSize;
    private int boardWidth;
    private int boardHeight;

    public Board(int boardHeight, int boardWidth) {
        board = new ArrayList<>(boardHeight * boardWidth);
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        this.boardSize = boardHeight * boardWidth;
        fillBoard();
    }

    private void fillBoard() {
        IntStream.range(0, boardSize)
                .boxed()
                .forEach(data -> board.add(new BoardField(data)));
    }

    public int size() {
        return board.size();
    }

    public BoardField get(int position) {
        return board.get(position);
    }

    public void draw(String playerSign, int position) throws BoardException {

        try {
            if (board.get(position).isTaken())
                throw new AlreadyTakenPositionException();
            board.get(position).setSymbol(playerSign);

        } catch (IndexOutOfBoundsException e) {
            throw new DrawingBesideBoardException();
        }

    }

    public void clear() {
        board = new ArrayList<>(boardSize);
        fillBoard();
    }

    public List<BoardField> getBoard() {
        return Collections.unmodifiableList(board);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        for (BoardField boardField : board) {
            stringBuilder.append(boardField);
            counter++;

            if (counter == boardWidth) {
                //stringBuilder.append(System.getProperty("line.separator"));
                stringBuilder.append("\n");
                counter = 0;
            }
        }
        return stringBuilder.toString();
    }
}
