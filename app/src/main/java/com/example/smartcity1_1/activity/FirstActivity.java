package com.example.smartcity1_1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity1_1.AppClient;
import com.example.smartcity1_1.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/1 at 18:57
 */
public class FirstActivity extends AppCompatActivity {
    private int arr[] = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};
    private ViewPager viewPager;
    private LinearLayout layout;
    List<ImageView> imageViews;
    private SharedPreferences sharedPreferences;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            startActivity(new Intent(FirstActivity.this, MainActivity.class));
            finish();
            return false;
        }
    });
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = AppClient.sharedPreferences;
        if  (sharedPreferences.getBoolean(AppClient.IsFist,true)){
            sharedPreferences.edit().putBoolean(AppClient.IsFist,false).apply();
        }else {
            handler.sendEmptyMessage(0);
        }
        setContentView(R.layout.first_layout);
        initView();
        imageViews = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            layoutParams.setMargins(20, 0, 20, 0);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(arr[i]);
            TextView textView = new TextView(this);
            textView.setLayoutParams(layoutParams);
            if (i == 0) {
                textView.setBackgroundResource(R.drawable.yuna_select);
            } else {
                textView.setBackgroundResource(R.drawable.yuna_noselect);
            }
            layout.addView(textView);
            imageViews.add(imageView);
        }
        imageViews.add(new ImageView(this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int j = 0; j < layout.getChildCount(); j++) {
                    TextView textView1 = (TextView) layout.getChildAt(j);
                    if (j == position) {
                        textView1.setBackgroundResource(R.drawable.yuna_select);
                    } else {
                        textView1.setBackgroundResource(R.drawable.yuna_noselect);
                    }
                }
                if (position == imageViews.size() - 1) {
                    startActivity(new Intent(FirstActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViews.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView = imageViews.get(position);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        });

    }

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        layout = findViewById(R.id.layout);
    }
}
