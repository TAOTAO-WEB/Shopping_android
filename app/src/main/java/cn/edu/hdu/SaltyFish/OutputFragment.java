package cn.edu.hdu.SaltyFish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.edu.hdu.SaltyFish.base.OutputShop;
import cn.edu.hdu.SaltyFish.utils.dbutils;
import cn.edu.hdu.SaltyFish.utils.idutils;

public class OutputFragment extends Fragment {
    private TextView cancelview;
    private Button bt_output;
    private EditText storyView;
    private ConstraintLayout opmoney;
    private ConstraintLayout optel;
    private ConstraintLayout optitle;
    private TextView out_sec_txt;
    private TextView out_sec_sect_txt;
    private TextView out_sec_title;
    private ImageView picture_add;
    private OutputShop outputShop = new OutputShop();
    String num = ".*[0-9].*";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.output_fragment, container, false);
        cancelview = view.findViewById(R.id.cancelView);  //取消按钮
        bt_output = view.findViewById(R.id.bt_output);    //发布按钮
        storyView = view.findViewById(R.id.storyView);    //输入框
        picture_add = view.findViewById(R.id.picture_add);
        opmoney = view.findViewById(R.id.outputtab_money);
        optel = view.findViewById(R.id.outputtab_tel);
        optitle = view.findViewById(R.id.outputtab_title);
        out_sec_txt = view.findViewById(R.id.output_sec_tab);
        out_sec_sect_txt = view.findViewById(R.id.output_sec_sec_tab);
        out_sec_title = view.findViewById(R.id.output_sec_title_tab);

        outputShop.setOPtitle("");
        outputShop.setOPtelephone("");
        outputShop.setOPmoney("");

        //输入商品名称
        optitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(getActivity());
                new AlertDialog.Builder(getActivity()).setTitle("请输入商品名称(10个字以内)")
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                               out_sec_title.setText(et.getText());
                               outputShop.setOPtitle(et.getText().toString());
                            }
                        }).setNegativeButton("取消",null).show();
            }
        });

        //输入开价
        opmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(getActivity());
                new AlertDialog.Builder(getActivity()).setTitle("请输入价格")
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                                if(! et.getText().toString().matches(num)){
                                    Toast.makeText(getActivity(), "请输入正常价格",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    out_sec_txt.setText(et.getText());
                                    outputShop.setOPmoney(et.getText().toString());
                                }
                            }
                        }).setNegativeButton("取消",null).show();
            }
        });

        //输入手机号
        optel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(getActivity());
                new AlertDialog.Builder(getActivity()).setTitle("请输入11位手机号")
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                                if(et.getText().length() != 11 || ! et.getText().toString().matches(num)){
                                    Toast.makeText(getActivity(), "请输入合法手机号",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    out_sec_sect_txt.setText(et.getText());
                                    outputShop.setOPtelephone(et.getText().toString());
                                }
                            }
                        }).setNegativeButton("取消",null).show();
            }
        });

        //发布按钮事件
        bt_output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","");
                outputShop.setOPcontext(storyView.getText().toString());

                if(!username.equals("")){
                    if(outputShop.getOPcontext().equals("")){
                        Toast.makeText(getActivity(), "至少说点什么吧",Toast.LENGTH_SHORT).show();
                    }
                    else if(outputShop.getOPmoney().equals("")){
                        Toast.makeText(getActivity(), "请输入价格",Toast.LENGTH_SHORT).show();
                    }
                    else if(outputShop.getOPtelephone().equals("")){
                        Toast.makeText(getActivity(), "请输入电话",Toast.LENGTH_SHORT).show();
                    }
                    else if(outputShop.getOPtitle().equals("")){
                        Toast.makeText(getActivity(), "请输入商品名称",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //存入mysql数据库
                                Connection con = null;
                                PreparedStatement stat = null;
                                try {
                                    con = dbutils.getconnection();
                                    String sql = "insert into output values(?,?,?,?,?,?,?)";
                                    stat = con.prepareStatement(sql);
                                    stat.setString(1, outputShop.getOPcontext());
                                    stat.setString(2, outputShop.getOPmoney());
                                    stat.setString(3, outputShop.getOPtelephone());
                                    stat.setString(4,outputShop.getOPtitle());
                                    stat.setString(5, username);
                                    stat.setString(6, idutils.getid());
                                    stat.setString(7, "0");
                                    stat.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                dbutils.close(stat,con);
                            }
                        }).start();
                        Toast.makeText(getActivity(), "已发布",Toast.LENGTH_SHORT).show();
                        // 开启页面跳转
                        Intent intent = new Intent(getActivity(),BaseActivity.class);
                        getContext().startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(getActivity(), "先去登陆哦 亲",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //取消点击事件
        cancelview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BaseActivity.class);
                getContext().startActivity(intent);
            }
        });

        //访问相册
        picture_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }
}
