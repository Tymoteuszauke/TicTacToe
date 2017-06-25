package com.bratek;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;

import static org.testng.Assert.assertTrue;

/**
 * Created by bratek on 29.06.17.
 */
@Test
public class GameTest {

    private Game game;

    @BeforeTest
    public void prepare(){
        this.game = new Game();
    }

    @DataProvider(name = "correctNames")
    public static Object[][] correctNames(){
        return new Object[][]{
                {"Matt", true},
                {"Kasia", true},
                {"Janek", true},
                {"marco", true}
        };
    }

    @DataProvider(name = "incorrectNames")
    public static Object[][] incorrectNames(){
        return new Object[][]{
                {"Mate.12", "ASdqw123"},
                {"mate.12", "asdo2pwqe "},
                {"mate*/", "asd~"}
        };
    }

    @Test(expectedExceptions = InputMismatchException.class, dataProvider = "incorrectNames")
    public void shouldThrowExceptionIfNameIsNotValid(String incorrectName, String incorrectName2){
        InputStream stream = new ByteArrayInputStream(incorrectName.getBytes());
        game.createPlayer(stream);
        stream = new ByteArrayInputStream(incorrectName2.getBytes());
        game.createPlayer(stream);
    }

    @Test(dataProvider = "correctNames")
    public void shouldCreateUserIfNameIsValid(String correctName, boolean result){
        game.createPlayer(new ByteArrayInputStream(correctName.getBytes()));

        assertTrue(game.players.contains(new Player(correctName)));
    }


}
