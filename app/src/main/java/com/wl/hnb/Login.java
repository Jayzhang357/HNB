package com.wl.hnb;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.ui.AppBarConfiguration;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends Activity {

    private AppBarConfiguration appBarConfiguration;
    private EditText username, password;
    private Button login;
    private ImageView zcline, passwordline;
    private TextView zc, forget;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            // 如果没有定位权限，则向用户请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, REQUEST_LOCATION_PERMISSION);
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

            String usernametext = sharedPreferences.getString("username", "");
            String passwordtext = sharedPreferences.getString("password", "");
            username.setText(usernametext);
            password.setText(passwordtext);
            Intent intent = getIntent();
            String abc = intent.getStringExtra("message");

            if (abc != null) {

            } else {


            }

            // 如果已经有定位权限，则执行相应操作
            // 这里可以调用启动地图定位的代码
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            boolean allPermissionsGranted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (!allPermissionsGranted) {
                // 如果有任何一个权限被拒绝，立即退出应用程序
                finishAffinity();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

                String usernametext = sharedPreferences.getString("username", "");
                String passwordtext = sharedPreferences.getString("password", "");
                username.setText(usernametext);
                password.setText(passwordtext);
                Intent intent = getIntent();
                String abc = intent.getStringExtra("message");

                if (abc != null) {

                } else {


                }

                // 用户授予了所有权限，执行相应操作
                // 这里可以调用启动地图定位的代码
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


// 检查权限


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);
        iniView();

        requestLocationPermission();
    }

    private void iniView() {
        zc = (TextView) findViewById(R.id.zc);
        zc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Login.this, Zhuce.class);


                startActivity(intent);

            }
        });
        forget= (TextView) findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Login.this, Forget.class);


                startActivity(intent);

            }
        });
        zcline = (ImageView) findViewById(R.id.zcline);
        passwordline = (ImageView) findViewById(R.id.passwordline);
        username = (EditText) findViewById(R.id.username);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (hasFocus) {

                            zcline.setBackgroundResource(R.color.line_green);
                            // EditText 获取焦点时触发的逻辑
                            // 例如，显示提示或更改外观
                        } else {
                            zcline.setBackgroundResource(R.color.line_gray);
                            // EditText 失去焦点时触发的逻辑
                            // 例如，验证输入或隐藏提示
                        }
                    }
                });

            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (charSequence.toString().trim().length() == 0) {
                            // EditText 中没有输入
                            if (password.getText().length() == 0 || username.getText().length() == 0) {
                                login.setEnabled(false);
                                login.setBackgroundResource(R.drawable.solid_bggreengray);
                            }
                        } else {
                            if (password.getText().length() > 0 && username.getText().length() > 0) {
                                login.setEnabled(true);
                                login.setBackgroundResource(R.drawable.solid_bggreen);
                            }
                            // EditText 中有输入
                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        password = (EditText) findViewById(R.id.password);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (hasFocus) {

                            passwordline.setBackgroundResource(R.color.line_green);
                            // EditText 获取焦点时触发的逻辑
                            // 例如，显示提示或更改外观
                        } else {
                            passwordline.setBackgroundResource(R.color.line_gray);
                            // EditText 失去焦点时触发的逻辑
                            // 例如，验证输入或隐藏提示
                        }
                    }
                });

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (charSequence.toString().trim().length() == 0) {
                            // EditText 中没有输入
                            if (password.getText().length() == 0 || username.getText().length() == 0) {
                                login.setEnabled(false);
                                login.setBackgroundResource(R.drawable.solid_bggreengray);
                            }
                        } else {
                            if (password.getText().length() > 0 && username.getText().length() > 0) {
                                login.setEnabled(true);
                                login.setBackgroundResource(R.drawable.solid_bggreen);
                            }
                            // EditText 中有输入
                        }
                    }
                });

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });
    }

}