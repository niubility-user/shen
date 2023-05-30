package com.jingdong.common.jdreactFramework.activities;

import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactExtendPackage;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;

/* loaded from: classes5.dex */
public class JDReactNativeLivingPayActivity extends JDReactNativeBaseCommonActivity {
    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public JDReactModuleEntity getReactEntity() {
        return new JDReactModuleEntity(JDReactConstant.AVAILABILITY_LIVING_PAY, JDReactConstant.AVAILABILITY_LIVING_PAY, null);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public ReactPackage getReactPackage() {
        return new JDReactExtendPackage();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public void initView(ReactRootView reactRootView, String str, boolean z, String str2, String str3) {
        setContentView(reactRootView);
    }
}
