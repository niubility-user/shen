package com.jingdong.common.jdreactFramework.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactPackageDemo;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.helper.IReactPackagesFactory;
import com.jingdong.common.jdreactFramework.helper.LoadExceptionListener;
import com.jingdong.common.jdreactFramework.helper.PermissionHelper;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.jdreactFramework.track.TrackUtil;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.jdreactframewok.R;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.io.File;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class JDReactNativeBasePureActivity extends FragmentActivity implements View.OnClickListener, LoadExceptionListener, JDReactNativeBaseFragment.JDReactCallback, IReactPackagesFactory {
    private static final String TAG = "JDReactNativeBasePureActivity";
    private String mBundleName;
    private JDReactNativeBaseFragment mJDReactNativeBaseFragment;

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void addRootView(ReactRootView reactRootView, String str, boolean z, String str2, String str3) {
        initView(reactRootView, str, z, str2, str2);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void clearFresco() {
        clearImageMemory();
    }

    public void clearImageMemory() {
    }

    public void closeXView() {
    }

    public Fragment createMFragement(String str) {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.helper.IReactPackagesFactory
    public List<ReactPackage> createReactPackages() {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public Fragment createWebFragement(String str) {
        return createMFragement(str);
    }

    public void destroyXView() {
    }

    public void enablePV(boolean z) {
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void enablePVMta(boolean z) {
        enablePV(z);
    }

    public boolean forceCloseXView() {
        return false;
    }

    public String getBundlePath() {
        PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(getApplicationContext(), this.mBundleName);
        if (pluginDir == null || TextUtils.isEmpty(pluginDir.pluginDir)) {
            return "";
        }
        return pluginDir.pluginDir + File.separator + this.mBundleName + ".jsbundle";
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public int getLayoutID() {
        return R.layout.jdreactnative_layout_common;
    }

    public String getRNTitle() {
        return "";
    }

    protected JDReactModuleEntity getReactEntity() {
        Intent intent = getIntent();
        Bundle bundle = null;
        if (intent == null) {
            return new JDReactModuleEntity(null, null, null);
        }
        String stringExtra = intent.getStringExtra("appname");
        String stringExtra2 = intent.getStringExtra(JDReactConstant.IntentConstant.MODULE_NAME);
        Object obj = intent.getExtras().get("param");
        if (obj instanceof Bundle) {
            bundle = (Bundle) obj;
        } else if (obj instanceof String) {
            bundle = AresCommonUtils.jsonStr2Bundle((String) obj);
        }
        return new JDReactModuleEntity(stringExtra, stringExtra2, bundle);
    }

    public ReactPackage getReactPackage() {
        return new JDReactPackageDemo();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public ReactPackage getReactPackageManger() {
        return getReactPackage();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public int getRootViewHolderId() {
        return 0;
    }

    protected void initView(ReactRootView reactRootView, String str, boolean z, String str2, String str3) {
        ImageView imageView = (ImageView) findViewById(R.id.llBtnBack);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.reactRootViewHolder);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.parent);
        TextView textView = (TextView) findViewById(R.id.reactTitle);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.rlTop);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            if (relativeLayout2 != null) {
                if (z) {
                    relativeLayout2.setVisibility(8);
                } else {
                    relativeLayout2.setVisibility(0);
                }
            }
            if (imageView != null) {
                imageView.setVisibility(0);
                imageView.setOnClickListener(this);
            }
            if (linearLayout != null) {
                linearLayout.addView(reactRootView, -1, -1);
                return;
            }
            return;
        }
        TrackUtil.trackLoadFail(9, str2);
        this.mJDReactNativeBaseFragment.showRetryDialog();
    }

    public boolean isDebug() {
        return false;
    }

    public boolean isHiden() {
        return true;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public boolean isOpenLoadingView() {
        return showLoading();
    }

    public boolean isUserRNTools() {
        return true;
    }

    public boolean launchActivityWithOpenapp(String str) {
        return false;
    }

    public boolean launchMpage(String str) {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void lunchOpenApp(String str) {
        launchActivityWithOpenapp(str);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void lunchWebPage(String str) {
        launchMpage(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        JDReactNativeBaseFragment jDReactNativeBaseFragment = this.mJDReactNativeBaseFragment;
        if (jDReactNativeBaseFragment != null) {
            jDReactNativeBaseFragment.onActivityForResult(i2, i3, intent);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void onBackPressedCalled() {
        onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.llBtnBack) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JDReactModuleEntity reactEntity = getReactEntity();
        this.mBundleName = reactEntity.getmBundleName();
        String str = reactEntity.getmModuleName();
        Bundle bundle2 = reactEntity.getmLaunchOptions();
        super.onCreate(bundle);
        setContentView(R.layout.jdreactnative_layout_main);
        Intent intent = getIntent();
        getBundlePath();
        boolean booleanExtra = intent.getBooleanExtra("download_failed", false);
        boolean booleanExtra2 = intent.getBooleanExtra("force", false);
        String stringExtra = intent.getStringExtra("version");
        String stringExtra2 = intent.getStringExtra(JDReactConstant.COMMITID);
        String stringExtra3 = intent.getStringExtra("common");
        Boolean valueOf = Boolean.valueOf(intent.getBooleanExtra(HttpDnsConfig.PREDOWNLOAD_PARAMS, false));
        String str2 = TextUtils.isEmpty(stringExtra3) ? "common" : stringExtra3;
        int intExtra = intent.getIntExtra("type", 4);
        boolean booleanExtra3 = intent.getBooleanExtra(JDReactConstant.IntentConstant.FORCE_CHECK_UPDATE, false);
        boolean booleanExtra4 = intent.getBooleanExtra(JDReactConstant.IntentConstant.FORCE_LOAD_AFTER_UPDATE_CHECK, false);
        String stringExtra4 = intent.getStringExtra(JDReactConstant.IntentConstant.BACKUP_URL);
        int dimension = (int) getResources().getDimension(R.dimen.jdreact_progressbar_size);
        int intExtra2 = intent.getIntExtra(JDReactConstant.IntentConstant.PROGRESSBAR_SIZE_HEIGHT, dimension);
        int intExtra3 = intent.getIntExtra(JDReactConstant.IntentConstant.PROGRESSBAR_SIZE_WIDTH, dimension);
        JDReactNativeBaseFragment newInstance = JDReactNativeBaseFragment.newInstance(this.mBundleName, str, bundle2, isDebug(), stringExtra, stringExtra2, booleanExtra, booleanExtra2, "", str2, false, getRNTitle(), isHiden(), intExtra, valueOf.booleanValue());
        this.mJDReactNativeBaseFragment = newInstance;
        newInstance.setParam(JDReactConstant.IntentConstant.FORCE_CHECK_UPDATE, booleanExtra3);
        this.mJDReactNativeBaseFragment.setParam(JDReactConstant.IntentConstant.FORCE_LOAD_AFTER_UPDATE_CHECK, booleanExtra4);
        this.mJDReactNativeBaseFragment.setParam(JDReactConstant.IntentConstant.BACKUP_URL, stringExtra4);
        this.mJDReactNativeBaseFragment.setParam(JDReactConstant.IntentConstant.PROGRESSBAR_SIZE_HEIGHT, intExtra2);
        this.mJDReactNativeBaseFragment.setParam(JDReactConstant.IntentConstant.PROGRESSBAR_SIZE_WIDTH, intExtra3);
        this.mJDReactNativeBaseFragment.setLoadExceptionListener(this);
        this.mJDReactNativeBaseFragment.setReactPackagesFactory(this);
        getSupportFragmentManager().beginTransaction().add(R.id.main, this.mJDReactNativeBaseFragment).commitAllowingStateLoss();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4 && this.mJDReactNativeBaseFragment != null) {
            if (forceCloseXView()) {
                return true;
            }
            this.mJDReactNativeBaseFragment.onBackPressed();
            return false;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        JDReactNativeBaseFragment jDReactNativeBaseFragment;
        if (i2 == 82 && (jDReactNativeBaseFragment = this.mJDReactNativeBaseFragment) != null) {
            return jDReactNativeBaseFragment.onMenuKeyUp();
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.LoadExceptionListener
    public void onLoadException(int i2, String str, String str2, ReactRootView reactRootView) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        PermissionHelper.dispatchPermissionResult(this, i2, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseFragment.JDReactCallback
    public void refreshStatusBar(boolean z) {
    }

    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        JDReactNativeBaseFragment jDReactNativeBaseFragment = this.mJDReactNativeBaseFragment;
        if (jDReactNativeBaseFragment != null) {
            jDReactNativeBaseFragment.sendEvent(str, writableMap);
        }
    }

    public boolean showLoading() {
        return true;
    }

    public void showXView(int i2, String str, boolean z, long j2, Callback callback, Callback callback2) {
    }
}
