package com.bratek;

import com.bratek.board.Board;
import com.bratek.communication.Messenger;
import com.bratek.communication.UIMessenger;
import com.bratek.game.ScoreListener;
import com.bratek.player.Player;
import com.bratek.player.PlayersScoreboard;
import com.bratek.utils.WinUtil;

import java.util.*;

/**
 * Created by bratek on 29.06.17.
 */
public class Game implements ScoreListener {

    private final int MAX_PLAYERS = 2;

    private List<Player> players;
    private Messenger messenger;
    private Board board;
    private Player currentPlayer;
    private PlayersScoreboard playersScoreboard;

    private boolean gameContinue = true;

    public Game(Messenger messenger){
        players = new ArrayList<>();
        this.messenger = messenger;
    }

    public void start() {
        gamePreparation();
        playersScoreboard = new PlayersScoreboard();
        playersScoreboard.providePlayers(players);
        playersScoreboard.setScoreListener(this);

        currentPlayer = players.get(0);

        while (gameContinue) {
            message(String.format("...%s your move...", currentPlayer.getName()));
            message(board.toString());
            int chosenPosition = messenger.takeDigit();
            board.draw(currentPlayer.getSign(), chosenPosition);

            if (WinUtil.winnerFound(board, chosenPosition, 3)) {
                playersScoreboard.addPoint(currentPlayer);
                playersScoreboard.printScore();
                board.clear();
            }

            currentPlayer = players.stream()
                    .filter(data -> currentPlayer.getPosition() != data.getPosition())
                    .findAny()
                    .get();
        }

    }

    public void gamePreparation() {
        System.out.println("...Player creation sequence...");

        //TODO create another way of player creation sequence
        for (int i = 0; i < MAX_PLAYERS; i++) {
            message(String.format("Player %d provide name", i + 1));
            String name = messenger.takeCharacterSequence();
            String sign = "";

            if (players.size() == 0) {
                message(String.format("Player %d provide sign", i + 1));
                sign = takePlayerSignInput();
            } else {
                //TODO redo this thing it sucks so many dicks
                if (players.get(0).getSign().equals("X")) {
                    sign = "O";
                } else {
                    sign = "X";
                }
            }
            players.add(new Player.PlayerBuilder()
                    .setSign(sign)
                    .name(name)
                    .positionOrder(players.size() + 1).build());
        }

        message("Set board size: ");
        message("Board height: ");
        int height = messenger.takeDigit();
        message("Board width");
        int width = messenger.takeDigit();
        message("...Preparing board...");
        prepareBoard(height, width);
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

    public Player createPlayer(String sign, String name) {
        if (!isSign(sign)){
            throw new InputMismatchException("This is incorrect sign!");        }

        return new Player.PlayerBuilder()
                .positionOrder(players.size() + 1)
                .setSign(sign)
                .name(name)
                .build();
    }

    private boolean isSign(String sign) {
        return sign.equals("O") || !sign.equals("X");
    }

    public String takePlayerSignInput() {
        return messenger.takePlayerSign();
    }

    @Override
    public boolean minGamesPrompt() {
        gameContinue = false;
        return false;
    }
}
