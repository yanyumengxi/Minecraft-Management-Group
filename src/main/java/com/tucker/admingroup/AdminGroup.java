package com.tucker.admingroup;

import com.tucker.admingroup.api.inside.InsideInfoHeader;
import com.tucker.admingroup.utils.ConsoleOperationsUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.Configuration;

public final class AdminGroup extends JavaPlugin {
    public FileConfiguration config = getConfig();

    public void InitializationLoadPlugin(){
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  InsideInfoHeader.getHeader() + "&b===================================");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&aPlaceholderAPI Plugins Not Found");
        }
        try {
            saveDefaultConfig();
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&aDefault Configuration File Loading successful");
        } catch (Exception e) {
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&cLoading Default Configuration File Failed");
        }
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  InsideInfoHeader.getHeader() + "&b===================================");
    }

    @Override
    public void onLoad() {
        InitializationLoadPlugin();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {

    }
}
