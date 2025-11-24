package com.example.bunchbank.Models;

import com.example.bunchbank.Views.AccountType;
import com.example.bunchbank.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Model {

    private static Model model;
    private final ViewFactory viewFactory;
    private final DataBaseDriver dataBaseDriver;

    // Client Data Section
    private final Client client;
    private boolean clientLoginSuccessFlag;

    // Admin Data Section

    private boolean adminLoginSuccessFlag;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.dataBaseDriver = new DataBaseDriver();

        // Client Data Section
        this.clientLoginSuccessFlag = false;
        this.client = new Client("", "", "", null, null, null);

        // Admin Data Section
        this.adminLoginSuccessFlag = false;

    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DataBaseDriver getDataBaseDriver() {
        return dataBaseDriver;
    }

    //
    // Client Method Section
    //

    public boolean getClientLoginSuccessFlag() {
        return clientLoginSuccessFlag;
    }

    public void setClientLoginSuccessFlag(boolean flag) {
        this.clientLoginSuccessFlag = flag;
    }

    public Client getClient() {
        return client;
    }

    public void evaluateClientCred(String pAddress, String password) {
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = dataBaseDriver.getClientData(pAddress, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.payeeAddressProperty().set(resultSet.getString("PayeeAddress"));
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]),
                        Integer.parseInt(dateParts[1]),
                        Integer.parseInt(dateParts[2]));
                this.client.dateProperty().set(date);
                this.clientLoginSuccessFlag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //
    // Admin Method Section
    //

    public boolean getAdminLoginSuccessFlag() {
        return adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean flag) {
        this.adminLoginSuccessFlag = flag;
    }

    public void evaluateAdminCred(String username, String password) {
        ResultSet resultSet = dataBaseDriver.getAdminData(username, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.adminLoginSuccessFlag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
