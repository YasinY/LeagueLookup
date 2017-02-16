package com.lollookup;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.plaf.nimbus.State;
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
                new ChangeListener<State>() {
                    public void changed(ObservableValue ov, State oldState, State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            webengine.executeScript("document.getElementById('header').innerHTML = '<p>Hi</p>';");
                        }
                    }
                });
    }

    private void appendDataToTable() {
    }
    private void executeScript(String script) {
        webView.getEngine().executeScript(script);
    }



}
