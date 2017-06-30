package com.bratek;


import com.bratek.board.Position;
import com.bratek.board.Tile;
import com.bratek.communication.Messenger;
import com.bratek.communication.UIMessenger;

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


        Messenger uiMessenger = new UIMessenger(System.out, System.in);


        Scanner scanner = new Scanner(System.in);
        //TODO: Prepare bi-lingual impl of communication with players.

        Game game = new Game(uiMessenger);
        game.message("Tell me your name: ");
        game.createPlayer();
        game.message("Size of board");
        game.createHashBoard(9);







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

        System.out.println("Ask about size: ");


        System.out.println("Choose your tile: ");



        System.out.println("Second Player choose your sign: ");

        System.out.println("Choose your tile: ");

    }

    private static void putSign(String userSign, Map<Integer, Tile> board, int field) {
        board.get(field).setSign(userSign);
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


}
