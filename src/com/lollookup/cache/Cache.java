package com.lollookup.cache;

import com.lollookup.config.Config;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yasin on 20.03.2017.
 * @version 1.0
 */
public class Cache {

    private List<File> fileList;

    public Cache() {
        this.fileList = new ArrayList<>();
        this.fileList.add(new File(Config.RELATIVE_DATA_DIRECTORY));
        this.fileList.add(new File(Config.HTML_FILES_DIRECTORY));
        this.fileList.add(new File(Config.STYLE_SHEETS_DIRECTORY));
        createDirectories();
    }

    private void createDirectories() {
        fileList.forEach(File::mkdir);
    }

    public static boolean exists() {
        return Files.exists(Paths.get(Config.CACHE));
    }
}
