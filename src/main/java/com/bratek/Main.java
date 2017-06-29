package com.bratek;


import com.bratek.board.Position;
import com.bratek.board.Tile;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by bratek on 28.06.17.
 */
public class Main {
    public static void main(String[] args) {
        //TODO: Prepare impl of game with Human or game with Computer.
        System.out.println("Human vs Human (1) \nHuman vs Computer(2) \nComputer vs Computer (3)");

        //TODO: Prepare bi-lingual impl of communication with players.
        System.out.println("Tell me, which character will start first: ");
        Scanner scanner = new Scanner(System.in);

        //TODO: Prepare impl of User, contains his sign.
        String userSign = scanner.nextLine();

//        //TODO: Prepare impl of board and tiles.
//        String [][] board = new String[3][3];
//
//        //TODO: Prepare impl of initialize board.
//        for (String[] row : board)
//            Arrays.fill(row, "0");
//
//        //TODO: Think about impl of display board.
//        System.out.println(Arrays.deepToString(board));
//
//        //TODO: Player moves in theirs turns
//        System.out.println("Player moves:");

        //TODO:


        Map<Integer, Tile> board = generateBoard();

        drawBoard(board);

        System.out.println("Chose your tile: ");
        int field = Integer.parseInt(scanner.nextLine());

        board.get(field).setSign(userSign);

        drawBoard(board);
    }

    private static void drawBoard(Map<Integer, Tile> board) {
        board.forEach((k,v) -> {
            System.out.print("|" + v.getSign() + "|");
            System.out.print("|" + k + "|");
            if(k % 3 == 0){
                System.out.println();
            }
            k++;
        });
    }

    private static Map<Integer, Tile> generateBoard() {
        Map<Integer, Tile> board = new HashMap<>();
            board.put(1,new Tile(new Position(1,1),"X"));
            board.put(2,new Tile(new Position(1,2),"X"));
            board.put(3,new Tile(new Position(1,3),"X"));
            board.put(4,new Tile(new Position(2,1),"X"));
            board.put(5,new Tile(new Position(2,2),"X"));
            board.put(6,new Tile(new Position(2,3),"X"));
            board.put(7,new Tile(new Position(3,1),"X"));
            board.put(8,new Tile(new Position(3,2),"X"));
            board.put(9,new Tile(new Position(3,3),"X"));

        return board;
    }
}
