package com.example.smartcity1_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity1_1.R;
import com.example.smartcity1_1.fragment.CommenFragemtn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 11:33
 */
public class LjbmActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ImageView tvTitle;
    private TextView tvContent;
    private TextView tvLjbm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ljbm_layout);
        initView();
        title.setText("报名活动");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        title1.setText("评论");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LjbmActivity.this, JyxcActivity.class);
//                intent.putExtra("index",1);
//                startActivity(intent);
                CommenFragemtn fragemtn = new CommenFragemtn();
                fragemtn.show(getSupportFragmentManager(),"aa");
            }
        });
        tvTitle.setImageResource(getIntent().getIntExtra("info", R.mipmap.tuxiang5));
        tvContent.setText("     《智慧党建》献礼2021年建党100周年。\n" +
                "随着工作生活信息化、网络化、数据化持续加深，传统的党建工作方式对党员特别是年轻一代党员的吸引力和凝聚力在降低。智慧党建是运用信息化新技术，整合各方资源，更有效地加强组织管理，提高服务群众水平，扩大党在网络世界存在感和数字化影响力，提高党的执政能力，巩固党的执政基础的新平台、新模式、新形态。\n" +
                "通过智慧党建系统建设，主要解决党建宣传、学习、管理、资源等方面的基础问题。使党建管理从模糊走向精确，改变了以往手工操作、人工管理等存在的随意性、模糊性不足，通过量化计分、实时智能分析、自动提醒服务等实现党建管理的精确化、智能化、人文化和科学化。主要包括以下功能模块。\n" +
                "1.党建展示：首页幻灯片轮播图显示；\n" +
                "2.党建动态：包括文章展示、文章分类、文章评论等；\n" +
                "3.党员学习：包括课程分类、章节管理、学习课程记录标识、课程评论等，课程内容包括语音、视频等资源；\n" +
                "4.组织活动：包括活动展示、活动报名、活动留言等；\n" +
                "5.建言献策：包括提交问题以及建议给上级，查看留言等；\n" +
                "6.随手拍：包括随手拍照上传，发现身边的先进，反馈身边的问题，实现人人监督。\n" +
                "\n");
        tvLjbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LjbmActivity.this, "报名成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);
        tvLjbm = findViewById(R.id.tv_ljbm);
    }
}
