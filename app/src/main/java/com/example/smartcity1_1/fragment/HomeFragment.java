package com.example.smartcity1_1.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
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
import com.example.smartcity1_1.activity.ReadNewActivity;
import com.example.smartcity1_1.adapter.HomeServiceAdapter;
import com.example.smartcity1_1.adapter.SubjectAdapter;
import com.example.smartcity1_1.bean.GetAllSubject;
import com.example.smartcity1_1.bean.HomePhoto;
import com.example.smartcity1_1.bean.NewsList;
import com.example.smartcity1_1.bean.ServiceHome;
import com.example.smartcity1_1.net.VolleyImage;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyLoImage;
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
 * @Create by 张瀛煜 on 2020/10/1 at 20:04
 */
public class HomeFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private GridView girdService;
    private GridView girdTheme;
    private LinearLayout layoutNew;
    List<HomePhoto> homePhotos;
    List<ServiceHome> serviceHomes;
    GetAllSubject allSubject;
    private LinearLayout newsLayout;
    private AppMainActivity appMainActivity;

    public HomeFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }


    List<ImageView> imageViews;
    List<NewsList> newsLists;

    Map<String, String> map = new HashMap<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        imageViews = new ArrayList<>();
        setVolley_Image();
        setVolley_Service();
        setVolley_Subject();
        setVolley_NewsList();
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

    private void setNewType() {
        NewsList newsList = new NewsList();
        newsList.setType("推荐");
        newsLists.add(0, newsList);
        for (int i = 0; i < newsLists.size(); i++) {
            map.put(newsLists.get(i).getType(), newsLists.get(i).getType());
        }
        int j = 0;
        for (String string : map.values()) {
            final TextView textView = new TextView(appMainActivity);
            textView.setText(string);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            final int finalJ = j;
            if (finalJ == 0) {
                textView.setTextColor(Color.RED);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < layoutNew.getChildCount(); i++) {
                        TextView textView = (TextView) layoutNew.getChildAt(i);
                        if (i == finalJ) {
                            textView.setTextColor(Color.RED);
                        } else {
                            textView.setTextColor(Color.BLACK);
                        }
                    }
                    String type = textView.getText().toString();
                    setLayoutType(type);
                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(20, 0, 20, 0);
            textView.setLayoutParams(layoutParams);
            layoutNew.addView(textView);
            j++;
        }
        setLayoutType("推荐");
    }

    private void setLayoutType(String type) {
        List<NewsList> newsListList = new ArrayList<>();
        for (int j = 0; j < newsLists.size(); j++) {
            NewsList newsList1 = newsLists.get(j);
            if (type.equals("推荐")) {
                if (newsList1.getFlag() == 1) {
                    newsListList.add(newsList1);
                }
            } else {
                if (newsList1.getType().equals(type)) {
                    newsListList.add(newsList1);
                }
            }
        }
        newsLayout.removeAllViews();
        for (int j = 0; j < newsListList.size(); j++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.news_item, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(layoutParams);
            final ViewHolder holder = new ViewHolder();
            holder.itemImage = view.findViewById(R.id.item_image);
            holder.itemTitle = view.findViewById(R.id.item_title);
            holder.itemContext = view.findViewById(R.id.item_context);
            holder.itemMsg = view.findViewById(R.id.item_msg);
            final NewsList newsList = newsListList.get(j);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(appMainActivity, ReadNewActivity.class);
                    intent.putExtra("info", newsList);
                    startActivity(intent);
                }
            });
            VolleyImage volleyImage = new VolleyImage();
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
            holder.itemTitle.setText(newsList.getTitle());
            holder.itemContext.setText(newsList.getContent());
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("getCommitById")
                    .setJsonObject("id",newsList.getId())
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                            int all = jsonArray.length();
                            holder.itemMsg.setText("总评：" +all + "发布日期：" + newsList.getPublicTime());
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();

            newsLayout.addView(view);
        }
    }

    static class ViewHolder {

        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemContext;
        private TextView itemMsg;
    }


    private void setVolley_Subject() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllSubject")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        allSubject = new Gson().fromJson(jsonObject.toString(), GetAllSubject.class);
                        List<String> strings = new ArrayList<>();
                        String jsonArray[] = jsonObject.optString("ROWS_DETAIL").replace("[", "").replace("]", "").split(",");
                        for (int i = 0; i < jsonArray.length; i++) {
                            strings.add(jsonArray[i].trim());
                        }
                        girdTheme.setAdapter(new SubjectAdapter(appMainActivity, strings));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
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
                        HomeServiceAdapter adapter = new HomeServiceAdapter(getContext(), serviceHomes);
                        adapter.setOnUpDate(new OnUpDate() {
                            @Override
                            public void upDate(String name) {
                                if (name.equals("更多服务")) {
                                    appMainActivity.replaceService();
                                } else if (name.equals("新闻中心")) {
                                    appMainActivity.replaceNews();
                                }
                            }
                        });
                        girdService.setAdapter(adapter);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_Image() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getImages")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        homePhotos = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<HomePhoto>>() {
                                }.getType());
                        setViewImage();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setViewImage() {
        for (int i = 0; i < homePhotos.size(); i++) {
            VolleyImage volleyImage = new VolleyImage();
            final int finalI = i;
            volleyImage.setUrl(homePhotos.get(i).getPath())
                    .setVolleyLoImage(new VolleyLoImage() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            ImageView imageView = new ImageView(appMainActivity);
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            imageView.setImageBitmap(bitmap);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageViews.add(imageView);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(appMainActivity, ReadNewActivity.class);
                                    intent.putExtra("id", homePhotos.get(finalI).getNewid());
                                    startActivity(intent);
                                }
                            });
                            if (imageViews.size() == 5) {
                                for (int i = 0; i < imageViews.size(); i++) {
                                    viewFlipper.addView(imageViews.get(i));
                                }
                                viewFlipper.startFlipping();
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
    }

    private void initView() {
        viewFlipper = getView().findViewById(R.id.view_flipper);
        girdService = getView().findViewById(R.id.gird_service);
        girdTheme = getView().findViewById(R.id.gird_theme);
        layoutNew = getView().findViewById(R.id.layout_new);
        newsLayout = getView().findViewById(R.id.news_layout);
    }
}
