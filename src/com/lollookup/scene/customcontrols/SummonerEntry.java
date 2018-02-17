package com.lollookup.scene.customcontrols;

/**
 * @author Yasin
 */
public class SummonerEntry {

    private String name;

    private long id;


    public SummonerEntry(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}