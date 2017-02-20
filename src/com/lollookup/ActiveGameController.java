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
                        webEngine.executeScript("var redTeam = document.getElementById('redTeam');" +
                                "redTeam.insertRow(1).insertCell(0).innerHTML='TEST';" +
                                "redTeam.insertRow(2).insertCell(0).innerHTML='TEST';");
                    }
                });
    }

    private void appendDataToTable() {
    }

    private void executeScript(String script) {
        webView.getEngine().executeScript(script);
    }


}
