package com.bratek;

import com.bratek.communication.UIMessenger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by bratek on 29.06.17.
 */
@Test
public class GameTest {

    private Game game;

    private UIMessenger uiMessenger;
    @BeforeTest
    public void prepare(){
        this.uiMessenger = new UIMessenger(System.out, System.in);
        this.game = new Game(uiMessenger);
    }

    @DataProvider(name = "sizeOfBoard")
    public static Object[][] sizeOfBoard(){
        return new Object[][]{
                {3,3},
                {5,5},
                {10,10}
        };
    }

    @DataProvider(name = "correctNames")
    public static Object[][] correctNames(){
        return new Object[][]{
                {"Matt", "X"},
                {"Kasia", "X"},
                {"Janek", "O"},
                {"marco", "X"},
                {"Mar22", "X"}
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

    @DataProvider(name = "correctCommands")
    public static Object[][] correctCommands(){
        return new Object[][]{
                {"X",""},
                {"O",""},
                {"Matt", ""}
        };
    }


    @DataProvider(name = "incorrectCommands")
    public static Object[][] incorrectCommands(){
        return new Object[][]{
                {"Xczq*",""},
                {"Oqwe/",""},
                {"123 asdq",""},
                {"A/", ""}
        };
    }

    @Test(expectedExceptions = InputMismatchException.class, dataProvider = "incorrectNames")
    public void shouldThrowExceptionIfPlayerIsNotValid(String incorrectName, String sign){
        uiMessenger.setInputStream(new ByteArrayInputStream(incorrectName.getBytes()));
        game.createPlayer();

    }



    @Test(dataProvider = "correctCommands")
    public void shouldReturnPlayerCommand(String command, String result){
        uiMessenger.setInputStream(new ByteArrayInputStream(command.getBytes()));

        assertEquals(game.takePlayerCommand(),command);
    }

    @Test(expectedExceptions = InputMismatchException.class, dataProvider = "incorrectCommands")
    public void shouldThrowAnExceptionIfCommandIsIncorrect(String command, String result){
        uiMessenger.setInputStream(new ByteArrayInputStream(command.getBytes()));
        game.takePlayerCommand();
    }

    @Test(dataProvider = "sizeOfBoard")
    public void shouldCreateEmptyBoardWithGivenSize(int vertical, int horizontal){
        //game.createHashBoard(vertical * horizontal);

        //assertEquals(vertical * horizontal, game.getBoard().size());
    }


}
