package com.tucker.admingroup.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ExecuteCommandBase {
    void runCommand(CommandSender sender, Command command, String label, String[] args);
}
