package com.bratek;


import java.util.Arrays;
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

        //TODO: Prepare impl of board and tiles.
        String [][] board = new String[3][3];

        //TODO: Prepare impl of initialize board.
        for (String[] row : board)
            Arrays.fill(row, "0");

        //TODO: Think about impl of display board.
        System.out.println(Arrays.deepToString(board));

        //TODO: Player moves in theirs turns
        System.out.println("Player moves:");

        //TODO:


    }
}
