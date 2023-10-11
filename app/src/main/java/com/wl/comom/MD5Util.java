package com.wl.comom;

import android.util.Log;
import java.security.MessageDigest;

public class MD5Util {

    public static String md5(String input) {
        try {
            // 创建一个MD5消息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());

            // 获取加密后的字节数组
            byte[] digest = md.digest();

            // 将字节数组转换为16进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();
        } catch (Exception e) {
            Log.e("MD5Util", "MD5 encryption failed: " + e.getMessage());
            return null;
        }
    }
}
