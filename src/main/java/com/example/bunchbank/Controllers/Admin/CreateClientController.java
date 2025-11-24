package com.example.bunchbank.Controllers.Admin;

import com.example.bunchbank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField password_fld;
    public CheckBox pAddress_box;
    public Label pAddress_lbl;
    public CheckBox ch_acc_box;
    public TextField ch_amount_fld;
    public CheckBox sv_acc_box;
    public TextField sv_amount_fld;
    public Button create_client_btn;
    public Label error_lbl;

    public String payeeAddress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void craeteCheckingAccount() {
        double balance = Double.parseDouble(ch_amount_fld.getText());
        // Generate Account Number
        String firstSection = "3201";
        String lastSection = Integer.toString(new Random().nextInt(9999) + 1000);
        String accountNumber = firstSection + " " + lastSection;
        //Create the Checking Account
        Model.getInstance().getDataBaseDriver().createCheckingAccount(
                payeeAddress, accountNumber, 10, balance
        );
    }
}
