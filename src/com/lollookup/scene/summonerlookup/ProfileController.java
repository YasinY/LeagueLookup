package com.lollookup.scene.summonerlookup;

import com.lollookup.scene.customcontrols.RankInfo;
import com.lollookup.scene.data.ChampionInfoData;
import com.lollookup.scene.data.SummonerData;
import com.lollookup.scene.customcontrols.ChampionInfo;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.stream.Stream;


/**
 * @author Yasin
 */
public class ProfileController {

    @FXML
    private ImageView profileIcon;

    @FXML
    private Text summonerName;

    @FXML
    private Text summonerLevel;

    @FXML
    private VBox championDataContainer;

    @FXML
    private HBox rankDataContainer;

    public void createProfile(SummonerData data, ChampionInfoData... championData) {
        this.profileIcon.setImage(new Image(data.getProfileIconUrl()));
        this.summonerName.setText(data.getSummonerName());
        this.summonerLevel.setText("Level: " + data.getSummonerLevel());
        Stream.of(championData).forEach(p -> championDataContainer.getChildren().add(new ChampionInfo(p)));
        //data.getLeagueEntries().forEach(y -> y.forEach(leagueEntry -> rankDataContainer.getChildren().add(new RankInfo(leagueEntry))));
    }


}
