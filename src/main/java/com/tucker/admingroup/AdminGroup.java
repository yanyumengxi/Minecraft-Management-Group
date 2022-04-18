package com.tucker.admingroup;

import com.google.gson.*;
import com.tucker.admingroup.api.inside.InsideInfoHeader;
import com.tucker.admingroup.api.v3.GetPluginInfo;
import com.tucker.admingroup.command.AdminGroupCommand;
import com.tucker.admingroup.command.execute.groupChat.GroupChatModule;
import com.tucker.admingroup.command.supplement.AdminGroupCommandTab;
import com.tucker.admingroup.config.GroupsManger;
import com.tucker.admingroup.config.LanguageManager;
import com.tucker.admingroup.config.PlayersManager;
import com.tucker.admingroup.utils.BinaryUtils;
import com.tucker.admingroup.utils.ConsoleOperationsUtils;
import com.tucker.admingroup.utils.Metrics;
import com.tucker.admingroup.utils.Papi;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public final class AdminGroup extends JavaPlugin {
    public static AdminGroup adminGroup;
    public FileConfiguration config = getConfig();
    private final String data = "68747470733a2f2f79616e79756d656e6778692e6769746875622e696f2f436c69656e74757064617465536572766963652f7570646174652e6a736f6e==";
    private String newestVersion = "";

    public String GetNewestHandler(String dat) {
        String str="";
        try
        {
            URL url = new URL(dat);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            String location = conn.getRequestProperty("location");
            int resCode = conn.getResponseCode();
            conn.connect();
            InputStream stream = conn.getInputStream();
            byte[] data=new byte[102400];
            int length=stream.read(data);
            str=new String(data,0,length);
            conn.disconnect();
            stream.close();
        }
        catch(Exception ee)
        {
            System.out.print("ee:"+ee.getMessage());
        }
        return str;
    }


    @Override
    public void onLoad() {
        LanguageManager.createLanguageFile();
        InitializationLoadPlugin();
        DataCodeVerification();
        PlayersManager.InitializationPermissions();
    }

    public void InitializationLoadPlugin(){
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  InsideInfoHeader.getHeader() + "&b===================================");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        int PluginId = 14922;
        Metrics metrics = new Metrics(this,PluginId);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&ePlaceholderAPI Plugins Not Found");
        }
        else {
            new Papi().register();
        }
        try {
            saveDefaultConfig();
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&aDefault Configuration File Loaded successful");
        } catch (Exception e) {
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&cLoading Default Configuration File Failed");
        }
        try {
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bLanguage Loading...");
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&aLanguage Loaded");
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&aLanguage File Loaded successful");
        } catch (Exception e) {
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&aLanguage File Loaded Error");
        }

        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bGroups Data Loading...");
        GroupsManger.createGroupsFile();
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bGroups Data Loaded");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bPlayer Data Loading...");
        PlayersManager.createPlayersFile();
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bPlayer Data Loaded");

        // ShowAuthors();

        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  InsideInfoHeader.getHeader() + "&b===================================");
    }

    public void DataCodeVerification(){
        if(!Objects.equals(getConfig().getString("settings.config-version"), GetPluginInfo.getVersion())){
            ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&cThe configuration file version does not match the plugin Jar body version!");
        }
        // if(getConfig().getBoolean("settings.auto-update")){
        //     if (Objects.equals(newestVersion, "")) {
        //         newestVersion = GetNewestHandler(BinaryUtils.hexStrStr(data.substring(0, data.length() - 2)));
        //     }
        //     JsonObject jsonObject = new Gson().fromJson(newestVersion, JsonObject.class);
        //     JsonObject cloudConfidence = jsonObject.getAsJsonObject("app")
        //             .get("minecraft").getAsJsonObject()
        //             .get("server").getAsJsonObject()
        //             .get("plugin").getAsJsonObject()
        //             .get("AdminGroup").getAsJsonObject();
        //     String version = cloudConfidence.get("version").getAsString();
        //     String url = cloudConfidence.get("url").getAsString();
        //     if (!Objects.equals(version, GetPluginInfo.getVersion())) {
        //         ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&5--------- UPDATE! &5----------");
        //         ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "");
        //         ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + LanguageManager.getLang.getString("update").replace("{%=update_url=%}", url));
        //         ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "");
        //         ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&5--------- UPDATE! &5----------");
        //     }
        // }
    }
    public void InitializationEnablePlugin () {
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  InsideInfoHeader.getHeader() + "&b===================================");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&aDefault Configuration File Enabled successful");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bLanguage Enabling...");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bLanguage Enabled");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bGroups Data Enabling...");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bGroups Data Enabled");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bPlayer Data Enabling...");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bPlayer Data Enabled");

        Bukkit.getPluginCommand("admingroup").setExecutor(new AdminGroupCommand());
        Bukkit.getPluginCommand("admingroup").setTabCompleter(new AdminGroupCommandTab());
        Bukkit.getPluginCommand("grc").setExecutor(new GroupChatModule());

        // ShowAuthors();
    }

    @Override
    public void onEnable() {
        InitializationEnablePlugin();
    }

    public void InitializationUninstallPlugin() {
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  InsideInfoHeader.getHeader() + "&b===================================");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&aDefault Configuration File Uninstalled successful");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bLanguage Uninstalling...");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bLanguage Uninstalled");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bGroups Data Uninstalling...");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bGroups Data Uninstalled");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bPlayer Data Uninstalling...");
        ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
        ConsoleOperationsUtils.sendConsoleMsgNext(this, InsideInfoHeader.getHeader() + "&bPlayer Data Uninstalled");
        // ShowAuthors();
    }

    private void ShowAuthors() {
        try {
            if (!LanguageManager.getLang.getString("author").equals("")) {
                if (Objects.equals(newestVersion, "")) {
                    newestVersion = GetNewestHandler(BinaryUtils.hexStrStr(data.substring(0, data.length() - 2)));
                }
                JsonObject jsonObject = new Gson().fromJson(newestVersion, JsonObject.class);
                JsonArray authors = jsonObject.get("app").getAsJsonObject()
                        .get("minecraft").getAsJsonObject()
                        .get("server").getAsJsonObject()
                        .get("plugin").getAsJsonObject()
                        .get("AdminGroup").getAsJsonObject()
                        .get("authors").getAsJsonArray();

                ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
                for (JsonElement author : authors) {
                    ConsoleOperationsUtils.sendConsoleMsgNext(
                            this, InsideInfoHeader.getHeader() +
                                    LanguageManager.getLang.getString("author") +
                                    author.getAsJsonObject().get("name") + ", " +
                                    LanguageManager.getLang.getString("author-github") +
                                    author.getAsJsonObject().get("github"));
                }
                ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
                ConsoleOperationsUtils.sendConsoleMsgNext(this,  InsideInfoHeader.getHeader() + "&b===================================");
            }
        } catch (Exception e) {
            if (Objects.equals(newestVersion, "")) {
                newestVersion = GetNewestHandler(BinaryUtils.hexStrStr(data.substring(0, data.length() - 2)));
            }
            JsonObject jsonObject = new Gson().fromJson(newestVersion, JsonObject.class);
            JsonArray authors = jsonObject.get("app").getAsJsonObject()
                    .get("minecraft").getAsJsonObject()
                    .get("server").getAsJsonObject()
                    .get("plugin").getAsJsonObject()
                    .get("AdminGroup").getAsJsonObject()
                    .get("authors").getAsJsonArray();

            ConsoleOperationsUtils.sendConsoleMsgNext(this,  "");
            for (JsonElement author : authors) {
                ConsoleOperationsUtils.sendConsoleMsgNext(
                        this, InsideInfoHeader.getHeader() +
                                "&5开发者:&a" + author.getAsJsonObject().get("name") +
                                ", " + "&5Github:" + author.getAsJsonObject().get("github"));
            }
        }
    }

    @Override
    public void onDisable() {
        InitializationUninstallPlugin();
    }
}

class LoadLanguageThread extends Thread {
    @Override
    public void run() {
        LanguageManager.createLanguageFile();
    }
}