package com.lollookup;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebView;

import javax.script.ScriptEngineManager;
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
                        webEngine.executeScript("addSummoner();");
                    }
                });
        webEngine.getLoadWorker().exceptionProperty().addListener( (ov, t, t1) ->  System.out.println("Received exception: "+ t1.getMessage()));
        webEngine.setOnError(System.out::println);
        final ScriptEngineManager engineManager = new ScriptEngineManager();
        engineManager.getEngineFactories().forEach(factory -> {
            System.out.println("=====================");
            System.out.println("Engine name:" + factory.getEngineName());
            System.out.println("Engine version:" + factory.getEngineVersion());
            System.out.println("Language name:" + factory.getLanguageName());
            System.out.println("Language version:" + factory.getLanguageVersion());
            factory.getNames().forEach(System.out::println);
        });


    }

    private void appendDataToTable() {
    }

    private void executeScript(String script) {
        webView.getEngine().executeScript(script);
    }


}
