package com.example.smartcity1_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartcity1_1.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.bt_setting).setOnClickListener(this);
        findViewById(R.id.bt_mian).setOnClickListener(this);
    }

    //不需要修改显示或者状态的控件没必要引用
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_setting:
                startActivity(new Intent(MainActivity.this,NetActivity.class));
                break;
            case R.id.bt_mian:
                startActivity(new Intent(MainActivity.this,AppMainActivity.class));
                finish();
                break;
        }
    }
}