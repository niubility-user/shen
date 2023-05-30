package com.jingdong.common.jdreactFramework.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class JDReactShowErrorViewUtils {
    public ImageView llBtnErrorBack;
    public Activity mActivity;
    public ImageView mNoDataImage;
    public TextView mNoDataTV1;
    public TextView mNoDataTV2;
    public TextView mNoDataTV3;
    public RelativeLayout mNoDataView;
    public RelativeLayout mRl;
    public Button retryBut;
    View view;
    public String TAG = "ShowErrorViewUtils";
    int mode = 0;

    public JDReactShowErrorViewUtils(Activity activity, RelativeLayout relativeLayout) {
        this.mActivity = activity;
        this.view = LinearLayout.inflate(activity, R.layout.jdreact_jd_common_state_page_03, null);
        this.mNoDataView = new RelativeLayout(this.mActivity);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, DPIUtil.getHeight());
        layoutParams.addRule(13);
        this.mNoDataView.addView(this.view, layoutParams);
        if (relativeLayout != null) {
            relativeLayout.addView(this.mNoDataView, layoutParams);
        }
        initView();
        this.mNoDataView.setVisibility(8);
    }

    private View createErrorView(View.OnClickListener onClickListener) {
        OKLog.d(this.TAG, "createErrorView item ivew");
        View inflate = LinearLayout.inflate(this.mActivity, R.layout.jdreact_jd_common_state_page_03, null);
        ((ImageView) inflate.findViewById(R.id.jd_tip_image)).setBackgroundResource(R.drawable.jdreact_y_03);
        ((TextView) inflate.findViewById(R.id.jd_tip_tv1)).setText(R.string.jdreact_loading_error_tips_1);
        ((TextView) inflate.findViewById(R.id.jd_tip_tv2)).setText(R.string.jdreact_loading_error_tips_2);
        Button button = (Button) inflate.findViewById(R.id.jd_tip_button);
        button.setText(R.string.jdreact_loading_error_again);
        button.setOnClickListener(onClickListener);
        RelativeLayout relativeLayout = new RelativeLayout(this.mActivity);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, DPIUtil.getHeight());
        layoutParams.addRule(13);
        relativeLayout.addView(inflate, layoutParams);
        inflate.setVisibility(0);
        return relativeLayout;
    }

    private void initEmptyRetry() {
        OKLog.d(this.TAG, "createEmptyView has item view");
        this.mNoDataImage.setBackgroundResource(R.drawable.jdreact_y_04);
        this.mNoDataTV1.setText("\u4eba\u592a\u591a\u8bf7\u6c42\u8d85\u65f6");
        this.mNoDataTV2.setText("\u53bb\u5176\u4ed6\u5730\u65b9\u770b\u770b\u5427");
        this.retryBut.setVisibility(0);
    }

    private void initView() {
        this.llBtnErrorBack = (ImageView) this.mNoDataView.findViewById(R.id.llBtnErrorBack);
        this.mRl = (RelativeLayout) this.mNoDataView.findViewById(R.id.rlErrorTop);
        this.llBtnErrorBack.setImageDrawable(ContextCompat.getDrawable(this.mActivity, R.drawable.jdreact_common_title_back_selector));
        this.mNoDataImage = (ImageView) this.mNoDataView.findViewById(R.id.jd_tip_image);
        this.mNoDataTV1 = (TextView) this.mNoDataView.findViewById(R.id.jd_tip_tv1);
        this.mNoDataTV2 = (TextView) this.mNoDataView.findViewById(R.id.jd_tip_tv2);
        this.mNoDataTV3 = (TextView) this.mNoDataView.findViewById(R.id.jd_tip_tv3);
        this.retryBut = (Button) this.mNoDataView.findViewById(R.id.jd_tip_button);
        this.llBtnErrorBack.setVisibility(0);
        this.llBtnErrorBack.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.utils.JDReactShowErrorViewUtils.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.getId() == R.id.llBtnErrorBack) {
                    JDReactShowErrorViewUtils.this.mActivity.finish();
                }
            }
        });
        if (JDReactHelper.newInstance().getUIModeHelper() != null) {
            this.mode = JDReactHelper.newInstance().getUIModeHelper().getCurrentUIMode();
            setUIMode();
            if (this.mActivity instanceof LifecycleOwner) {
                DeepDarkChangeManager.getInstance().addDeepDarkChangeListener((LifecycleOwner) this.mActivity, new Observer<Integer>() { // from class: com.jingdong.common.jdreactFramework.utils.JDReactShowErrorViewUtils.2
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(@Nullable Integer num) {
                        JDReactShowErrorViewUtils.this.mode = num.intValue();
                        JDReactShowErrorViewUtils.this.setUIMode();
                        JDReactShowErrorViewUtils.this.view.invalidate();
                    }
                });
            }
        }
    }

    public View getErrorViewHasRetry(View.OnClickListener onClickListener) {
        initEmptyRetry();
        if (onClickListener != null) {
            this.retryBut.setOnClickListener(onClickListener);
            this.retryBut.setText(R.string.jdreact_loading_error_again);
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
            this.mNoDataImage.setBackgroundResource(R.drawable.jdreact_y_03);
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

    public void setUIMode() {
        if (this.mode == 0) {
            int color = this.mActivity.getResources().getColor(R.color.jdreact_c_8C8C8C);
            int color2 = this.mActivity.getResources().getColor(R.color.jdreact_c_BFBFBF);
            int color3 = this.mActivity.getResources().getColor(R.color.jdreact_c_FFFFFF);
            this.mNoDataView.setBackgroundColor(color3);
            this.mRl.setBackgroundDrawable(ContextCompat.getDrawable(this.mActivity, R.drawable.jdreact_bg_top));
            this.llBtnErrorBack.setImageDrawable(ContextCompat.getDrawable(this.mActivity, R.drawable.jdreact_common_title_back_selector));
            this.view.setBackgroundColor(color3);
            this.mNoDataTV1.setTextColor(color);
            this.mNoDataTV2.setTextColor(color2);
            this.mNoDataTV3.setTextColor(color2);
            return;
        }
        this.mNoDataView.setBackgroundColor(DeepDarkUtils.getDarkColor_FFFFFF());
        this.mRl.setBackgroundDrawable(ContextCompat.getDrawable(this.mActivity, R.drawable.jdreact_bg_top_black));
        this.llBtnErrorBack.setImageDrawable(ContextCompat.getDrawable(this.mActivity, R.drawable.jdreact_common_title_back_selector_black));
        this.view.setBackgroundColor(DeepDarkUtils.getDarkColor_FFFFFF());
        this.mNoDataTV1.setTextColor(DeepDarkUtils.getDarkColor_8C8C8C());
        this.mNoDataTV2.setTextColor(DeepDarkUtils.getDarkColor_BFBFBF());
        this.mNoDataTV3.setTextColor(DeepDarkUtils.getDarkColor_BFBFBF());
    }

    public JDReactShowErrorViewUtils(Activity activity, LinearLayout linearLayout) {
        this.mActivity = activity;
        View inflate = LinearLayout.inflate(activity, R.layout.jdreact_jd_common_state_page_03, null);
        this.view = inflate;
        ((TextView) inflate.findViewById(R.id.jd_tip_tv1)).setTextColor(activity.getResources().getColor(R.color.jdreact_c_8C8C8C));
        this.mNoDataView = new RelativeLayout(this.mActivity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        this.mNoDataView.addView(this.view, layoutParams2);
        linearLayout.addView(this.mNoDataView, layoutParams);
        initView();
    }
}
