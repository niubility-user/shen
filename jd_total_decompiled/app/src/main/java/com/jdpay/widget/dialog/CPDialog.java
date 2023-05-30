package com.jdpay.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jdpay.membercode.R;

/* loaded from: classes18.dex */
public class CPDialog extends Dialog {
    private int cancelButTxtColor;
    private boolean isCancelable;
    private Button mCancelBtn;
    private boolean mCancelBtnVisibal;
    private View.OnClickListener mCancelClick;
    private String mCancelStr;
    private Object mCancelTag;
    private LinearLayout mContentLayout;
    private Context mContext;
    private View mCustomView;
    private View.OnClickListener mDefaultCancelClick;
    private View.OnClickListener mDefaultOkClick;
    private String mMsg;
    private TextView mMsgTxt;
    private Button mOkBtn;
    private boolean mOkBtnVisibal;
    private View.OnClickListener mOkClick;
    private String mOkStr;
    private Object mOkTag;
    private CountDownTimer mTimer;
    private String mTitle;
    private View mTitleLayout;
    private TextView mTitleTxt;
    private int okButTxtColor;

    public CPDialog(Context context) {
        super(context, R.style.cp_dialog);
        this.mTitleLayout = null;
        this.mContentLayout = null;
        this.mCustomView = null;
        this.isCancelable = true;
        this.mTitleTxt = null;
        this.mOkBtnVisibal = false;
        this.mCancelBtnVisibal = false;
        this.okButTxtColor = -1;
        this.cancelButTxtColor = -1;
        this.mTimer = new CountDownTimer(2000L, 2000L) { // from class: com.jdpay.widget.dialog.CPDialog.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (CPDialog.this.isShowing()) {
                    CPDialog.this.dismiss();
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
            }
        };
        this.mDefaultOkClick = new View.OnClickListener() { // from class: com.jdpay.widget.dialog.CPDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CPDialog.this.mOkClick != null) {
                    CPDialog.this.mOkClick.onClick(view);
                }
                CPDialog.super.dismiss();
            }
        };
        this.mDefaultCancelClick = new View.OnClickListener() { // from class: com.jdpay.widget.dialog.CPDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CPDialog.this.mCancelClick != null) {
                    CPDialog.this.mCancelClick.onClick(view);
                }
                CPDialog.super.dismiss();
            }
        };
        this.mContext = context;
        this.mOkStr = context.getString(R.string.jdpay_common_ok);
        this.mCancelStr = this.mContext.getString(R.string.jdpay_common_cancel);
    }

    private void setLayout() {
        if (this.mCustomView != null) {
            this.mContentLayout.removeAllViews();
            this.mContentLayout.addView(this.mCustomView);
        }
        View findViewById = findViewById(R.id.view_splider);
        this.mCancelBtn.setText(this.mCancelStr);
        if (this.mCancelBtnVisibal) {
            this.mCancelBtn.setVisibility(0);
            this.mCancelBtn.setBackgroundResource(this.mOkBtnVisibal ? R.drawable.jdpay_cp_btn_left_light_bg : R.drawable.jdpay_cp_btn_bottom_light_bg);
            findViewById.setVisibility(this.mOkBtnVisibal ? 0 : 8);
        } else {
            this.mCancelBtn.setVisibility(8);
        }
        this.mOkBtn.setText(this.mOkStr);
        if (this.mOkBtnVisibal) {
            this.mOkBtn.setVisibility(0);
            this.mOkBtn.setBackgroundResource(this.mCancelBtnVisibal ? R.drawable.jdpay_cp_btn_right_light_bg : R.drawable.jdpay_cp_btn_bottom_light_bg);
            findViewById.setVisibility(this.mCancelBtnVisibal ? 0 : 8);
        } else {
            this.mOkBtn.setVisibility(8);
        }
        if (this.mCancelBtnVisibal || this.mOkBtnVisibal) {
            return;
        }
        if (this.isCancelable) {
            this.mTimer.start();
            setCancelable(true);
        }
        findViewById(R.id.btn_layout).setVisibility(8);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        super.cancel();
        CountDownTimer countDownTimer = this.mTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        Resources resources;
        int i2;
        Resources resources2;
        int i3;
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.jdpay_common_cp_dialog);
        setCancelable(false);
        this.mTitleLayout = findViewById(R.id.title);
        if (TextUtils.isEmpty(this.mTitle)) {
            this.mTitleLayout.setVisibility(8);
        } else {
            this.mTitleLayout.setVisibility(0);
        }
        TextView textView = (TextView) findViewById(R.id.title_text);
        this.mTitleTxt = textView;
        textView.setText(this.mTitle);
        this.mContentLayout = (LinearLayout) findViewById(R.id.layout_view);
        TextView textView2 = (TextView) findViewById(R.id.txt_msg);
        this.mMsgTxt = textView2;
        textView2.setText(this.mMsg);
        Button button = (Button) findViewById(R.id.jdpay_cpdialog_btn_cancel);
        this.mCancelBtn = button;
        if (this.cancelButTxtColor != -1) {
            resources = getContext().getResources();
            i2 = this.cancelButTxtColor;
        } else {
            resources = getContext().getResources();
            i2 = R.color.jdpay_common_main_color_red;
        }
        button.setTextColor(resources.getColor(i2));
        this.mCancelBtn.setOnClickListener(this.mDefaultCancelClick);
        Button button2 = (Button) findViewById(R.id.jdpay_cpdialog_btn_ok);
        this.mOkBtn = button2;
        if (this.okButTxtColor != -1) {
            resources2 = getContext().getResources();
            i3 = this.okButTxtColor;
        } else {
            resources2 = getContext().getResources();
            i3 = R.color.jdpay_common_main_color_red;
        }
        button2.setTextColor(resources2.getColor(i3));
        this.mOkBtn.setOnClickListener(this.mDefaultOkClick);
        setLayout();
        Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        double width = defaultDisplay.getWidth();
        Double.isNaN(width);
        attributes.width = (int) (width * 0.8d);
        getWindow().setAttributes(attributes);
        getWindow().setGravity(17);
    }

    public void setCancelBtnTexColor(int i2) {
        this.cancelButTxtColor = i2;
    }

    public CPDialog setCancelButton(String str, View.OnClickListener onClickListener, Object obj) {
        this.mCancelBtnVisibal = true;
        this.mCancelClick = onClickListener;
        this.mCancelTag = obj;
        if (!TextUtils.isEmpty(str)) {
            this.mCancelStr = str;
        }
        return this;
    }

    public CPDialog setMsg(String str) {
        this.mMsg = str;
        return this;
    }

    public void setNoButtonCancelable(boolean z) {
        this.isCancelable = z;
    }

    public CPDialog setOkButton(String str, View.OnClickListener onClickListener) {
        this.mOkBtnVisibal = true;
        this.mOkClick = onClickListener;
        if (!TextUtils.isEmpty(str)) {
            this.mOkStr = str;
        }
        return this;
    }

    public CPDialog setOkButton(String str, View.OnClickListener onClickListener, Object obj) {
        this.mOkBtnVisibal = true;
        this.mOkClick = onClickListener;
        this.mOkTag = obj;
        if (!TextUtils.isEmpty(str)) {
            this.mOkStr = str;
        }
        return this;
    }

    public CPDialog setTitle(String str) {
        this.mTitle = str;
        return this;
    }

    public CPDialog setView(View view) {
        this.mCustomView = view;
        return this;
    }

    public void setmOkBtnTexColor(int i2) {
        this.okButTxtColor = i2;
    }

    @Override // android.app.Dialog
    public void show() {
        if (isShowing()) {
            return;
        }
        super.show();
    }
}
