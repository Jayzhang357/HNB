package com.wl.hnb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Projection;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.mapapi.utils.AreaUtil;
import com.baidu.mapapi.utils.DistanceUtil;
import com.wl.adapter.DataAdapteradress;
import com.wl.adapter.DataAdapterzb;
import com.wl.entry.Adress;
import com.wl.entry.Zhengben;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Hdk extends Activity implements View.OnClickListener{

    private ImageView add, deletetxt;
    private EditText searchcontent;
    private Button delete, syb, complate,ireturn;
    private Bitmap resizedBitmap;
    private RelativeLayout r6,rr1;
    private TextView t1;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ireturn:
               finish();
                break;

            case R.id.deletetxt:
                searchcontent.setText("");
                deletetxt.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

// 在用户完成输入后隐藏键盘
                imm.hideSoftInputFromWindow(searchcontent.getWindowToken(), 0);
                break;
            case R.id.t1:
                 imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

// 在用户完成输入后隐藏键盘
                imm.hideSoftInputFromWindow(searchcontent.getWindowToken(), 0);

                if(searchcontent.getText().toString().length()==0)return;
                Geocoder mCoder = new Geocoder(getBaseContext(), Locale.CHINESE);


                List<Address> addresses = null;
                try {

                    addresses = mCoder.getFromLocation(mlatitude, mlongitude
                            ,
                            1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LatLng chinaCenter = new LatLng(mlatitude, mlongitude);
                String city = "北京";
                if (addresses.size() > 0)
                    city = addresses.get(0).getAdminArea().toString();
                mSuggestionSearch.requestSuggestion(new SuggestionSearchOption()
                        .city(city).location(chinaCenter)
                        .keyword(searchcontent.getText().toString()));
                break;
            case R.id.add:
                Point centerPoint = new Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);

// 将屏幕坐标转换为地理坐标
                LatLng centerLatLng = mBaiduMap.getProjection().fromScreenLocation(centerPoint);
                points.add(centerLatLng);
                showDraw();
                break;
            case R.id.delete:
                if (mMum != null)
                    mMum.remove();
                if (mMum1 != null)
                    mMum1.remove();

                if (mTexttemp != null)
                    mTexttemp.remove();
                points.clear();
                if (mPolylinesum != null)
                    mPolylinesum.remove();
                if (mMarkersum.size() > 0) {
                    for (Overlay marker : mMarkersum) {
                        marker.remove();
                    }
                    mMarkersum.clear(); // 移除标记
                }
                if (mTextsum.size() > 0) {
                    for (Overlay marker : mTextsum) {
                        marker.remove();
                    }
                    mTextsum.clear(); // 移除标记
                }
                complate.setVisibility(View.INVISIBLE);
                break;
            case R.id.syb:
                if (points.size() > 0) {
                    if (mMum != null)
                        mMum.remove();
                    if (mMum1 != null)
                        mMum1.remove();
                    if (mTexttemp != null)
                        mTexttemp.remove();
                    points.remove(points.size() - 1);
                    if (mPolylinesum != null)
                        mPolylinesum.remove();
                    if (mMarkersum.size() > 0) {
                        for (Overlay marker : mMarkersum) {
                            marker.remove();
                        }
                        mMarkersum.clear(); // 移除标记
                    }
                    if (mTextsum.size() > 0) {
                        for (Overlay marker : mTextsum) {
                            marker.remove();
                        }
                        mTextsum.clear(); // 移除标记
                    }
                    showDraw();
                }
                break;
            case R.id.complate:
                AlertDialog.Builder builder = new AlertDialog.Builder(Hdk.this);
                builder
                        .setMessage(getString(R.string.hdk_mes))
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (points.size() < 3)
                                    return;
                                LatLngBounds.Builder builder = new LatLngBounds.Builder();

// 将所有顶点添加到builder中
                                for (LatLng point : points) {
                                    builder.include(point);
                                }

// 创建一个包含所有顶点的LatLngBounds对象
                                LatLngBounds bounds = builder.build();

// 计算包含所有顶点的地图状态
                                int padding = 500; // 可以根据需要调整边界留白
                                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(bounds, 100, 100, 200, 1200);

// 将地图状态应用到地图上
                                mBaiduMap.animateMapStatus(mapStatusUpdate);
                                // 隐藏当前位置图标
                                mBaiduMap.setMyLocationEnabled(false);
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                mBaiduMap.snapshot(callback);
                                rmess.setVisibility(View.VISIBLE);
                                center.setVisibility(View.GONE);
                                rr1.setVisibility(View.INVISIBLE);
                                setMessage();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); // Close the activity if the user cancels the update

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
                break;
            case R.id.type:
                if (getType) {
                    type.setImageResource(R.drawable.map_icon_2d);
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    getType = false;
                } else {
                    type.setImageResource(R.drawable.map_icon_3d);
                    getType = true;
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                }
                showDraw();
                break;

            case R.id.location:
                if(rmess.getVisibility()==View.VISIBLE)
                {
                     chinaCenter = new LatLng(centerPointtemp.latitude, centerPointtemp.longitude); // 中国中心点的经纬度坐标
                    mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(chinaCenter));
                    // 获取BaiduMap对象
                    // 获取BaiduMap对象
                    BaiduMap baiduMap = mMapView.getMap();

// 获取当前地图的地理位置
                    MapStatus mapStatus = baiduMap.getMapStatus();

// 计算新的地图中心位置
                    LatLng currentCenter = mapStatus.target;
                    Projection projection = baiduMap.getProjection();
                    Point currentCenterPoint = projection.toScreenLocation(currentCenter);

                    int verticalOffsetInPixels = (int) (100 * getResources().getDisplayMetrics().density);
                    Point newCenterPoint = new Point(currentCenterPoint.x, currentCenterPoint.y + verticalOffsetInPixels);
                    LatLng newCenter = projection.fromScreenLocation(newCenterPoint);

// 创建MapStatusUpdate对象，将地图中心设置为新位置
                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(newCenter);

// 将地图状态应用到地图上
                    baiduMap.animateMapStatus(mapStatusUpdate);


                }
                else {
                     chinaCenter = new LatLng(mlatitude, mlongitude); // 中国中心点的经纬度坐标
                    mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(chinaCenter));
                }

                break;
            case R.id.l1:
                rmess.setVisibility(View.GONE);
                center.setVisibility(View.VISIBLE);
                rr1.setVisibility(View.VISIBLE);

                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdk);
        iniView();
        initLocation();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(18));
        mBaiduMap.getUiSettings().setRotateGesturesEnabled(false);

        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.map_icon_zb);
        resizedBitmap = Bitmap.createScaledBitmap(bitmap.getBitmap(), 120, 120, false);
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(listener);
        mBaiduMap.setOnMapClickListener(listener1);

    }

    private SuggestionSearch mSuggestionSearch;
    List<LatLng> points = new ArrayList<>();

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // 获取控件的高度
            Log.e("高度", r6.getHeight() + "");
            mMapView.getChildAt(2).setPadding(0, 0, 0, r6.getHeight() - 3000);//这是控制缩放控件的位置

            // 处理高度值
        }
    }
    BaiduMap.OnMapClickListener listener1 = new BaiduMap.OnMapClickListener() {
        /**
         * 地图单击事件回调函数
         *
         * @param point 点击的地理坐标
         */
        @Override
        public void onMapClick(LatLng point) {
            listView.setVisibility(View.GONE);
        }

        /**
         * 地图内 Poi 单击事件回调函数
         *
         * @param mapPoi 点击的 poi 信息
         */
        @Override
        public void onMapPoiClick(MapPoi mapPoi) {
            listView.setVisibility(View.GONE);
        }
    };
    private void showDraw() {
        if (points.size() >= 3)
            complate.setVisibility(View.VISIBLE);
        else
            complate.setVisibility(View.INVISIBLE);
        if (mPolylinesum != null)
            mPolylinesum.remove();
        if (mTexttemp != null)
            mTexttemp.remove();
        if (mMum != null)
            mMum.remove();
        if (mMum1 != null)
            mMum1.remove();
        if (points.size() > 2) {
            mPolygonOptions = new PolygonOptions()
                    .points(points)
                    .fillColor(getColor(R.color.green_dt)) //填充颜色green_t
                    .stroke(new Stroke(5, getColor(R.color.green))); //边框宽度和颜色
            mPolylinesum = mBaiduMap.addOverlay(mPolygonOptions);
            Log.e("面积", AreaUtil.calculateArea(points) + "");
        } else if (points.size() == 2) {
            OverlayOptions mOverlayOptions = new PolylineOptions()
                    .width(5)
                    .color(getColor(R.color.green))
                    .points(points);

            mPolylinesum = mBaiduMap.addOverlay(mOverlayOptions);


        }
        double b = 0;
        double l = 0;
        if (mMarkersum.size() > 0) {
            for (Overlay marker : mMarkersum) {
                marker.remove();
            }
            mMarkersum.clear(); // 移除标记
        }
        if (mTextsum.size() > 0) {
            for (Overlay marker : mTextsum) {
                marker.remove();
            }
            mTextsum.clear(); // 移除标记
        }
        int colortext = R.color.red;
        if (getType) {
            colortext = R.color.white;
        }

        for (int i = 0; i < points.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(points.get(i)).anchor(0.5f, 0.8f)
                    .icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap));
