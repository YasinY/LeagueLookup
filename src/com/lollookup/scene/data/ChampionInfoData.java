package com.lollookup.scene.data;

/**
 * @author Yasin
 */
public class ChampionInfoData {

    private String url;
    private String KDA;
    private String winRate;
    private String masteryScore;
    private String masteryLevel;

    public ChampionInfoData(String url, String kda, String winRate, String masteryScore, String masteryLevel) {
        this.url = url;
        this.KDA = kda;
        this.winRate = winRate;
        this.masteryScore = masteryScore;
        this.masteryLevel = masteryLevel;
    }

    public String getUrl() {
        return url;
    }

    public String getKDA() {
        return KDA;
    }

    public String getWinRate() {
        return winRate;
    }

    public String getMasteryScore() {
        return masteryScore;
    }

    public String getMasteryLevel() {
        return masteryLevel;
    }
}
