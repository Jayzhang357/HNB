package com.wl.hnb;

import static android.view.View.inflate;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wl.adapter.DataAdapterzb;
import com.wl.entry.Zhengben;

import java.util.ArrayList;


public class Zhangben extends Activity {

    private Button btnFinish,btnBack;
    private Spinner dropdown_menu;
    private String[] pathlist=new String[]{"2023年全年","2023年9月","2023年8月"};

    private TextView biaoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zb);
        iniView();
    }

    private void iniView() {
        biaoti= (TextView) findViewById(R.id.biaoti);
        dropdown_menu = (Spinner) findViewById(R.id.dropdown_menu);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                R.layout.custom_spinner_item, pathlist) {


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
                biaoti.setText(pathlist[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
          dropdown_menu.setAdapter(adapter1);
       //adapter1.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        dropdown_menu.setSelection(itemGet);

        ArrayList    list = new ArrayList<Object>();
        Zhengben abc=new Zhengben();
        abc.type=0;
        abc.name="加油加油加油加";
        abc.money="1000000.00";
        abc.month="";
        abc.time="9月3号10：00";
        Zhengben abc1=new Zhengben();
        abc1.month="1月";
        list.add(abc1);

        list.add(abc);   list.add(abc);   list.add(abc);

        list.add(abc1);

        list.add(abc);   list.add(abc);   list.add(abc);list.add(abc);list.add(abc);list.add(abc);list.add(abc);list.add(abc);
        DataAdapterzb  dataAdapter = new DataAdapterzb(this, list);
        projectgd = (ListView) findViewById(R.id.projectgd);
        projectgd.setAdapter(dataAdapter);

    }
    private  int itemGet=0;
    private ListView projectgd;
}
