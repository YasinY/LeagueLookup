package com.lollookup.scene.customcontrols;

import com.yasinyazici.riot.data.summoner.ranked.league.LeagueEntry;
import com.yasinyazici.riot.data.summoner.ranked.league.LeagueEntryData;
import com.yasinyazici.riot.data.summoner.ranked.league.QueueType;
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
public class RankInfo extends Pane {

    @FXML
    private Text queueType;

    @FXML
    private ImageView rankImage;

    @FXML
    private Text divisionName;

    @FXML
    private Text rank;

    @FXML
    private Text lpAmount;


    public RankInfo(LeagueEntry leagueEntry) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rankinfo.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LeagueEntryData leagueEntryData = leagueEntry.getEntries().get(0);
        String tier = leagueEntry.getTier();
        queueType.setText(QueueType.identifyQueueType(leagueEntry.getQueue()).getName());
        rankImage.setImage(new Image("http://sh0ck.bplaced.net/league_assets/rank_images/" + tier.toLowerCase() + ".png"));
        divisionName.setText(leagueEntry.getName());
        rank.setText(tier + " " + leagueEntryData.getDivision());
        lpAmount.setText(leagueEntryData.getLeaguePoints() + " LP");
    }
}
