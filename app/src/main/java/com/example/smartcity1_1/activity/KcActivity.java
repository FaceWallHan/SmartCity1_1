package com.example.smartcity1_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity1_1.R;
import com.example.smartcity1_1.adapter.KcAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 10:52
 */
public class KcActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private GridView girdView;
    private List<String> strings;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kc_layout);
        initView();
        title.setText(getIntent().getStringExtra("info"));
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        strings = new ArrayList<>();
        strings.add("01  前言");
        strings.add("02  马克思主义");
        strings.add("03  马克思主义为什么要中国化");
        strings.add("04  从站起来，到富起来");
        strings.add("05  毛泽东思想");
        strings.add("06  邓小平理论");
        strings.add("07  实事求是");
        girdView.setAdapter(new KcAdapter(this,strings));
        girdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(KcActivity.this, LookKcActivity.class);
                intent.putExtra("info", strings.get(position));
                startActivity(intent);
            }
        });

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        girdView = findViewById(R.id.gird_view);
    }
}
