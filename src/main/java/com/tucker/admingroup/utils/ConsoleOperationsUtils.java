package com.tucker.admingroup.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

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
        if (color.equals("")) {
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
        if (color.equals("")) {
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
        if (color.equals("")) {
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
}
