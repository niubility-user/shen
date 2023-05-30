package com.jingdong.common.babelrn;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.babelrn.entity.BabelRNEntity;
import com.jingdong.common.babelrn.module.JDReactBabelPackage;
import com.jingdong.common.babelrn.module.OnRNDataChangeListener;
import com.jingdong.common.babelrn.module.RNFloorEngin;
import com.jingdong.common.babelrn.utils.BabelRNDownloadUtil;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.download.PluginListenerNew;
import com.jingdong.common.jdreactFramework.download.PluginUpdateInfo;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BabelRNManager implements LifecycleObserver {
    public String appName;
    private String bundlePath;
    private int bundleType;
    public AbstractJDReactInitialHelper mAbstractJDReactInitialHelper;
    private int mAppWidth = 0;
    private BabelRNEntity mBabelRNEntity;
    public String mBabelRNUrl;
    private OnRNDataChangeListener mOnRNDataChangeListener;
    private boolean split;

    public BabelRNManager() {
        JDReactAuraHelper.getInstance().setPackageManger();
    }

    public void initRNManager(BaseActivity baseActivity, BabelRNEntity babelRNEntity, String str) {
        this.bundlePath = str;
        Bundle bundle = new Bundle();
        putParams(bundle, babelRNEntity.params);
        this.mAbstractJDReactInitialHelper = new AbstractJDReactInitialHelper(baseActivity, babelRNEntity.appName, "", babelRNEntity.moduleName, bundle, str, null) { // from class: com.jingdong.common.babelrn.BabelRNManager.2
            {
                BabelRNManager.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected void defaultOnBackPressed() {
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected ReactPackage getDefaultReactPackage() {
                return AbstractJDReactInitialHelper.getPackageManger();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected ReactPackage getExtendReactPackage() {
                return new JDReactBabelPackage();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected void initRootView(ReactRootView reactRootView) {
            }
        };
        boolean equals = "1".equals(babelRNEntity.split);
        this.split = equals;
        this.bundleType = 0;
        if (equals) {
            this.bundleType = 3;
        }
        this.mAbstractJDReactInitialHelper.initReactManager(null, this.bundlePath, "", baseActivity, babelRNEntity.appName, babelRNEntity.moduleName, null, equals, this.bundleType, "");
        OnRNDataChangeListener onRNDataChangeListener = this.mOnRNDataChangeListener;
        if (onRNDataChangeListener != null) {
            onRNDataChangeListener.onDataChanged();
        }
    }

    private void put(Bundle bundle, String str, Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof Boolean) {
                    bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof String) {
                    bundle.putString(str, String.valueOf(obj));
                } else if (obj instanceof Byte) {
                    bundle.putByte(str, ((Byte) obj).byteValue());
                } else if (obj instanceof Float) {
                    bundle.putFloat(str, ((Float) obj).floatValue());
                } else if (obj instanceof Integer) {
                    bundle.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Short) {
                    bundle.putShort(str, ((Short) obj).shortValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(str, ((Double) obj).doubleValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(str, ((Long) obj).longValue());
                } else {
                    bundle.putString(str, obj.toString());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void putParams(Bundle bundle, String str) {
        if (bundle == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                put(bundle, next, jSONObject.opt(next));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public Bundle getParamsBundle() {
        Bundle bundle = new Bundle();
        BabelRNEntity babelRNEntity = this.mBabelRNEntity;
        if (babelRNEntity != null) {
            putParams(bundle, babelRNEntity.params);
        }
        return bundle;
    }

    public ReactInstanceManager getReactManager() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            return abstractJDReactInitialHelper.getReactManager();
        }
        return null;
    }

    public void load(final BaseActivity baseActivity, final BabelRNEntity babelRNEntity, String str) {
        if (babelRNEntity == null) {
            return;
        }
        this.mAppWidth = DPIUtil.getAppWidth(baseActivity);
        this.mBabelRNEntity = babelRNEntity;
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null && abstractJDReactInitialHelper.getReactManager() != null) {
            String str2 = this.mBabelRNUrl;
            if (str2 == null || str2.equals(babelRNEntity.jsUrl)) {
                return;
            }
            this.mAbstractJDReactInitialHelper.onDestroy();
            this.mAbstractJDReactInitialHelper = null;
        }
        this.appName = babelRNEntity.appName;
        this.mBabelRNUrl = babelRNEntity.jsUrl;
        if (ReactModuleAvailabilityUtils.getModuleAvailability(babelRNEntity.moduleName)) {
            boolean z = SharedPreferencesUtil.getSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false);
            if (!z && !TextUtils.isEmpty(babelRNEntity.jsUrl) && !TextUtils.isEmpty(babelRNEntity.jsBundleName) && !TextUtils.isEmpty(babelRNEntity.moduleName)) {
                BabelRNDownloadUtil.getBundlePath(baseActivity, babelRNEntity.jsUrl, babelRNEntity.moduleName, babelRNEntity.jsBundleName, str, new PluginListenerNew() { // from class: com.jingdong.common.babelrn.BabelRNManager.1
                    {
                        BabelRNManager.this = this;
                    }

                    @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
                    public void onDownloadProgressChanged(int i2) {
                    }

                    @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
                    public void onFailure(String str3, String str4, boolean z2, String str5) {
                    }

                    @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
                    public void onFinish(final PluginUpdateInfo pluginUpdateInfo) {
                        baseActivity.post(new Runnable() { // from class: com.jingdong.common.babelrn.BabelRNManager.1.1
                            {
                                AnonymousClass1.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                BabelRNManager.this.initRNManager(baseActivity, babelRNEntity, pluginUpdateInfo.pluginUpdateName);
                            }
                        });
                    }
                });
            } else if (z) {
                initRNManager(baseActivity, babelRNEntity, "");
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration, Activity activity) {
        int appWidth = DPIUtil.getAppWidth(activity);
        if (appWidth == 0 || appWidth == this.mAppWidth) {
            return;
        }
        this.mAppWidth = appWidth;
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper == null || abstractJDReactInitialHelper.getReactManager() == null) {
            return;
        }
        try {
            int appHeight = DPIUtil.getAppHeight(activity);
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("screenWidth", appWidth);
            createMap.putInt("screenHeight", appHeight);
            BabelRNEntity babelRNEntity = this.mBabelRNEntity;
            boolean z = babelRNEntity != null && babelRNEntity.forceRebuild();
            createMap.putBoolean("force", z);
            sendEvent("screenSizeChanged", createMap);
            resizeRnView(activity, z);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("BabelRNManager", e2);
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onDestroy();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onPause();
        }
    }

    public void onRefresh() {
        OnRNDataChangeListener onRNDataChangeListener = this.mOnRNDataChangeListener;
        if (onRNDataChangeListener != null) {
            onRNDataChangeListener.onRefresh();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onResume();
            if (this.mAbstractJDReactInitialHelper.getCurrentReactContext() != null) {
                try {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("appName", this.appName);
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mAbstractJDReactInitialHelper.getCurrentReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("JDReactNativeRefresh", createMap);
                } catch (Exception unused) {
                }
            }
        }
    }

    public void resizeRnView(Activity activity, boolean z) {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper;
        if (z && (abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper) != null) {
            abstractJDReactInitialHelper.onDestroy();
            AbstractJDReactInitialHelper abstractJDReactInitialHelper2 = this.mAbstractJDReactInitialHelper;
            String str = this.bundlePath;
            BabelRNEntity babelRNEntity = this.mBabelRNEntity;
            abstractJDReactInitialHelper2.initReactManager(null, str, "", activity, babelRNEntity.appName, babelRNEntity.moduleName, null, this.split, this.bundleType, "");
            RNFloorEngin.getInstance().forceAllRecreate();
            onResume();
            return;
        }
        RNFloorEngin.getInstance().updateAllScreenSize();
    }

    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.sendEvent(str, writableMap);
        }
    }

    public void setOnRNDataChangeListener(OnRNDataChangeListener onRNDataChangeListener) {
        this.mOnRNDataChangeListener = onRNDataChangeListener;
    }

    public void startReactApplication(ReactRootView reactRootView, Bundle bundle) {
        if (reactRootView == null) {
            return;
        }
        reactRootView.startReactApplication(getReactManager(), this.appName, bundle);
    }
}
