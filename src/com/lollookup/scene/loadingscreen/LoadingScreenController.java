package com.lollookup.scene.loadingscreen;

/**
 * @author Yasin on 20.03.2017.
 * @version 1.0
 */
public class LoadingScreenController {

    private String summonerName;

    public LoadingScreenController(String summonerName) {
        this.summonerName = summonerName;
    }

    public void loadData() {

    }

    public String getSummonerName() {
        return summonerName;
    }
}
