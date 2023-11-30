package com.alexanderhasslund.demo.main;
import com.alexanderhasslund.demo.main.Engine.DatabaseConnector;
import com.alexanderhasslund.demo.main.PlayerInteraction.GameStartControl;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        GameStartControl gameStartControl = new GameStartControl();

        gameStartControl.startIntroductionGame();


    }
}


