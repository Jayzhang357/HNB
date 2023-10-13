package com.wl.hnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;


public class CMsum extends Activity  implements View.OnClickListener{

    private Button ireturn,btnBack;
    private RelativeLayout r1,r2,r3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmsy);
        iniView();

    }

    private void iniView() {
        ireturn=(Button) findViewById(R.id.ireturn);
        ireturn.setOnClickListener(this);
        r1=(RelativeLayout) findViewById(R.id.r1);
        r2=(RelativeLayout) findViewById(R.id.r2);
        r3=(RelativeLayout) findViewById(R.id.r3);
        r3.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.ireturn:

                finish();
                break;
            case R.id.r1:
                break;
            case R.id.r2:


                break;
            case R.id.r3:

                Intent  mIntent = new Intent(CMsum.this
                        ,
                        Hdk.class);
                startActivity(mIntent);
                break;

        }
    }

}
