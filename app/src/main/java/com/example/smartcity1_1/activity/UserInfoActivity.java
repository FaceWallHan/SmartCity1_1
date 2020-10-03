package com.example.smartcity1_1.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.AppClient;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.bean.User;
import com.example.smartcity1_1.fragment.MyCenterFragment;
import com.example.smartcity1_1.net.VolleyImage;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyLoImage;
import com.example.smartcity1_1.net.VolleyTo;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 10:28
 */
public class UserInfoActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout lauyoutPhoto;
    private LinearLayout lauyoutName;
    private TextView tvNick;
    private LinearLayout lauyoutSex;
    private TextView tvSex;
    private LinearLayout lauyoutTel;
    private TextView tvTel;
    private LinearLayout lauyoutId;
    private TextView tvId;
    private TextView tvSave;
    private User user;
    private ImageView ivPhoto;

    public static void actionStart(Context context, User info) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra("info", info);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_layout);
        initView();
        title.setText("个人信息");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        user = (User) getIntent().getSerializableExtra("info");
        tvNick.setText(user.getNickname());
        tvTel.setText(user.getTel());
        tvSex.setText(user.getSex());
        StringBuilder stringBuilder = new StringBuilder("370502200009111613");
        stringBuilder.replace(2, 14, "***************");
        tvId.setText(stringBuilder.toString());
        VolleyImage volleyImage = new VolleyImage();
        volleyImage.setUrl(user.getImage())
                .setVolleyLoImage(new VolleyLoImage() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ivPhoto.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        lauyoutName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, MotifNick.class);
                intent.putExtra("info", tvNick.getText().toString());
                intent.putExtra("title", "昵称");
                startActivityForResult(intent, 1);
            }
        });
        lauyoutSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, MotifSex.class);
                intent.putExtra("info", tvSex.getText().toString());
                intent.putExtra("title", "性别");
                startActivityForResult(intent, 2);
            }
        });
        lauyoutTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, MotifNick.class);
                intent.putExtra("info", tvTel.getText().toString());
                intent.putExtra("title", "电话");
                startActivityForResult(intent, 3);
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("setUserInfo")
                        //{"username":"","image":"","nickname":"","sex":"","tel":""} 只修改username 其他键值对值为空
                        .setJsonObject("username", AppClient.username)
                        .setJsonObject("nickname", tvNick.getText().toString())
                        .setJsonObject("image", "")
                        .setJsonObject("tel", tvTel.getText().toString())
                        .setJsonObject("sex", tvSex.getText().toString())
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Toast.makeText(UserInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(UserInfoActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    tvNick.setText(data.getStringExtra("date"));
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    tvSex.setText(data.getStringExtra("date"));
                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    tvTel.setText(data.getStringExtra("date"));
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        lauyoutPhoto = findViewById(R.id.lauyout_photo);
        lauyoutName = findViewById(R.id.lauyout_name);
        tvNick = findViewById(R.id.tv_nick);
        lauyoutSex = findViewById(R.id.lauyout_sex);
        tvSex = findViewById(R.id.tv_sex);
        lauyoutTel = findViewById(R.id.lauyout_tel);
        tvTel = findViewById(R.id.tv_tel);
        lauyoutId = findViewById(R.id.lauyout_id);
        tvId = findViewById(R.id.tv_id);
        tvSave = findViewById(R.id.tv_save);
        ivPhoto = findViewById(R.id.iv_photo);
    }
}
