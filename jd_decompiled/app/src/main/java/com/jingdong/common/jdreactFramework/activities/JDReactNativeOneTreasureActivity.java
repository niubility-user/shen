package com.jingdong.common.jdreactFramework.activities;

import android.widget.LinearLayout;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactExtendPackage;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;

/* loaded from: classes5.dex */
public class JDReactNativeOneTreasureActivity extends JDReactNativeBaseCommonActivity {
    private LinearLayout reactRootViewHolder;

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public JDReactModuleEntity getReactEntity() {
        return new JDReactModuleEntity(JDReactConstant.AVAILABILITY_ONE_TREASURE, JDReactConstant.AVAILABILITY_ONE_TREASURE, null);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public ReactPackage getReactPackage() {
        return new JDReactExtendPackage();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public void initView(ReactRootView reactRootView, String str, boolean z, String str2, String str3) {
        LinearLayout linearLayout = new LinearLayout(this);
        this.reactRootViewHolder = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        setContentView(this.reactRootViewHolder);
        this.reactRootViewHolder.addView(reactRootView, -1, -1);
    }
}
