package com.example.bunchbank.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseDriver {

    private Connection conn;

    public DataBaseDriver() {
        //jdbc:sqlite:E:/JavaProjects/MazeBank/mazebank.db
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:mazebank.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //
//    Client Section
//


    //
//    Admin Section
//


    //
//    Utility Methods
//

}
