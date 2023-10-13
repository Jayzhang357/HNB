package com.wl.hnb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;


public class Admin extends Activity  implements View.OnClickListener{


    private LinearLayout sy,admin;
    private ImageView cm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        // 设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }



        iniView();
    }

    private void iniView() {
        sy=(LinearLayout) findViewById(R.id.sy);
        admin=(LinearLayout) findViewById(R.id.admin);
        cm=(ImageView)findViewById(R.id.cm);
        cm.setOnClickListener(this);
        sy.setOnClickListener(this); // 设置"sy"按钮的点击侦听器
        admin.setOnClickListener(this); // 设置"admin"按钮的点击侦听器
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.sy:
                Intent mIntent = new Intent(Admin.this
                        ,
                        Sum.class);
                startActivity(mIntent);
                finish();
                break;
            case R.id.cm:
                 mIntent = new Intent(Admin.this
                        ,
                        CMsum.class);
                startActivity(mIntent);


                break;
        }
    }
}
