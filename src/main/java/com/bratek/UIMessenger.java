package com.bratek;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by bratek on 29.06.17.
 */


/*
* How to write TDD for this class or better... How to write TDD if I have Interfaces.
* */
public class UIMessenger {
    private PrintStream printStream;
    private InputStream inputStream;

    public UIMessenger(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.inputStream = inputStream;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    void printMessage(String message){
        printStream.println(message);
    }

    String takeUserCommand(){
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine();
    }
}
