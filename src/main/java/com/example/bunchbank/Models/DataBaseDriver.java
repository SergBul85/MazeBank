package com.example.bunchbank.Models;

import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDate;

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

    public ResultSet getTransactions(String pAddress, int limit) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM TRANSACTIONS " +
                    "WHERE Sender = '" + pAddress + "' OR Receiver = '" + pAddress + "' LIMIT " + limit);
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

    public void createClient(String fName, String lName, String pAddress, String password, LocalDate date) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate(
                    MessageFormat.format("INSERT INTO Clients (FirstName, LastName, PayeeAddress, Password, Date) " +
                            "VALUES (''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'')", fName, lName, pAddress, password, date)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createCheckingAccount(String owner, String number, double tLimit, double balance) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate(MessageFormat.format("INSERT INTO CheckingAccounts (Owner, AccountNumber, TransactionLimit, Balance) " +
                    "VALUES (''{0}'', ''{1}'', ''{2}'', ''{3}'')", owner, number, tLimit, balance));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSavingsAccount(String owner, String number, double withdrawalLimit, double balance) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate(MessageFormat.format("INSERT INTO SavingsAccounts " +
                    "(Owner, AccountNumber, WithdrawalLimit, Balance) " +
                    "VALUES (''{0}'', ''{1}'', ''{2}'', ''{3}'')", owner, number, withdrawalLimit, balance));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllClientData() {
        Statement statement;
        ResultSet resultSet = null;

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet searchClient(String pAddress) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(MessageFormat.format("SELECT * FROM Clients WHERE PayeeAddress = ''{0}''", pAddress));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void depositSavings(String pAddress, double amount) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("UPDATE  SavingsAccounts SET " +
                    "Balance = " + amount + " WHERE Owner = '" + pAddress + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //
//    Utility Methods
//

    public int getLastClientsId() {
        Statement statement;
        ResultSet resultSet;

        int id = 0;

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sqlite_sequence WHERE name='Clients'");
            id = resultSet.getInt("seq");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ResultSet getCheckinAccountData(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAccounts WHERE Owner = '" + pAddress + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet getSavingsAccountData(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner = '" + pAddress + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}
