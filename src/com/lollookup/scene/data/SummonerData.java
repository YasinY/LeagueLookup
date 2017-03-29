package com.lollookup.scene.data;

import com.yasinyazici.riot.data.summoner.Summoner;
import com.yasinyazici.riot.data.summoner.ranked.league.LeagueEntry;

import java.util.Collection;
import java.util.List;

/**
 * @author Yasin
 */
public class SummonerData {

    private String summonerName;

    private String profileIconUrl;

    private String summonerLevel;

    private Collection<List<LeagueEntry>> leagueEntries;

    public SummonerData(Summoner summoner, Collection<List<LeagueEntry>> leagueEntries) {
        this.summonerName = summoner.getName();
        this.profileIconUrl = "http://avatar.leagueoflegends.com/" + summoner.getRegion().getShortCode() + "/" + summonerName.replace(" ", "") + ".png";
        this.summonerLevel = String.valueOf(summoner.getSummonerLevel());
        this.leagueEntries = leagueEntries;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public String getProfileIconUrl() {
        return profileIconUrl;
    }

    public String getSummonerLevel() {
        return summonerLevel;
    }

    public Collection<List<LeagueEntry>> getLeagueEntries() {
        return leagueEntries;
    }
}
