package com.xyq.util;

public class EncryptUtil {
    private static final String SALT="bWxkbmphdmE=";
    public static String getPwd(String password){
        return new MD5Code().getMD5ofStr(password+"({"+SALT+"})");
    }
}
