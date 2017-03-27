package com.lollookup.scene.activegame;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.lollookup.config.Config;
import com.yasinyazici.riot.data.currentgame.data.CurrentGameParticipant;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.util.List;

public class ActiveGameController {

    @FXML
    private WebView webView;



    /**
     * loads both teams, called with initialize method
     */
    public void loadActiveGame() throws IOException {
        final WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(Files.toString(Config.ACTIVE_GAME_HTML, Charsets.UTF_8));
        webEngine.getLoadWorker().stateProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED) {
                        executeScript("addSummoner(new Summoner('Test', 'red', 'gold 5', new ChampionData('KhaZix', 'http://ddragon.leagueoflegends.com/cdn/7.3.3/img/spell/FlashFrost.png', 2000, 7, 52.3, 3.14, 50, 300));");
                    }
                });

    }

    private void executeScript(String script) {
        webView.getEngine().executeScript(script);
    }


}
