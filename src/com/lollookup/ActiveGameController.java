package com.lollookup;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class ActiveGameController {

    @FXML
    private WebView blueTeam;

    @FXML
    private WebView redTeam;

    public void initialize() {
        blueTeam.getEngine().loadContent("lol");
        redTeam.getEngine().loadContent("lol");
    }

}
