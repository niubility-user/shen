package com.jingdong.common.jdreactFramework.activities;

import android.content.Intent;
import android.os.Build;
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
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactPackageDemo;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment;
import com.jingdong.common.jdreactFramework.helper.IReactPackagesFactory;
import com.jingdong.common.jdreactFramework.helper.LoadExceptionListener;
import com.jingdong.common.jdreactFramework.helper.PermissionHelper;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.jdreactFramework.track.TrackUtil;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.jdreactframewok.R;
import java.util.List;

/* loaded from: classes5.dex */
public class JDReactNativeDebugActivity extends FragmentActivity implements View.OnClickListener, LoadExceptionListener, JDReactNativeDebugFragment.JDReactCallback, IReactPackagesFactory {
    private static final String TAG = "JDReactNativeDebugActivity";
    private JDReactNativeDebugFragment reactNativeDebugFragment;

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
    public void addRootView(ReactRootView reactRootView, String str, boolean z, String str2, String str3) {
        initView(reactRootView, str, z, str2, str2);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
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

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
    public Fragment createWebFragement(String str) {
        return createMFragement(str);
    }

    public void destroyXView() {
    }

    public void enablePV(boolean z) {
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
    public void enablePVMta(boolean z) {
        enablePV(z);
    }

    public boolean forceCloseXView() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
    public int getLayoutID() {
        return R.layout.jdreactnative_layout_common;
    }

    public String getRNTitle() {
        return "";
    }

    protected JDReactModuleEntity getReactEntity() {
        String str;
        Bundle bundle;
        Bundle extras;
        Object obj;
        Intent intent = getIntent();
        String str2 = null;
        boolean z = false;
        if (intent == null || (extras = intent.getExtras()) == null) {
            str = null;
            bundle = null;
        } else {
            String str3 = null;
            str = null;
            bundle = null;
            for (String str4 : extras.keySet()) {
                if (JDReactConstant.IntentConstant.MODULE_NAME.equals(str4)) {
                    str3 = extras.getString(JDReactConstant.IntentConstant.MODULE_NAME);
                } else if ("appname".equals(str4)) {
                    str = extras.getString("appname");
                } else if ("param".equals(str4)) {
                    String string = extras.getString("param", null);
                    if (!TextUtils.isEmpty(string)) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        try {
                            JDJSONObject parseObject = JDJSON.parseObject(string);
                            for (String str5 : parseObject.keySet()) {
                                Object obj2 = parseObject.get(str5);
                                if (str5 != null && str5.equals("transparentenable")) {
                                    if (obj2 instanceof Boolean) {
                                        z = ((Boolean) obj2).booleanValue();
                                    } else if (obj2 instanceof String) {
                                        String str6 = (String) obj2;
                                        if (!TextUtils.isEmpty(str6) && DYConstants.DY_TRUE.equals(str6)) {
                                            z = true;
                                        }
                                    }
                                    bundle.putBoolean(str5, z);
                                } else if (obj2 != null) {
                                    if (obj2 instanceof Boolean) {
                                        bundle.putBoolean(str5, ((Boolean) obj2).booleanValue());
                                    } else if (obj2 instanceof String) {
                                        bundle.putString(str5, String.valueOf(obj2));
                                    } else if (obj2 instanceof Byte) {
                                        bundle.putByte(str5, ((Byte) obj2).byteValue());
                                    } else if (obj2 instanceof Float) {
                                        bundle.putFloat(str5, ((Float) obj2).floatValue());
                                    } else if (obj2 instanceof Integer) {
                                        bundle.putInt(str5, ((Integer) obj2).intValue());
                                    } else if (obj2 instanceof Short) {
                                        bundle.putShort(str5, ((Short) obj2).shortValue());
                                    } else if (obj2 instanceof Double) {
                                        bundle.putDouble(str5, ((Double) obj2).doubleValue());
                                    } else if (obj2 instanceof Long) {
                                        bundle.putLong(str5, ((Long) obj2).longValue());
                                    } else {
                                        bundle.putString(str5, obj2.toString());
                                    }
                                }
                            }
                        } catch (Exception unused) {
                        }
                    }
                } else if (!TextUtils.isEmpty(str4) && !"pluginName".equals(str4) && !"params".equals(str4) && !"pluginPath".equals(str4) && !"jumpFrom".equals(str4) && !"version".equals(str4) && !LoginConstans.JUMP_DES.equals(str4) && (obj = extras.get(str4)) != null) {
                    if (bundle == null) {
                        bundle = new Bundle();
                    }
                    if (str4 != null && str4.equals("transparentenable")) {
                        if (obj instanceof Boolean) {
                            z = ((Boolean) obj).booleanValue();
                        } else if (obj instanceof String) {
                            String str7 = (String) obj;
                            if (!TextUtils.isEmpty(str7) && DYConstants.DY_TRUE.equals(str7)) {
                                z = true;
                            }
                        }
                        bundle.putBoolean(str4, z);
                    } else if (obj instanceof Boolean) {
                        bundle.putBoolean(str4, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof String) {
                        bundle.putString(str4, String.valueOf(obj));
                    } else if (obj instanceof Byte) {
                        bundle.putByte(str4, ((Byte) obj).byteValue());
                    } else if (obj instanceof Float) {
                        bundle.putFloat(str4, ((Float) obj).floatValue());
                    } else if (obj instanceof Integer) {
                        bundle.putInt(str4, ((Integer) obj).intValue());
                    } else if (obj instanceof Short) {
                        bundle.putShort(str4, ((Short) obj).shortValue());
                    } else if (obj instanceof Double) {
                        bundle.putDouble(str4, ((Double) obj).doubleValue());
                    } else if (obj instanceof Long) {
                        bundle.putLong(str4, ((Long) obj).longValue());
                    } else {
                        bundle.putString(str4, obj.toString());
                    }
                }
            }
            str2 = str3;
        }
        if (str2 != null && str2.equals("JDReactCollectJDBeans")) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("sdk_version", Build.VERSION.SDK_INT);
        }
        JDReactModuleEntity jDReactModuleEntity = new JDReactModuleEntity(str, str2, bundle);
        jDReactModuleEntity.setTransparentEnable(z);
        return jDReactModuleEntity;
    }

    public ReactPackage getReactPackage() {
        JDReactAuraHelper.getInstance().setPackageManger();
        return AbstractJDReactInitialHelper.getPackageManger() != null ? AbstractJDReactInitialHelper.getPackageManger() : new JDReactPackageDemo();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
    public ReactPackage getReactPackageManger() {
        return getReactPackage();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
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
        this.reactNativeDebugFragment.showRetryDialog();
    }

    public boolean isDebug() {
        return false;
    }

    public boolean isHiden() {
        return true;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
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

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
    public void lunchOpenApp(String str) {
        launchActivityWithOpenapp(str);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
    public void lunchWebPage(String str) {
        launchMpage(str);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        JDReactNativeDebugFragment jDReactNativeDebugFragment = this.reactNativeDebugFragment;
        if (jDReactNativeDebugFragment != null) {
            jDReactNativeDebugFragment.onActivityForResult(i2, i3, intent);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
    public void onBackPressedCalled() {
        onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.llBtnBack) {
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JDReactModuleEntity reactEntity = getReactEntity();
        String str = reactEntity.getmModuleName();
        Bundle bundle2 = reactEntity.getmLaunchOptions();
        super.onCreate(bundle);
        setContentView(R.layout.jdreactnative_layout_main);
        JDReactNativeDebugFragment newInstance = JDReactNativeDebugFragment.newInstance(str, bundle2, getIntent().getStringExtra("bundlePath"), getRNTitle(), isHiden());
        this.reactNativeDebugFragment = newInstance;
        newInstance.setLoadExceptionListener(this);
        this.reactNativeDebugFragment.setReactPackagesFactory(this);
        getSupportFragmentManager().beginTransaction().add(R.id.main, this.reactNativeDebugFragment).commitAllowingStateLoss();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4 && this.reactNativeDebugFragment != null) {
            if (forceCloseXView()) {
                return true;
            }
            this.reactNativeDebugFragment.onBackPressed();
            return false;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        JDReactNativeDebugFragment jDReactNativeDebugFragment;
        if (i2 == 82 && (jDReactNativeDebugFragment = this.reactNativeDebugFragment) != null) {
            return jDReactNativeDebugFragment.onMenuKeyUp();
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

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeDebugFragment.JDReactCallback
    public void refreshStatusBar(boolean z) {
    }

    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        JDReactNativeDebugFragment jDReactNativeDebugFragment = this.reactNativeDebugFragment;
        if (jDReactNativeDebugFragment != null) {
            jDReactNativeDebugFragment.sendEvent(str, writableMap);
        }
    }

    public boolean showLoading() {
        return true;
    }

    public void showXView(int i2, String str, boolean z, long j2, Callback callback, Callback callback2) {
    }
}
