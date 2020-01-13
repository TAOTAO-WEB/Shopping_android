package cn.edu.hdu.SaltyFish.utils;

import java.util.HashMap;
import java.util.Map;

import cn.edu.hdu.SaltyFish.R;

//数据库图片名转换动态资源id
public class imageidutils {
    private static Map<String,Integer> map = new HashMap<>();

    static {
        map.put("keyboard", R.drawable.keyboard);
        map.put("router",R.drawable.router);
        map.put("youpan",R.drawable.youpan);
        map.put("shouhuan",R.drawable.shouhuan);
        map.put("earphone",R.drawable.earphone);
    }

    public static Integer getimageid(String name){
        if(map.containsKey(name)){
            return map.get(name);
        }
        return 0;
    }
}
