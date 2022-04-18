package com.tucker.admingroup.config;

import com.tucker.admingroup.AdminGroup;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Groups.yml配置文件调用模块
 * @author JiNan
 */
public class GroupsManger {
    private static final Plugin plugin = AdminGroup.getPlugin(AdminGroup.class);
    public static final File groups = new File(plugin.getDataFolder(),"groups.yml");
    public static final FileConfiguration getGroups = YamlConfiguration.loadConfiguration(groups);

    public static void createGroupsFile() {
        File file = new File("groups.yml");
        if (!file.exists()) {
            plugin.saveResource("groups.yml",false);
        }
    }

    public static void saveGroupsFile(){
        try{
            getGroups.save(groups);
        }catch (java.io.IOException ignored){}
        try{
            getGroups.load(groups);
        }catch (IOException | InvalidConfigurationException ignored){}
    }

    public static void setGroups(String path,String string){
        getGroups.set(path,string);
        saveGroupsFile();
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

}
