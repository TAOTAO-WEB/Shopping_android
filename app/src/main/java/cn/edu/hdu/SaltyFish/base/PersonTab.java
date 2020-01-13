package cn.edu.hdu.SaltyFish.base;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.SaltyFish.R;


public class PersonTab {
    private int tab_image_id;
    private String tab_name;

    public int getTab_image_id() {
        return tab_image_id;
    }

    public void setTab_image_id(int tab_image_id) {
        this.tab_image_id = tab_image_id;
    }

    public String getTab_name() {
        return tab_name;
    }

    public void setTab_name(String tab_name) {
        this.tab_name = tab_name;
    }

    static String[] person_name = {"发布商品","我的购物车","我的咸鱼币",
            "账号绑定","身份认证"};

    static int rs_int[] = {R.drawable.ic_person_home,
                            R.drawable.ic_money,
                            R.drawable.ic_favorite,
                            R.drawable.ic_account,
                            R.drawable.ic_safe,

    };

    static String[] setting_name = {"个人资料","收获地址","关于咸鱼"};

    //获取个人页面的图标
    final static int NUM_TAB1 = 3;
    public static List<PersonTab> getTab(){
        List<PersonTab> personTabs = new ArrayList<PersonTab>();
        for(int i = 0; i < NUM_TAB1; i++){
            PersonTab personTab = new PersonTab();
            personTab.setTab_name(person_name[i]);
            personTab.setTab_image_id(rs_int[i]);
            personTabs.add(personTab);
        }
        return personTabs;
    }

    final static int NUM_TAB2 = 2;
    public static List<PersonTab> getTab2(){
        List<PersonTab> personTabs = new ArrayList<PersonTab>();
        for(int i = 0; i < NUM_TAB2; i++){
            PersonTab personTab = new PersonTab();
            personTab.setTab_name(person_name[i + 3]);
            personTab.setTab_image_id(rs_int[i + 3]);
            personTabs.add(personTab);
        }
        return personTabs;
    }

    //获取设置页面图标
    final static int NUM_TAB3 = 3;
    public static List<PersonTab> getTab3(){
        List<PersonTab> personTabs = new ArrayList<PersonTab>();
        for(int i = 0; i < NUM_TAB3; i++){
            PersonTab personTab = new PersonTab();
            personTab.setTab_name(setting_name[i]);
            personTabs.add(personTab);
        }
        return personTabs;
    }
}
