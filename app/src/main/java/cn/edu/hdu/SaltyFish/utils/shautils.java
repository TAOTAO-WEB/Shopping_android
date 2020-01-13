package cn.edu.hdu.SaltyFish.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class shautils {

    //对密码进行加密
    public static String getsha(String psd) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(psd.getBytes());
        String res = new BigInteger(md.digest()).toString();
        return res;
    }
}
