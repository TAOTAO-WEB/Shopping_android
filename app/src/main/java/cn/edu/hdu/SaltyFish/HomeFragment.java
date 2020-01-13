package cn.edu.hdu.SaltyFish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.SaltyFish.Adapter.HomeAdapter;
import cn.edu.hdu.SaltyFish.base.HomeData;
import cn.edu.hdu.SaltyFish.utils.dbutils;
import cn.edu.hdu.SaltyFish.utils.imageidutils;


public class HomeFragment extends Fragment{
    private List<Object> mObjects;
    private RecyclerView recyclerView;
    //初始化轮播图图片
    private static Integer bannerFirst = R.drawable.ipad;
    private static Integer bannerSecond = R.drawable.keyboard;
    private static Integer bannerThird = R.drawable.mouse;
    private static Integer bannerFourth = R.drawable.ipad2;

    //初始化四格图片
    private static Integer focFirst = R.drawable.climb;
    private static Integer focSecond = R.drawable.shoe;
    private static Integer focThird = R.drawable.home;
    private static Integer focforth = R.drawable.money2;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    HomeAdapter adapter = new HomeAdapter(getContext(), mObjects);
                    adapter.setHasStableIds(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.home_fragment, container, false);
            recyclerView = view.findViewById(R.id.home_recycler);


            if (mObjects == null) {
                mObjects = new ArrayList<>();
            }

            /*
            初始化轮播图
             */
            List<Integer> bannerid = new ArrayList<>();
            //轮播图
            HomeData.TypeBanner banner = new HomeData.TypeBanner();
            bannerid.add(bannerFirst);
            bannerid.add(bannerSecond);
            bannerid.add(bannerThird);
            bannerid.add(bannerFourth);
            banner.setRsid(bannerid);

            //设置轮播图数据
            mObjects.add(banner);

            /*
            初始化四格数据
             */
            HomeData.TypeFocus focus = new HomeData.TypeFocus();
            focus.setPicFirst(focFirst);
            focus.setPicSecond(focSecond);
            focus.setPicThird(focThird);
            focus.setPicForth(focforth);
            focus.setTitleFirst("逛同城");
            focus.setTitleSecond("61.2万人在玩");
            focus.setTitleThird("租借");
            focus.setTitleFourth("你真的很会玩");
            focus.setTitleFifth("租房");
            focus.setTitleSixth("真实房东");
            focus.setTitleSeventh("咸鱼币");
            focus.setTitleEighth("签到换好礼");
            //设置榜单
            mObjects.add(focus);


            //从数据库获取商品信息
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Connection con = dbutils.getconnection();
                    PreparedStatement state = null;
                    String sql = "select * from typebottom";
                    ResultSet res;
                    try {
                        state = con.prepareStatement(sql);
                        res = state.executeQuery();
                        while (res.next()){
                            Integer imageid = imageidutils.getimageid(res.getString(1));
                            String title = res.getString(2);
                            String context = res.getString(3);
                            Double price = res.getDouble(4);
                            mObjects.add(new HomeData.TypeBottom(imageid,title,context,price));
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = mObjects;
                            handler.sendMessage(msg);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    public static class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}






