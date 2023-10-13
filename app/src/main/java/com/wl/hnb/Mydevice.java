package com.wl.hnb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wl.adapter.DataAdapterdevice;
import com.wl.adapter.DataAdapterzb;
import com.wl.delete.DeleteRecyclerView;
import com.wl.delete.MyAdapter;
import com.wl.delete.OnItemClickListener;
import com.wl.entry.Device;
import com.wl.entry.Zhengben;

import java.util.ArrayList;


public class Mydevice extends Activity implements View.OnClickListener{

    private Button btnFinish,btnBack;
    private ListView projectgd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydevice);
        iniView();
    }

    private void iniView() {
        ArrayList list = new ArrayList<Object>();

        Device abc=new Device();
        list.add(abc);


        list.add(abc);   list.add(abc);   list.add(abc);list.add(abc);list.add(abc);list.add(abc);list.add(abc);list.add(abc);
      /*  DataAdapterdevice dataAdapter = new DataAdapterdevice(this, list);
        projectgd = (ListView) findViewById(R.id.projectgd);
        projectgd.setAdapter(dataAdapter);*/

        DeleteRecyclerView     recyclerView = (DeleteRecyclerView) findViewById(R.id.id_item_remove_recyclerview);



        final MyAdapter adapter = new MyAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Mydevice.this, "** " + list.get(position) + " **", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position) {
                adapter.removeItem(position);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ireturn:
                finish();
                break;
        }
    }
}
