package com.example.smartcity1_1.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.activity.AppMainActivity;
import com.example.smartcity1_1.adapter.AllServiceAdapter;
import com.example.smartcity1_1.bean.ServiceInfo;
import com.example.smartcity1_1.dialog.ShowSearchDialog;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyTo;
import com.example.smartcity1_1.util.OnUpDate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 11:45
 */
public class AllServiceFragemnt extends Fragment {
    private EditText etSearch;
    private ExpandableListView expandListView;
    private AppMainActivity appMainActivity;

    public AllServiceFragemnt(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_service_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley();
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode()) {
                    switch (event.getAction()) {
                        case KeyEvent.ACTION_UP:
                            ShowSearchDialog dialog = new ShowSearchDialog(etSearch.getText().toString(), appMainActivity);
                            dialog.show(getChildFragmentManager(), "aaa");
                            return true;
                        default:
                            return true;
                    }
                }
                return false;
            }
        });
    }

    private List<String> serviceType;
    private Map<String, List<ServiceInfo>> map;

    private void setVolley() {
        map = new HashMap<>();
        serviceType = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllServiceType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            serviceType.add(jsonArray.optString(i));
                        }
                        setVolley_Detail();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_Detail() {
        for (int i = 0; i < serviceType.size(); i++) {
            VolleyTo volleyTo = new VolleyTo();
            final int finalI = i;
            volleyTo.setUrl("getServiceByType")
                    .setJsonObject("type", serviceType.get(i))
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            List<ServiceInfo> serviceInfos = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                    , new TypeToken<List<ServiceInfo>>() {
                                    }.getType());
                            map.put(serviceType.get(finalI), serviceInfos);
                            if (map.size() == serviceType.size()) {
                                setExpandListView();
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
    }

    private void setExpandListView() {
        AllServiceAdapter allServiceAdapter = new AllServiceAdapter(serviceType, map);
        expandListView.setAdapter(allServiceAdapter);
        allServiceAdapter.setOnUpDate(new OnUpDate() {
            @Override
            public void upDate(String name) {
                appMainActivity.replaceNews();
            }
        });

    }

    private void initView() {
        etSearch = getView().findViewById(R.id.et_search);
        expandListView = getView().findViewById(R.id.expand_list_view);
        expandListView.setGroupIndicator(null);
    }
}
