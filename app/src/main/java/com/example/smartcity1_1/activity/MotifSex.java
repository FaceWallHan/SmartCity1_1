package com.example.smartcity1_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity1_1.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 8:48
 */
public class MotifSex extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private RadioButton rbMan;
    private RadioButton rbWoman;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nick_sex_layout);
        initView();
        title.setText(getIntent().getStringExtra("title"));
        if (getIntent().getStringExtra("info").equals("男")) {
            rbMan.setChecked(true);
        } else {
            rbWoman.setChecked(true);
        }
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title1.setText("完成");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("date", rbMan.isChecked() ? "男" : "女");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        rbMan = findViewById(R.id.rb_man);
        rbWoman = findViewById(R.id.rb_woman);
    }
}
