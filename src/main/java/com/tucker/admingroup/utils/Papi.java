package com.tucker.admingroup.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class Papi extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "JiNan";
    }

    @Override
    public String getIdentifier() {
        return "AdminGroup";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("test")){
            return null;
        }
        return null; // Placeholder is unknown by the Expansion
    }
}