package com.tamdt.pmbackend.common.ulti;

public class StringUtils {

    public static boolean isNullOrEmpty(String str){
        if(str !=null && !"".equals(str.trim()))
            return false;
        return true;
    }

    public static String escapeSql(String input) {
        String result = input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
        return result;
    }
    public static String toLikeString(String content) {
        return "%" + StringUtils.escapeSql(content.toLowerCase().trim()) + "%";
    }


}
