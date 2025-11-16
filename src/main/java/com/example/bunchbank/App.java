package com.example.bunchbank;

import com.example.bunchbank.Views.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showLoginWindow();

    }
}
