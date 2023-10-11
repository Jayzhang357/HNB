package com.wl.hnb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.AreaUtil;

import java.util.ArrayList;
import java.util.List;


public class Sum extends Activity {

    private Button btnFinish,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);
        iniView();
    }
    List<LatLng> pts=new ArrayList<>();
    private void iniView() {
        pts.add(new LatLng(39.906935, 116.401394)); // 添加多个坐标点
        pts.add(new LatLng(39.907405, 116.402381));
        pts.add(new LatLng(39.907563, 116.401450));
        pts.add(new LatLng(39.907563, 116.401194));
        pts.add(new LatLng(39.907563, 116.401194));
        AreaUtil.calculateArea(pts);
        Log.e("面积", AreaUtil.calculateArea(pts)+"");

    }

}
