package com.bratek.game;

import com.bratek.board.Board;
import com.bratek.communication.Messenger;
import com.bratek.exceptions.AlreadyTakenPositionException;
import com.bratek.player.Player;
import com.bratek.player.PlayersScoreboard;
import com.bratek.utils.WinUtil;

/**
 * Created by Mateusz on 10.07.2017.
 */
public class Turn {

    private Player currentPlayer;
    private PlayersScoreboard playersScoreboard;
    private Board board;
    private Messenger messenger;

    private Turn(){}

    static class TurnBuilder {
        Turn turn = new Turn();

        public TurnBuilder player(Player player) {
            turn.currentPlayer = player;
            return this;
        }

        public TurnBuilder playersScoreboard(PlayersScoreboard playersScoreboard) {
            turn.playersScoreboard = playersScoreboard;
            return this;
        }

        public TurnBuilder board(Board board) {
            turn.board = board;
            return this;
        }

        public TurnBuilder messenger(Messenger messenger) {
            turn.messenger = messenger;
            return this;
        }

        public Turn build() {
            return turn;
        }
    }

    protected void makeTurn() {
        if (currentPlayer != null && playersScoreboard != null && board != null && messenger != null) {

            messenger.printMessage(String.format("...%s your move...", currentPlayer.getName()));
            messenger.printMessage(board.toString());
            int chosenPosition = -1;

            boolean goAgain = true;
            do {
                try {
                    chosenPosition = messenger.takePlayerMove();
                    board.draw(currentPlayer.getSign(), chosenPosition);
                } catch (AlreadyTakenPositionException e) {
                    messenger.printMessage("You cannot overwrite a position!");
                    continue;
                } catch (NumberFormatException e) {
                    messenger.printMessage("Only decimal numbers allowed");
                    continue;
                }
                goAgain = false;
            } while (goAgain);

            if (WinUtil.winnerFound(board, chosenPosition, playersScoreboard.getWinningSequence())) {
                playersScoreboard.addPoint(currentPlayer);
                playersScoreboard.printScore();
                board.clear();
            }

        }
    }
}
