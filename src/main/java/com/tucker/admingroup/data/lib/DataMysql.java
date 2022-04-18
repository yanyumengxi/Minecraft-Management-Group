package com.tucker.admingroup.data.lib;

import com.tucker.admingroup.AdminGroup;
import org.bukkit.plugin.Plugin;

import java.sql.*;

/**
 * Mysql DataBase Operation
 * @author lingqi
 */
public class DataMysql {

    private static final Plugin plugin = AdminGroup.getPlugin(AdminGroup.class);
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    /**
     * Connect database
     * @return true/false
     */
    public static boolean getConnection(){
        boolean res = false;
        String type = plugin.getConfig().getString("datasource.type").toLowerCase();
        String host = plugin.getConfig().getString("datasource.host");
        String port = plugin.getConfig().getString("datasource.port");
        String userName = plugin.getConfig().getString("datasource.username");
        String passWord = plugin.getConfig().getString("datasource.password");
        String coding = plugin.getConfig().getString("datasource.coding");
        if (type.equals("mysql")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://" + host + ":" + port + "?useUnicode=true&characterEncoding=" + coding;
                connection = DriverManager.getConnection(url, userName, passWord);
                res = true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }

    /**
     * Query data
     * @param sql SQL statement
     * @return Returns the ResultSet object
     */
    public static ResultSet executeSelect(String sql){
        try {
            getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    /**
     * Modify or delete data
     * @param sql SQL statement
     * @return If it is 0, it fails
     */
    public int executeUpdate(String sql){
        int res = 0;
        try {
            getConnection();
            statement = connection.createStatement();
            res = statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    /**
     * Close link
     */
    public void close(){
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
