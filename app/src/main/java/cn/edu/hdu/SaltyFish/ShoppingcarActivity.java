package cn.edu.hdu.SaltyFish;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.SaltyFish.Adapter.ShoppingcarAdapter;
import cn.edu.hdu.SaltyFish.base.Shoppingcar;
import cn.edu.hdu.SaltyFish.utils.dbutils;

/*
    购物车
 */
public class ShoppingcarActivity extends BaseActivity {
    List<Shoppingcar> shoppingcarList = new ArrayList<>();
    RecyclerView recyclerView;
    ImageView shoppingcar_back;
    Context context = this;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    List<Shoppingcar> shoppingcarList = (List<Shoppingcar>) msg.obj;
                    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                    layoutManager.setOrientation(RecyclerView.VERTICAL);

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

                    ShoppingcarAdapter shoppingcarAdapter = new ShoppingcarAdapter(context,shoppingcarList);
                    recyclerView.setAdapter(shoppingcarAdapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con = dbutils.getconnection();
                PreparedStatement state = null;
                ResultSet res = null;
                String sql = "select SCimageid,SCname,SCmoney,SCNO,isdelete from shoppingcar where username=?";
                try {
                    state = con.prepareStatement(sql);
                    state.setString(1,username);
                    res = state.executeQuery();
                    while (res.next()){
                        Integer scimageid = res.getInt(1);
                        String scname = res.getString(2);
                        String scmoney = res.getString(3);
                        String scno = res.getString(4);
                        String isdelete = res.getString(5);
                        if(isdelete.equals("0")){
                            Shoppingcar shoppingcar = new Shoppingcar(scimageid,scname,scmoney,scno);
                            shoppingcarList.add(shoppingcar);
                        }
                    }
                    dbutils.close(res,state,con);
                    Message msg =new Message();
                    msg.what = 1;
                    msg.obj = shoppingcarList;
                    handler.sendMessage(msg);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        recyclerView = findViewById(R.id.shoppingcar_recy);


        //回退
        shoppingcar_back = findViewById(R.id.shoppingcar_back);
        shoppingcar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingcarActivity.this.finish();
            }
        });
    }
}
