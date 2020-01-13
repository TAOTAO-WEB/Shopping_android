package cn.edu.hdu.SaltyFish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.hdu.SaltyFish.Adapter.PersonAdapter;
import cn.edu.hdu.SaltyFish.base.PersonTab;

import static android.app.Activity.RESULT_OK;

public class PersonFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private TextView Login;
    private ImageView settings;
    private ImageView head;

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_fragment, container, false);
        List<PersonTab> personTabs;
        personTabs = PersonTab.getTab();
        List<PersonTab> personTabs2;
        personTabs2 = PersonTab.getTab2();
        settings = view.findViewById(R.id.settings);
        head = view.findViewById(R.id.h_head);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        //设置按钮
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.equals("")){
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent,1);
                }
                else {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                }
            }
        });

        //登录后点击默认头像进入相册
        if(!username.equals("")){
            head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivity(intent);
                }
            });
        }

        recyclerView = view.findViewById(R.id.recycler_tab);
        recyclerView2 = view.findViewById(R.id.recycle_tab2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        PersonAdapter Adapter = new PersonAdapter(getActivity(), personTabs);
        recyclerView.setAdapter(Adapter);

        PersonAdapter Adapter2 = new PersonAdapter(getActivity(),personTabs2);
        recyclerView2.setAdapter(Adapter2);

        Login = view.findViewById(R.id.Login);
        if(!username.equals("")){
            Login.setText(username);
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.equals("")){
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent,1);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String username = data.getStringExtra("username");
                    Login.setText(username);
                    Intent intent = new Intent(getContext(),BaseActivity.class);
                    getActivity().startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
