package com.example.bunchbank.Controllers.Admin;

import com.example.bunchbank.Models.Model;
import com.example.bunchbank.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        create_client_btn.setOnAction(event -> onCreateClient());
        clients_btn.setOnAction(event -> onCLients());
        deposit_btn.setOnAction(event -> onDeposit());
    }

    private void onCreateClient() {
        Model.getInstance().getViewFactory().getAdminSelectedMemuItem().set(AdminMenuOptions.CREATE_CLIENT);
    }

    private void onCLients() {
        Model.getInstance().getViewFactory().getAdminSelectedMemuItem().set(AdminMenuOptions.CLIENTS);
    }

    private void onDeposit() {
        Model.getInstance().getViewFactory().getAdminSelectedMemuItem().set(AdminMenuOptions.DEPOSIT);
    }

}
