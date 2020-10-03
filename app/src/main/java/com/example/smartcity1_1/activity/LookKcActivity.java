package com.example.smartcity1_1.activity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.smartcity1_1.R;
import com.example.smartcity1_1.fragment.CommenFragemtn;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 11:02
 */
public class LookKcActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private VideoView videoView;
    private LinearLayout layoutNew;
    private FloatingActionButton floatingButton;
    private TextView tvWd;
    private TextView tvBj;
    private TextView tvJc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_kc_layout);
        initView();
        title.setText(getIntent().getStringExtra("info"));
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car1));
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });
        tvBj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBj.setTextColor(Color.RED);
                tvJc.setTextColor(Color.BLACK);
                tvWd.setTextColor(Color.BLACK);
            }
        });
        tvJc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvJc.setTextColor(Color.RED);
                tvBj.setTextColor(Color.BLACK);
                tvWd.setTextColor(Color.BLACK);
            }
        });
        tvWd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvWd.setTextColor(Color.RED);
                tvBj.setTextColor(Color.BLACK);
                tvJc.setTextColor(Color.BLACK);
            }
        });
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommenFragemtn fragemtn = new CommenFragemtn();
                fragemtn.show(getSupportFragmentManager(),"aaa");
            }
        });
    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        videoView = findViewById(R.id.video_view);
        layoutNew = findViewById(R.id.layout_new);
        floatingButton = findViewById(R.id.floating_button);
        tvWd = findViewById(R.id.tv_wd);
        tvBj = findViewById(R.id.tv_bj);
        tvJc = findViewById(R.id.tv_jc);
    }
}
