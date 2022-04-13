package com.tucker.admingroup.api.inside;

/**
 * Getter Information Header or Footer
 * @author lingqi
 */
public class InsideInfoHeader {

    /**
     * Get Information Header.
     * @return Information header (Include trailing spaces)
     */
    public static String getHeader() {
        return "&b[&aAdminGroup&b] &r";
    }

    /**
     * Get Information Header (Update Version).
     * @param prev front Information Header Color (type: Code)
     * @param center center Information Header Color (type: Code)
     * @param lower lower Information Header Color (type: Code)
     * @return Information header (Include trailing spaces)
     */
    public static String getHeaderNext(String prev, String center, String lower) {
        return prev + "[" + center + "AdminGroup" + lower + "] &r";
    }

    /**
     * Get Information Header (Pro Update Version).
     * @param prevColor front Color
     * @param prevContent front Content
     * @param centerColor center Color
     * @param centerContent center Content
     * @param lowerColor lower Color
     * @param lowerContent lower Content
     * @param TrailingSpace is Include Trailing Spaces
     * @return Header Information
     */
    public static String getHeaderProAll(String prevColor, String prevContent, String centerColor, String centerContent, String lowerColor, String lowerContent, boolean TrailingSpace) {
        String res = "";
        if (TrailingSpace) {
            res = prevColor + prevContent + centerColor + centerContent + lowerColor + lowerContent + " &r";
        } else {
            res = prevColor + prevContent + centerColor + centerContent + lowerColor + lowerContent + "&r";
        }
        return res;
    }
}
