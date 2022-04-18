package com.tucker.admingroup.command;


import com.tucker.admingroup.AdminGroup;
import com.tucker.admingroup.api.inside.InsideInfoHeader;
import com.tucker.admingroup.config.LanguageManager;
import com.tucker.admingroup.utils.ConsoleOperationsUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;

import java.io.Console;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AdminGroupCommand implements CommandExecutor {
    private static final Plugin plugin = AdminGroup.getPlugin(AdminGroup.class);
    private static final FileConfiguration config = plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            sendHelp(player);
        } else if (args.length == 1 && Objects.equals(args[0], "help")) {
            sendHelp(player);
        } else if (args.length == 1 && Objects.equals(args[0], "chat")) {
//            new GroupChatModule().runCommand(sender, command, label, args);
        } else if (args.length == 1 && Objects.equals(args[0], "group")) {

        } else if (args.length == 1 && Objects.equals(args[0], "permissions")) {

        } else if (args.length == 1 && Objects.equals(args[0], "reset")) {

        } else if (args.length == 1 && Objects.equals(args[0], "reload")) {

        }
        return false;
    }


    private static void sendHelp(Player player) {
        //输出帮助信息
        List<String> helps = LanguageManager.getLang.getStringList("help");
        for (String help : helps) {
            ConsoleOperationsUtils.sendPlayerMsgNext(player, InsideInfoHeader.getHeader() + help);
        }
    }

    /**
     * Call annotation class
     * @deprecated Discard
     * @param status status
     * @param folder folder
     * @return string
     */
    public static String runCommandEvent(int status, String folder) {
        String res = "";
        Reflections reflections = new Reflections("com.tucker.admingroup.command.execute." + folder);
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(ExecuteClassBase.class);
        for (Class<?> clazz : typesAnnotatedWith) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(ExecuteAnnotationBase.class)) {
                    ExecuteAnnotationBase annotation = method.getAnnotation(ExecuteAnnotationBase.class);
                    if (annotation != null && annotation.Id() == status) {
                        try {
                            res = (String) method.invoke(clazz.newInstance(), status);
                        } catch (Exception e) {
                            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return res;
    }
}

























