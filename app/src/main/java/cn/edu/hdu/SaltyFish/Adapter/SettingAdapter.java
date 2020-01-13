package cn.edu.hdu.SaltyFish.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.hdu.SaltyFish.R;
import cn.edu.hdu.SaltyFish.base.PersonTab;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    Context context;
    List<PersonTab> personTabs;

    public SettingAdapter(Context context,List<PersonTab> personTabs){
        this.context = context;
        this.personTabs = personTabs;
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_item,parent,false);
        SettingViewHolder viewHolder = new SettingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        String name = personTabs.get(position).getTab_name();
        holder.per_name.setText(name);
    }

    @Override
    public int getItemCount() {
        return personTabs.size();
    }

    public static class SettingViewHolder extends RecyclerView.ViewHolder{
        public TextView per_name;
        public SettingViewHolder(@NonNull View itemView){
            super(itemView);
            per_name = itemView.findViewById(R.id.setting_tab);
        }
    }
}
