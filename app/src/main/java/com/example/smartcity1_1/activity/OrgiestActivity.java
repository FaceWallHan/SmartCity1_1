package com.example.smartcity1_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity1_1.R;
import com.example.smartcity1_1.adapter.MyStudyAdapter;
import com.example.smartcity1_1.bean.NewsList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 11:25
 */
public class OrgiestActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ImageView tvTitle;
    private ListView listView;
    List<NewsList> newsLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orgiest_layout);
        initView();
        title.setText("组织活动");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newsLists = new ArrayList<>();
        final NewsList newsList = new NewsList();
        newsList.setFlag(R.mipmap.tuxiang6);
        newsList.setTitle("周减脂挑战|Week1");
        newsList.setContent("报名时间：09.24-10.04");
        newsList.setPublicTime("热度：12,891");
        newsLists.add(newsList);
        NewsList newsList2 = new NewsList();
        newsList2.setTitle("周减脂挑战|Week2");
        newsList2.setContent("报名时间：10.24-10.30");
        newsList2.setPublicTime("热度：10,891");
        newsList2.setFlag(R.mipmap.tuxiang5);
        newsLists.add(newsList2);
        listView.setAdapter(new MyStudyAdapter(OrgiestActivity.this, newsLists));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrgiestActivity.this, LjbmActivity.class);
                intent.putExtra("info", newsLists.get(position).getFlag());
                startActivity(intent);
            }
        });
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrgiestActivity.this, LjbmActivity.class);
                intent.putExtra("info", R.mipmap.tuxiang5);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvTitle = findViewById(R.id.tv_title);
        listView = findViewById(R.id.list_view);
    }
}
