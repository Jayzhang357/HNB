package com.wl.hnb;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Addzd extends Activity {

    private Button income,pay;

    private TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addzb);
        iniView();
    }
    private  Boolean  type=true;
    private int year, month, day;
    private void iniView() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        date=(TextView)findViewById(R.id.date);
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY年MM月dd日");
        date.setText(formatter.format(new Date(System.currentTimeMillis())));
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Addzd.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // 用户选择日期后的处理
                        year = selectedYear;
                        month = selectedMonth+1;
                        day = selectedDay;
                        date.setText(year+"年"+month+"月"+day+"日");
                        // 在这里处理选择的日期，例如显示在TextView上
                        // textView.setText(selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
                    }
                }, year, month, day);

                // 显示日期选择器
                datePickerDialog.show();
            }
        });
        income=(Button)findViewById(R.id.income);
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                income.setBackgroundResource(R.drawable.solid_btn_gw);
                income.setTextColor(getColor(R.color.green));
                pay.setBackgroundResource(R.drawable.solid_btn_grayw);
                pay.setTextColor(getColor(R.color.line_gray));
                type=true;
            }
        });
        pay=(Button)findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pay.setBackgroundResource(R.drawable.solid_btn_gw);
                pay.setTextColor(getColor(R.color.green));
                income.setBackgroundResource(R.drawable.solid_btn_grayw);
                income.setTextColor(getColor(R.color.line_gray));
                type=false;
            }
        });
    }

}
