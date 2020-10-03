package com.example.smartcity1_1.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.AppClient;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.activity.CommitActivity;
import com.example.smartcity1_1.activity.MotifPwd;
import com.example.smartcity1_1.activity.OrderActivity;
import com.example.smartcity1_1.activity.UserInfoActivity;
import com.example.smartcity1_1.bean.User;
import com.example.smartcity1_1.net.VolleyImage;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyLoImage;
import com.example.smartcity1_1.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 10:12
 */
public class MyCenterFragment extends Fragment {
    private ImageView ivImage;
    private TextView tvName;
    private TextView tvTel;
    private LinearLayout lauyoutInfo;
    private LinearLayout layoutOrder;
    private LinearLayout layoutPwd;
    private LinearLayout layoutSubmit;
    private TextView tvExit;
    private List<User> users;
    private int index = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_center_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        lauyoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoActivity.actionStart(getContext(), users.get(index));
            }
        });
        layoutPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MotifPwd.class);
                intent.putExtra("info", users.get(index));
                startActivity(intent);
            }
        });
        layoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OrderActivity.class);
                intent.putExtra("info", users.get(index));
                startActivity(intent);
            }
        });
        layoutSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CommitActivity.class);
                intent.putExtra("info", users.get(index));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setVolley();

    }

    public void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        users = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<User>>() {
                                }.getType());
                        setMyInfo();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setMyInfo() {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(AppClient.username)) {
                index = i;
                tvName.setText(user.getUsername());
                tvTel.setText(user.getTel());
                VolleyImage volleyImage = new VolleyImage();
                volleyImage.setUrl(user.getImage())
                        .setVolleyLoImage(new VolleyLoImage() {
                            @Override
                            public void onResponse(Bitmap bitmap) {
                                ivImage.setImageBitmap(bitmap);
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
                return;
            }
        }
    }

    private void initView() {
        ivImage = getView().findViewById(R.id.iv_image);
        tvName = getView().findViewById(R.id.tv_name);
        tvTel = getView().findViewById(R.id.tv_tel);
        lauyoutInfo = getView().findViewById(R.id.lauyout_info);
        layoutOrder = getView().findViewById(R.id.layout_order);
        layoutPwd = getView().findViewById(R.id.layout_pwd);
        layoutSubmit = getView().findViewById(R.id.layout_submit);
        tvExit = getView().findViewById(R.id.tv_exit);
    }
}
