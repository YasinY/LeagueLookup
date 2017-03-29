package com.lollookup.scene.data;

/**
 * @author Yasin
 */
public class ChampionInfoData {

    private String url;
    private String name;
    private String KDA;
    private String winRate;
    private String masteryScore;
    private String masteryLevel;
    private String averageCS;
    private String totalSessionsPlayed;

    public ChampionInfoData(String url, String name, String kda, String winRate, String masteryScore, String masteryLevel, String averageCS, String totalSessionsPlayed) {
        this.url = url;
        this.name = name;
        this.KDA = kda;
        this.winRate = winRate;
        this.masteryScore = masteryScore;
        this.masteryLevel = masteryLevel;
        this.averageCS = averageCS;
        this.totalSessionsPlayed = totalSessionsPlayed;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
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

    public String getAverageCS() {
        return averageCS;
    }

    public String getTotalSessionsPlayed() {
        return totalSessionsPlayed;
    }
}
