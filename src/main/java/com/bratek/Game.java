package com.bratek;

import com.bratek.board.Board;
import com.bratek.communication.Messenger;
import com.bratek.communication.UIMessenger;
import com.bratek.player.Player;

import javax.lang.model.SourceVersion;
import java.util.*;

/**
 * Created by bratek on 29.06.17.
 */
public class Game {

    private List<Player> players;
    private Messenger messenger;
    private Board board;


    public Game(Messenger messenger){
        players = new ArrayList<>();
        this.messenger = messenger;
    }

    public void start() {

    }

    public void prepareBoard(int height, int width) {
        this.board = new Board(height, width);
    }

    public Game(UIMessenger messenger) {
        players = new ArrayList<>();
        this.messenger = messenger;
    }

    public void message(String message){
        messenger.printMessage(message);
    }

    public void createPlayer() {
        String name = takePlayerCommand();
        if(!SourceVersion.isName(name)){
            throw new InputMismatchException("This is not a name!");
        }
        message("Provide your sign: (X or O)");
        String sign = takePlayerCommand();

        if(isSign(sign)){
            throw new InputMismatchException("This is incorrect sign!");
        }

    }

    private boolean isSign(String sign) {
        if(sign.equals("X"))
            return false;
        if(sign.equals("O")){
            return false;
        }
        return true;
    }

    public String takePlayerCommand() {
        return messenger.takePlayerCommand();
    }


}
