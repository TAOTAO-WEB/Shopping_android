package cn.edu.hdu.SaltyFish;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.hdu.SaltyFish.utils.dbutils;
import cn.edu.hdu.SaltyFish.utils.shautils;
import cn.edu.hdu.SaltyFish.utils.toastutils;

public class LoginActivity extends BaseActivity {
    private EditText loginUsernameEditText;
    private EditText loginPasswordEditText;
    private Button loginButton;
    private Button gotoSignupButton;
    private ImageView login_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsernameEditText = findViewById(R.id.edit_login_username);
        loginPasswordEditText = findViewById(R.id.edit_login_password);
        loginButton = findViewById(R.id.btn_login);
        gotoSignupButton = findViewById(R.id.btn_goto_signup);
        login_back = findViewById(R.id.login_back);

        //回退按钮
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginUsernameEditText.getText().toString();
                String password = shautils.getsha(loginPasswordEditText.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Connection con = dbutils.getconnection();
                        PreparedStatement state = null;
                        ResultSet res = null;
                        String sql = "select username,userpsd from user";
                        try {
                            state = con.prepareStatement(sql);
                            res = state.executeQuery();
                            while (res.next()){
                                String name = res.getString(1);
                                String psd = res.getString(2);
                                if(name.equals(username) && psd.equals(password)){
                                    //保留登录信息 假装是token
                                    SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username",loginUsernameEditText.getText().toString());
                                    editor.apply();

                                    dbutils.close(res,state,con);

                                    Intent intent = new Intent();
                                    intent.putExtra("username",username);
                                    setResult(RESULT_OK,intent);
                                    finish();
                                    return;
                                }
                            }
                            toastutils.show(LoginActivity.this,"不存在该用户或密码错误");
                            //Toast.makeText(LoginActivity.this,"不存在该用户或密码错误",Toast.LENGTH_SHORT).show();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        gotoSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode,data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String username = data.getStringExtra("username");
                    loginUsernameEditText.setText(username);
                }
                break;
            default:
                break;
        }
    }
}
