package com.lollookup.scene.data;

/**
 * @author Yasin
 */
public class SummonerData {

    private String profileIconUrl;

    private String summonerName;

    private String summonerLevel;

    public SummonerData(String profileIconUrl, String summonerName, String summonerLevel) {
        this.profileIconUrl = profileIconUrl;
        this.summonerName = summonerName;
        this.summonerLevel = summonerLevel;
    }

    public String getProfileIconUrl() {
        return profileIconUrl;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public String getSummonerLevel() {
        return summonerLevel;
    }
}
