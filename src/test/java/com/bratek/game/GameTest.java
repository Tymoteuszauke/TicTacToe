package com.bratek.game;

import com.bratek.communication.UIMessenger;
import com.bratek.game.Game;
import com.bratek.player.Player;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;

import static org.testng.Assert.assertEquals;

/**
 * Created by bratek on 29.06.17.
 */
@Test
public class GameTest {

    private Game game;

    private UIMessenger uiMessenger;

    @BeforeTest
    public void prepare() {
        this.uiMessenger = new UIMessenger(System.out, System.in);
        this.game = new Game(uiMessenger);
    }

    @DataProvider(name = "sizeOfBoard")
    public static Object[][] sizeOfBoard() {
        return new Object[][]{
                {3, 3},
                {5, 5},
                {10, 10}
        };
    }

    @DataProvider(name = "correctNames")
    public static Object[][] correctNames() {
        return new Object[][]{
                {"Matt", "X"},
                {"Kasia", "X"},
                {"Janek", "O"},
                {"marco", "X"},
                {"Mar22", "X"}
        };
    }

    @DataProvider(name = "incorrectNames")
    public static Object[][] incorrectNames() {
        return new Object[][]{
                {"Mate.12", "ASdqw123"},
                {"mate.12", "asdo2pwqe "},
                {"mate*/", "asd~"}
        };
    }

    @DataProvider(name = "correctCommands")
    public static Object[][] correctCommands() {
        return new Object[][]{
                {"X"},
                {"O"},
        };
    }


    @DataProvider
    public static Object[][] incorrectSymbols() {
        return new Object[][]{
                {"Xczq*"},
                {"Oqwe/"},
                {"123 asdq"},
                {"A/"}
        };
    }

    @DataProvider
    public static Object[][] playersCreationData() {
        return new Object[][]{
                {new Player.PlayerBuilder()
                        .positionOrder(1)
                        .setSign("X")
                        .name("Tymek")
                        .build()},
                {new Player.PlayerBuilder()
                        .positionOrder(1)
                        .setSign("O")
                        .name("Remigiusz")
                        .build()}
        };
    }



//    @Test(dataProvider = "correctCommands")
//    public void shouldReturnPlayerCommand(String command) {
//        uiMessenger.setInputStream(new ByteArrayInputStream(command.getBytes()));
//        assertEquals(game.takePlayerSymbolInput(), command);
//    }

    @Test(expectedExceptions = InputMismatchException.class, dataProvider = "incorrectSymbols")
    public void shouldThrowAnExceptionIfCommandIsIncorrect(String command) {

        uiMessenger.setInputStream(new ByteArrayInputStream(command.getBytes()));
        uiMessenger.takePlayerSymbol();
    }


    @DataProvider
    public static Object[][] playersAllowedSymbols() {
        return new Object[][]{
                {"X"},
                {"O"}
        };
    }

    @Test(dataProvider = "playersCreationData")
    public void createPlayersTest(Player player) {
        Player comparePlayer = game.createPlayer(player.getSign(), player.getName(), player.getPosition());
        assertEquals(comparePlayer, player);
    }

    @Test
    public void isSymbolTestExpectTrue() {
        String symbol = "O";
        assertEquals(game.isSymbol(symbol), true);
        symbol = "X";
        assertEquals(game.isSymbol(symbol), true);
    }

    @Test(dataProvider = "incorrectSymbols")
    public void isSymbolTestExpectFalse(String symbol) {
        assertEquals(game.isSymbol(symbol), false);
    }


}
