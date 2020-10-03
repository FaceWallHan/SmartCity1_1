package com.example.smartcity1_1.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity1_1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 15:50
 */
public class InputDialog extends AppCompatDialog {
    private RelativeLayout rlOutsideView;
    private LinearLayout rlInputdlgView;
    private RelativeLayout rlComment;
    private EditText etInputMessage;
    private ImageView ivConfirm;
    private TextView tvTest;

    public InputDialog(Context context) {
        super(context);
        initView();
        setLayout();
    }

    public interface OnTextSendListener {

        void onTextSend(String msg);

        void dismiss();
    }

    private OnTextSendListener mOnTextSendListener;

    public void setmOnTextSendListener(OnTextSendListener mOnTextSendListener) {
        this.mOnTextSendListener = mOnTextSendListener;
    }

    private int mLastDiff;


    private void initView() {
        setContentView(R.layout.dialog_input_text_msg);
        rlOutsideView = findViewById(R.id.rl_outside_view);
        rlInputdlgView = findViewById(R.id.rl_inputdlg_view);
        rlComment = findViewById(R.id.rl_comment);
        etInputMessage = findViewById(R.id.et_input_message);
        ivConfirm = findViewById(R.id.iv_confirm);
        tvTest = findViewById(R.id.tv_test);
        rlInputdlgView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                Rect r = new Rect();
                //获取当前界面可视部分
                InputDialog.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = InputDialog.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;

                if (heightDifference <= 0 && mLastDiff > 0) {
                    InputDialog.this.dismiss();
                }
                mLastDiff = heightDifference;
            }
        });
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
                    InputDialog.this.dismiss();
                return false;
            }
        });
        ivConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = etInputMessage.getText().toString().trim();

                if (!TextUtils.isEmpty(msg)) {
                    mOnTextSendListener.onTextSend(msg);
                    InputDialog.this.dismiss();
                } else {
                    Toast.makeText(getContext(), "请输入文字", Toast.LENGTH_LONG).show();
//                Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
        setCancelable(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}
