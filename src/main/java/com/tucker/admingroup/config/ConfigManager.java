package com.tucker.admingroup.config;

import com.tucker.admingroup.AdminGroup;
import com.tucker.admingroup.entity.ConfigInfo;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration Manager
 * @author lingqi
 */
public class ConfigManager {

    private static final Plugin plugin = AdminGroup.getPlugin(AdminGroup.class);

    /**
     * Configurations
     */
    public static List<ConfigInfo> configs = new ArrayList<>();

    public static void LoaderCommandFiles() {
        File file = new File(plugin.getDataFolder(), "/command");
        if (!file.exists()) {
            plugin.saveResource("command/examples.json", false);
        }
    }

    /**
     * Read all contents of the file and save it to the second parameter
     * @param path {String} File Path
     * @return {String} All contents of the document
     */
    private static String readFile(String path) {
        String result = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String str = "";
            StringBuilder resultBuilder = new StringBuilder(result);
            while ((str = bufferedReader.readLine()) != null) {
                resultBuilder.append(str);
            }
            result = resultBuilder.toString();
        } catch (Exception e) {
            result = "404";
        }
        return result;
    }
}

