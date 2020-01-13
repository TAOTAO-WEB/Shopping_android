package cn.edu.hdu.SaltyFish;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.SaltyFish.Adapter.OutputAdapter;
import cn.edu.hdu.SaltyFish.base.OutputShop;
import cn.edu.hdu.SaltyFish.utils.dbutils;

 //我的发布
public class OutputActivity extends BaseActivity{
    private ImageView output_back;
    private RecyclerView op_rec;
    private List<OutputShop> outputShopList = new ArrayList<>();
    private Context context = this;




    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    List<OutputShop> outputShopList = (List<OutputShop>) msg.obj;
                    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                    layoutManager.setOrientation(RecyclerView.VERTICAL);
                    op_rec.setLayoutManager(layoutManager);
                    op_rec.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

                    OutputAdapter outputAdapter = new OutputAdapter(context,outputShopList);
                    op_rec.setAdapter(outputAdapter);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        op_rec = findViewById(R.id.output_rec);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取数据库发布商品的数据
                Connection con = null;
                SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","");
                String sql = "select OPcontext,OPtelephone,OPmoney,OPtitle,isdelete,OPNO from output where username=?";
                PreparedStatement state = null;
                ResultSet res = null;
                try {
                    con = dbutils.getconnection();
                    state = con.prepareStatement(sql);
                    state.setString(1,username);
                    res = state.executeQuery();
                    while (res.next()){
                        String opcontext = res.getString(1);
                        String optel = res.getString(2);
                        String opmoney = res.getString(3);
                        String optitle = res.getString(4);
                        String isdelete = res.getString(5);
                        String opno = res.getString(6);
                        //判断发布的商品是否被删除
                        if(isdelete.equals("0")){
                            OutputShop outputShop = new OutputShop(opcontext,opmoney,optel,optitle,opno);
                            outputShopList.add(outputShop);
                        }
                    }
                    dbutils.close(res,state,con);
                    Message message = new Message();
                    message.obj = outputShopList;
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //回退按钮
        output_back = findViewById(R.id.output_back);
        output_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputActivity.this.finish();
            }
        });
    }
}
