package cn.edu.hdu.SaltyFish.utils;

import java.util.UUID;

public class idutils {

    //自动生成数据库主键
    public static String getid(){
        UUID uuid = UUID.randomUUID();
        String strID = uuid.toString().replaceAll("-", "");
        return strID;
    }
}
