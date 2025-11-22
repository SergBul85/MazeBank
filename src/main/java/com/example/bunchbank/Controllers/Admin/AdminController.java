package com.example.bunchbank.Controllers.Admin;

import com.example.bunchbank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMemuItem().addListener((
                observable, oldValue, newValue) -> {
            switch (newValue) {
                case CREATE_CLIENT:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateClientView());
                    break;
                case DEPOSIT:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getDepositView());
                    break;
                default:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getClientsView());
            }

        });
    }
}
