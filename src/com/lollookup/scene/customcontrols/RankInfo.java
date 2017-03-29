package com.lollookup.scene.customcontrols;

import com.yasinyazici.riot.data.summoner.ranked.league.LeagueEntry;
import com.yasinyazici.riot.data.summoner.ranked.league.LeagueEntryData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javax.xml.soap.Text;
import java.io.IOException;

/**
 * @author Yasin
 */
public class RankInfo extends VBox {

    @FXML
    private Text queueType;

    @FXML
    private ImageView rankImage;

    @FXML
    private Text divisonName;

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
        rankImage.setImage(new Image(""));
        divisonName.setTextContent(leagueEntry.getName());
        rank.setTextContent("Rank: " + leagueEntry.getTier() + " " + leagueEntryData.getDivision());
        lpAmount.setTextContent("LP: " + leagueEntryData.getLeaguePoints());
    }
}
