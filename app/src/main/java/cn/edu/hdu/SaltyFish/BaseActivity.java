package cn.edu.hdu.SaltyFish;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hdu.SaltyFish.Adapter.FragmentAdapter;

public class BaseActivity extends AppCompatActivity {
    private BottomNavigationView bnView;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        /*
        隐藏标题栏
         */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //隐藏状态栏
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        bnView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(6); //设置需要缓存的view个数
        List<Fragment> fragments = new ArrayList<>();


        fragments.add(new HomeFragment());
        fragments.add(new OutputFragment());
        fragments.add(new PersonFragment());

        FragmentAdapter adapter = new FragmentAdapter(fragments,getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        /*
        点击事件监听
         */
        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();

                switch (menuId) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_sport:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_user:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

    /*
    改变按钮状态
     */
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            bnView.getMenu().getItem(position).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });

    }
}

