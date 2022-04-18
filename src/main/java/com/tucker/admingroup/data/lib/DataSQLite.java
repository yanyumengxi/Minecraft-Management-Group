package com.tucker.admingroup.data.lib;

import com.tucker.admingroup.AdminGroup;
import org.bukkit.plugin.Plugin;

import java.sql.*;

/**
 * SQLite DataBase Operation
 * @author lingqi
 */
public class DataSQLite {
    private static final Plugin plugin = AdminGroup.getPlugin(AdminGroup.class);

    private static Connection connection;

    /**
     * Getting Connection
     * @return true/false
     */
    public static boolean getConnection () {
        boolean IsSuccess = false;
        try {
            Class.forName("org.sqlite.JDBC");
            String path = plugin.getConfig().getString("datasource.path-sqlite");
            if (path.contains("{%=PluginData=%}")) {
                path.replace("{%=PluginData=%}",plugin.getDataFolder().getPath());
            }
            connection = DriverManager.getConnection("jdbc:sqlite:");
            IsSuccess = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return IsSuccess;
    }

    /**
     * Create DataBase Table
     * @param CreateTableSql Create DataBase Table Sql Statement
     * @param TabName Table Name
     */
    public static void createTable(String CreateTableSql, String TabName) {
        try {
            Statement statement = connection.createStatement();
            if (TabName != null && !"".equals(TabName)) {
                statement.executeQuery("DROP table if exists " + TabName + ";");
            }
            statement.executeUpdate(CreateTableSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Setting Sql statement
     * @param preparedStatement {PreparedStatement} PreparedStatement Object
     */
    public static void setSql(PreparedStatement preparedStatement){
        try {
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Submit SQL statement execution
     * @param preparedStatement {PreparedStatement} PreparedStatement Object
     */
    public static void submitSql(PreparedStatement preparedStatement){
        try {
            connection.setAutoCommit(false);
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Close Connection
     * @param preparedStatement {preparedStatement} preparedStatement Object
     */
    public static void close(PreparedStatement preparedStatement){
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
