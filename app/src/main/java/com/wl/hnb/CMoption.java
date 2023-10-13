package com.wl.hnb;

import static android.view.View.inflate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.List;


public class CMoption extends Activity {

    private Button btnFinish,btnBack;
    private static final int PICK_IMAGE_REQUEST_CODE = 1;
    private TextView fztxt;
    private LinearLayout fz,lzylx;
    private String[] pathlist=new String[]{"啊","啊","啊","啊","啊","啊","啊","自定义"};
    private String[]zylxlist=new String[]{"小麦","水稻","玉米","大豆","大豆啊啊啊","甘蔗","薯类","自定义"};
    private int selectedTextViewIndex = -1; // 选中的TextView索引
    private TextView[] textViews; // 存储创建的TextView
    private  int itemGet=0;
    private RelativeLayout imagerl1,imagerl2,imagerl3;
    private ImageView image1,image2,imagedelete1,imagedelete2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmoption);
        iniView();
    }

    private void iniView() {
        image1= (ImageView) findViewById(R.id.image1);
        image2= (ImageView) findViewById(R.id.image2);
        imagedelete1= (ImageView) findViewById(R.id.imagedelete1);
        imagedelete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理点击事件
                imagerl1.setVisibility(View.GONE);
                imagerl3.setVisibility(View.VISIBLE);
            }
        });
        imagedelete2= (ImageView) findViewById(R.id.imagedelete2);
        imagedelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理点击事件
                imagerl2.setVisibility(View.GONE);
                imagerl3.setVisibility(View.VISIBLE);
            }
        });
        imagerl1= (RelativeLayout) findViewById(R.id.imagerl1);
        imagerl2= (RelativeLayout) findViewById(R.id.imagerl2);
        imagerl3= (RelativeLayout) findViewById(R.id.imagerl3);
        imagerl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理点击事件
                // 创建一个Intent，用于打开相册
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                // 确保只选择图像类型的文件
                intent.setType("image/*");

                // 启动系统相册
                startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
            }
        });
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
            int contentWidth = measureTextWidth(textView);
            int widthWithPadding = contentWidth + dpToPx(20);

            // 设置宽度为计算后的值，高度为32dp
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    widthWithPadding,
                    dpToPx(32)
            ));

            // 设置默认样式
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setTextSize(14);

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
                    Log.e("点击",finalIndex+"");
                }
            });
            if (i % maxTextViewsPerRow == 0) {
                currentRowLinearLayout = new LinearLayout(this);
                currentRowLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                lzylx.addView(currentRowLinearLayout);
                View spacerView = new View(this);
                spacerView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dpToPx(5)
                ));
                lzylx.addView(spacerView);
            }

            // 添加TextView到LinearLayout
            currentRowLinearLayout.addView(textView);
            View spacerView = new View(this);
            spacerView.setLayoutParams(new LinearLayout.LayoutParams(
                    dpToPx(5), // 5dp的宽度作为间隔
                    LinearLayout.LayoutParams.MATCH_PARENT
            ));
            currentRowLinearLayout.addView(spacerView);
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

           textViews[i].setBackgroundResource(R.drawable.solid_btn_grayw_16);

       }
        selectedTextViewIndex = clickedIndex;
        if (selectedTextViewIndex != -1) {
            // 恢复之前选中的TextView到默认样式
            textViews[selectedTextViewIndex].setTextColor(getResources().getColor(R.color.green));
            textViews[selectedTextViewIndex].setBackgroundResource(R.drawable.solid_btn_gw_16);
        }
        // 更新选中的TextView索引

    }
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
    private int measureTextWidth(TextView textView) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredWidth();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            // 用户已经成功选择了照片
            if (data != null && data.getData() != null) {
                // 获取所选图像的URI
                Uri imageUri = data.getData();

                try {
                    // 从URI中获取图像并将其设置到ImageView中
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    if(imagerl1.getVisibility()!=View.VISIBLE)
                    {
                        image1.setImageBitmap(bitmap);
                        imagerl1.setVisibility(View.VISIBLE);
                    }
                    else if(imagerl2.getVisibility()!=View.VISIBLE)
                    {
                        image2.setImageBitmap(bitmap);
                        imagerl2.setVisibility(View.VISIBLE);
                    }
                    if(imagerl1.getVisibility()==View.VISIBLE&&imagerl2.getVisibility()==View.VISIBLE)
                        imagerl3.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