//在地图上添加Marker，并显示
            Marker marker = (Marker) mBaiduMap.addOverlay(markerOptions);
            marker.setDraggable(true);
            mMarkersum.add(marker);
            Bundle markerData = new Bundle();
            markerData.putInt("id", i);

            marker.setExtraInfo(markerData);
            if (i == 0 && points.size() > 2) {
                LatLng centerPoint1 = new LatLng((points.get(points.size() - 1).latitude + points.get(0).latitude) / 2,
                        (points.get(points.size() - 1).longitude + points.get(0).longitude) / 2);

                TextOptions textOptions = new TextOptions()
                        .text(String.format("%.2f", DistanceUtil.getDistance(points.get(0), points.get(points.size() - 1))) + "m") // 格式化长度并添加单位
                        .position(centerPoint1) // 设置显示位置为折线的起点
                        .fontSize(24) // 设置文字大小
                        .fontColor(getColor(colortext)).rotate((float) getAngle(points.get(points.size() - 1), points.get(0))); // 设置文字对齐方式

                mTexttemp = mBaiduMap.addOverlay(textOptions); // 将文字覆盖物添加到地图上
            } else if (i > 0) {
                LatLng centerPoint1 = new LatLng((points.get(i - 1).latitude + points.get(i).latitude) / 2,
                        (points.get(i - 1).longitude + points.get(i).longitude) / 2);

                TextOptions textOptions = new TextOptions()
                        .text(String.format("%.2f", DistanceUtil.getDistance(points.get(i), points.get(i - 1))) + "m") // 格式化长度并添加单位
                        .position(centerPoint1) // 设置显示位置为折线的起点
                        .fontSize(24) // 设置文字大小

                        .fontColor(getColor(colortext)).rotate((float) getAngle(points.get(i - 1), points.get(i))); // 设置文字对齐方式

                mTextsum.add(mBaiduMap.addOverlay(textOptions)); // 将文字覆盖物添加到地图上

            }
            b += points.get(i).latitude;
            l += points.get(i).longitude;
            if (i == points.size() - 1 && points.size() > 2) {
                centerPointtemp = new LatLng(b / points.size(),
                        l / points.size());

                Typeface customTypeface = Typeface.createFromAsset(getAssets(), "mbz.TTF");
                mushu=  String.format("%.2f", AreaUtil.calculateArea(points) * 0.0002471054) + "亩";
                TextOptions textOptions = new TextOptions()
                        .text(mushu) // 格式化长度并添加单位
                        .position(centerPointtemp) // 设置显示位置为折线的起点
                        .fontSize(40) // 设置文字大小


                        .fontColor(getColor(R.color.red))
                        .bgColor(getColor(R.color.green));

                mMum = mBaiduMap.addOverlay(textOptions);


            }

        }
    }
    private String mushu;
    private LatLng centerPointtemp;
    private  ArrayList    listadress;
    OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            //处理sug检索结果
            List<SuggestionResult.SuggestionInfo> getAllSuggestions = suggestionResult.getAllSuggestions();
            listadress = new ArrayList<Object>();


            for (int i = 0; i < getAllSuggestions.size(); i++) {
                Adress abc=new Adress();
                abc.adress=getAllSuggestions.get(i).address;
                abc.name=getAllSuggestions.get(i).key;
                abc.pt=getAllSuggestions.get(i).pt;
                listadress.add(abc);
            }
            DataAdapteradress dataAdapter = new DataAdapteradress(Hdk.this, listadress);

            listView.setAdapter(dataAdapter);
            listView.setVisibility(View.VISIBLE);
        }
    };
    private  ListView listView;

    private RelativeLayout  rmess;
    private void iniView() {
        ireturn= (Button) findViewById(R.id.ireturn);
        ireturn.setOnClickListener(this);
        rmess = (RelativeLayout) findViewById(R.id.rmess);
        deletetxt = (ImageView) findViewById(R.id.deletetxt);
        deletetxt.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Adress abc= (Adress)listadress.get(position);
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(abc.pt));
                listView.setVisibility(View.GONE);
            }
        });
        listView.setVisibility(View.GONE);
        t1 = (TextView) findViewById(R.id.t1);
        searchcontent = (EditText) findViewById(R.id.searchcontent);
        searchcontent.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }//文本改变之前执行

            @Override
            //文本改变的时候执行
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果长度为0
                if (s.length() == 0) {
                    //隐藏“删除”图片
                    deletetxt.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                } else {//长度不为0
                    //显示“删除图片”
                    deletetxt.setVisibility(View.VISIBLE);
                    //显示ListView

                }
            }

            public void afterTextChanged(Editable s) {
            }//文本改变之后执行
        });
        t1.setOnClickListener(this);
        r6 = (RelativeLayout) findViewById(R.id.r6);
        rr1 = (RelativeLayout) findViewById(R.id.rr1);
        rr1.setVisibility(View.VISIBLE);
        add = (ImageView) findViewById(R.id.add);
        add.setOnClickListener(this);
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(this);
        syb = (Button) findViewById(R.id.syb);
        syb.setOnClickListener(this);
        complate = (Button) findViewById(R.id.complate);
        complate.setVisibility(View.INVISIBLE);
        complate.setOnClickListener(this);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();


        BaiduMap.OnMarkerDragListener markerDragListener = new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {
                // 标记正在被拖拽时的回调
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                // 标记拖拽结束时的回调
                Bundle data = marker.getExtraInfo();
                int id = data.getInt("id");

                Log.e("点击", "点击" + id);
                LatLng newPosition = marker.getPosition();

// 将屏幕坐标转换为地理坐标

                points.set(id, newPosition);
                showDraw();
            }

            @Override
            public void onMarkerDragStart(Marker marker) {
                // 标记开始被拖拽时的回调
            }
        };

