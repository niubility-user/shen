package com.jingdong.common.jdreactFramework.modules;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.DevInternalSettings;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.download.PluginDownloadInfo;
import com.jingdong.common.jdreactFramework.download.PluginUpdateInfo;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.FileUtil;
import com.jingdong.common.jdreactFramework.utils.NetConfig;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.util.ArrayList;
import org.mp4parser.aspectj.lang.JoinPoint;

/* loaded from: classes5.dex */
public class JDReactDebugModule extends ReactContextBaseJavaModule implements DevInternalSettings.Listener {
    private DevInternalSettings mDevSettings;

    public JDReactDebugModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mDevSettings = new DevInternalSettings(reactApplicationContext, this);
    }

    private String getAppBuildNumber(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (Exception unused) {
            return null;
        }
    }

    void addData(ArrayList<PluginVersion> arrayList, WritableArray writableArray, String str) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            PluginVersion pluginVersion = arrayList.get(i2);
            if (pluginVersion != null) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("bundle_name", pluginVersion.pluginName);
                createMap.putString("bundle_version", pluginVersion.pluginVersion);
                createMap.putString("bundle_path", pluginVersion.pluginDir);
                if (ReactSharedPreferenceUtils.getBlockPath(JDReactConstant.BLOCK).equals(pluginVersion.pluginDir)) {
                    createMap.putBoolean("bundle_lock", true);
                } else {
                    createMap.putBoolean("bundle_lock", false);
                }
                long fileSize = FileUtil.getFileSize(pluginVersion.pluginDir + pluginVersion.pluginName + ".jsbundle");
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(fileSize);
                createMap.putString("bundle_size", sb.toString());
                createMap.putString("bundle_type", "upgrade_pkg");
                writableArray.pushMap(createMap);
            }
        }
    }

    @ReactMethod
    public void delPKG(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (readableMap == null || !readableMap.hasKey("bundle_path")) {
            return;
        }
        String string = readableMap.getString("bundle_path");
        String blockPath = ReactSharedPreferenceUtils.getBlockPath(JDReactConstant.BLOCK);
        File file = new File(string);
        FileUtil.deleteDir(file);
        if (file.exists()) {
            return;
        }
        if (blockPath.equals(string)) {
            ReactSharedPreferenceUtils.setBlockInfo(JDReactConstant.BLOCK, "", "", "");
        }
        if (callback != null) {
            callback.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void downloadPKG(ReadableMap readableMap, Callback callback, Callback callback2) {
        String[] list;
        WritableMap createMap = Arguments.createMap();
        if (readableMap != null && readableMap.hasKey("bundleName") && readableMap.hasKey("bundleVersion") && readableMap.hasKey("bundleUrl") && readableMap.hasKey("bundleId")) {
            String string = readableMap.getString("bundleName");
            String string2 = readableMap.getString("bundleVersion");
            String string3 = readableMap.getString("bundleUrl");
            Double valueOf = Double.valueOf(readableMap.getDouble("bundleId"));
            String absolutePath = JDReactConstant.ReactDownloadPath.getAbsolutePath();
            StringBuilder sb = new StringBuilder();
            sb.append(absolutePath);
            String str = File.separator;
            sb.append(str);
            sb.append("debug");
            sb.append(str);
            sb.append(string);
            sb.append(str);
            sb.append(string2);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(valueOf);
            sb.append(str);
            File file = new File(sb.toString());
            if (file.isDirectory() && (list = file.list()) != null && list.length > 0) {
                createMap.putBoolean("datas", true);
                if (callback != null) {
                    callback.invoke(createMap);
                    return;
                }
                return;
            }
            PluginDownloadInfo pluginDownloadInfo = new PluginDownloadInfo();
            PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
            pluginUpdateInfo.pluginUpdateName = string;
            pluginUpdateInfo.pluginUpdateVersion = string2;
            pluginUpdateInfo.pluginDownloadUrl = string3;
            pluginUpdateInfo.PluginId = valueOf.doubleValue();
            pluginDownloadInfo.setPluginResult(pluginUpdateInfo);
            ReactNativeUpdateManager.getInstance().addDebugDownloadTask(pluginDownloadInfo);
            createMap.putBoolean("datas", false);
            if (callback != null) {
                callback.invoke(createMap);
            }
        }
    }

    @ReactMethod
    public void getAppInfo(ReadableMap readableMap, Callback callback, Callback callback2) {
        WritableMap createMap = Arguments.createMap();
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            createMap.putString(ApplicationUpgradeHelper.APP_VERSION, getAppBuildNumber(currentActivity));
            createMap.putString("app_code", NetConfig.sAppCode);
            String debugInfo = ReactSharedPreferenceUtils.getDebugInfo(JDReactConstant.IntentConstant.MODULE_NAME);
            createMap.putString("app_name", "" + getAppName(currentActivity));
            createMap.putString("bundle_name", "" + debugInfo);
            createMap.putString("bundle_version", "" + ReactSharedPreferenceUtils.getDebugInfo("version"));
            createMap.putString("params", "" + ReactSharedPreferenceUtils.getDebugInfo("param"));
            createMap.putString("sdk_version", "" + AbstractJDReactInitialHelper.getJDReactFrameworkVersion());
            createMap.putString(Constants.PARAM_PLATFORM, "android");
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putMap("datas", createMap);
            if (callback != null) {
                callback.invoke(createMap2);
                return;
            }
            return;
        }
        callback2.invoke(new Object[0]);
    }

    public String getAppName(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactDebugModule";
    }

    @ReactMethod
    public void getNetList(ReadableMap readableMap, Callback callback, Callback callback2) {
    }

    @ReactMethod
    public void getPKG(ReadableMap readableMap, Callback callback, Callback callback2) {
        WritableMap createMap = Arguments.createMap();
        WritableArray createArray = Arguments.createArray();
        if (readableMap != null && readableMap.hasKey("debugBusinessId")) {
            String string = readableMap.getString("debugBusinessId");
            StringBuilder sb = new StringBuilder();
            File file = JDReactConstant.ReactDownloadPath;
            sb.append(file.getAbsolutePath());
            String str = File.separator;
            sb.append(str);
            sb.append(string);
            String sb2 = sb.toString();
            addData(ReactVersionUtils.getPluginVersionListsMap(sb2, string), createArray, sb2);
            String str2 = file.getAbsolutePath() + str + "debug" + str + string + str;
            addData(ReactVersionUtils.getPluginVersionListsMap(str2, string), createArray, str2);
            Activity currentActivity = getCurrentActivity();
            PluginVersion pluginPreloadDataPath = ReactVersionUtils.getPluginPreloadDataPath(currentActivity, string);
            if (pluginPreloadDataPath != null) {
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putString("bundle_name", pluginPreloadDataPath.pluginName);
                createMap2.putString("bundle_version", pluginPreloadDataPath.pluginVersion);
                createMap2.putString("bundle_path", pluginPreloadDataPath.pluginDir);
                if (ReactSharedPreferenceUtils.getBlockPath(JDReactConstant.BLOCK).equals(pluginPreloadDataPath.pluginDir)) {
                    createMap2.putBoolean("bundle_lock", true);
                } else {
                    createMap2.putBoolean("bundle_lock", false);
                }
                long j2 = 0;
                try {
                    j2 = currentActivity.getAssets().open(pluginPreloadDataPath.pluginDir + str + pluginPreloadDataPath.pluginName + ".jsbundle").available();
                } catch (Exception unused) {
                }
                createMap2.putString("bundle_size", "" + j2);
                createMap2.putString("bundle_type", "buildin_pkg");
                createArray.pushMap(createMap2);
            }
            createMap.putArray("datas", createArray);
        }
        if (callback != null) {
            callback.invoke(createMap);
        }
    }

    @ReactMethod
    public void getSwitchStatus(ReadableMap readableMap, Callback callback, Callback callback2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("js_remotely", this.mDevSettings.isRemoteJSDebugEnabled());
        createMap.putBoolean("live_reloading", this.mDevSettings.isReloadOnJSChangeEnabled());
        createMap.putBoolean("hot_reloading", this.mDevSettings.isHotModuleReplacementEnabled());
        createMap.putBoolean("inspector", this.mDevSettings.isElementInspectorEnabled());
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("datas", createMap);
        if (callback != null) {
            callback.invoke(createMap2);
        }
    }

    @Override // com.facebook.react.devsupport.DevInternalSettings.Listener
    public void onInternalSettingsChanged() {
    }

    @ReactMethod
    public void reloadPage(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (readableMap == null || !readableMap.hasKey("debugBusinessId")) {
            return;
        }
        Activity currentActivity = getCurrentActivity();
        com.jingdong.common.jdreactFramework.Constants.isReload = true;
        currentActivity.finish();
    }

    @ReactMethod
    public void setSwitchStatus(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (readableMap != null) {
            if (readableMap.hasKey("type") && readableMap.hasKey("value")) {
                boolean z = readableMap.getBoolean("value");
                String string = readableMap.getString("type");
                if ("js_remotely".equals(string)) {
                    this.mDevSettings.setRemoteJSDebugEnabled(z);
                    com.jingdong.common.jdreactFramework.Constants.isJDReactDebug = true;
                } else if ("live_reloading".equals(string)) {
                    this.mDevSettings.setReloadOnJSChangeEnabled(z);
                } else if ("hot_reloading".equals(string)) {
                    this.mDevSettings.setHotModuleReplacementEnabled(z);
                    com.jingdong.common.jdreactFramework.Constants.isReload = true;
                } else if ("inspector".equals(string)) {
                    this.mDevSettings.setElementInspectorEnabled(z);
                    com.jingdong.common.jdreactFramework.Constants.isInspector = true;
                }
                callback.invoke(new Object[0]);
                return;
            }
            callback2.invoke(new Object[0]);
            return;
        }
        callback2.invoke(new Object[0]);
    }

    @ReactMethod
    public void toggleLockPKG(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (readableMap.hasKey("bundle_name") && readableMap.hasKey("bundle_path") && readableMap.hasKey("type")) {
            String string = readableMap.getString("bundle_name");
            String string2 = readableMap.getString("bundle_path");
            if (JoinPoint.SYNCHRONIZATION_LOCK.equals(readableMap.getString("type"))) {
                ReactSharedPreferenceUtils.setBlockInfo(JDReactConstant.BLOCK, string2, "", string);
            } else {
                ReactSharedPreferenceUtils.setBlockInfo(JDReactConstant.BLOCK, "", "", "");
            }
            callback.invoke(new Object[0]);
        }
    }
}
