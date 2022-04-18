package com.tucker.admingroup.config;

import com.tucker.admingroup.AdminGroup;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class LanguageManager {

    private static final Plugin plugin = AdminGroup.getPlugin(AdminGroup.class);
    public static final File language = new File(plugin.getDataFolder(),"language/"+plugin.getConfig().getString("settings.language")+".yml");
    public static final FileConfiguration getLang = YamlConfiguration.loadConfiguration(language);

    public static void createLanguageFile() {
        File file = new File("language/"+plugin.getConfig().getString("settings.language")+".yml");
        if (!file.exists()) {
            plugin.saveResource("language/zh_CN.yml",false);
        }
    }

    public static void saveLanguageFile(){
        try{
        getLang.save(language);
        }catch (java.io.IOException ignored){}
        try{
        getLang.load(language);
        }catch (IOException | InvalidConfigurationException ignored){}
    }

    public static void setLang(String path,String string){
        getLang.set(path,string);
        saveLanguageFile();
    }

}
