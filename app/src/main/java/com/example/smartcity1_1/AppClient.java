package com.example.smartcity1_1;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/1 at 19:03
 */
public class AppClient extends Application {
    private static RequestQueue requestQueue;
    public static SharedPreferences sharedPreferences;
    public static final String IP = "Ip";
    public static final String Port = "Port";
    public static String username = "abc";
    public static final String IsFist = "isFirst";
    private static AppClient instance;

    public static AppClient getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        requestQueue = Volley.newRequestQueue(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static void add(JsonObjectRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }

    public static void add(ImageRequest imageRequest) {
        requestQueue.add(imageRequest);
    }
}
