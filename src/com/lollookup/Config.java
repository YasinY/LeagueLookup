package com.lollookup;

import java.io.File;

/**
 * Created by Yasin on 07.02.2017
 * E-mail: yasin_programmer@hotmail.com
 * Github: YasinY
 */
public class Config {

    public static String DATA_DIRECTORY = "./data/";

    public static String STYLE_SHEETS_DIRECTORY = DATA_DIRECTORY + "css/";

    public static String HTML_FILES_DIRECTORY = DATA_DIRECTORY + "html/";

    public static File ACTIVE_GAME_HTML = new File(HTML_FILES_DIRECTORY + "activegame.html");
}
