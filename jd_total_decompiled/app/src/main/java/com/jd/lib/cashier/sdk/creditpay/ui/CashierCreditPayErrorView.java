package com.jd.lib.cashier.sdk.creditpay.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public class CashierCreditPayErrorView extends LinearLayout {
    private static final String TAG = "CashierCreditPayErrorView";
    private ImageView errorLoadingImage;
    private TextView tvErrorTips1;

    public CashierCreditPayErrorView(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.lib_cashier_sdk_credit_pay_error_page_layout, this);
        initView();
        initData();
        onHandSkinMode();
    }

    private void initData() {
        this.errorLoadingImage.setBackgroundResource(R.drawable.lib_cashier_sdk_error_page_bg);
        this.tvErrorTips1.setText(R.string.lib_cashier_sdk_search_voice_network_error);
    }

    private void initView() {
        this.errorLoadingImage = (ImageView) findViewById(R.id.lib_cashier_credit_pay_error_tip_image);
        this.tvErrorTips1 = (TextView) findViewById(R.id.lib_cashier_credit_pay_error_tip_tv);
    }

    public void onHandSkinMode() {
        try {
            setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F5F5F5, JDDarkUtil.COLOR_141212));
            this.tvErrorTips1.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_BFBFBF));
        } catch (Exception e2) {
            r.d(TAG, e2.getMessage());
        }
    }

    public void onShowErrorMsg(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.tvErrorTips1.setText(str);
    }

    public CashierCreditPayErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }
}
