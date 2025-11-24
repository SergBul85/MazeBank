package com.example.bunchbank.Models;

import java.sql.*;

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
    public ResultSet getClientData(String pAddress, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CLIENTS WHERE PayeeAddress = '" + pAddress + "' AND Password = '" + password + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    //
//    Admin Section
//
    public ResultSet getAdminData(String username, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Admins WHERE Username = '" + username + "' AND Password = '" + password + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //
//    Utility Methods
//

}
