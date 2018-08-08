package com.firetech.project.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * @ClassName MD5Util
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/7/26 18:47
 * @Version 1.0
 **/
public class MD5Util {
    private static final String SALT = "firetechsalt";

    public static String encode(String password) {
        password = password + SALT;
        MessageDigest md5;
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String resultPassWord;
        try {
            md5 = MessageDigest.getInstance("MD5");
            resultPassWord = base64Encoder.encode(md5.digest(password.getBytes("utf-8")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultPassWord;
    }
}
