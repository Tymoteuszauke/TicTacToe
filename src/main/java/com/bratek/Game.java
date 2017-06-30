package com.bratek;

import com.bratek.communication.Messenger;
import com.bratek.communication.UIMessenger;

import javax.lang.model.SourceVersion;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bratek on 29.06.17.
 */
public class Game {

    private List<Player> players;

    private Messenger messenger;

    public Game(Messenger messenger){
        players = new ArrayList<>();
        this.messenger = messenger;
    }

    public Game(UIMessenger messenger) {
        players = new ArrayList<>();
        this.messenger = messenger;
    }

    public void message(String message){
        messenger.printMessage(message);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void createPlayer() {
        Scanner scanner = new Scanner(messenger.getInputStream());
        String name = scanner.nextLine();
        if(!SourceVersion.isName(name)){
            throw new InputMismatchException("This is not a name!");
        }
        Player player = new Player(name);
        players.add(player);
    }

    public String takePlayerCommand() {
        return messenger.takePlayerCommand();
    }
}
