package com.example.smartcity1_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.smartcity1_1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 10:38
 */
public class MyClassFragemnt extends Fragment {
    private BottomNavigationView topNav;
    private FrameLayout frameLayout3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_class_fragemnt, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getChildFragmentManager().beginTransaction().replace(R.id.frame_layout3, new MyStudyFragment()).commit();
        topNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.my_my_class:
                        getChildFragmentManager().beginTransaction().replace(R.id.frame_layout3, new MyStudyFragment()).commit();
                        break;
                    case R.id.my_all_class:
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        topNav = getView().findViewById(R.id.top_nav);
        frameLayout3 = getView().findViewById(R.id.frame_layout3);
    }
}