// 设置标记拖拽监听器
        mBaiduMap.setOnMarkerDragListener(markerDragListener);
        type = (ImageView) findViewById(R.id.type);
        type.setOnClickListener(this);
        location = (ImageView) findViewById(R.id.location);
        location.setOnClickListener(this);

        ll1= (LinearLayout) findViewById(R.id.ll1);
        ll1.setOnClickListener(this);
        ll2= (LinearLayout) findViewById(R.id.ll2);
        ll3= (LinearLayout) findViewById(R.id.ll3);
        ll4= (LinearLayout) findViewById(R.id.ll4);
        center = (ImageView) findViewById(R.id.center);
        center.setVisibility(View.VISIBLE);

    }
    private void setMessage()
    {
        fpSetAb = (ViewFlipper) findViewById(R.id.message);
        fpSetAb.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(Hdk.this);
        View view = inflater.inflate(R.layout.page_message, null);
        fpSetAb.addView(view);
        fpSetAb.setDisplayedChild(0);
        TextView ms=(TextView)view.findViewById(R.id.ms);
        ms.setText(mushu);
     }
    private LinearLayout ll1,ll2,ll3,ll4;
    private ViewFlipper fpSetAb;
    private boolean getType = true;
    private ImageView location, type,center;
    private PolygonOptions mPolygonOptions;
    private Overlay mPolylinesum;
    private Overlay mTexttemp;
    private Overlay mMum;
    private Overlay mMum1;
    private List<Overlay> mMarkersum = new ArrayList<>();
    private List<Overlay> mTextsum = new ArrayList<>();

