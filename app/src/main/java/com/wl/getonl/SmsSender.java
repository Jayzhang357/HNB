package com.wl.getonl;

import android.os.AsyncTask;
import android.util.Log;

import com.wl.comom.MD5Util;
import com.wl.comom.SmsSenderCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SmsSender extends AsyncTask<Void, Void, String> {
    private static final String TAG = "SmsSender";
    private static final String BASE_URL = "https://api.mix2.zthysms.com/v2/sendSmsTp";
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private String userName;
    private String pwd;
    private String yzm;
    private String phonenumber;

    private SmsSenderCallback callback;

    public SmsSender(String userName, String pwd, String yzm, String phonenumber, SmsSenderCallback callback) {
        this.userName = userName;
        this.pwd = pwd;
        this.yzm = yzm;
        this.phonenumber=phonenumber;
        // 构造函数...
        this.callback = callback;
    }
    @Override
    protected String doInBackground(Void... voids) {
        try {

            OkHttpClient client = new OkHttpClient();

            JSONObject requestJson = new JSONObject();
            //账号
            requestJson.put("username", userName);
            //tKey
            long tKey = System.currentTimeMillis() / 1000;
            requestJson.put("tKey", tKey);
            //明文密码
            requestJson.put("password", MD5Util.md5(MD5Util.md5(pwd) + tKey));
            //模板ID
            requestJson.put("tpId", "96427");
            //签名
            requestJson.put("signature", "【慧农】");
            //扩展号
            requestJson.put("ext", "");
            //自定义参数
            requestJson.put("extend", "");
            //发送记录集合
            JSONArray records = new JSONArray();
            JSONObject record = new JSONObject();
            //手机号
            record.put("mobile", phonenumber);
            //替换变量
            JSONObject param = new JSONObject();
            param.put("valid_code", yzm);
            record.put("tpContent", param);
            records.put(record);
            requestJson.put("records", records);

            RequestBody requestBody = RequestBody.create(requestJson.toString(), JSON_MEDIA_TYPE);

            Request request = new Request.Builder()
                    .url(BASE_URL)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                Log.e(TAG, "Network request failed");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            Log.i(TAG, "Response: " + result);
            if (callback != null) {
                callback.onSmsSendComplete(result);
            }
        } else {
            Log.e(TAG, "Network request failed or result is null.");
            callback.onSmsSendComplete("false");
        }
    }
}
