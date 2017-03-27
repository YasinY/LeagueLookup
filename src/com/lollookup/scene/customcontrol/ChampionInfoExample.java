package com.lollookup.scene.customcontrol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Yasin
 */
public class ChampionInfoExample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //ChampionInfo championInfo = new ChampionInfo(new ChampionInfoData("https://placeholdit.imgix.net/~text?txtsize=33&txt=350%C3%97150&w=350&h=150", "1:1", "50%", "0", "1"));
        ChampionInfo championInfo = new ChampionInfo();
        //championInfo.setData(new ChampionInfoData("https://placeholdit.imgix.net/~text?txtsize=33&txt=350%C3%97150&w=350&h=150", "1:1", "50%", "0", "1"));
        stage.setScene(new Scene(championInfo));
        stage.setTitle("Custom Control");
        stage.setWidth(300);
        stage.setHeight(200);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
