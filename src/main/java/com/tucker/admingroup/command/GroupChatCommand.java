package com.tucker.admingroup.command;

import com.tucker.admingroup.api.inside.InsideInfoHeader;
import com.tucker.admingroup.config.GroupsManger;
import com.tucker.admingroup.config.PlayersManager;
import com.tucker.admingroup.utils.ConsoleOperationsUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GroupChatCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        FileConfiguration gc = GroupsManger.getGroups;
        ConfigurationSection gcc = gc.getConfigurationSection("groups");
        FileConfiguration pc = PlayersManager.getPlayers;
        ConfigurationSection pcc = pc.getConfigurationSection("players");
        Player player = (Player) sender;
        String pn = player.getName();
        if (args.length == 0) {
            ConsoleOperationsUtils.sendPlayerMsgNext(player, InsideInfoHeader.getHeader() + "&b==========&6Welcome to group chat&b==========");
            ConsoleOperationsUtils.sendPlayerMsgNext(player, InsideInfoHeader.getHeader() + "&a/grc mail [GroupName] [Content] &a-&3Send mail to a group");
            ConsoleOperationsUtils.sendPlayerMsgNext(player, InsideInfoHeader.getHeader() + "&a/grc shout [Content] &a-&3Shout to all management groups that are lower than their own management group");
        }
        if (args.length == 2) {
            String CommandContentBya = args[0];
            String CommandContentByb = args[1];
            boolean result = false;
            if (CommandContentBya.equalsIgnoreCase("shout")) {
                for (String pcn : pcc.getKeys(false)) {
                    if (pcn.equalsIgnoreCase(pn)) {
                        int PlayerLevel = pc.getInt("players." + pcn + ".group-level");
                        for (String playerLowers : pcc.getKeys(false)) {
                            int OtherLevel = pc.getInt("players." + playerLowers + ".group-level");
                            if (PlayerLevel > OtherLevel) {
                                ConsoleOperationsUtils.sendBroadCastMsgNext("&b[&a玩家&6" + pcn + "&a正在喊话&b]&f: " + CommandContentByb);
                            }
                        }
                        result = true;
                        break;
                    }
                }
                if (result) {
                    ConsoleOperationsUtils.sendPlayerMsgNext(player, InsideInfoHeader.getHeader() + "&2发送成功");
                } else {
                    ConsoleOperationsUtils.sendPlayerMsgNext(player, InsideInfoHeader.getHeader() + "&4发送失败");
                }
            }
        }
        if (args.length == 3) {
            String commandContentBya = args[0];
            String groupName = args[1];
            String commandContentByb = args[2];
            boolean result = false;
            if (commandContentBya.equalsIgnoreCase("mail")) {
                for (String gcn : gcc.getKeys(false)) {
                    if (gcn.equalsIgnoreCase(groupName)) {
                        List<String> members = gc.getStringList("groups." + gcn + ".members");
                        for (String member : members) {
                            Player gcnon = Bukkit.getPlayer(member);
                            gcnon.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "玩家" + ChatColor.GOLD + member + ChatColor.GREEN + "向你发送了一份邮件" + ChatColor.AQUA + "]" + ChatColor.WHITE + ":" + commandContentByb);
                        }
                        result = true;
                        break;
                    }
                }
                if (result) {
                    ConsoleOperationsUtils.sendPlayerMsgNext(player, InsideInfoHeader.getHeader() + "&2发送成功");
                } else {
                    ConsoleOperationsUtils.sendPlayerMsgNext(player, InsideInfoHeader.getHeader() + "&4发送失败");
                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("shout");
            list.add("mail");
        }
        return list;
    }
}
