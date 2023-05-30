package com.jingdong.common.jdreactFramework.activities;

import android.os.Bundle;
import com.facebook.react.ReactPackage;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactExtendPackage;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;

/* loaded from: classes5.dex */
public class JDReactNativeJShopMineActivity extends JDReactNativeBaseCommonActivity {
    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public JDReactModuleEntity getReactEntity() {
        setbundleParam(JDReactConstant.AVAILABILITY_JSHOP_MINE, JDReactConstant.AVAILABILITY_JSHOP_MINE, true);
        return new JDReactModuleEntity(JDReactConstant.AVAILABILITY_JSHOP_MINE, JDReactConstant.AVAILABILITY_JSHOP_MINE, null);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public ReactPackage getReactPackage() {
        return new JDReactExtendPackage();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setUseBasePV(false);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public boolean showLoading() {
        return true;
    }
}
