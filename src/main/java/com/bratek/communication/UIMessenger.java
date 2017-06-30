package com.bratek.communication;

import javax.lang.model.SourceVersion;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by bratek on 29.06.17.
 */


/*
* How to write TDD for this class or better... How to write TDD if I have Interfaces.
* */
public class UIMessenger implements Messenger{
    private PrintStream printStream;
    private InputStream inputStream;

    public UIMessenger() {

    }

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

    /*
    *   How to test this method?
    * */
    public void printMessage(String message){
        printStream.println(message);
    }

    public String takePlayerCommand(){
        Scanner scanner = new Scanner(inputStream);
        String command = scanner.nextLine();
        if(!isCommand(command)){
            throw new InputMismatchException("Wrong Command");
        }
        return command;
    }

    private boolean isCommand(String command) {
        if(SourceVersion.isName(command))
            return true;
        if(command.equals("X") || command.equals("O"))
            return true;

        return false;
    }
}
