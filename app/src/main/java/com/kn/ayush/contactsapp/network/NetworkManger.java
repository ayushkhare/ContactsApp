package com.kn.ayush.contactsapp.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by ayush on 1/6/16
 */
public class NetworkManger {

    public NetworkManger() {
    }

    public JSONObject makeHttpRequest(String url, String method, HashMap<String, String> params) {
        String json = "";
        JSONObject jsonObj = null;
        StringBuilder sbParams = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0) {
                    sbParams.append("&");
                }
                sbParams.append(key).append("=").append(URLEncoder.encode(params.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }
        int status=0;

        // Note: probably better to use OkHttp (in future if have time)
        HttpURLConnection connection = null;
        try {
            URL urlObj;
            if (method.equals("POST")) {
                String paramsString = sbParams.toString();
                urlObj = new URL(url);
                connection = (HttpURLConnection) urlObj.openConnection(); //opening http connection to the required URL
                connection.setRequestMethod(method); //setting request method "POST"
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.connect();
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(paramsString);
                bufferedWriter.close();
                outputStream.close();
                status = connection.getResponseCode();

            }
            switch (status) {
                case 200:
                case 201:
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    json = stringBuilder.toString();
                    jsonObj = new JSONObject(json);
            }
        } catch (JSONException e) {
            Log.d("exception: ", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObj;
    }
}

