package com.lollookup;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import javafx.fxml.FXML;
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
        webView.getEngine().loadContent(Files.toString(Config.ACTIVE_GAME_HTML, Charsets.UTF_8));
    }


}
