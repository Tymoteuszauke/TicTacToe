package com.bratek.communication;

import com.bratek.board.Board;

import javax.lang.model.SourceVersion;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by bratek on 29.06.17.
 */


/*
* How to write TDD for this class or better... How to write TDD if I have Interfaces.
* */
public class UIMessenger implements Messenger {
    private PrintStream printStream;
    private InputStream inputStream;

    public UIMessenger() {
    }

    public UIMessenger(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.inputStream = inputStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String printMessage(String message){
        printStream.println(message);
        return message;
    }

    public String takePlayerSymbol() {
        Scanner scanner = new Scanner(inputStream);
        String symbol = scanner.nextLine().toUpperCase();

        if (symbol.equals("X") || symbol.equals("O")) return symbol;
        throw new InputMismatchException("Wrong Command");
    }

    public int takeDigit() throws NumberFormatException {
        Scanner scanner = new Scanner(inputStream);
        return Integer.parseInt(scanner.nextLine());
    }

    public int takePlayerMove() throws NumberFormatException {
        Scanner scanner = new Scanner(inputStream);
        return Integer.parseInt(scanner.nextLine());
    }

    public String takeCharacterSequence() {
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine();
    }
}
