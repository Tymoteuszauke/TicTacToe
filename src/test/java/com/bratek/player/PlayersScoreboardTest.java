package com.bratek.player;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Mateusz on 07.07.2017.
 */
public class PlayersScoreboardTest {

    private List<Player> players = new ArrayList<>();
    private PlayersScoreboard playersScoreboard;

    @DataProvider
    public static Object[][] dataProviderMethodName() {
        return new Object[][] {
        {}
        };
    }

    @BeforeMethod()
    public void setupPlayers() {



        players.add(new Player.PlayerBuilder()
                .name("Tymek")
                .positionOrder(1)
                .setSign("X")
                .build());

        players.add(new Player.PlayerBuilder()
                .name("Remigiusz")
                .positionOrder(2)
                .setSign("O")
                .build());

        playersScoreboard = new PlayersScoreboard();
        playersScoreboard.providePlayers(players);
        playersScoreboard.setScoreListener(() -> {

        });
    }

    @AfterMethod
    public void clearPlayers() {
        players.clear();
    }

    @Test
    public void testMinimumGamesEncounteredExpectTrue() {

        playersScoreboard.addPoint(players.get(0));
        playersScoreboard.addPoint(players.get(0));
        playersScoreboard.addPoint(players.get(0));

        assertEquals(playersScoreboard.minimumGamesEncountered(), true);
    }

    @Test
    public void testPrintScore() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        playersScoreboard.addPoint(players.get(0));
        playersScoreboard.addPoint(players.get(0));
        playersScoreboard.addPoint(players.get(0));

        playersScoreboard.addPoint(players.get(1));

        playersScoreboard.printScore();

        String whatIsInConsole = out.toString();
        String whatShouldBePrinted =  "Amount of games played: " + playersScoreboard.getGamesCounter() +
                System.getProperty("line.separator") + playersScoreboard.getPlayers() + System.getProperty("line.separator");

        assertEquals(whatIsInConsole, whatShouldBePrinted);
    }

    @Test
    public void testAddPoint() throws Exception {

        playersScoreboard.addPoint(players.get(0));
        playersScoreboard.addPoint(players.get(0));

        playersScoreboard.addPoint(players.get(1));

        assertEquals((int)playersScoreboard.getPlayers().get("Tymek"), 2);

    }

}