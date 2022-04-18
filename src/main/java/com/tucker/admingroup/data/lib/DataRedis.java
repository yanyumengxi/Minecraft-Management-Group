package com.tucker.admingroup.data.lib;

import com.tucker.admingroup.AdminGroup;
import org.bukkit.plugin.Plugin;
import redis.clients.jedis.Jedis;

/**
 * Redis DataBase Operation
 * @author lingqi
 */
public class DataRedis {
    private static final Plugin plugin = AdminGroup.getPlugin(AdminGroup.class);
    private static Jedis jedis;

    /**
     * Getter Connection
     */
    public static void getConnection(){
        String host = plugin.getConfig().getString("datasource.host-redis");
        int port = plugin.getConfig().getInt("datasource.port-redis");
        String password = plugin.getConfig().getString("datasource.password-redis");
        jedis = new Jedis(host, port);
        jedis.auth(password);
    }

    /**
     * Add Data
     * @param key {String} Data key
     * @param value {String} Data Content
     */
    public static void append(String key, String value){
        jedis.set(key, value);
    }

    /**
     * Modify Data
     * @param key {String} Data key
     * @param value {String} Data Content
     */
    public static void set(String key, String value){
        jedis.append(key, value);
    }

    /**
     * Cover Data
     * @param key {String} Data key
     * @param value {String} Data Content
     */
    public static void cover(String key, String value){
        jedis.set(key, value);
    }

    /**
     *  Delete Data
     * @param key {String} Data key
     */
    public static void del(String key){
        jedis.del(key);
    }

    /**
     * Query specified data
     * @param key {String} Data key
     * @return Data value
     */
    public static String findByKey(String key){
        return jedis.get(key);
    }

    /**
     * Query all data
     * @return data
     */
    public static String findByAll(){
        return jedis.get("*");
    }
}
