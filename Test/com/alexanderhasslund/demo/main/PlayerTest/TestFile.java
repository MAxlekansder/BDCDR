package com.alexanderhasslund.demo.main.PlayerTest;

import com.alexanderhasslund.demo.main.Classes.Rogue;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Engine.StringManipulator;
import com.alexanderhasslund.demo.main.Monster.BasicMonsters.MonsterBrute;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.StringLore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFile {

    PlayerControllerTest playerControllerTest = new PlayerControllerTest();


    @Test
    @DisplayName("Test adding players")
    public void testAddPlayer() {

        playerControllerTest.chooseClass();
        PlayerTest currentPlayer = playerControllerTest.getPlayerList().get(0);

        //assertEquals(playerControllerTest.getPlayerList());
        System.out.println(currentPlayer);

        assertTrue(!playerControllerTest.getPlayerList().isEmpty());
    }

    @Test
    @DisplayName("Adding experience")
    public void playerAddExperience() {

        playerControllerTest.chooseClass();
        PlayerTest currentPlayer = playerControllerTest.getPlayerList().get(0);

        //System.out.println(currentPlayer.getExperience());
        currentPlayer.setExperience(100);

        assertEquals(100, currentPlayer.getExperience());
    }

    @Test
    @DisplayName("Adding levels")
    public void calculatePlayerLevel() {

        playerControllerTest.chooseClass();
        PlayerTest currentPlayer = playerControllerTest.getPlayerList().get(0);
        currentPlayer.setExperience(22);
        // level up math
        /*
        level =
         */
        //for (int i = currentPlayer.getExperience(); i > 0; i--) {
        int PlayerCurrentExperienceExperince = currentPlayer.getExperience();
        currentPlayer.setExperience(0);
        System.out.println("old strength "+currentPlayer.getBaseStrength());
        System.out.println("old damage "+currentPlayer.getBaseDamage());
        System.out.println("old defence " + currentPlayer.getBaseDefence());
        System.out.println("old HP" + currentPlayer.getMaxHp());

        for (int i = PlayerCurrentExperienceExperince; i > 0; i--) {
            currentPlayer.setExperience(currentPlayer.getExperience()+1);

            if (currentPlayer.getExperience() == 10) {
                currentPlayer.setLevel(currentPlayer.getLevel() +1);
                currentPlayer.setExperience(0);

                currentPlayer.setBaseStrength(currentPlayer.getBaseStrength() + (int)(currentPlayer.getLevel() / 4));
                currentPlayer.setBaseAgility(currentPlayer.getBaseAgility() + (int)(currentPlayer.getLevel() / 4));
                currentPlayer.setBaseIntellect(currentPlayer.getBaseIntellect() + (int)(currentPlayer.getLevel() / 2.2));

                currentPlayer.setBaseDamage(currentPlayer.getBaseDamage() + (int)(currentPlayer.getBaseStrength() / 10));
                currentPlayer.setBaseDefence(currentPlayer.getBaseDefence() + (int) (currentPlayer.getBaseStrength()/ 7));
                currentPlayer.setMaxHp(currentPlayer.getMaxHp() + (int) (currentPlayer.getBaseStrength() * 0.5));

                currentPlayer.setStrength(currentPlayer.getStrength() + (int)(currentPlayer.getLevel() / 1.1));
                currentPlayer.setAgility(currentPlayer.getAgility() + (int)(currentPlayer.getLevel() / 0.9));
                currentPlayer.setIntellect(currentPlayer.getIntellect() + (int)(currentPlayer.getLevel() / 2.2));

                currentPlayer.setDamage(currentPlayer.getDamage() + (int)(currentPlayer.getStrength() / 3));
                currentPlayer.setDefence(currentPlayer.getDefence() + (int) (currentPlayer.getStrength() * 0.2));
                currentPlayer.setHp(currentPlayer.getHp()+ (int) (currentPlayer.getStrength() * 0.1));

            }
        }

        System.out.println("new strength "+currentPlayer.getBaseStrength());
        System.out.println("New damage " +currentPlayer.getBaseDamage());
        System.out.println("New defence " + currentPlayer.getBaseDefence());
        System.out.println("New HP " + currentPlayer.getMaxHp());

        System.out.println(currentPlayer.getExperience());
        assertEquals(3,currentPlayer.getLevel());
        assertEquals(2, currentPlayer.getExperience());
        assertEquals(currentPlayer.getBaseDamage(), 24);

    }
    @RepeatedTest(3)
    @DisplayName("Adding monsters to list")
    public void addMonsters(){
    List<Monster> monsterListTest = new ArrayList<>();
    monsterListTest.add(new MonsterBrute());

    assertEquals(1,monsterListTest.size());
    }

   // @BeforeAll
   // @Test
    @Timeout(1000)
    public static void testPresentation(){
        StringManipulator stringManipulator = new StringManipulator();
        stringManipulator.manipulateString(StringLore.bossIntroThaal());
        stringManipulator.manipulateString(StringLore.youWin());
    }


    @RepeatedTest(8)
    @DisplayName("testing rand of size")
    public void monsteDoingDamage() {
        Random random = new Random();
        Rogue rogue = new Rogue();
        playerControllerTest.chooseClass();
        PlayerTest currentPlayer = playerControllerTest.getPlayerList().get(0);
        int randomPlayer = random.nextInt(playerControllerTest.getPlayerList().size());
        assertEquals(0,randomPlayer);

        playerControllerTest.chooseClass();
        playerControllerTest.chooseClass();

       assertEquals(3, playerControllerTest.getPlayerList().size());

    }

    @Test
    @DisplayName("Adding barb and rogue")
    public void testAddMorePlayers() {
        playerControllerTest.getPlayerList().add(new BarbarianTest());
        playerControllerTest.getPlayerList().add(new RogueTest());

        assertEquals(2, playerControllerTest.getPlayerList().size());
        playerControllerTest.getPlayerList().remove(0);
        //should return rogue as barb was removed
        assertEquals(playerControllerTest.getPlayerList().get(0).getClassName().startsWith("ROGUE", 6), playerControllerTest.getPlayerList().get(0).getClassName().startsWith("ROGUE", 6));
    }

    @Test
    @DisplayName("For adding names")
    public void testInputString() {
        String strTest = "Alexander";
        assertTrue(strTest instanceof String, strTest);
    }

    @ParameterizedTest
    @ValueSource (ints = {100, 200, 300, 400})
    public void testMoreLevels(int number) {
        playerControllerTest.getPlayerList().add(new BarbarianTest());
        PlayerTest currentPlayerTest = playerControllerTest.getPlayerList().get(0);

        int PlayerCurrentExperienceExperince = number;
        currentPlayerTest.setExperience(0);
        int expectedLevels = 1;
        for (int i = PlayerCurrentExperienceExperince; i > 0; i--) {
            currentPlayerTest.setExperience(currentPlayerTest.getExperience()+1);


            if (currentPlayerTest.getExperience() == 100) { // fix better logic for leveling...

                currentPlayerTest.setLevel(currentPlayerTest.getLevel() +1);

                currentPlayerTest.setExperience(0);
                expectedLevels++;

            }
        }
        assertEquals(expectedLevels,currentPlayerTest.getLevel());

    }

}
