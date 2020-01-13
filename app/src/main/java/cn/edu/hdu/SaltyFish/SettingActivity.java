package cn.edu.hdu.SaltyFish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.hdu.SaltyFish.Adapter.SettingAdapter;
import cn.edu.hdu.SaltyFish.base.PersonTab;

public class SettingActivity extends BaseActivity {
    private Button bt_out;
    private RecyclerView recyclerView;
    private ImageView setting_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        List<PersonTab> personTabs;
        personTabs = PersonTab.getTab3();

        recyclerView = findViewById(R.id.settings_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        SettingAdapter settingAdapter = new SettingAdapter(this,personTabs);
        recyclerView.setAdapter(settingAdapter);

        //回退按钮
        setting_back = findViewById(R.id.setting_back);
        setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.this.finish();
            }
        });

        //退出登陆
        bt_out = findViewById(R.id.bt_out);
        bt_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(SettingActivity.this, BaseActivity.class);
                startActivity(intent);
            }
        });
    }
}
