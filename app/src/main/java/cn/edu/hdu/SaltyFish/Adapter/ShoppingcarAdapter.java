package cn.edu.hdu.SaltyFish.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cn.edu.hdu.SaltyFish.R;
import cn.edu.hdu.SaltyFish.base.Shoppingcar;
import cn.edu.hdu.SaltyFish.utils.dbutils;


/*
    购物车容器类
 */
public class ShoppingcarAdapter extends RecyclerView.Adapter<ShoppingcarAdapter.ShoppingcarViewHolder>{
    Context context;
    List<Shoppingcar> shoppingcarList;

    public ShoppingcarAdapter(Context context,List<Shoppingcar> shoppingcarList){
        this.context = context;
        this.shoppingcarList = shoppingcarList;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    shoppingcarList.remove(msg.arg1);
                    notifyDataSetChanged();
            }
        }
    };

    @NonNull
    @Override
    public ShoppingcarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcart_item,parent,false);
        ShoppingcarViewHolder viewHolder = new ShoppingcarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingcarViewHolder holder, int position) {
        int imageid = shoppingcarList.get(position).getImageid();
        String title = shoppingcarList.get(position).getName();
        String money = shoppingcarList.get(position).getMoney();
        String id = shoppingcarList.get(position).getScno();

        holder.shoppingcar_text.setText(title);
        holder.shoppingcar_money.setText(money);
        holder.shoppingcar_image.setImageResource(imageid);

        holder.btn_shopping_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Connection con = dbutils.getconnection();
                        PreparedStatement state = null;
                        String sql = "update shoppingcar set isdelete=1 where SCNO=?";
                        try {
                            state = con.prepareStatement(sql);
                            state.setString(1,id);
                            state.executeUpdate();
                            Message msg = new Message();
                            msg.what = 1;
                            msg.arg1 = position;
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
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return shoppingcarList.size();
    }


    public static class ShoppingcarViewHolder extends RecyclerView.ViewHolder{
        public TextView shoppingcar_text;
        public ImageView shoppingcar_image;
        public TextView shoppingcar_money;
        public Button btn_shopping_cancel;
        public ShoppingcarViewHolder(@NonNull View itemView){
            super(itemView);
            shoppingcar_image = itemView.findViewById(R.id.shoppingcar_img);
            shoppingcar_text = itemView.findViewById(R.id.shoppingcar_text);
            shoppingcar_money = itemView.findViewById(R.id.shoppingcar_money);
            btn_shopping_cancel = itemView.findViewById(R.id.btn_shopping_cancel);
        }
    }
}