//设置地图双击事件监听

    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    private Boolean firstLogin = true;
    private double mlatitude, mlongitude;
    BaiduMap.SnapshotReadyCallback callback = new BaiduMap.SnapshotReadyCallback() {

        /**
         * 地图截屏回调接口
         *
         * @param snapshot 截屏返回的 bitmap 数据
         */
        @Override
        public void onSnapshotReady(Bitmap snapshot) {
            Bitmap originalBitmap = snapshot;

            int originalWidth = originalBitmap.getWidth();
            int originalHeight = originalBitmap.getHeight();

// 计算要裁剪的区域的左上角坐标和宽度、高度
            int left = 0; // 起始 X 坐标，这里假设裁剪的区域是原始图片宽度的 1/4
            int top = 400; // 起始 Y 坐标，这里假设裁剪的区域是原始图片高度的 1/4
            int width = originalWidth; // 裁剪的宽度，这里假设裁剪的区域是原始图片宽度的 1/2
            int height = originalHeight - 800; // 裁剪的高度，这里假设裁剪的区域是原始图片高度的 1/2

// 使用 createBitmap() 方法裁剪图片
            Bitmap croppedBitmap = Bitmap.createBitmap(originalBitmap, left, top, width, height);


            mBaiduMap.setMyLocationEnabled(true);
        }
    };

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // MapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            if (firstLogin && mlatitude != 0 && mlongitude != 0) {
                LatLng chinaCenter = new LatLng(mlatitude, mlongitude);
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(chinaCenter));
                firstLogin = false;
            }
            mlatitude = location.getLatitude();
            mlongitude = location.getLongitude();

            MyLocationData myLocationData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())// 设置定位数据的精度信息，单位：米
                    .direction(300)// 此处设置开发者获取到的方向信息，顺时针0-360
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(myLocationData);
        }
    }

    public void initLocation() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        LocationClient.setAgreePrivacy(true);
        try {
            // 定位初始化
            mLocClient = new LocationClient(getApplicationContext());
            mLocClient.registerLocationListener(new MyLocationListener());
            //获取地图控件引用

            //开启地图的定位图层
            mBaiduMap.setMyLocationEnabled(true);
            //通过LocationClientOption设置LocationClient相关参数
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            //设置locationClientOption
            mLocClient.setLocOption(option);
            //注册LocationListener监听器
            MyLocationListener myLocationListener = new MyLocationListener();
            mLocClient.registerLocationListener(myLocationListener);
            //开启地图定位图层
            mLocClient.start();

        } catch (Exception e) {

        }
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mLocClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        mSuggestionSearch.destroy();
        super.onDestroy();
    }

    // 计算两点之间的角度
    private double getAngle(LatLng startPoint, LatLng endPoint) {
        double x1 = startPoint.longitude;
        double y1 = startPoint.latitude;
        double x2 = endPoint.longitude;
        double y2 = endPoint.latitude;
        double dx = x2 - x1;
        double dy = y2 - y1;
        Log.e("角度", Math.toDegrees(Math.atan2(dy, dx)) + "");
        return change_jiaodu(Math.toDegrees(Math.atan2(dy, dx)));
    }

    public double change_jiaodu(double line) {
        if (line < 0)
            line = 360 + line;
        if (line > 360)
            line = line - 360;
        if (90 < line && line < 270)
            line += 180;
        Log.e("角度改变", line + "");
        return line;
    }
}
