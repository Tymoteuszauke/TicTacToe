package com.bratek.player;

import com.bratek.communication.Messenger;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mateusz on 05.07.2017.
 */

public class PlayersScoreboard {

    private Map<String, Integer> players = new HashMap<>();
    private int gamesCounter;
    private ScoreListener scoreListener;
    private Messenger messenger;
    private int winningSequence;

    boolean minimumGamesEncountered() {
        return gamesCounter == 3;
    }

    int getGamesCounter() {
        return gamesCounter;
    }

    public Map<String, Integer> getPlayers() {
        return Collections.unmodifiableMap(players);
    }

    public void providePlayers(List<Player> players) {
        for (Player player : players) {
            this.players.put(player.getName(), 0);
        }
    }

    public void printScore() {
        messenger.printMessage("Amount of games played: " + gamesCounter);
        messenger.printMessage(players.toString());
    }

    public void addPoint(Player currentPlayer) {
        gamesCounter++;
        int currentScore = players.get(currentPlayer.getName());
        players.replace(currentPlayer.getName(), currentScore + 1);

        if (minimumGamesEncountered()) {
            scoreListener.minGamesPrompt();
            gamesCounter = 0;
        }
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public void setScoreListener(ScoreListener scoreListener) {
        this.scoreListener = scoreListener;
    }

    public void setWinningSequence(int winningSequence) {
        this.winningSequence = winningSequence;
    }

    public int getWinningSequence() {
        return winningSequence;
    }
}

