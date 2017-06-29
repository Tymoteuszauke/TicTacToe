package com.bratek;

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

//    @Test(expectedExceptions = InputMismatchException.class)
//    public void shouldThrowExceptionIfSignIsNotValid(){
//        Game game = new Game();
//        String sign = "O";
//        InputStream stream = new ByteArrayInputStream(sign.getBytes());
//        game.setUserSign(sign);
//    }

    @Test(expectedExceptions = InputMismatchException.class)
    public void shouldThrowExceptionIfNameIsNotValid(){
        Game game = new Game();
        String incorrectName = "12zxcq..a.";
        InputStream stream = new ByteArrayInputStream(incorrectName.getBytes());
        game.createPlayer(stream);
    }

    @Test
    public void shouldCreateUserIfNameIsValid(){
        Game game = new Game();
        String correctName = "Matt";

        game.createPlayer(new ByteArrayInputStream(correctName.getBytes()));

        assertTrue(game.players.contains(new Player(correctName)));
    }


}
