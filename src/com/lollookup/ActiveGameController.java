package com.lollookup;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;

public class ActiveGameController {

    @FXML
    private WebView webView;


    /**
     * gets automatically called
     */
    public void initialize() throws IOException {
        loadActiveGame();
    }

    /**
     * loads both teams, called with initialize method
     */
    private void loadActiveGame() throws IOException {
        final WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(Files.toString(Config.ACTIVE_GAME_HTML, Charsets.UTF_8));
        webEngine.getLoadWorker().stateProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED) {
                        String champName = "kha'zix";
                        String javaScript = "addSummoner('redTeam', 'test', 'http://ddragon.leagueoflegends.com/cdn/7.3.3/img/spell/FlashFrost.png', 'khazix', 2000);";
                        webEngine.executeScript("addName('redTeam', 1, 'yasin');");
                        //webEngine.executeScript("addSummoner('redTeam', 'test', 'http://ddragon.leagueoflegends.com/cdn/7.3.3/img/spell/FlashFrost.png', 'khazix', 2000);");
                                //"setCurrentChampion('redTeam', '2', 'http://ddragon.leagueoflegends.com/cdn/7.3.3/img/spell/FlashFrost.png', 'KhaZix', 2000);");
                    }
                });
    }

    private void appendDataToTable() {
    }

    private void executeScript(String script) {
        webView.getEngine().executeScript(script);
    }


}
