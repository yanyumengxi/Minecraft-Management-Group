package com.tucker.admingroup.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Plugin Server Operation Class
 * @author Lingqi
 */
public class ConsoleOperationsUtils {

    /**
     * Send Server Log Message.
     * @param plugin {JavaPlugin} JavaPlugin Class Object.
     * @param Message {String} Message Content.
     * @param color {String} Message Color Content. Example: 0
     */
    public static void sendConsoleMsg(JavaPlugin plugin, String Message, @Nullable String color) {
        String MessageStr = "";
        if (!Objects.equals(color, "")) {
            MessageStr = ChatColor.translateAlternateColorCodes('&', '&' + color + Message);
        }
        plugin.getServer().getConsoleSender().sendMessage(MessageStr);
    }

    /**
     * Send Server Log Message (Update Version).
     * @param plugin {JavaPlugin} JavaPlugin Class Object.
     * @param Message {String} Message Content.
     */
    public static void sendConsoleMsgNext(JavaPlugin plugin, String Message) {
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
    }

    /**
     * Send Player Message.
     * @param player {Player} Player Object.
     * @param Message {String} Message Main Content.
     * @param color {String} Message Color Content. Example: 0
     */
    public static void sendPlayerMsg(Player player, String Message, @Nullable String color) {
        String MessageStr = "";
        if (!Objects.equals(color, "")) {
            MessageStr = ChatColor.translateAlternateColorCodes('&', '&' + color + Message);
        }
        player.sendMessage(MessageStr);
    }

    /**
     * Send Player Message (Update Version).
     * @param player {Player} Player Object.
     * @param Message {String} Message Main Content.
     */
    public static void sendPlayerMsgNext(Player player, String Message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
    }

    /**
     * Send Player Raw Message.
     * @param player {Player} Player Object.
     * @param Message {String} Message Main Content.
     * @param color {String} Message Color Content. Example: 0
     */
    public static void sendPlayerRowMsg(Player player, String Message, @Nullable String color) {
        String MessageStr = "";
        if (!Objects.equals(color, "")) {
            MessageStr = ChatColor.translateAlternateColorCodes('&', '&' + color + Message);
        }
        player.sendRawMessage(MessageStr);
    }

    /**
     * Send Player Raw Message (Update Version).
     * @param player {Player} Player Object.
     * @param Message {String} Message Main Content.
     */
    public static void sendPlayerRowMsgNext(Player player, String Message) {
        player.sendRawMessage(ChatColor.translateAlternateColorCodes('&', Message));
    }

    /**
     * Broadcast information to server.
     * @param Message {String} Message Content
     * @param color {String} Message Color
     */
    public static void sendBroadCastMsg(String Message, @Nullable String color){
        String MessageStr = "";
        if (!Objects.equals(color, "")) {
            MessageStr = ChatColor.translateAlternateColorCodes('&', '&' + color + Message);
        }
        Bukkit.broadcastMessage(MessageStr);
    }

    /**
     * Broadcast information to server (Update Version).
     * @param Message {String}  Message Content
     */
    public static void sendBroadCastMsgNext(String Message){
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Message));
    }
}
