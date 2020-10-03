package com.example.smartcity1_1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.adapter.HomeNewsAdapter;
import com.example.smartcity1_1.bean.NewsList;
import com.example.smartcity1_1.fragment.HomeFragment;
import com.example.smartcity1_1.net.VolleyImage;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyLoImage;
import com.example.smartcity1_1.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 10:14
 */
public class DjdtActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout typeLayout;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.djdt_layout);
        initView();
        title.setText("党建动态");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVolley_NewsList();
    }

    List<NewsList> newsLists;

    private void setVolley_NewsList() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNEWsList")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newsLists = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<NewsList>>() {
                                }.getType());
                        setNewType();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    Map<String, String> map;

    private void setNewType() {
        map = new HashMap<>();
        NewsList newsList = new NewsList();
        newsList.setType("推荐");
        newsLists.add(0, newsList);
        for (int i = 0; i < newsLists.size(); i++) {
            map.put(newsLists.get(i).getType(), newsLists.get(i).getType());
        }
        int j = 0;
        for (String string : map.values()) {
            final TextView textView = new TextView(DjdtActivity.this);
            textView.setText(string);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            final int finalJ = j;
            if (finalJ == 0) {
                textView.setTextColor(Color.RED);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < typeLayout.getChildCount(); i++) {
                        TextView textView = (TextView) typeLayout.getChildAt(i);
                        if (i == finalJ) {
                            textView.setTextColor(Color.RED);
                        } else {
                            textView.setTextColor(Color.BLACK);
                        }
                    }
                    String type = textView.getText().toString();
                    setListView(type);
                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(40, 0, 40, 0);
            textView.setLayoutParams(layoutParams);
            typeLayout.addView(textView);
            j++;
        }
        setListView("推荐");
    }

    private void setListView(String type) {
        final List<NewsList> newsListList = new ArrayList<>();
        for (int j = 0; j < newsLists.size(); j++) {
            NewsList newsList1 = newsLists.get(j);
            if (type.equals("推荐")) {
                if (newsList1.getFlag() == 1) {
                    newsListList.add(newsList1);
                }
            } else {
                if (newsList1.getType().equals(type)) {
                    newsListList.add(newsList1);
                }
            }
        }
        listView.setAdapter(new HomeNewsAdapter(DjdtActivity.this, newsListList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DjdtActivity.this, ReadNewActivity.class);
                intent.putExtra("info",newsListList.get(position));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        typeLayout = findViewById(R.id.type_layout);
        listView = findViewById(R.id.list_view);
    }
}
