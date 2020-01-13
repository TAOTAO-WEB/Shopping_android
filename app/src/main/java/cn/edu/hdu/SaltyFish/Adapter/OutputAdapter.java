package cn.edu.hdu.SaltyFish.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cn.edu.hdu.SaltyFish.OutputshopActivity;
import cn.edu.hdu.SaltyFish.R;
import cn.edu.hdu.SaltyFish.base.OutputShop;
import cn.edu.hdu.SaltyFish.utils.dbutils;

public class OutputAdapter extends RecyclerView.Adapter<OutputAdapter.OutputViewHolder> {
    Context context;
    List<OutputShop> outputShopList;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    outputShopList.remove(msg.arg1);
                    notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    public OutputAdapter(Context context, List<OutputShop> outputShopList){
        this.context = context;
        this.outputShopList = outputShopList;
    }

    @NonNull
    @Override
    public OutputViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.output_shop,parent,false);
        OutputViewHolder viewHolder = new OutputViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OutputViewHolder holder, int position) {
        String title = outputShopList.get(position).getOPtitle();
        String money = outputShopList.get(position).getOPmoney();
        String key = outputShopList.get(position).getOPNO();
        String opcontext = outputShopList.get(position).getOPcontext();
        holder.opmoney.setText(money);
        holder.optitle.setText(title);

        //进入商品详情页
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OutputshopActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("money",money);
                intent.putExtra("context",opcontext);
                context.startActivity(intent);
            }
        });


        //设置isdelete为true 删除发布的商品
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Connection con = dbutils.getconnection();
                        String sql = "update output set isdelete=1 where OPNO=?";
                        PreparedStatement state;
                        try {
                            state = con.prepareStatement(sql);
                            state.setString(1,key);
                            state.executeUpdate();
                            Message msg = new Message();
                            msg.arg1 = position;
                            msg.what = 1;
                            handler.sendMessage(msg);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return outputShopList.size();
    }

    public static class OutputViewHolder extends RecyclerView.ViewHolder{
        TextView optitle;
        TextView opmoney;
        Button btn_delete;
        public OutputViewHolder(@NonNull View itemView) {
            super(itemView);
            optitle = itemView.findViewById(R.id.op_title);
            opmoney = itemView.findViewById(R.id.shop_money);
            btn_delete = itemView.findViewById(R.id.delete_shop);
        }
    }
}
