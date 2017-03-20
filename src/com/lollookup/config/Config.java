package com.lollookup.config;

import java.io.File;

/**
 * Created by Yasin on 07.02.2017
 * E-mail: yasin_programmer@hotmail.com
 * Github: YasinY
 */
public class Config {

    public static double VERSION = 1;

    public static String USER_DIR = System.getProperty("user.home") + "/";

    public static String CACHE = USER_DIR + "leaguelookup/";
    public static String RELATIVE_DATA_DIRECTORY = CACHE + "data/";
    public static String DATA_DIRECTORY = "./data/";

    public static String STYLE_SHEETS_DIRECTORY = DATA_DIRECTORY + "css/";

    public static String HTML_FILES_DIRECTORY = DATA_DIRECTORY + "frontend/";

    public static File ACTIVE_GAME_HTML = new File(HTML_FILES_DIRECTORY + "activegame.html");
}
