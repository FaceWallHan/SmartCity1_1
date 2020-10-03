package com.example.smartcity1_1.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.activity.AppMainActivity;
import com.example.smartcity1_1.adapter.HomeServiceAdapter;
import com.example.smartcity1_1.adapter.SearchServiceAdapter;
import com.example.smartcity1_1.bean.ServiceHome;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyTo;
import com.example.smartcity1_1.util.OnUpDate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 15:22
 */
public class ShowSearchDialog extends DialogFragment {
    private String value;
    private TextView tvFinish;
    private List<ServiceHome> serviceHomes;
    private GridView girdView;
    private AppMainActivity appMainActivity;

    public ShowSearchDialog(String value, AppMainActivity appMainActivity) {
        this.value = value;
        this.appMainActivity = appMainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_dialog_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley_Service();

    }

    private void setVolley_Service() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getServiceInOrder")
                .setJsonObject("order", 1)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        serviceHomes = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<ServiceHome>>() {
                                }.getType());
                        setView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private List<ServiceHome> searchList;

    private void setView() {
        searchList = new ArrayList<>();
        for (int i = 0; i < serviceHomes.size(); i++) {
            ServiceHome serviceHome = serviceHomes.get(i);
            if (serviceHome.getName().contains(value)) {
                searchList.add(serviceHome);
            }
            Log.i("aaa", "setView: " + serviceHome.getName().contains(value));
        }
        SearchServiceAdapter searchServiceAdapter = new SearchServiceAdapter(getContext(), searchList);
        girdView.setAdapter(searchServiceAdapter);
        searchServiceAdapter.setOnUpDate(new OnUpDate() {
            @Override
            public void upDate(String name) {
                if (name.equals("更多服务")) {
                    appMainActivity.replaceService();
                } else if (name.equals("新闻中心")) {
                    appMainActivity.replaceNews();
                }
            }
        });
        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private void initView() {
        tvFinish = getView().findViewById(R.id.tv_finish);
        girdView = getView().findViewById(R.id.gird_view);
    }
}
