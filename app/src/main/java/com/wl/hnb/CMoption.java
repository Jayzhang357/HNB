package com.wl.hnb;

import static android.view.View.inflate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class CMoption extends Activity {

    private Button btnFinish,btnBack;
    private TextView fztxt;
    private LinearLayout fz,lzylx;
    private String[] pathlist=new String[]{"啊","啊","啊","啊","啊","啊","啊","自定义"};
    private String[]zylxlist=new String[]{"小麦","水稻","玉米","大豆","大豆啊啊啊啊啊","甘蔗","薯类","自定义"};
    private int selectedTextViewIndex = -1; // 选中的TextView索引
    private TextView[] textViews; // 存储创建的TextView
    private  int itemGet=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmoption);
        iniView();
    }

    private void iniView() {
        fz= (LinearLayout) findViewById(R.id.fz);
        lzylx= (LinearLayout) findViewById(R.id.lzylx);
        // 假设布局中有一个LinearLayout，你可以根据你的布局文件来修改这个id

        textViews = new TextView[zylxlist.length];
        int maxTextViewsPerRow = 5; // 每一行最多5个TextView
        LinearLayout currentRowLinearLayout = null;
        // 创建TextView并添加到LinearLayout中
        for (int i = 0; i < zylxlist.length; i++) {
            String text = zylxlist[i];

            // 创建一个新的TextView
            TextView textView = new TextView(this);
            textView.setText(text);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    dpToPx(63),
                    dpToPx(32)
            ));

            // 设置默认样式
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setTextSize(14);
            textView.setWidth();
            textView.setTypeface(getResources().getFont(R.font.medium));
            textView.setBackgroundResource(R.drawable.solid_btn_grayw_16);
            textView.setGravity(Gravity.CENTER);

            // 添加点击监听器
            final int finalIndex = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 处理点击事件
                    handleTextViewClick(finalIndex);
                }
            });
            if (i % maxTextViewsPerRow == 0) {
                currentRowLinearLayout = new LinearLayout(this);
                currentRowLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                lzylx.addView(currentRowLinearLayout);
            }

            // 添加TextView到LinearLayout
            currentRowLinearLayout.addView(textView);
            textViews[i] = textView;
        }
        fztxt= (TextView) findViewById(R.id.fztxt);
        fz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CMoption.this);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        CMoption.this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        pathlist
                ) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setGravity(Gravity.CENTER); // 设置文本居中显示
                        return view;
                    }
                };

// 设置对话框的列表项适配器
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 处理列表项点击事件
                        fztxt.setText(pathlist[which]);
                    }
                });

// 创建并显示对话框
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
       /* dropdown_menu = (Spinner) findViewById(R.id.dropdown_menu);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                R.layout.custom_spinner_item_cm, pathlist) {


            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = inflate(getContext(), R.layout.custom_spinner_dropdown_item,
                        null);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setText(pathlist[position]);
                if (position == itemGet) {
                    //    textView.setBackgroundResource(R.color.gray);
                } else {
                    //  textView.setBackgroundResource(R.color.white);
                }
                Log.e("获取QQ",position+"");
                return view;
            }
        };
        dropdown_menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemGet=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dropdown_menu.setAdapter(adapter1);*/
    }
    private void handleTextViewClick(int clickedIndex) {


        // 更新点击的TextView样式
       for(int i=0;i<textViews.length;i++)
       {
           textViews[i].setTextColor(getResources().getColor(R.color.black));
           textViews[i].setTextSize(14);
           textViews[i].setTypeface(getResources().getFont(R.font.medium));
           textViews[i].setBackgroundResource(R.drawable.solid_btn_grayw_16);

       }
        if (selectedTextViewIndex != -1) {
            // 恢复之前选中的TextView到默认样式

            textViews[selectedTextViewIndex].setTextColor(getResources().getColor(R.color.green));
            textViews[selectedTextViewIndex].setTextSize(14);
            textViews[selectedTextViewIndex].setTypeface(getResources().getFont(R.font.medium));
            textViews[selectedTextViewIndex].setBackgroundResource(R.drawable.solid_btn_gw_16);
        }
        // 更新选中的TextView索引
        selectedTextViewIndex = clickedIndex;
    }
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

}
