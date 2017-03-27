package com.lollookup.scene.customcontrol;

import com.lollookup.scene.data.ChampionInfoData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javax.xml.soap.Text;
import java.io.IOException;

/**
 * @author Yasin
 */
public class ChampionInfo extends Pane {

    @FXML
    private ImageView championImage;

    @FXML
    private Text KDA;

    @FXML
    private Text winRate;

    @FXML
    private Text masteryScore;

    @FXML
    private Text masteryLevel;

    public ChampionInfo(ChampionInfoData championInfoData) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "championinfo.fxml"));
        Pane root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.championImage.setImage(new Image(championInfoData.getUrl()));
        this.KDA.setTextContent(championInfoData.getKDA());
        this.winRate.setTextContent(championInfoData.getWinRate());
        this.masteryScore.setTextContent(championInfoData.getMasteryScore());
        this.masteryLevel.setTextContent(championInfoData.getMasteryLevel());
    }

}
