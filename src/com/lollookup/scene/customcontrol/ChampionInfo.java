package com.lollookup.scene.customcontrol;

import com.lollookup.scene.data.ChampionInfoData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @author Yasin
 */
public class ChampionInfo extends Pane {

    @FXML
    private ImageView championImage;

    @FXML
    private Text name;

    @FXML
    private Text KDA;

    @FXML
    private Text winRate;

    @FXML
    private Text masteryScore;

    @FXML
    private Text masteryLevel;

    public ChampionInfo(ChampionInfoData championInfoData) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("championinfo.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.championImage.setImage(new Image(championInfoData.getUrl()));
        this.name.setText(championInfoData.getName());
        this.KDA.setText("KDA: " + championInfoData.getKDA());
        this.winRate.setText("Win rate: " + championInfoData.getWinRate());
        this.masteryScore.setText("Mastery score: " + championInfoData.getMasteryScore());
        this.masteryLevel.setText("Mastery leve: " + championInfoData.getMasteryLevel());
    }

}
