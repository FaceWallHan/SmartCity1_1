package com.example.smartcity1_1.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.activity.AppMainActivity;
import com.example.smartcity1_1.activity.DjdtActivity;
import com.example.smartcity1_1.activity.DyxxActivity;
import com.example.smartcity1_1.activity.JyxcActivity;
import com.example.smartcity1_1.activity.OrgiestActivity;
import com.example.smartcity1_1.activity.ReadNewActivity;
import com.example.smartcity1_1.activity.SspActivity;
import com.example.smartcity1_1.adapter.DjAdapter;
import com.example.smartcity1_1.bean.NewsList;
import com.example.smartcity1_1.net.VolleyImage;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyLoImage;
import com.example.smartcity1_1.net.VolleyTo;
import com.example.smartcity1_1.util.OnUpDate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 9:12
 */
public class SmartFragment extends Fragment {
    private AppMainActivity appMainActivity;
    private GridView girdView;
    private boolean isLoop = true;
    private List<View> views;
    private List<NewsList> newsLists;
    private ViewFlipper viewFlipper;

    public SmartFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_fragment, container, false);
    }

    private List<String> strings;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley_NewsList();
        strings = new ArrayList<>();
        strings.add("党建动态");
        strings.add("党员学习");
        strings.add("组织活动");
        strings.add("建言献策");
        strings.add("随手拍");
        strings.add("更多");
        DjAdapter adapter = new DjAdapter(appMainActivity, strings);
        girdView.setAdapter(adapter);
        adapter.setOnUpDate(new OnUpDate() {
            @Override
            public void upDate(String name) {
                switch (name) {
                    case "党建动态":
                        startActivity(new Intent(appMainActivity, DjdtActivity.class));
                        break;
                    case "党员学习":
                        startActivity(new Intent(appMainActivity, DyxxActivity.class));
                        break;
                    case "建言献策":
                        startActivity(new Intent(appMainActivity, JyxcActivity.class));
                        break;
                    case "组织活动":
                        startActivity(new Intent(appMainActivity, OrgiestActivity.class));
                        break;
                    case "随手拍":
                        startActivity(new Intent(appMainActivity, SspActivity.class));
                        break;
                }
            }
        });

    }


    private void setVolley_NewsList() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNEWsList")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newsLists = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<NewsList>>() {
                                }.getType());
                        setNewType();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    @Override
    public void onResume() {
        super.onResume();
        isLoop = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isLoop = false;
    }

    private void setNewType() {
        views = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            View view = LayoutInflater.from(appMainActivity).inflate(R.layout.dj_viewpager_item, null);
            final ViewHolder holder = new ViewHolder();
            holder.itemImage = view.findViewById(R.id.item_image);
            holder.itemTitle = view.findViewById(R.id.item_title);
            final NewsList newsList = newsLists.get(i);
            VolleyImage volleyImage = new VolleyImage();
            holder.itemTitle.setText(newsList.getTitle());
            volleyImage.setUrl(newsList.getImg())
                    .setVolleyLoImage(new VolleyLoImage() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.itemImage.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(appMainActivity, ReadNewActivity.class);
                    intent.putExtra("id", newsList.getId());
                    startActivity(intent);
                }
            });
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            views.add(view);
        }
        for (int i = 0; i < views.size(); i++) {
            viewFlipper.addView(views.get(i));
        }
        viewFlipper.startFlipping();
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            //viewPager.setCurrentItem(msg.what, msg.what == 0 ? false : true);
            return false;
        }
    });

    class CopyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }

    static class ViewHolder {

        private ImageView itemImage;
        private TextView itemTitle;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoop = false;
    }

    private void initView() {
        girdView = getView().findViewById(R.id.gird_view);
        viewFlipper = getView().findViewById(R.id.view_flipper);
    }
}
