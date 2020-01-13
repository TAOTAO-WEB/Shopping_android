package cn.edu.hdu.SaltyFish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.hdu.SaltyFish.utils.dbutils;
import cn.edu.hdu.SaltyFish.utils.idutils;
import cn.edu.hdu.SaltyFish.utils.shautils;
import cn.edu.hdu.SaltyFish.utils.toastutils;

public class SignupActivity extends BaseActivity{
    private EditText signupUsernameEditText;
    private EditText signupPasswordEditText;
    private EditText signupRepasswordEditText;
    private Button signupButton;
    private ImageView signup_back;

    final static int size_password = 6;
    final static int size_username = 4;
    String little_eng =  ".*[a-z].*";
    String big_eng = ".*[A-Z].*";
    String num = ".*[0-9].*";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupUsernameEditText = findViewById(R.id.edit_signup_username);
        signupPasswordEditText = findViewById(R.id.edit_signup_password);
        signupRepasswordEditText = findViewById(R.id.edit_signup_repassword);
        signupButton = findViewById(R.id.btn_signup);
        signup_back = findViewById(R.id.signup_back);

        //回退按钮
        signup_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignupActivity.this.finish();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signupUsernameEditText.getText().toString().length() < size_username){
                    Toast.makeText(SignupActivity.this, "用户名长度至少为4",Toast.LENGTH_SHORT).show();
                }
                else if(! signupPasswordEditText.getText().toString().equals(signupRepasswordEditText.getText().toString())){
                    Toast.makeText(SignupActivity.this, "两次密码输入不一致",Toast.LENGTH_SHORT).show();
                }
                else if(signupPasswordEditText.getText().toString().length() < size_password){
                    Toast.makeText(SignupActivity.this, "密码长度至少为6",Toast.LENGTH_SHORT).show();
                }
                else if(! signupPasswordEditText.getText().toString().matches(little_eng)){
                    Toast.makeText(SignupActivity.this, "密码未包含小写字母",Toast.LENGTH_SHORT).show();
                }
                else if(! signupPasswordEditText.getText().toString().matches(big_eng)){
                    Toast.makeText(SignupActivity.this, "密码未包含大写字母",Toast.LENGTH_SHORT).show();
                }
                else if(! signupPasswordEditText.getText().toString().matches(num)){
                    Toast.makeText(SignupActivity.this, "密码未包含数字",Toast.LENGTH_SHORT).show();
                }
                else {

                    //判断用户名是否重复 若用户名无重复则注册
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Connection con = dbutils.getconnection();
                            PreparedStatement state = null;
                            ResultSet res =null;
                            String sql = "select username from user";
                            try {
                                state = con.prepareStatement(sql);
                                res = state.executeQuery();
                                while (res.next()){
                                    String name = res.getString(1);
                                    if(name.equals(signupUsernameEditText.getText().toString())){
                                        toastutils.show(SignupActivity.this,"用户名重复");
                                        dbutils.close(res,state,con);
                                        break;
                                    }
                                    else {
                                        dbutils.close(res,state,con);
                                        //数据库添加记录
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Connection con = dbutils.getconnection();
                                                PreparedStatement state = null;
                                                String sql = "insert user values(?,?,?)";
                                                try {
                                                    state = con.prepareStatement(sql);
                                                    state.setString(1,signupUsernameEditText.getText().toString());
                                                    state.setString(2, shautils.getsha(signupPasswordEditText.getText().toString()));
                                                    state.setString(3, idutils.getid());
                                                    state.executeUpdate();
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                                dbutils.close(state,con);
                                                Intent intent = new Intent();
                                                intent.putExtra("username", signupUsernameEditText.getText().toString());
                                                setResult(RESULT_OK, intent);
                                                finish();
                                            }
                                        }).start();
                                    }
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    }
                }
        });
    }
}

