package com.jingdong.common.jdreactFramework.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.jdreactframewok.R;
import java.io.File;

/* loaded from: classes5.dex */
public class JDReactNativeFragment extends JDReactNativeBaseFragment {
    private String mBundleName;
    JDReactNativeBaseFragment.JDReactCallback mCallback = new JDReactNativeBaseFragment.JDReactCallback() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeFragment.1
        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public void addRootView(ReactRootView reactRootView, String str, boolean z, String str2, String str3) {
            JDReactNativeFragment.this.initView(reactRootView, str, z, str2, str2);
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public void clearFresco() {
            JDReactNativeFragment.this.clearImageMemory();
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public Fragment createWebFragement(String str) {
            return JDReactNativeFragment.this.createMFragement(str);
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public void enablePVMta(boolean z) {
            JDReactNativeFragment.this.enablePV(z);
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public int getLayoutID() {
            return JDReactNativeFragment.this.getLayoutResource();
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public ReactPackage getReactPackageManger() {
            return JDReactNativeFragment.this.getReactPackage();
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public int getRootViewHolderId() {
            return JDReactNativeFragment.this.getRootViewHolder();
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public boolean isOpenLoadingView() {
            return JDReactNativeFragment.this.showLoading();
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public void lunchOpenApp(String str) {
            JDReactNativeFragment.this.launchActivityWithOpenapp(str);
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public void lunchWebPage(String str) {
            JDReactNativeFragment.this.launchMpage(str);
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public void onBackPressedCalled() {
            JDReactNativeFragment.this.onBackKeyPressed();
        }

        @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
        public void refreshStatusBar(boolean z) {
        }
    };

    public JDReactNativeFragment(String str, String str2, Bundle bundle, boolean z, String str3, boolean z2, boolean z3, String str4, String str5, boolean z4, String str6, boolean z5, int i2) {
        setArguments(JDReactNativeBaseFragment.initData(str, str2, bundle, z, str3, "0", z2, z3, str4, str5, z4, str6, z5, i2, false));
        setJDReactCallback(this.mCallback);
    }

    public void clearImageMemory() {
    }

    public Fragment createMFragement(String str) {
        return null;
    }

    public void enablePV(boolean z) {
    }

    public String getBundlePath() {
        PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(JDReactHelper.newInstance().getApplicationContext(), this.mBundleName);
        if (pluginDir == null || TextUtils.isEmpty(pluginDir.pluginDir)) {
            return "";
        }
        return pluginDir.pluginDir + File.separator + this.mBundleName + ".jsbundle";
    }

    protected Intent getCreateIntent() {
        return new Intent();
    }

    public int getLayoutResource() {
        return R.layout.jdreactnative_layout_common;
    }

    public String getRNTitle() {
        return "";
    }

    protected JDReactModuleEntity getReactEntity() {
        Intent createIntent = getCreateIntent();
        Bundle bundle = null;
        if (createIntent == null) {
            return new JDReactModuleEntity(null, null, null);
        }
        String stringExtra = createIntent.getStringExtra("appname");
        String stringExtra2 = createIntent.getStringExtra(JDReactConstant.IntentConstant.MODULE_NAME);
        if (createIntent.getExtras() != null) {
            Object obj = createIntent.getExtras().get("param");
            if (obj instanceof Bundle) {
                bundle = (Bundle) obj;
            } else if (obj instanceof String) {
                bundle = AresCommonUtils.jsonStr2Bundle((String) obj);
            }
        }
        return new JDReactModuleEntity(stringExtra, stringExtra2, bundle);
    }

    public int getRootViewHolder() {
        return 0;
    }

    public void init() {
        JDReactModuleEntity reactEntity = getReactEntity();
        this.mBundleName = reactEntity.getmBundleName();
        String str = reactEntity.getmModuleName();
        Bundle bundle = reactEntity.getmLaunchOptions();
        Intent createIntent = getCreateIntent();
        getBundlePath();
        boolean booleanExtra = createIntent.getBooleanExtra("download_failed", false);
        boolean booleanExtra2 = createIntent.getBooleanExtra("force", false);
        String stringExtra = createIntent.getStringExtra("version");
        String stringExtra2 = createIntent.getStringExtra(JDReactConstant.COMMITID);
        String stringExtra3 = createIntent.getStringExtra("common");
        setArguments(JDReactNativeBaseFragment.initData(this.mBundleName, str, bundle, isDebug(), stringExtra, stringExtra2, booleanExtra, booleanExtra2, "", TextUtils.isEmpty(stringExtra3) ? "common" : stringExtra3, false, getRNTitle(), isHiden(), createIntent.getIntExtra("type", 4), false));
        setJDReactCallback(this.mCallback);
    }

    public void initView(ReactRootView reactRootView, String str, boolean z, String str2, String str3) {
    }

    public boolean isDebug() {
        return false;
    }

    public boolean isHiden() {
        return true;
    }

    public boolean isUserRNTools() {
        return true;
    }

    public void launchActivityWithOpenapp(String str) {
    }

    public void launchMpage(String str) {
    }

    public void onBackKeyPressed() {
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        setJDReactCallback(this.mCallback);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public boolean showLoading() {
        return true;
    }

    public JDReactNativeFragment(String str, String str2, Bundle bundle, boolean z, String str3, String str4, boolean z2, boolean z3, String str5, String str6, boolean z4, String str7, boolean z5, int i2) {
        setArguments(JDReactNativeBaseFragment.initData(str, str2, bundle, z, str3, str4, z2, z3, str5, str6, z4, str7, z5, i2, false));
        setJDReactCallback(this.mCallback);
    }

    public JDReactNativeFragment() {
        init();
    }
}
