package com.bratek.game;

import com.bratek.board.Board;
import com.bratek.communication.Messenger;
import com.bratek.player.Player;
import com.bratek.player.PlayersScoreboard;
import com.bratek.player.ScoreBoardListener;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by Mateusz on 09.07.2017.
 */
public class Game implements ScoreBoardListener {
    private final int MAX_PLAYERS = 2;

    private List<Player> players;
    private Messenger messenger;
    private Board board;
    private Player currentPlayer;

    private boolean gameContinues = true;

    public Game(Messenger messenger) {
        players = new ArrayList<>();
        this.messenger = messenger;
    }

    public void start() {
        gamePreparation();
        PlayersScoreboard playersScoreboard = preparePlayerScoreboard();

        while (gameContinues) {

            new Turn.TurnBuilder()
                    .player(currentPlayer)
                    .board(board)
                    .messenger(messenger)
                    .playersScoreboard(playersScoreboard)
                    .build()
                    .makeTurn();

            currentPlayer = players.stream()
                    .filter(data -> currentPlayer.getPosition() != data.getPosition())
                    .findAny()
                    .get();
        }
    }

    public void prepareBoard(int height, int width) {
        this.board = new Board(height, width);
    }

    public Player createPlayer(String symbol, String name, int position) {
        return new Player.PlayerBuilder()
                .positionOrder(position)
                .setSign(symbol)
                .name(name)
                .build();
    }

    public void scoreBoardAnnouncement() {
        message("Do you wish to continue? \n" +
                "1. Yes \n" +
                "2. No");

        switch (messenger.takeCharacterSequence()) {
            case "1":
                gameContinues = true;
                break;
            case "2":
                gameContinues = false;
                break;
            default:
                message("Bad decision");
                this.scoreBoardAnnouncement();
        }
    }

    boolean isSymbol(String symbol) {
        return symbol.equals("O") || symbol.equals("X");
    }

    private PlayersScoreboard preparePlayerScoreboard() {

        PlayersScoreboard playersScoreboard = PlayersScoreboard.prepareGameScoreboard(players, messenger);
        playersScoreboard.setScoreBoardListener(this);

        currentPlayer = players.get(0);

        message("Set winning sequence");
        int winningSequence;
        while (true) {
            winningSequence = messenger.takeDigit();
            if (winningSequence > board.getBoardHeight() || winningSequence > board.getBoardWidth()) {
                message("You cannot set wwinning sequence bigger than board width or height!");
                continue;
            }
            break;
        }

        playersScoreboard.setWinningSequence(winningSequence);
        return playersScoreboard;
    }

    private void takeBoardDataAndCreate() {

        int height;
        int width;
        message("...Set board size... ");
        while (true) {
            message("Board height: ");
            height = messenger.takeDigit();
            message("Board width");
            width = messenger.takeDigit();

            if (height < 3 || width < 3) {
                messenger.printMessage("You minimum board size: 3x3");
            } else {
                break;
            }
        }
        message("...Preparing board...");
        prepareBoard(height, width);
    }

    private void gamePreparation() {
        message("...Player creation sequence...");

        //TODO create another way of player creation sequence
        for (int i = 0; i < MAX_PLAYERS; i++) {
            message(String.format("Player %d provide name", i + 1));
            String name = messenger.takeCharacterSequence();
            String symbol = "";

            if (players.size() == 0) {
                message(String.format("Player %d provide symbol", i + 1));

                while (!isSymbol(symbol)) {
                    try {
                        symbol = messenger.takePlayerSymbol();
                    } catch (InputMismatchException e) {
                        message("Must be x or o");
                    }
                }
            } else {
                if (players.get(0).getSign().equals("X")) {
                    symbol = "O";
                } else {
                    symbol = "X";
                }
            }
            players.add(createPlayer(symbol, name, players.size() + 1));
        }

        takeBoardDataAndCreate();
    }

    private void message(String message) {
        messenger.printMessage(message);
    }
}
