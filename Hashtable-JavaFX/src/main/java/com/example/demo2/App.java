package com.example.demo2;

import com.example.demo2.presentation.model.controller.Presentation;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage)  {
        Presentation presentation = new Presentation();
        presentation.showPresentation(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}