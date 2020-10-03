package com.example.smartcity1_1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.smartcity1_1.R;
import com.example.smartcity1_1.activity.KcActivity;
import com.example.smartcity1_1.adapter.MyStudyAdapter;
import com.example.smartcity1_1.bean.NewsList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 10:43
 */
public class MyStudyFragment extends Fragment {
    private ListView listView;
    private List<NewsList> newsLists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_study_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        newsLists = new ArrayList<>();
        final NewsList newsList = new NewsList();
        newsList.setFlag(R.mipmap.tuxiang1);
        newsList.setTitle("韩立英-毛泽东思想和中国特色社会主义理论体系");
        newsList.setContent("石家庄信息工程职业技术学院\r\n第二次开课");
        newsList.setPublicTime("2020-1-1-2020-10-1");
        newsLists.add(newsList);
        NewsList newsList2 = new NewsList();
        newsList2.setTitle("人人用的上的思维导图");
        newsList2.setContent("石家庄信息工程职业技术学院\r\n第二次开课");
        newsList2.setPublicTime("2020-1-1-2020-10-1");
        newsList2.setFlag(R.mipmap.tuxiang2);
        newsLists.add(newsList2);
        listView.setAdapter(new MyStudyAdapter(getContext(),newsLists));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), KcActivity.class);
                intent.putExtra("info",newsLists.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        listView = getView().findViewById(R.id.list_view);
    }
}
