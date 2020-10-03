package com.example.smartcity1_1.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity1_1.R;
import com.example.smartcity1_1.adapter.SspAdapter;
import com.example.smartcity1_1.util.OnUpDate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 11:18
 */
public class JyxcActivity extends AppCompatActivity {

    List<Bitmap> bitmaps = new ArrayList<>();
    SspAdapter adapter;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etMsg;
    private GridView girdView;
    private Button btSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jyxc_layout);
        initView();
        int id = getIntent().getIntExtra("index", 0);
        if (id == 0) {
            title.setText("建言献策");
            itemChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            title1.setText("查看留言");
            title1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(JyxcActivity.this, cKlyActivity.class));
                }
            });
        } else {
            title.setText("留言");
            itemChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmaps.clear();
                bitmaps.add(null);
                adapter.notifyDataSetChanged();
                etMsg.setText("");
                Toast.makeText(JyxcActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
            }
        });
        adapter = new SspAdapter(this, bitmaps);
        bitmaps.add(null);
        girdView.setAdapter(adapter);
        adapter.setOnUpDate(new OnUpDate() {
            @Override
            public void upDate(String name) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //系统常量， 启动相机的关键
                startActivityForResult(openCameraIntent, 1); // 参数常量为自定义的request code, 在取返回结果时有用
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    bitmaps.add(0, bm);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void initView() {

        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        etMsg = findViewById(R.id.et_msg);
        girdView = findViewById(R.id.gird_view);
        btSubmit = findViewById(R.id.bt_submit);
    }
}
