package com.jingdong.common.sample.jshop.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class JshopShowErrorViewUtils {
    public String TAG = "ShowErrorViewUtils";
    public Activity mActivity;
    public ImageView mNoDataImage;
    public TextView mNoDataTV1;
    public TextView mNoDataTV2;
    public TextView mNoDataTV3;
    public RelativeLayout mNoDataView;
    public Button retryBut;

    public JshopShowErrorViewUtils(Activity activity, RelativeLayout relativeLayout) {
        this.mActivity = activity;
        View inflate = LinearLayout.inflate(activity, R.layout.c3, null);
        this.mNoDataView = new RelativeLayout(this.mActivity);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, DPIUtil.getHeight());
        layoutParams.addRule(13);
        this.mNoDataView.addView(inflate, layoutParams);
        if (relativeLayout != null) {
            relativeLayout.addView(this.mNoDataView, layoutParams);
        }
        initView();
        this.mNoDataView.setVisibility(8);
    }

    private View createErrorView(View.OnClickListener onClickListener) {
        Log.d(this.TAG, "createErrorView item ivew");
        View inflate = LinearLayout.inflate(this.mActivity, R.layout.c3, null);
        ((ImageView) inflate.findViewById(R.id.bz)).setBackgroundResource(R.drawable.y_03);
        ((TextView) inflate.findViewById(R.id.c0)).setText(R.string.h9);
        ((TextView) inflate.findViewById(R.id.c1)).setText(R.string.loading_error_tips_2);
        Button button = (Button) inflate.findViewById(R.id.bw);
        button.setText(R.string.a0f);
        button.setOnClickListener(onClickListener);
        RelativeLayout relativeLayout = new RelativeLayout(this.mActivity);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, DPIUtil.getHeight());
        layoutParams.addRule(13);
        relativeLayout.addView(inflate, layoutParams);
        inflate.setVisibility(0);
        return relativeLayout;
    }

    private void initEmptyRetry() {
        Log.d(this.TAG, "createEmptyView has item view");
        this.mNoDataImage.setBackgroundResource(R.drawable.y_04);
        this.mNoDataTV1.setText("\u4eba\u592a\u591a\u8bf7\u6c42\u8d85\u65f6");
        this.mNoDataTV2.setText("\u53bb\u5176\u4ed6\u5730\u65b9\u770b\u770b\u5427");
        this.retryBut.setVisibility(0);
    }

    private void initView() {
        this.mNoDataImage = (ImageView) this.mNoDataView.findViewById(R.id.bz);
        this.mNoDataTV1 = (TextView) this.mNoDataView.findViewById(R.id.c0);
        this.mNoDataTV2 = (TextView) this.mNoDataView.findViewById(R.id.c1);
        this.mNoDataTV3 = (TextView) this.mNoDataView.findViewById(R.id.c2);
        this.retryBut = (Button) this.mNoDataView.findViewById(R.id.bw);
        if (JshopTextViewUtils.IS_LARGE_MODE) {
            JshopTextViewUtils.getInstance().doTextFontScaled(this.mNoDataTV1, this.mNoDataTV2, this.mNoDataTV3, this.retryBut);
        }
    }

    public View getErrorViewHasRetry(View.OnClickListener onClickListener) {
        initEmptyRetry();
        if (onClickListener != null) {
            this.retryBut.setOnClickListener(onClickListener);
            this.retryBut.setText(R.string.a0f);
            this.retryBut.setVisibility(0);
            this.retryBut.setEnabled(true);
        } else {
            this.retryBut.setVisibility(4);
        }
        return this.mNoDataView;
    }

    public void hiddenErrorView() {
        RelativeLayout relativeLayout = this.mNoDataView;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
    }

    public void setButtonText(String str) {
        Button button = this.retryBut;
        if (button != null) {
            button.setText(str);
        }
    }

    public void setErrorImage(int i2) {
        if (i2 != -1) {
            this.mNoDataImage.setBackgroundResource(i2);
        } else {
            this.mNoDataImage.setBackgroundResource(R.drawable.y_03);
        }
    }

    public void setMessageInfo(String str, String str2, String str3) {
        if (this.mNoDataTV1 != null) {
            if (!TextUtils.isEmpty(str)) {
                this.mNoDataTV1.setText(str);
            } else {
                this.mNoDataTV1.setText("");
            }
        }
        if (this.mNoDataTV2 != null) {
            if (!TextUtils.isEmpty(str2)) {
                this.mNoDataTV2.setText(str2);
            } else {
                this.mNoDataTV2.setText("");
            }
        }
        if (this.mNoDataTV3 != null) {
            if (!TextUtils.isEmpty(str3)) {
                this.mNoDataTV3.setText(str3);
            } else {
                this.mNoDataTV3.setText("");
            }
        }
    }

    public JshopShowErrorViewUtils(Activity activity, LinearLayout linearLayout) {
        this.mActivity = activity;
        View inflate = LinearLayout.inflate(activity, R.layout.c3, null);
        ((TextView) inflate.findViewById(R.id.c0)).setTextColor(activity.getResources().getColor(R.color.e0));
        this.mNoDataView = new RelativeLayout(this.mActivity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, DPIUtil.getHeight());
        layoutParams.gravity = 17;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, DPIUtil.getHeight());
        layoutParams2.addRule(13);
        this.mNoDataView.addView(inflate, layoutParams2);
        linearLayout.addView(this.mNoDataView, layoutParams);
        initView();
    }
}
