package com.example.smartcity1_1.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity1_1.AppClient;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.util.Uitls;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/1 at 19:26
 */
public class NetActivity extends AppCompatActivity {
    private TextView title;
    private EditText etIp;
    private EditText etPort;
    private Button btSave;
    private Button btExit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_layout);
        initView();
        sharedPreferences = AppClient.sharedPreferences;
        etIp.setText(sharedPreferences.getString(AppClient.IP,"118.190.26.201"));
        etPort.setText(sharedPreferences.getString(AppClient.Port,"8080"));
        etPort.setText("8080");
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etIp.getText()) || TextUtils.isEmpty(etPort.getText())) {
                    Uitls.showDialog("Ip或端口号不能为空", NetActivity.this);
                    return;
                }
                sharedPreferences.edit().putString(AppClient.IP, etIp.getText().toString()).apply();
                sharedPreferences.edit().putString(AppClient.Port, etPort.getText().toString()).apply();
                Toast.makeText(NetActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("网络设置");
    }

    private void initView() {
        title = findViewById(R.id.title);
        etIp = findViewById(R.id.et_ip);
        etPort = findViewById(R.id.et_port);
        btSave = findViewById(R.id.bt_save);
        btExit = findViewById(R.id.bt_exit);
    }
}
