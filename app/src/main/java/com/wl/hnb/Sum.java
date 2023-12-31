package com.wl.hnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.AreaUtil;

import java.util.ArrayList;
import java.util.List;


public class Sum extends Activity  implements View.OnClickListener {

    private Button btnFinish,btnBack;
    private LinearLayout sy,admin;
    private ImageView cm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);
        iniView();
    }
    List<LatLng> pts=new ArrayList<>();
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


            case R.id.cm:
                Intent mIntent = new Intent(Sum.this
                        ,
                        CMsum.class);
                startActivity(mIntent);
                break;
            case R.id.admin:

                 mIntent = new Intent(Sum.this
                        ,
                        Admin.class);
                startActivity(mIntent);
                finish();
                break;
        }
    }

}
