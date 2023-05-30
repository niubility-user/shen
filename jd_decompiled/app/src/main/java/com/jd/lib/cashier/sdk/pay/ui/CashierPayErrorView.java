package com.jd.lib.cashier.sdk.pay.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;

/* loaded from: classes14.dex */
public class CashierPayErrorView extends LinearLayout {
    private Button errorLoadingButton;
    private ImageView errorLoadingImage;
    private TextView tvErrorTips1;
    private TextView tvErrorTips2;

    public CashierPayErrorView(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.lib_cashier_sdk_pay_error_page_layout, this);
        initView();
        initData();
    }

    private void initData() {
        this.errorLoadingImage.setBackgroundResource(R.drawable.lib_cashier_sdk_error_page_bg);
        this.errorLoadingButton.setText(R.string.lib_cashier_sdk_loading_error_again);
        this.tvErrorTips1.setText(R.string.lib_cashier_sdk_search_voice_network_error);
        this.tvErrorTips2.setText(R.string.lib_cashier_sdk_search_network_reload);
    }

    private void initView() {
        this.errorLoadingImage = (ImageView) findViewById(R.id.jd_tip_image);
        this.errorLoadingButton = (Button) findViewById(R.id.jd_tip_button);
        this.tvErrorTips1 = (TextView) findViewById(R.id.jd_tip_tv1);
        this.tvErrorTips2 = (TextView) findViewById(R.id.jd_tip_tv2);
    }

    public CashierPayErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }
}
