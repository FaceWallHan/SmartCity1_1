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
import com.example.smartcity1_1.data.DataCenter;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        //这是一种很推荐的写法 if中处理异常情况然后剩余部分去专心写逻辑
        if (!DataCenter.getInstance().getIsFirst()){
            startActivity(new Intent(FirstActivity.this, MainActivity.class));
            finish();//这样写还有好处就是不会出现你写的那种 第一个画面一闪而过的情况
            return;
        }
        DataCenter.getInstance().setIsFirst(false);//返回值是是否存入成功
        initView();
        imageViews = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            layoutParams.setMargins(20, 0, 20, 0);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setImageResource(arr[i]);//你既然用了imageview 加载图片就用    imageView.setImageResource(); 不然你用imageview的意义呢 这两种方式区别很大 backgroun 不能去控制管理 建议用src
           //看题目要求图片怎么显示 根据需求改scaletype
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageView textView = new ImageView(this); //textview 不加载文字的话就换imageview吧 还是不要用backgroun 理由同上
            textView.setLayoutParams(layoutParams);
            if (i == 0) {
                textView.setImageResource(R.drawable.yuna_select);
            } else {
                textView.setImageResource(R.drawable.yuna_noselect);
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
                    ImageView textView1 = (ImageView) layout.getChildAt(j);
                    if (j == position) {
                        textView1.setImageResource(R.drawable.yuna_select);
                    } else {
                        textView1.setImageResource(R.drawable.yuna_noselect);
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
