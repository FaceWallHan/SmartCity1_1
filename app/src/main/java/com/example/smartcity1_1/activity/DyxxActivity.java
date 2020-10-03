package com.example.smartcity1_1.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity1_1.R;
import com.example.smartcity1_1.fragment.AllClassFragement;
import com.example.smartcity1_1.fragment.MyClassFragemnt;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 10:22
 */
public class DyxxActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private BottomNavigationView bottomNaviga;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dyxx_layout);
        initView();
        title.setText("党员学习");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout2, new AllClassFragement()).commit();
        bottomNaviga.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.all_class:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout2, new AllClassFragement()).commit();
                        break;
                    case R.id.my_class:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout2, new MyClassFragemnt()).commit();
                        break;
                }
                return true;
            }
        });
    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        bottomNaviga = findViewById(R.id.bottom_naviga);
    }
}
