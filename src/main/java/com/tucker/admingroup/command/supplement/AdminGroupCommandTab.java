package com.tucker.admingroup.command.supplement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminGroupCommandTab implements TabExecutor {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("help");
            list.add("chat");
            list.add("group");
            list.add("permissions");
            list.add("reset");
            list.add("reload");
        } else if (args.length == 2 && Objects.equals(args[0], "chat")) {
            list.add("fullShout");
            list.add("shout");
        } else if (args.length == 2 && Objects.equals(args[0], "group")) {
            list.add("addGroup");
            list.add("delGroup");
        } else if (args.length == 2 && Objects.equals(args[0], "permissions")) {
            list.add("addPermissions");
            list.add("delPermissions");
        }
        return list;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
