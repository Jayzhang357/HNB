package com.wl.getonl;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.wl.entry.ResponseData2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;

public class GetDeviceLocation extends AsyncTask<Void, Void, String> {
    private static final String TAG = "SmsSender";
    private static final String BASE_URL = "https://api.mix2.zthysms.com/v2/sendSmsTp";
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private String serialnumber;

    public GetDeviceLocation(String DeviceID) {

        InputDeviceID inputDeviceID = new InputDeviceID();
        inputDeviceID.setDeviceID(DeviceID);

        // 将输入参数序列化为JSON字符串
        this.serialnumber = new Gson().toJson(inputDeviceID);
        Log.e("複製",this.serialnumber);
    }
    public class InputDeviceID {
        private String serialnumber;

        public String getDeviceID() {
            return serialnumber;
        }

        public void setDeviceID(String DeviceID) {
            this.serialnumber = DeviceID;
        }
    }

    public class InputParams {
        private String orgid;

        public String getorgid() {
            return orgid;
        }

        public void setorgid(String orgid) {
            this.orgid = orgid;
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {


            long time = System.currentTimeMillis();

                URL url = new URL("http://rinotrack.unistrong.com/v1.0/webapi/generateSign2?accessKeyId=75a7759acd1c4a228f188846b3dade5a&accessKeySecret=k0Yac7Cu7wy1BGr9D9gujDrtoCh7p621ak4zE1YTzWI=&timestamp=" + time);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // 设置请求方法为POST
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // 发送请求
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(serialnumber.getBytes());
                outputStream.flush();
                outputStream.close();

                // 获取响应
                int responseCode = connection.getResponseCode();
                // 可选：设置请求头、设置超时时间等

                // 获取响应码

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // 读取响应内容
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    reader.close();

                    String response = stringBuilder.toString();
                    Gson gson = new Gson();
                    ResponseData2 responseDataArray = gson.fromJson(response, ResponseData2.class);


                    Log.e("複製", response);
                    Log.e("複製", responseDataArray.data);
                    String urlstr="http://rinotrack.unistrong.com/v1.0/webapi/device/getDeviceLocation?sign=" +     responseDataArray.data + "&accessKeyId=75a7759acd1c4a228f188846b3dade5a&timestamp=" + time;



                    url = new URL(urlstr);

                    Log.e("複製", urlstr);

                    connection = (HttpURLConnection) url.openConnection();

                    // 设置请求方法为POST
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);


                    // 发送请求
                    outputStream = connection.getOutputStream();
                    outputStream.write(serialnumber.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // 读取响应内容
                        inputStream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        stringBuilder = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                        reader.close();

                        response = stringBuilder.toString();
                        Log.e("複製", response);

                        // 在UI线程中处理响应内容
                        String finalResponse = response;

                    } else {
                        // 处理请求失败
                    }
                    connection.disconnect();
                }

        }catch (IOException e) {
                e.printStackTrace();
                // 处理异常
            }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            Log.i(TAG, "Response: " + result);
        } else {
            Log.e(TAG, "Network request failed or result is null.");
        }
    }
}
