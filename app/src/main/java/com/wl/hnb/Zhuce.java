package com.wl.hnb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wl.comom.SmsSenderCallback;
import com.wl.getonl.GetDeviceLocation;
import com.wl.getonl.SmsSender;
import com.wl.getonl.GetDeviceInfo;

import java.util.Random;
import java.util.regex.Pattern;


public class Zhuce extends Activity implements SmsSenderCallback {


    private EditText username, yzm, password, passwordag;
    private Button zc, hqzcm,ireturn;
    private ImageView zcline, passwordline, passwordagline, yzmline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc);
        iniView();


        /*GetDeviceLocation smsSender1 = new GetDeviceLocation("HZG30CA01MC");

        smsSender1.execute();*/
    }

    private static String generateRandomNumericCode(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // 生成随机数字（0-9）
            sb.append(digit);
        }
        return sb.toString();
    }

    private void iniView() {
        zcline = (ImageView) findViewById(R.id.zcline);
        yzmline = (ImageView) findViewById(R.id.yzmline);
        passwordagline = (ImageView) findViewById(R.id.passwordagline);
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
                            if (password.getText().length() == 0 || username.getText().length() == 0 || passwordag.getText().length() == 0 || yzm.getText().length() == 0) {
                                zc.setEnabled(false);
                                zc.setBackgroundResource(R.drawable.solid_bggreengray);
                            }
                        } else {
                            if (password.getText().length() > 0 && username.getText().length() > 0 && passwordag.getText().length() > 0 && yzm.getText().length() > 0) {
                                zc.setEnabled(true);
                                zc.setBackgroundResource(R.drawable.solid_bggreen);
                            }
                            // EditText 中有输入
                        }
                        if (isValidPhoneNumber(username.getText().toString())) {
                            hqzcm.setBackgroundResource(R.drawable.solid_bggreen);
                            hqzcm.setEnabled(true);
                        } else {
                            hqzcm.setBackgroundResource(R.drawable.solid_bggreengray);
                            hqzcm.setEnabled(false);
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
                            if (password.getText().length() == 0 || username.getText().length() == 0 || passwordag.getText().length() == 0 || yzm.getText().length() == 0) {
                                zc.setEnabled(false);
                                zc.setBackgroundResource(R.drawable.solid_bggreengray);
                            }
                        } else {
                            if (password.getText().length() > 0 && username.getText().length() > 0 && passwordag.getText().length() > 0 && yzm.getText().length() > 0) {
                                zc.setEnabled(true);
                                zc.setBackgroundResource(R.drawable.solid_bggreen);
                            }
                            // EditText 中有输入
                        }
                        if (password.getText().toString().equals(passwordag.getText().toString())) {
                            passwordag.setTextColor(getResources().getColor(R.color.black));

                        } else {
                            passwordag.setTextColor(getResources().getColor(R.color.redtext));


                        }
                    }
                });

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        passwordag = (EditText) findViewById(R.id.passwordag);
        passwordag.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (hasFocus) {

                            passwordagline.setBackgroundResource(R.color.line_green);
                            // EditText 获取焦点时触发的逻辑
                            // 例如，显示提示或更改外观
                        } else {
                            passwordagline.setBackgroundResource(R.color.line_gray);
                            // EditText 失去焦点时触发的逻辑
                            // 例如，验证输入或隐藏提示
                        }
                    }
                });

            }
        });
        passwordag.addTextChangedListener(new TextWatcher() {
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
                            if (password.getText().length() == 0 || username.getText().length() == 0 || passwordag.getText().length() == 0 || yzm.getText().length() == 0) {
                                zc.setEnabled(false);
                                zc.setBackgroundResource(R.drawable.solid_bggreengray);
                            }
                        } else {
                            if (password.getText().length() > 0 && username.getText().length() > 0 && passwordag.getText().length() > 0 && yzm.getText().length() > 0) {
                                zc.setEnabled(true);
                                zc.setBackgroundResource(R.drawable.solid_bggreen);
                            }
                            // EditText 中有输入
                        }
                        if (password.getText().toString().equals(passwordag.getText().toString())) {
                            passwordag.setTextColor(getResources().getColor(R.color.black));
                            passwordagline.setBackgroundResource(R.color.line_green);
                        } else {
                            passwordag.setTextColor(getResources().getColor(R.color.redtext));

                            passwordagline.setBackgroundResource(R.color.redtext);
                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        yzm = (EditText) findViewById(R.id.yzm);
        yzm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (hasFocus) {

                            yzmline.setBackgroundResource(R.color.line_green);
                            // EditText 获取焦点时触发的逻辑
                            // 例如，显示提示或更改外观
                        } else {
                            yzmline.setBackgroundResource(R.color.line_gray);
                            // EditText 失去焦点时触发的逻辑
                            // 例如，验证输入或隐藏提示
                        }
                    }
                });

            }
        });
        yzm.addTextChangedListener(new TextWatcher() {
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
                            if (password.getText().length() == 0 || username.getText().length() == 0 || passwordag.getText().length() == 0 || yzm.getText().length() == 0) {
                                zc.setEnabled(false);
                                zc.setBackgroundResource(R.drawable.solid_bggreengray);
                            }
                        } else {
                            if (password.getText().length() > 0 && username.getText().length() > 0 && passwordag.getText().length() > 0 && yzm.getText().length() > 0) {
                                zc.setEnabled(true);
                                zc.setBackgroundResource(R.drawable.solid_bggreen);
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
        zc = (Button) findViewById(R.id.zc);
        zc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });
        ireturn= (Button) findViewById(R.id.ireturn);
        ireturn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

             finish();


            }
        });
        hqzcm = (Button) findViewById(R.id.hqzcm);
        hqzcm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String userName = "yiluohy";
                String password = "Esurvey1116";
                yzmtem= generateRandomNumericCode(6);
                SmsSender smsSender = new SmsSender(userName, password, yzmtem, username.getText().toString(), Zhuce.this);

                smsSender.execute();

                   /*
*/


            }
        });
    }
    public void setMessage(String message)
    {
        LayoutInflater inflater = getLayoutInflater();
        View toastView = inflater.inflate(R.layout.custom_toast, null);

        TextView textView = toastView.findViewById(R.id.custom_toast_message);
        textView.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.show();
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // 使用正则表达式匹配手机号码
        String regex = "[1][34578][0-9]{9}"; // 11位数字，第一位不为0
        return Pattern.matches(regex, phoneNumber);
    }
    public   String yzmtem="";
    private CountDownTimer countDownTimer;

    @Override
    public void onSmsSendComplete(String result) {
        Log.e("獲取", result);
      if(result.contains("success")) {

          countDownTimer = new CountDownTimer(60000, 1000) {
              @Override
              public void onTick(long millisUntilFinished) {
                  // 更新按钮文本为倒计时时间（秒）
                  hqzcm.setEnabled(false);
                  hqzcm.setBackgroundResource(R.drawable.solid_bggreengray);

                  hqzcm.setText((millisUntilFinished / 1000) + "s");
              }

              @Override
              public void onFinish() {
                  // 倒计时完成后恢复按钮状态并设置文本
                  if (isValidPhoneNumber(username.getText().toString())) {
                      hqzcm.setBackgroundResource(R.drawable.solid_bggreen);
                      hqzcm.setEnabled(true);
                  } else {
                      hqzcm.setBackgroundResource(R.drawable.solid_bggreengray);
                      hqzcm.setEnabled(false);
                  }
                  hqzcm.setText(getString(R.string.hqyzm));
              }
          }.start();
      }
      else
      {
          setMessage(getString(R.string.wrongyzm));
      }

    }
}
