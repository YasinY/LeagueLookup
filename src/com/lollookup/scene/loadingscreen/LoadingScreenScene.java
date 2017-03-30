package com.lollookup.scene.loadingscreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Yasin on 20.03.2017.
 * @version 1.0
 */
public class LoadingScreenScene extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("entry.fxml"));
        primaryStage.setTitle("League Lookup");
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) {

    }
}
