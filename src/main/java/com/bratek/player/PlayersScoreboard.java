package com.bratek.player;

import com.bratek.game.ScoreListener;

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

    public boolean minimumGamesEncountered() {
        return gamesCounter == 3;
    }

    public void providePlayers(List<Player> players) {
        for (Player player : players) {
            this.players.put(player.getName(), 0);
        }
    }

    public void printScore() {
        System.out.println("Amount of games played: " + gamesCounter);
        System.out.println(players);
    }

    public void addPoint(Player currentPlayer) {
        gamesCounter++;
        int currentScore = players.get(currentPlayer.getName());
        players.replace(currentPlayer.getName(), currentScore + 1);
    }

    public void setScoreListener(ScoreListener scoreListener) {
        this.scoreListener = scoreListener;
    }
}

