package com.tucker.admingroup.api.v3;

/**
 * Plugin Info Getter
 * @author lingqi
 */
public class GetPluginInfo {

    /**
     * Get Plugin Name
     * @return {String} Plugin Name
     */
    public static String getName() {
        return "AdminGroup";
    }

    /**
     * get Plugin Version
     * @return {String} Plugin Version
     */
    public static String getVersion() {
        return "1.0.0-beta";
    }

    /**
     * get Plugin Coding id
     * @return {String} Plugin Coding id
     */
    public static String getNameId() {
        return "admingroup";
    }
}
