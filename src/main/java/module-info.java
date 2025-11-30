module com.example.bunchbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;
    requires javafx.graphics;
//    requires com.example.bunchbank;


    opens com.example.bunchbank to javafx.fxml;
    exports com.example.bunchbank;
    exports com.example.bunchbank.Controllers;
    exports com.example.bunchbank.Controllers.Admin;
    exports com.example.bunchbank.Controllers.Client;

    exports com.example.bunchbank.Models;
    exports com.example.bunchbank.Views;
}