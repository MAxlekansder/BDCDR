package com.alexanderhasslund.demo.main.PlayerInteraction;

import com.alexanderhasslund.demo.main.Engine.StringManipulator;

import static com.alexanderhasslund.demo.main.Engine.Color.*;

public class StringLore {

    public String intro() {
        StringManipulator stringManipulator = new StringManipulator();


        return ("""
                'QUICK! The princess have been kidnapped...         
                Stowed away and locked in the dungeon darkest!' shouts a distressed citizen 
                'Someone needs to save the princess!!' another one yells
                                
                In the quiet town where nothing really happens, chaos abruptly spews out from the sewers 
                with fell-like substance, swords and mayhem
                obscure creatures raids the city 
                                
                                
                As the fires burns faster, with the town in flames you rush towards your hero deeds
                'Save the princess and save the city' you say to yourself, and with a heroes experience 
                something, or someone must have planned this...
                                
                you see a trail of blood, mud and a substance with a greenish texture leading to a alley. 
                The one alley you've seen so many times before, but never put much thought to it.
                You follow it, but in a snap 
                you fall down
                                
                realizing you're trapped. 
                Stuck in this hell, witnessing a world underneath
                the underworld 
                                
                left alone...
                to die... 
                
                """);
    }

    public void gameTitle() {

        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(RED + "SAVE PRINCESS BENNY" + RESET);
                Thread.sleep(300);
            }
        } catch (Exception e) {
            System.out.println("Intro taking to long");
        }
    }


    public String gameIntroductionClasses() {
        return ("""
                
                Hello Hero! 
                                
                Welcome to the game 'SAVE PRINCESS BENNY' 
                you have 3 classes... to start with.
                -----------------------------------------------------
                \033[1;31mBARBARIAN         \033[1;32mROGUE              \033[1;34mSORCERER\033[0m
                \033[1;37mBERSERKERS RAGE   FOOLS ELUSIVENESS  DRAGONS BREATH\033[0m
                High strength     High agility       High intellect
                High damage       Medium damage      Ramping damage
                High defence      Medium defence     Low defence   
                Low resource      High resource      Ramping resource
                
                -----------------------------------------------------   
                """);
    }

    public String ultimateClassPresentation() {
        return ("""
                
                -----------------------------------------------------   
                \033[1;33mBERSERKERS RAGE                    FOOLS ELUSIVENESS               DRAGONS BREATH\033[0m
                Executes the target                The rogue performs a            The sorcerer breathes fire  
                when monster hp is lower than      swift attack striking           to every enemy dealing massive
                30% of barbarians hp               all targets the rogue is        area of effect damage 
                                                   facing, dealing                 **TBA** burning every enemy
                                                   damage. But has a               for 2 rounds
                                                   **TBA**2 rounds cooldown
                -----------------------------------------------------   
                """);
    }

    public String gameIntroductionRules() {
        return ("""
                
                Each Class have their own unique play style and attributes:
                All have one basic attack that doesnt cost any resource. 
                Two spells that's on a low cost and deals modirate damage 
                One ulitmate as seen above - cost a lot more and might only be used once per level
                \033[1;31mBERSERKERS RAGE\033[0m | COSTS: 100
                \033[1;32mFOOLS ELUSIVENESS\033[0m | COSTS: 100
                \033[1;34mDRAGONS BREATH\033[0m | COSTS: 100
                
                Every class has a higher set of the main attributes per class -
                
                \033[1;31mStrength\033[0m is an attribute that grants more raw damage and protection
                \033[1;32mAgility\033[0m is an attirubte that has higher initiative and abit hybrid
                \033[1;34mIntellect\033[0m is an attribute that gives higher resources and higher scaling damage
                                
                                To make the best of it - PLEASE use only one of the classes per player...
                                therefor a 3 player party restriction.
                                
                The game have some basic rules, you can either play alone or with a party of 3.
                it's a turned based dungeon crawl, with a rogue-like twist... 
                You'll explore the skewering underworld of Azgarak Karaz
                beneath the city of Elderwell
                                
                where both you and the monsters have initative, the lower the number
                the better the chances for you to start the round
                                
                but remember...
                                
                If you die - the game ends
                good luck heroes...
                """);
    }


    public static String reachingUpperPlateau() {
        return ("""
                You've never seen anything like that before, rat-like vermin's...
               
                a whole world opens up, nothing like the world you're use to.
                In the underworld, a fully fleshed machinery with black tar build with black steel
                
                you can't believe your eyes when you reach the upper plateau
                """);
    }

    public static String theInnerCity() {
        return ("""
                The brothers was a though fight, but you made it trough
                
                "It's been entertaining" a voice echoes from above 
                
                "Never have we seen such a performance, usually no one gets passed the Deathrattler..."
                
                "Pfft... my grand design of the twins, Bram could have taken them out by himself!" 
                
                "SILENCE" - a figure that appears to be their leader shouts 
                
                "At least they're not getting past us. Guards! Kill them..."
                """);
    }

    public static String theFinalRoomThaal() {
        return("""
                With their last, final breath - the council of thaal in unison 
                breathes: " don't let them through" 
                
                "Thaal can't know that we failed..." 
                
                As the leader draws her last breath, you see her pointing towards something
                reaching for salvation and forgiveness, but get's no response 
                
                you realise that the leader was pointing towards a path leading to 
                the final plateau 
                
                but as you push for it, you still feel the curse of the inquisition....
                """);
    }

    public static String bossTagTeam() {
        return("""
                sounding like a horde running towards the last room you just finished
                Like shadows, you see nothing but the sound keeps getting closer
                
                you realise, there's not just one - but three of them...
                """);
    }

    public static String bossTwinBrothers() {
        return ("""
                In a synchronized sweep, two stone statues taller than anything you seen before
                both with a halberd reaching to the heavens - in a second both have entered a
                defensive state, you didnt have time to blink...
                
                They seem to be in sync, aligned with each other performing everything coordinated.
                """);
    }



    public static String bossTheInquisition() {
        return ("""
                
                The six of the order, the inquisition have been watching 
                all along - evaluating and playing with that you have faced as pawns
                
                Now it's time to take down the council of Thaal...
               
                """);
    }


    public static String bossIntroThaal() {
        return ("""
                
                
                In the realm of shadows, I, Thaal, the unyielding warlord
                command the underworld with a malevolence that echoes through every cavern and crevice 
                My dominion is draped in fear, and those who dare defy me find their destinies entwined with despair 
                The underworld bends to my will, a symphony of darkness conducted by the unchallenged lord of shadows
                
                This will not be a set back, everything you faced so far is just numbers. The council of what? Really...
                
                But do come - let the last breath you take, be be taken by me.
                """);
    }

    public static String bossLastBossThaal() {
        return ("""
                
                In Thaal's last breaths, a twisted satisfaction lingered in the corners of his consciousness. 
                Death had claimed him, but his malevolent legacy would endure, a shadow cast across the realms 
                whispering tales of the once-mighty Thaal who dared to challenge even the forces of darkness.
                
                "In our world, you're the antagonist" he laughed. Spitting blood as he tries to stand up.
                
                With out hesitation, you slit Thaals throat - ending his life for good...
                
               
                """);
    }

    public static String youWin() {
        return ("""
                            ===== CONGRATULATIONS! =====
                You saved the city and the princess! BENNY IS NOW SAFE! 
                
                        T H A N K  Y O U  F O R  P L A Y I N G
                        
                                   Designed by
                                Alexander Hasslund
                                
                                    Music by
                                Alexander Hasslund
                                
                                Scene and graphics 
                                Alexander hasslund
                                
                Voice actor 1, monster brute - Alexander Hasslund
                Voice actor 2, monster spellweaver - Alexander Hasslund    
                Voice actor 3, monster ranger - Alexander Hasslund
                Voice actor 4, guy on the phone in the back - Alexander Hasslund
                                
                """);
    }

}
