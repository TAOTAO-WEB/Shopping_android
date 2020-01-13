package cn.edu.hdu.SaltyFish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.edu.hdu.SaltyFish.utils.dbutils;
import cn.edu.hdu.SaltyFish.utils.idutils;

/*
    商品详细页
 */
public class ShoppingActivity extends BaseActivity {
    private ImageView imageView;
    private TextView shoppingfirst;
    private TextView shoppingsec;
    private TextView shoppingmoney;
    private Button shopping_btn;
    private ImageView shopping_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        imageView = findViewById(R.id.shopping_image);
        shoppingfirst = findViewById(R.id.shopping_firsttext);
        shoppingsec = findViewById(R.id.shopping_sectextview);
        shoppingmoney = findViewById(R.id.shopping_money);
        shopping_btn = findViewById(R.id.shopping_btn);
        shopping_back = findViewById(R.id.shopping_back);

        Intent intent = getIntent();
        Integer image = intent.getIntExtra("image",0);
        String firsttext = intent.getStringExtra("firstshop");
        String sectext = intent.getStringExtra("secshop");
        String money = "¥" + intent.getDoubleExtra("price",0.00);
        imageView.setImageResource(image);
        shoppingfirst.setText(firsttext);
        shoppingsec.setText(sectext);
        shoppingmoney.setText(money);

        //回退按钮
        shopping_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingActivity.this.finish();
            }
        });

        //添加购物车按钮设置
        shopping_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","");
                if(!username.equals("")){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Connection con = dbutils.getconnection();
                            PreparedStatement state;
                            String sql = "insert into shoppingcar values(?,?,?,?,?,?)";
                            try {
                                state = con.prepareStatement(sql);
                                state.setInt(1,image);
                                state.setString(2,firsttext);
                                state.setString(3,money);
                                state.setString(4, idutils.getid());
                                state.setString(5,"0");
                                state.setString(6,username);
                                state.executeUpdate();
                                dbutils.close(state,con);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    Toast.makeText(ShoppingActivity.this, "已加入购物车",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ShoppingActivity.this, "先去登陆哦",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
