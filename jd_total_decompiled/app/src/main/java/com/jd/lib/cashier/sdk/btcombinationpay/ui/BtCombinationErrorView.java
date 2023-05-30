package com.jd.lib.cashier.sdk.btcombinationpay.ui;

import android.content.Context;
import android.text.TextUtils;
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
public class BtCombinationErrorView extends LinearLayout {
    private static final String TAG = "CashierErrorView";
    private TextView errorLoadingButton;
    private ImageView errorLoadingImage;
    private TextView tvErrorTips1;

    public BtCombinationErrorView(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.lib_cashier_sdk_bt_combination_error_view_layout, this);
        initView();
        initData();
    }

    private void initData() {
        this.errorLoadingImage.setBackgroundResource(R.drawable.lib_cashier_sdk_error_page_bg);
        this.errorLoadingButton.setText(R.string.lib_cashier_sdk_loading_error_again);
        this.tvErrorTips1.setText(R.string.lib_cashier_sdk_search_voice_network_error);
    }

    private void initView() {
        this.errorLoadingImage = (ImageView) findViewById(R.id.jd_tip_image);
        this.errorLoadingButton = (TextView) findViewById(R.id.jd_tip_button);
        this.tvErrorTips1 = (TextView) findViewById(R.id.jd_tip_tv1);
        onHandModeSkin();
    }

    public void onHandModeSkin() {
        try {
            setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2, JDDarkUtil.COLOR_161515));
            this.tvErrorTips1.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_BFBFBF));
            this.errorLoadingButton.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            if (JDDarkUtil.isDarkMode()) {
                this.errorLoadingButton.setBackgroundResource(R.drawable.lib_cashier_sdk_button_a_01_dark);
            } else {
                this.errorLoadingButton.setBackgroundResource(R.drawable.lib_cashier_sdk_button_a_01);
            }
        } catch (Exception e2) {
            r.d(TAG, e2.getMessage());
        }
    }

    public void setErrorButtonListener(View.OnClickListener onClickListener) {
        this.errorLoadingButton.setOnClickListener(onClickListener);
    }

    public void setMegText(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getContext().getString(R.string.lib_cashier_sdk_search_voice_network_error);
        }
        this.tvErrorTips1.setText(str);
    }

    public BtCombinationErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }
}
