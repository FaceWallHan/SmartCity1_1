package com.example.smartcity1_1.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.adapter.CommenFragementAdapter;
import com.example.smartcity1_1.bean.CommitList;
import com.example.smartcity1_1.bean.User;
import com.example.smartcity1_1.dialog.InputDialog;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyTo;
import com.example.smartcity1_1.util.Uitls;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 15:17
 */
public class CommenFragemtn extends BottomSheetDialogFragment {
    private ListView dialogBottomsheetRvLists;
    private RelativeLayout dialogBottomsheetRlTitle;
    private ImageView dialogBottomsheetIvClose;
    private RelativeLayout rlComment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.commen_fragemnt, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        dialogBottomsheetIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setVolley();
    }

    List<CommitList> commitLists;

    private void setVolley_Commit() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getCommitById")
                .setJsonObject("id", 1)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        commitLists = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<CommitList>>() {
                                }.getType());
                        setCommitView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<User> users;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        users = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<User>>() {
                                }.getType());
                        setVolley_Commit();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
    private CommenFragementAdapter adapter;

    private void setCommitView() {
        adapter = new CommenFragementAdapter(getContext(), commitLists, users);
        dialogBottomsheetRvLists.setAdapter(adapter);
        rlComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputDialog dialog = new InputDialog(getContext());
                dialog.show();
                dialog.setmOnTextSendListener(new InputDialog.OnTextSendListener() {
                    @Override
                    public void onTextSend(String msg) {
                        commitLists.add(0,new CommitList(1,msg, Uitls.simpleDate("yyyy-MM-dd HH:mm:ss",new Date())
                                ,"abc"));
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void dismiss() {

                    }
                });
            }
        });
        final BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) getView().getParent());


        //dialog滑动监听
        mDialogBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if (newState == BottomSheetBehavior.STATE_SETTLING) {
                    if (slideOffset <= -0.28) {
                        //当向下滑动时 值为负数
                        dismiss();
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                CommenFragemtn.this.slideOffset = slideOffset;//记录滑动值
            }
        });
    }

    private float slideOffset;

    private void initView() {
        dialogBottomsheetRvLists = getView().findViewById(R.id.dialog_bottomsheet_rv_lists);
        dialogBottomsheetRlTitle = getView().findViewById(R.id.dialog_bottomsheet_rl_title);
        dialogBottomsheetIvClose = getView().findViewById(R.id.dialog_bottomsheet_iv_close);
        rlComment = getView().findViewById(R.id.rl_comment);
    }
}
