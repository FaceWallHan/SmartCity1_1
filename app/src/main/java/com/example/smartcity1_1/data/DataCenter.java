package com.example.smartcity1_1.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smartcity1_1.AppClient;


/**
 * SharedPreferences的一个操作类
 * */
public class DataCenter {
    private static final String FIRST_IS = "isfirst";

    private SharedPreferences sp;
    private SharedPreferences.Editor mEditor;

    private static DataCenter mSharedPreferencesManager;


    public synchronized static DataCenter getInstance() {
        if (mSharedPreferencesManager == null) {
            mSharedPreferencesManager = new DataCenter();
        }

        return mSharedPreferencesManager;
    }


    private DataCenter() {
        sp = AppClient.getInstance().getSharedPreferences("myapp", Context.MODE_PRIVATE);
        mEditor = sp.edit();
    }

    /***
     * 获取是否第一次进入
     */
    public boolean setIsFirst(boolean isfirst) {
        mEditor.putBoolean(FIRST_IS,isfirst );
        return mEditor.commit();
    }


    public boolean getIsFirst() {
        return sp.getBoolean(FIRST_IS, true);
    }


}
