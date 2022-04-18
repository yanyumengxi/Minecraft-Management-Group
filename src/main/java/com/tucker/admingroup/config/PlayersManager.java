package com.tucker.admingroup.config;

import com.tucker.admingroup.AdminGroup;
import com.tucker.admingroup.utils.ConsoleOperationsUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Players.yml配置文件调用模块
 * @author JiNan,Lingqi
 */
public class PlayersManager {
    private static final Plugin plugin = AdminGroup.getPlugin(AdminGroup.class);
    public static final File players = new File(plugin.getDataFolder(),"/players.yml");
    public static final FileConfiguration getPlayers = YamlConfiguration.loadConfiguration(players);

    public static void createPlayersFile() {
        File file = new File("players.yml");
        if (!file.exists()) {
            plugin.saveResource("players.yml",false);
        }
    }

    public static void savePlayersFile(){
        try{
            getPlayers.save(players);
        }catch (java.io.IOException ignored){}
        try{
            getPlayers.load(players);
        }catch (IOException | InvalidConfigurationException ignored){}
    }

    public static void setPlayers(String path,String string){
        getPlayers.set(path,string);
        savePlayersFile();
    }

    /**
     * Verify whether the ID in all permission groups where
     * the player belongs is the same as the player ID
     * 讲解: 根据传入的Player对象获取玩家名,然后在players.yml文件中找到该玩家名所在节点.
     * 从中取出"group-id"保存到InPlayerGroupID变量中.
     * 然后取出该玩家名节点下的"permissions"节点所有值（即:取出该玩家名下所有权限组名）保存在permissions变量中
     * 遍历permissions变量.
     * 取出groups.yml文件中找到遍历的permissions值下面的id保存在InGroupID变量中
     * 判断InPlayerGroupID变量与InGroupID变量是否不相等,若成立:将返回值变量赋值为false。
     * 遍历完成后,直接返回返回值变量
     * @param player {Player} Player Object
     * @return true/false
     */
    public static boolean compareGroupID(Player player) {
        boolean res = true;
        FileConfiguration playerFile = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "players.yml"));
        YamlConfiguration groupsFile = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "groups.yml"));
        int InPlayerGroupID = playerFile.getInt("players." + player.getName() + ".group-id");
        List<String> permissions = playerFile.getStringList("players." + player.getName() + ".permissions");
        for (String permission : permissions) {
            int InGroupID = groupsFile.getInt("groups." + permission + ".id");
            if (InPlayerGroupID != InGroupID) {
                res = false;
            }
        }
        return res;
    }

    public static boolean InitializationPermissions(){
        boolean res = true;
        try {
            YamlConfiguration playerFile = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "/players.yml"));
            YamlConfiguration groupsFile = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "/groups.yml"));
            Set<String> groups = groupsFile.getKeys(true);
            for (String group : groups) {
                if (group.split("\\.").length == 2){
                    String groupn = group.replace("groups.", "");
                    List<String> permissions = groupsFile.getStringList("groups." + groupn + ".permissions");
                    List<String> inheritances = groupsFile.getStringList("groups." + groupn + ".inheritance");
                    List<String> members = groupsFile.getStringList("groups." + groupn + ".members");
                    for (String inheritance : inheritances) {
                        List<String> inheritancePermissions = groupsFile.getStringList("groups." + inheritance + ".permissions");
                        permissions.addAll(inheritancePermissions);
                    }
                    for (String member : members) {
                        System.out.println(member);
                        ConsoleOperationsUtils.sendConsoleMsgNext(com.tucker.admingroup.AdminGroup.getProvidingPlugin(com.tucker.admingroup.AdminGroup.class),member);
                        playerFile.set("players." + member + ".permissions", permissions);
                        savePlayersFile();
                    }
                }
            }
        } catch (Exception e) {
            res = false;
        }
        return res;
    }
}
