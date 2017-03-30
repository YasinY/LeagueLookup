package com.lollookup.scene.summonerlookup;

import com.lollookup.scene.customcontrols.RankInfo;
import com.lollookup.scene.data.ChampionInfoData;
import com.lollookup.scene.data.SummonerData;
import com.lollookup.scene.customcontrols.ChampionInfo;
import com.lollookup.scene.entry.EntryController;
import com.lollookup.scene.entry.EntryScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;


/**
 * @author Yasin
 */
public class ProfileController implements Initializable {

    @FXML
    private ImageView profileIconBorder;
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

    @FXML
    public void createNewLookup() {
        try {
            new EntryScene().start(new Stage(StageStyle.DECORATED));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createProfile(SummonerData data, ChampionInfoData... championData) {
        this.profileIcon.setImage(new Image(data.getProfileIconUrl(), 114, 93, false, true));
        //this.profileIcon.setFitHeight(128);
        this.summonerName.setText(data.getSummonerName());
        this.summonerLevel.setText("Level: " + data.getSummonerLevel());
        Stream.of(championData).forEach(p -> championDataContainer.getChildren().add(new ChampionInfo(p)));
        data.getLeagueEntries().forEach(y -> y.forEach(leagueEntry -> rankDataContainer.getChildren().add(new RankInfo(leagueEntry))));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profileIconBorder.setImage(new Image("http://www.sh0ck.bplaced.net/league_assets/utils/border_128x128.png", 128, 128, false, false));
    }
}
