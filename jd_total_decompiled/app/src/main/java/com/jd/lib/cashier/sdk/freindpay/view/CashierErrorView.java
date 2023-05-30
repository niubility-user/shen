package com.jd.lib.cashier.sdk.freindpay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public class CashierErrorView extends LinearLayout {
    private static final String TAG = "CashierErrorView";
    private TextView errorLoadingButton;
    private ImageView errorLoadingImage;
    private TextView errorOrderListButton;
    private TextView tvErrorTips1;

    public CashierErrorView(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.lib_cashier_sdk_error_view_layout, this);
        initView();
        initData();
        onHandModeSkin();
    }

    private void initData() {
        this.errorLoadingImage.setBackgroundResource(R.drawable.lib_cashier_sdk_error_page_bg);
        this.errorLoadingButton.setText(R.string.lib_cashier_sdk_loading_error_again);
        this.errorOrderListButton.setText(R.string.lib_cashier_sdk_octopus_pay_other_left_btn_text);
        this.tvErrorTips1.setText(R.string.lib_cashier_sdk_error_page_tip_info);
    }

    private void initView() {
        this.errorLoadingImage = (ImageView) findViewById(R.id.jd_tip_image);
        this.errorLoadingButton = (TextView) findViewById(R.id.jd_tip_button);
        this.errorOrderListButton = (TextView) findViewById(R.id.jd_order_list_button);
        this.tvErrorTips1 = (TextView) findViewById(R.id.jd_tip_tv1);
        onHandModeSkin();
    }

    public void onHandModeSkin() {
        try {
            setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F5F5F5, JDDarkUtil.COLOR_141212));
            this.tvErrorTips1.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_BFBFBF));
            this.errorLoadingButton.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19));
            this.errorOrderListButton.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19));
            if (JDDarkUtil.isDarkMode()) {
                TextView textView = this.errorLoadingButton;
                int i2 = R.drawable.lib_cashier_sdk_button_a_01_dark;
                textView.setBackgroundResource(i2);
                this.errorOrderListButton.setBackgroundResource(i2);
            } else {
                TextView textView2 = this.errorLoadingButton;
                int i3 = R.drawable.lib_cashier_sdk_button_a_01;
                textView2.setBackgroundResource(i3);
                this.errorOrderListButton.setBackgroundResource(i3);
            }
        } catch (Exception e2) {
            r.d(TAG, e2.getMessage());
        }
    }

    public void setErrorButtonListener(View.OnClickListener onClickListener) {
        this.errorLoadingButton.setOnClickListener(onClickListener);
    }

    public void setErrorOrderListButtonListener(View.OnClickListener onClickListener) {
        this.errorOrderListButton.setOnClickListener(onClickListener);
    }

    public CashierErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }
}
