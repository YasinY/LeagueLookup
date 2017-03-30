package com.lollookup.scene.loadingscreen;

import com.lollookup.scene.activegame.ActiveGameController;
import com.lollookup.scene.data.ChampionInfoData;
import com.lollookup.scene.data.SummonerData;
import com.lollookup.scene.summonerlookup.ProfileController;
import com.lollookup.scene.summonerlookup.ProfileScene;
import com.yasinyazici.riot.LeagueAPI;
import com.yasinyazici.riot.data.champion.ChampionImage;
import com.yasinyazici.riot.data.championmastery.ChampionMastery;
import com.yasinyazici.riot.data.exceptions.DataException;
import com.yasinyazici.riot.data.exceptions.ReplyException;
import com.yasinyazici.riot.data.exceptions.WrongRequestFormatException;
import com.yasinyazici.riot.data.game.Season;
import com.yasinyazici.riot.data.staticdata.Region;
import com.yasinyazici.riot.data.summoner.Summoner;
import com.yasinyazici.riot.data.summoner.ranked.ChampionStats;
import com.yasinyazici.riot.data.summoner.ranked.league.LeagueEntry;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author Yasin on 20.03.2017.
 * @version 1.0
 */
public class LoadingScreenController implements Initializable {


    @FXML
    private ImageView placeholderImage;

    @FXML
    private ImageView loadingImage;

    @FXML
    private Text progressText;

    private LeagueAPI leagueAPI;

    private Summoner summoner;

    private Collection<List<LeagueEntry>> leagueEntries;

    private ChampionInfoData[] championInfoDatas;

    private Stage[] previousStages;

    public void setSummoner(String summonerName, String region) {
        this.leagueAPI = new LeagueAPI(Region.parseRegion(region));
        progressText.setText("Grabbing data..");
        try {
            this.summoner = leagueAPI.getSummoner(summonerName);
            progressText.setText("Got data..");
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
                System.out.println("Loading profile scene");
                createProfileScene();
            } catch (DataException | WrongRequestFormatException | ReplyException | IOException e) {
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
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lollookup/scene/activegame/active_game.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                ActiveGameController activeGameController = loader.getController();
                activeGameController.loadActiveGame();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void createProfileScene() throws IOException, DataException, WrongRequestFormatException, ReplyException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lollookup/scene/summonerlookup/profile.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));
        ProfileController controller = loader.getController();
        Task task = championDataTask();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(task);
        leagueEntries = leagueAPI.getLeagueEntries(summoner.getId()).values();
        task.setOnSucceeded((x) -> {
            Stream.of(previousStages).filter(Objects::nonNull).forEach(Stage::close);
            controller.createProfile(new SummonerData(summoner, leagueEntries), championInfoDatas);
            stage.show();
        });
    }

    private Task championDataTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                List<ChampionMastery> championMasteries = leagueAPI.getChampionMasteries(summoner.getId());
                championInfoDatas = leagueAPI.getChampionStatsRanked(summoner.getId(), Season.SEASON_7).getChampionStatsSummary().stream().filter(element -> element.getId() != 0).map(element -> {
                    try {
                        ChampionStats championStats = element.getChampionStats();
                        int championId = element.getId();
                        ChampionMastery championMastery = championMasteries.stream().filter(champion -> champion.getChampionId() == championId).findAny().orElse(null);
                        ChampionImage championData = leagueAPI.getChampionData(championId);
                        return new ChampionInfoData(leagueAPI.getImageUrl(championData), championData.getName(), championStats.displayAverageKDA(), championStats.displayWinrate(), String.valueOf(championMastery.getChampionPoints()), String.valueOf(championMastery.getChampionLevel()), String.valueOf(championStats.getAverageCreepScore()), String.valueOf(championStats.getTotalSessionsPlayed()));
                    } catch (ReplyException | DataException | IOException | WrongRequestFormatException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).toArray(ChampionInfoData[]::new);
                return null;
            }
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        placeholderImage.setImage(new Image("http://www.sh0ck.bplaced.net/league_assets/utils/sleeping_poro.gif"));
        loadingImage.setImage(new Image("http://www.sh0ck.bplaced.net/league_assets/utils/loading.gif"));
    }

    public void setPreviousStages(Stage ... previousStages) {
        this.previousStages = previousStages;
    }
}
