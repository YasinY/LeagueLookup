package com.lollookup.scene.loadingscreen;

import com.lollookup.scene.activegame.ActiveGameController;
import com.lollookup.scene.data.ChampionInfoData;
import com.lollookup.scene.data.SummonerData;
import com.lollookup.scene.summonerlookup.ProfileController;
import com.yasinyazici.riot.LeagueAPI;
import com.yasinyazici.riot.data.champion.ChampionImage;
import com.yasinyazici.riot.data.champion.impl.ChampionData;
import com.yasinyazici.riot.data.championmastery.ChampionMastery;
import com.yasinyazici.riot.data.currentgame.CurrentGameInfo;
import com.yasinyazici.riot.data.currentgame.data.CurrentGameParticipant;
import com.yasinyazici.riot.data.exceptions.DataException;
import com.yasinyazici.riot.data.exceptions.ReplyException;
import com.yasinyazici.riot.data.exceptions.WrongRequestFormatException;
import com.yasinyazici.riot.data.game.Season;
import com.yasinyazici.riot.data.staticdata.Region;
import com.yasinyazici.riot.data.summoner.Summoner;
import com.yasinyazici.riot.data.summoner.ranked.ChampionStats;
import com.yasinyazici.riot.data.summoner.ranked.ChampionStatsRanked;
import com.yasinyazici.riot.data.summoner.ranked.ChampionStatsSummary;
import com.yasinyazici.riot.data.summoner.ranked.league.LeagueEntry;
import com.yasinyazici.riot.data.summoner.ranked.league.QueueType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Yasin on 20.03.2017.
 * @version 1.0
 */
public class LoadingScreenController implements Initializable {


    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text progressText;

    private LeagueAPI leagueAPI;

    private Summoner summoner;

    public void setSummoner(String summonerName, String region) {
        this.leagueAPI = new LeagueAPI(Region.parseRegion(region));
        try {
            this.summoner = leagueAPI.getSummoner(summonerName);
        } catch (DataException e) {
            progressText.setText("Invalid data given");
        } catch (WrongRequestFormatException e) {
            progressText.setText("Something went wrong while creating a request");
        } catch (ReplyException e) {
            progressText.setText("Too many requests within a certain timespan, please try again later.");
        } catch (IOException e) {
            progressText.setText("I/O Error, please check your internet connection or try again later.");
        }
    }

    public void loadProfile() {
        if (summoner != null) {
            try {
                try {
                    createProfileScene();
                } catch (DataException e) {
                    e.printStackTrace();
                } catch (WrongRequestFormatException e) {
                    e.printStackTrace();
                } catch (ReplyException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadActiveGame() {
        if (summoner != null) {
            try {
                createActiveGameScene();
            } catch (IOException e) {
                progressText.setText("I/O Error, please check your internet connection or try again later.");
            }
        }
    }

    private void createActiveGameScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lollookup/scene/activegame/active_game.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));
        ActiveGameController activeGameController = loader.getController();
        activeGameController.loadActiveGame(); //Usually would get the local summoner
        stage.show();
    }

    private void createProfileScene() throws IOException, DataException, WrongRequestFormatException, ReplyException {
        String summonerName = summoner.getName();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lollookup/scene/summonerlookup/profile.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));
        ProfileController controller = loader.getController();
        ChampionInfoData[] championStatsSummary = leagueAPI.getChampionStatsRanked(summoner.getId(), Season.SEASON_7).getChampionStatsSummary().stream().map(element -> {
            try {
                if(element.getId() == 0) {
                    return null;
                }
                ChampionStats championStats = element.getChampionStats();
                return new ChampionInfoData(leagueAPI.getImageUrl(element.getId()), championStats.displayAverageKDA(), championStats.displayWinrate(), championStats.displayAverageCreepScore(), "0");
            } catch (ReplyException | DataException | IOException | WrongRequestFormatException e) {
                e.printStackTrace();
            }
            return null;
        }).toArray(ChampionInfoData[]::new);


        controller.createProfile(new SummonerData("http://avatar.leagueoflegends.com/" + summoner.getRegion().getShortCode() + "/" + summonerName.replace(" ", "") + ".png", summonerName, String.valueOf(summoner.getSummonerLevel())), championStatsSummary);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing " + location.getPath());
    }
}
