package com.lollookup.scene.entry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * @author Yasin on 20.03.2017.
 * @version 1.0
 */
public class EntryScene extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lollookup/scene/entry/entry.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        EntryController entryController = loader.getController();
        entryController.setStage(primaryStage);
        primaryStage.setTitle("League Lookup");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
