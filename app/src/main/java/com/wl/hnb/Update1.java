package com.wl.hnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Update1 extends Activity implements View.OnClickListener{

    private Button btnFinish,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview__devivce_item_layout);
        iniView();
    }

    private void iniView() {

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
