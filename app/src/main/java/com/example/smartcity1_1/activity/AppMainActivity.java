package com.example.smartcity1_1.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity1_1.R;
import com.example.smartcity1_1.fragment.AllServiceFragemnt;
import com.example.smartcity1_1.fragment.HomeFragment;
import com.example.smartcity1_1.fragment.MyCenterFragment;
import com.example.smartcity1_1.fragment.NewsFragment;
import com.example.smartcity1_1.fragment.SmartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/1 at 19:37
 */
public class AppMainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNav;
    private LinearLayout titleLayout;
    private EditText etSearch;
    private TextView tvTitle;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appmain_layout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment(this)).commit();

        bottomNav.setLabelVisibilityMode(1);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment(AppMainActivity.this)).commit();
                        titleLayout.setVisibility(View.VISIBLE);
                        tvTitle.setText("智慧城市");
                        break;
                    case R.id.action_service:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new AllServiceFragemnt(AppMainActivity.this)).commit();
                        titleLayout.setVisibility(View.GONE);
                        tvTitle.setText("智慧城市");
                        break;
                    case R.id.action_center:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MyCenterFragment()).commit();
                        titleLayout.setVisibility(View.VISIBLE);
                        tvTitle.setText("智慧城市");
                        break;
                    case R.id.action_new:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NewsFragment(AppMainActivity.this)).commit();
                        titleLayout.setVisibility(View.GONE);
                        tvTitle.setText("智慧城市");
                        break;
                    case R.id.action_dj:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new SmartFragment(AppMainActivity.this)).commit();
                        titleLayout.setVisibility(View.VISIBLE);
                        tvTitle.setText("智慧党建");
                        break;
                }
                return true;
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode()) {
                    switch (event.getAction()) {
                        case KeyEvent.ACTION_UP:
                            Intent intent = new Intent(AppMainActivity.this, SerachNewsActivity.class);
                            intent.putExtra("info", etSearch.getText().toString());
                            startActivity(intent);
                            return true;
                        default:
                            return true;
                    }
                }
                return false;
            }
        });
    }

    public void replaceNews() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NewsFragment(AppMainActivity.this)).commit();
        titleLayout.setVisibility(View.GONE);
        bottomNav.setSelectedItemId(R.id.action_new);
    }

    public void replaceService() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new AllServiceFragemnt(AppMainActivity.this)).commit();
        titleLayout.setVisibility(View.GONE);
        bottomNav.setSelectedItemId(R.id.action_service);
    }


    private void initView() {
        frameLayout = findViewById(R.id.frame_layout);
        bottomNav = findViewById(R.id.bottom_nav);
        titleLayout = findViewById(R.id.title_layout);
        etSearch = findViewById(R.id.et_search);
        tvTitle = findViewById(R.id.tv_title);
    }
}
