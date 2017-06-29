package com.bratek;

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

    private UIMessenger uiMessanger;

    public Game(){
        players = new ArrayList<>();
    }

    public Game(UIMessenger uiMessanger) {
        players = new ArrayList<>();
        this.uiMessanger = uiMessanger;
    }

    public void message(String message){
        uiMessanger.printMessage(message);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void createPlayer(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        String name = scanner.nextLine();
        if(!SourceVersion.isName(name)){
            throw new InputMismatchException("This is not a name!");
        }
        Player player = new Player(name);
        players.add(player);
    }
}
