package cn.edu.hdu.SaltyFish;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class shatest {

    @Test
    public  void getsha() {
        String psd = "admin";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(psd.getBytes());
        String res = new BigInteger(md.digest()).toString();
        System.out.println(res);
    }
}
