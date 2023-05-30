package com.jingdong.common.jdreactFramework.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.jingdong.app.mall.R;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactExtendPackage;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;

/* loaded from: classes5.dex */
public class JDReactNativeFlightOrderDetailActivity extends JDReactNativeBaseCommonActivity {
    private static final String TAG = "JDReactNativeFlightOrderDetailActivity";
    LinearLayout reactRootViewHolder;

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public int getLayoutResource() {
        return R.layout.reactnative_layout_flight_order_detail;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public JDReactModuleEntity getReactEntity() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        if (intent != null) {
            bundle.putString("orderCode", intent.getStringExtra("orderId"));
            bundle.putString("orderType", intent.getStringExtra("type"));
        }
        return new JDReactModuleEntity(JDReactConstant.AVAILABILITY_FLIGHTORDERDETAIL, JDReactConstant.AVAILABILITY_FLIGHTORDERDETAIL, bundle);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public ReactPackage getReactPackage() {
        return new JDReactExtendPackage();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public void initView(ReactRootView reactRootView, String str, boolean z, String str2, String str3) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.aba);
        this.reactRootViewHolder = linearLayout;
        linearLayout.addView(reactRootView, -1, -1);
    }
}
