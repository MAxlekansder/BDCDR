package com.alexanderhasslund.demo.main.PlayerInteraction;

import com.alexanderhasslund.demo.main.Player.PlayerController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayerChoice {

    public String nameAndClass() {
        PlayerController playerController = new PlayerController();
        return ("""
                Please choose a class: 
                1. \033[1;31mBARBARIAN\033[0m      2.\033[1;32mROGUE\033[0m     3.\033[1;34mSORCERER\033[0m""");
    }

    public String mainShop() {
        return ("""
                "Welcome to the main shop!
                            
                In here you have the option to buy new equipment
                These three different categories provides different bonus stats while in combat
                                
                \033[1;36mSWORDS            SHIELDS               POTIONS\033[0m
                provides more     provides protection   provides temporary
                damage towards    during combat         buffs during combat
                your enemies      chance to block
                             
                """);
    }

    public String insideShop() {
        return ("""
                
                1. SWORDS - provides more damage towards your enemies 
                2. SHIELDS - provides protection during combat and some shields have a chance to block
                3. POTIONS - provides temporary buffs during combat
                4. LEAVE...
                """);
    }

    public String swordsMenu() {
        return ("""
                WELCOME TO THE SWORD SHOP - - 5 to leave...
                
                \033[1;36m1.STANDARD SWORD     \033[1;34m2.FAST SWORD               \033[1;35m3.SHARP SWORD         \033[1;33m4.DIVINE SWORD\033[0m             
                \033[1;37mTHE TRUSTY SWORD     FOR THE FAST ATTACKER      THE TACHI BLADE       THE ONE TRUE BLADE\033[0m    
                
                Damage 6             Damage 12                  Damage 20             Damage 60
                Level lock 0         Level lock 2               Level lock 5          Level lock 10
                
                Price: 200           Price: 500                 Price: 1000           Price: 3000 
                """);
    }


    public String shieldsMenu() {
        return (""" 
                WELCOME TO THE SHIELD SHOP - 5 to leave...
                
                \033[1;36m1.STANDARD SHILED      \033[1;34m2.BULK SHILED                   \033[1;35m3.SPIKED SHIELD           \033[1;33m4.DIVINE SHIELD\033[0m             
                \033[1;37mTHE TRUSTY SHIELD      YOU'RE NOT GETTING THROUGH      BITE ME, ILL BITE BACK    SVALINN, THE SUN EATER\033[0m 
                
                Defence 5              Defence 10                      Defence 15                Defence 40
                Level lock 0           Level lock 2                    Level lock 5              Level lock 10
                
                Price: 200             Price: 500                      Price: 1000               Price: 3000 
                """);
    }

    public String potionsShop() {
        return ("""
                 WELCOME TO THE POTION SHOP - 5 to leave...
                                
                \033[1;36m1.POTION OF HEALTH    \033[1;34m2.POTION OF DEFENCE           \033[1;35m3.POTION OF HASTE\033[0m            
                \033[1;37mFOR THE THIRSTY       THOUGH NUT, ARENT YOU?        GOTTA GO FAST TO GET TO THE PRINCESS INTIME\033[0m  
                
                Restores some health  Increases defence             Increases your initiative 
                during combat         Sticks entire level           Sticks entire level    
                Health 40             Defence 20                    Initiative 1
                Price: 50             Price: 70                     Price: 200             
                """);
    }

    public String newGameSetup() {
        return ("""
                \033[1;33mNow, you get the chance to decide how many players 
                and what class each player picks 
                Remember that all players and classes gets saved to the same slot \033[0m
                """);
    }

    public void startMenuChoice() {
        Path path = Paths.get("Player.txt");
        //int savedFile = 0; // placeholder
        if (!Files.exists(path)) {
            System.out.println("""
                                        
                    1. START NEW GAME 
                    \033[0;37m2. CONTINUE SAVE \033[0m
                    3. HIGHSCORE
                    4. QUIT GAME""");
        } else
            System.out.println("""
                                    
                    1. START NEW GAME 
                    2. CONTINUE SAVE
                    3. HIGHSCORE
                    4. QUIT GAME""");
    }


    public String mainMenuChoice() {

        return ("""                 
                1. CONTINUE STORY
                2. SHOP
                3. SAVE CURRENT GAME
                4. CURRENT STATUS
                5. QUIT TO MAIN MENU""");
    }


    public String firstFightIntroduction() {
        return ("""
                Welcome hero!
                                
                This is your first time fighting, therefor ill just let you know how the combat works...
                you get some alternatives. As mentioned, every class has their own ability and also a trait...
                            
                Base Attack is just Attacking without consequences of your resource management...
                But spells cost...
                            
                These are the alternatives during combat: 
                CASE 1: Fight! This will present a new menu with the alternative to ATTACK or use SPELLS
                CASE 2: GET STATUS - let's you see how your heroes HP and resource
                CASE 3: FLEE! This fight was maybe too hard? or you forgot to use the shop to upgrade... 
                            
                That's it for now... Lets see how the first Fight goes!
                            
                Remember, killing a monster will grant you both experience for leveling and currency to spend in the shop
                Each floor gets progressively hard for each floor your clear ... and finally meet a boss
                
                Good luck...

                """);
    }

    public String fightSequence() {
        return ("""
                
                            ----\033[1;33m COMBAT OPTIONS \033[0m----
                1. FIGHT  2. USE POTIONS   3. GET STATUS    4. FLEE""");
    }

    public String abilityChoice() {
        return (""" 
                            ----\033[1;33m IN ACTION MENU \033[0m----
                1. BASIC ATTACK     2. CHOOSE SPELL     3. ULTIMATE""");
    }

    public String potionChoice() {
        return (""" 
                            ----\033[1;33m IN POTION MENU \033[0m----
                1. USE POTION    2. BACK TO MENU""");
    }

    public String presentMainMenu() {
        return("""
                
                In the main menu, you have the option to continue progressing to save princess Benny
                after every level there might be something hiding around the corner.
                As you do crawl through all levels, things might get more difficult...
                
                ... i recommend paying a visit to the shop, as you're only able to reach it 
                after each level encounter... """);
    }


    public String presentLevel1() {
        return ("""
                
                1. THE HALLS OF KARAZ
                \033[0;37m2. UPPER PLATEAU
                3. CITY OF MARKAZH
                4. THE GRAND HALL OF THAAL'S\033[0m
                5.BACK TO MAIN MENU
                """);
    }

    public String presentLevel2() {
        return ("""
                
                1. THE HALLS OF KARAZ
                2. UPPER PLATEAU
                \033[0;37m3. CITY OF MARKAZH
                4. THE GRAND HALL OF THAAL'S\033[0m
                5. BACK TO MAIN MENU
                """);
    }

    public String presentLevel3() {
        return ("""
                
                1. THE HALLS OF KARAZ
                2. UPPER PLATEAU
                3. CITY OF MARKAZH
                \033[0;37m4. THE GRAND HALL OF THAAL'S\033[0m
                5. BACK TO MAIN MENU
                """);
    }

    public String presentLevel4() {
        return ("""
                
                1. THE HALLS OF KARAZ
                2. UPPER PLATEAU
                3. CITY OF MARKAZH
                \033[0;37m4. THE GRAND HALL OF THAAL'S\033[0m
                5. BACK TO MAIN MENU
                """);
    }

    public String presentFinalLevel() {
        return ("""
                1. THE HALLS OF KARAZ
                2. UPPER PLATEAU
                3. CITY OF MARKAZH
                4. \033[1;33mTHE GRAND HALL OF THAAL'S\033[0m
                5. BACK TO MAIN MENU
                """);
    }

    public static String spellsBarbarian() {
        return ("""
                1. WAR CRY - grants strength to the party | resource: 20    2. ON THE DEFENCE - grants defence to the barbarian | resource: 10""");
    }

    public static String spellsRogue() {
        return ("""
                1. SHADOW DANCE - strikes target from behind - increasing defence | resource: 50     2. A THIEF'S THIEVERY  - steals gold, why not? resoruce: 30""");
    }

    public static String spellsSorcerer() {
        return ("""
                1. ELEMENTAL BARRIER - shields the player | cost: 40     2. ELEMENTAL BLAST - strikes two targets with elemental force | cost: 60
                """);
    }
}