package com.bratek;

import com.bratek.communication.Messenger;
import com.bratek.communication.UIMessenger;

import javax.lang.model.SourceVersion;
import java.io.InputStream;
import java.util.*;

/**
 * Created by bratek on 29.06.17.
 */
public class Game {

    private List<Player> players;

    private Messenger messenger;

    private Map<Integer, String> board;

    public Game(Messenger messenger){
        players = new ArrayList<>();
        this.messenger = messenger;
    }


    public List<Player> getPlayers() {
        return players;
    }

    public Map<Integer, String> getBoard() {
        return board;
    }

    public void setBoard(Map<Integer, String> board) {
        this.board = board;
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
        Player player = new Player(name, sign);
        players.add(player);
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

    public void createHashBoard(int size) {
        board = new HashMap<>();

        for(int i = 1; i <= size; i++){
            board.put(i, "#");
        }
    }
}
