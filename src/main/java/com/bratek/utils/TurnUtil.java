package com.bratek.utils;

import com.bratek.board.Board;
import com.bratek.communication.Messenger;
import com.bratek.player.Player;
import com.bratek.player.PlayersScoreboard;

/**
 * Created by Mateusz on 09.07.2017.
 */
public class TurnUtil {
    
    public static void makeTurn(Player currentPlayer, PlayersScoreboard playersScoreboard, Board board) {
        Messenger messenger = MessageUtil.Instance.getMessenger();
        messenger.printMessage(String.format("...%s your move...", currentPlayer.getName()));
        messenger.printMessage(board.toString());
        int chosenPosition = messenger.takeDigit();
        board.draw(currentPlayer.getSign(), chosenPosition);

        if (WinUtil.winnerFound(board, chosenPosition, 3)) {
            playersScoreboard.addPoint(currentPlayer);
            playersScoreboard.printScore();
            board.clear();
        }
    }
}
