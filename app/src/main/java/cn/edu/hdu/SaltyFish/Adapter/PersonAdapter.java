package cn.edu.hdu.SaltyFish.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.hdu.SaltyFish.OutputActivity;
import cn.edu.hdu.SaltyFish.R;
import cn.edu.hdu.SaltyFish.base.PersonTab;
import cn.edu.hdu.SaltyFish.ShoppingcarActivity;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    Context context;
    List<PersonTab> personTabList;
    public PersonAdapter(Context context,List<PersonTab> personTabList){
        this.context = context;
        this.personTabList = personTabList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_item,parent,false);
        PersonViewHolder viewHolder = new PersonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        final String name = personTabList.get(position).getTab_name();
        int img_id = personTabList.get(position).getTab_image_id();
        holder.per_name.setText(name);
        holder.per_img.setImageResource(img_id);

        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!username.equals("")){
                    if(name.equals("我的购物车")){
                        Intent intent = new Intent(context, ShoppingcarActivity.class);
                        context.startActivity(intent);
                    }
                    else if(name.equals("发布商品")){
                        Intent intent = new Intent(context, OutputActivity.class);
                        context.startActivity(intent);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return personTabList.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder{
        public TextView per_name;
        public ImageView per_img;
        public PersonViewHolder(@NonNull View itemView){
            super(itemView);
            per_img = itemView.findViewById(R.id.person_tab_img);
            per_name = itemView.findViewById(R.id.person_tab_name);
        }
    }
}
