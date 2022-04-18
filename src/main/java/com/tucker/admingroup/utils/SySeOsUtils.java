package com.tucker.admingroup.utils;

import java.io.*;

/**
 * File Operation
 * @author lingqi
 */
public class SySeOsUtils {

    /**
     * Read File Add Content
     * @param file {File} File Object
     * @return {String} File all Content
     */
    public static String readAll(File file){
        StringBuilder res = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                res.append(str);
            }
        } catch (FileNotFoundException e) {
            res.append("File Not Found");
        } catch (IOException e) {
            res.append("Read File Error");
        }
        return res.toString();
    }
}
