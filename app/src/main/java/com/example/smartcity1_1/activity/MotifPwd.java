package com.example.smartcity1_1.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.bean.User;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyTo;
import com.example.smartcity1_1.util.Uitls;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 10:55
 */
public class MotifPwd extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etStartPwd;
    private EditText etEndPwd;
    private EditText etEndPwd2;
    private TextView tvSave;
    private User user;
    private String yuanpwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motif_pwd);
        initView();
        user = (User) getIntent().getSerializableExtra("info");
        title.setText("修改密码");
        setVolley();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etStartPwd.getText().toString()) || TextUtils.isEmpty(etEndPwd.getText().toString())) {
                    Uitls.showDialog("密码不能为空", MotifPwd.this);
                    return;
                }
                String startPwd = etStartPwd.getText().toString();
                String pwd = etEndPwd.getText().toString();
                String pwd2 = etEndPwd2.getText().toString();
                if (!pwd.equals(pwd2)) {
                    Uitls.showDialog("两次密码不一致", MotifPwd.this);
                    return;
                }
                if (!yuanpwd.equals(startPwd)) {
                    Uitls.showDialog("原密码输入错误", MotifPwd.this);
                    return;
                }
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("setPwd")
                        .setJsonObject("username", user.getUsername())
                        .setJsonObject("pwd", pwd)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Toast.makeText(MotifPwd.this, "修改成功", Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();

            }
        });
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getPwd")
                .setJsonObject("username", user.getUsername())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        yuanpwd = jsonObject.optString("pwd");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        etStartPwd = findViewById(R.id.et_start_pwd);
        etEndPwd = findViewById(R.id.et_end_pwd);
        etEndPwd2 = findViewById(R.id.et_end_pwd2);
        tvSave = findViewById(R.id.tv_save);
    }
}
