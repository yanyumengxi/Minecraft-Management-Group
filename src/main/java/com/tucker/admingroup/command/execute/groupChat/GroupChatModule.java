package com.tucker.admingroup.command.execute.groupChat;

import com.tucker.admingroup.api.inside.InsideInfoHeader;
import com.tucker.admingroup.command.ExecuteAnnotationBase;
import com.tucker.admingroup.command.ExecuteClassBase;
import com.tucker.admingroup.config.GroupsManger;
import com.tucker.admingroup.config.LanguageManager;
import com.tucker.admingroup.config.PlayersManager;
import com.tucker.admingroup.utils.ConsoleOperationsUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ExecuteClassBase
public class GroupChatModule implements CommandExecutor {
    @ExecuteAnnotationBase(Id = 617142002, Description = "Group Chat Module")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Set<String> groups = GroupsManger.getGroups.getKeys(true);
        List<String> groupnames = new ArrayList<>();
        for (String group : groups) {
            if (group.split("\\.").length == 2) {
                String groupname = group.replace("groups.", "");
                groupnames.add(groupname);
            }
        }
        if (args.length == 0) {
            sendHelp(player);
        } else {
            if (args[0].equals("all") || args[0].equals("a")) {
                if (true) {
                    List<Player> ChatTarget = new ArrayList<>();
                    String TargetGroupName = "";
                    List<String> TargetPlayerName;
                    for (int i = 1; i <= groupnames.size(); i++) {
                        TargetGroupName = groupnames.get(i - 1);
                        TargetPlayerName = GroupsManger.getGroups.getStringList("groups." + TargetGroupName + ".members");
                        if (PlayersManager.getPlayers.getInt("players." + player + ".group-id") != -1 && GroupsManger.getGroups.getInt("groups." + TargetGroupName + ".group-level") <= PlayersManager.getPlayers.getInt("players." + player + ".level")) {
                            for (int j = 1; j <= TargetPlayerName.size(); j++) {
                                ChatTarget.add(Bukkit.getPlayer(TargetPlayerName.get(j - 1)));
                            }
                        }
                    }
                    for (int k = 1; k <= ChatTarget.size(); k++) {
                        ConsoleOperationsUtils.sendPlayerMsgNext(ChatTarget.get(k - 1), LanguageManager.getLang.getString("group-chat.message.from1") + player + LanguageManager.getLang.getString("group-chat.message.from2") + "：" + args[2]);
                    }
                } else
                    ConsoleOperationsUtils.sendPlayerMsgNext(player, LanguageManager.getLang.getString("group-chat.error.no-permission"));
            }


            else if (args[0].equals("group") || args[0].equals("g")) {
                if (true) {
                    List<Player> ChatTarget = new ArrayList<>();
                    List<String> TargetPlayerName = GroupsManger.getGroups.getStringList("groups." + args[1] + ".members");
                    for (int j = 1; j <= TargetPlayerName.size(); j++) {
                        ChatTarget.add(Bukkit.getPlayer(TargetPlayerName.get(j - 1)));
                    }
                    FromShowMessage(args, player, ChatTarget);
                } else
                    ConsoleOperationsUtils.sendPlayerMsgNext(player, LanguageManager.getLang.getString("group-chat.error.no-permission"));
            }

            else if (args[0].equals("player") || args[0].equals("p")) {
                if (true) {
                    List<Player> ChatTarget = new ArrayList<>();
                    String TargetGroupName = "";
                    List<String> TargetPlayerName = new ArrayList<>();
                    for (int i = 1; i <= groupnames.size(); i++) {
                        TargetGroupName = groupnames.get(i - 1);
                        TargetPlayerName = GroupsManger.getGroups.getStringList("groups." + TargetGroupName + ".members");
                        if (GroupsManger.getGroups.getString("groups." + TargetGroupName + ".level").equals(args[1])) {
                            for (int j = 1; j <= TargetPlayerName.size(); j++) {
                                ChatTarget.add(Bukkit.getPlayer(TargetPlayerName.get(j - 1)));
                            }
                        }
                    }
                    FromShowMessage(args, player, ChatTarget);
                } else
                    ConsoleOperationsUtils.sendPlayerMsgNext(player, LanguageManager.getLang.getString("group-chat.error.no-permission"));
            }

            else if (args[0].equals("help") || args[0].equals("h")) {
                sendHelp(player);
            }

            else
                ConsoleOperationsUtils.sendPlayerMsgNext(player, InsideInfoHeader.getHeader() + LanguageManager.getLang.getString("group-chat.error.unknown-command"));

        }
        return false;
    }

    private static void FromShowMessage(String[] args, Player player, List<Player> chatTarget) {
        for (int k = 1; k <= chatTarget.size(); k++) {
            if (PlayersManager.getPlayers.getInt("players." + player + ".group-id") != -1 && GroupsManger.getGroups.getInt("groups." + args[1] + ".group-level") <= PlayersManager.getPlayers.getInt("players." + player + ".level")) {
                ConsoleOperationsUtils.sendPlayerMsgNext(chatTarget.get(k - 1), LanguageManager.getLang.getString("group-chat.message.from1") + player + LanguageManager.getLang.getString("group-chat.message.from2") + "：" + args[3]);
            }
        }
    }

    private static void sendHelp(Player player) {
        List<String> list = LanguageManager.getLang.getStringList("group-chat.help");
        for (String s : list) {
            ConsoleOperationsUtils.sendPlayerMsgNext(player, InsideInfoHeader.getHeader() + s);
        }
    }
}
