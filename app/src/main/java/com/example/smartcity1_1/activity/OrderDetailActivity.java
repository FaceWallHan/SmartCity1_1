package com.example.smartcity1_1.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.adapter.OrderDetailsAdapter;
import com.example.smartcity1_1.bean.OrderDetail;
import com.example.smartcity1_1.bean.OrderTitle;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 11:32
 */
public class OrderDetailActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private TextView tvDdh;
    private TextView tvTime;
    private TextView tvMoney;
    private OrderTitle orderTitle;
    private List<OrderDetail> orderDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_layout);
        initView();
        orderTitle = (OrderTitle) getIntent().getSerializableExtra("info");
        setVolley();

    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getOrderById")
                .setJsonObject("id", orderTitle.getId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        orderDetails = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<OrderDetail>>() {
                                }.getType());
                        setLstView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setLstView() {
        listView.setAdapter(new OrderDetailsAdapter(this, orderDetails));
        tvTime.setText("交易日期：" + orderTitle.getDate());
        tvDdh.setText("订单号：" + orderTitle.getId());
        tvMoney.setText("总金额：" + orderTitle.getCost());
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        listView = findViewById(R.id.list_view);
        tvDdh = findViewById(R.id.tv_ddh);
        tvTime = findViewById(R.id.tv_time);
        tvMoney = findViewById(R.id.tv_money);
    }
}
