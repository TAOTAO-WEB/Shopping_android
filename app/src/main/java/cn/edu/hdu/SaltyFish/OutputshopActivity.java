package cn.edu.hdu.SaltyFish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OutputshopActivity extends BaseActivity {
    private ImageView osp_back;
    private TextView ops_title;
    private TextView ops_context;
    private TextView ops_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outputshop);

        ops_title = findViewById(R.id.osp_title);
        ops_context = findViewById(R.id.osp_context);
        ops_money = findViewById(R.id.osp_money);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String money = intent.getStringExtra("money");
        String opcontext = intent.getStringExtra("context");

        ops_title.setText(title);
        ops_context.setText(opcontext);
        ops_money.setText(money);

        //回退按钮
        osp_back = findViewById(R.id.outputshop_back);
        osp_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputshopActivity.this.finish();
            }
        });

    }
}
