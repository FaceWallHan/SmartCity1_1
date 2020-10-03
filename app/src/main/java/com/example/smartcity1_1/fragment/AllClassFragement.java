package com.example.smartcity1_1.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity1_1.R;
import com.example.smartcity1_1.activity.DyxxActivity;
import com.example.smartcity1_1.activity.KcActivity;
import com.example.smartcity1_1.adapter.AllClassAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 10:29
 */
public class AllClassFragement extends Fragment {
    private LinearLayout typeLayout;
    private GridView gidrView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_class_fragment, container, false);


    }

    List<String> strings = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setLayoutType();
        strings.add("四六级火箭班");
        strings.add("人人用得上的思维导图");
        strings.add("四六级火箭班");
        strings.add("人人用得上的思维导图");
        strings.add("四六级火箭班");
        strings.add("人人用得上的思维导图");
        gidrView.setAdapter(new AllClassAdapter(getContext(), strings));
        gidrView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), KcActivity.class);
                intent.putExtra("info", strings.get(position));
                startActivity(intent);
            }
        });
    }

    private void setLayoutType() {
        int j = 0;
        String arr[] = {"精选", "VIP", "付费精品", "文学", "英语", "语文", "数学", "国学"};
        for (String string : arr) {
            final TextView textView = new TextView(getActivity());
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
                    for (int i = 0; i < typeLayout.getChildCount(); i++) {
                        TextView textView = (TextView) typeLayout.getChildAt(i);
                        if (i == finalJ) {
                            textView.setTextColor(Color.RED);
                        } else {
                            textView.setTextColor(Color.BLACK);
                        }
                    }
                    String type = textView.getText().toString();
                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(40, 0, 40, 0);
            textView.setLayoutParams(layoutParams);
            typeLayout.addView(textView);
            j++;
        }
    }

    private void initView() {
        typeLayout = getView().findViewById(R.id.type_layout);
        gidrView = getView().findViewById(R.id.gidr_view);
    }
}
