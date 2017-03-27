package com.lollookup.scene.summonerlookup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Yasin
 */
public class ProfileScene extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile.fxml"));
        //fxmlLoader.setController(new LoadingScreenController("simon"));
        primaryStage.setTitle("League Lookup");
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
    }
}
