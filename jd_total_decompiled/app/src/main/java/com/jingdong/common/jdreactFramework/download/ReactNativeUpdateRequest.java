package com.jingdong.common.jdreactFramework.download;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.JDReactHttpSetting;
import com.jingdong.common.jdreactFramework.lifecycle.LifecycleOwner;
import com.jingdong.common.jdreactFramework.track.TrackUtil;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.NetConfig;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.utils.LangUtils;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ReactNativeUpdateRequest {
    private static final String TAG = "ReactNativeUpdate";
    private static ReactNativeUpdateRequest reactRequestInstance;
    private List<PluginDownloadInfo> jsonDownloadInfoList;
    private Context reactContext;

    /* loaded from: classes5.dex */
    public interface RequestCallback {
        void onResult(boolean z);
    }

    private ReactNativeUpdateRequest(Context context) {
        this.reactContext = context;
    }

    public static ReactNativeUpdateRequest getInstance(Context context) {
        ReactNativeUpdateRequest reactNativeUpdateRequest = reactRequestInstance;
        return reactNativeUpdateRequest != null ? reactNativeUpdateRequest : new ReactNativeUpdateRequest(context);
    }

    public void parseH5Url(JDJSONArray jDJSONArray) {
        if (jDJSONArray != null) {
            boolean z = Build.VERSION.SDK_INT >= 16;
            try {
                JDJSONArray parseArray = JDJSON.parseArray(jDJSONArray.toString().replaceAll("\\r\\n", "").replaceAll(LangUtils.SINGLE_SPACE, ""));
                if (parseArray == null || parseArray == null || parseArray.size() <= 0) {
                    return;
                }
                for (int i2 = 0; i2 < parseArray.size(); i2++) {
                    JDJSONObject optJSONObject = parseArray.optJSONObject(i2);
                    if (optJSONObject != null && optJSONObject.optString(JDReactConstant.ModuleCode) != null) {
                        ReactModuleAvailabilityUtils.saveModuleBackupUrl(optJSONObject.optString(JDReactConstant.ModuleCode), optJSONObject.optString("degradePath"));
                        ReactModuleAvailabilityUtils.saveModuleAvailability(optJSONObject.optString(JDReactConstant.ModuleCode), ("1".equals(optJSONObject.optString("isNeedDegrade")) ^ true) && z);
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public void parseUpgradeInfo(JDJSONArray jDJSONArray) {
        if (jDJSONArray != null) {
            try {
                ReactSharedPreferenceUtils.saveUpdateInfo(jDJSONArray.toString());
                List<PluginDownloadInfo> pluginDownloadModel = ReactUpdateModelHelper.setPluginDownloadModel(jDJSONArray);
                this.jsonDownloadInfoList = pluginDownloadModel;
                if (pluginDownloadModel == null || pluginDownloadModel.size() <= 0) {
                    return;
                }
                for (PluginDownloadInfo pluginDownloadInfo : ReactVersionDiff.getReactDownloadLists(this.jsonDownloadInfoList)) {
                    if (pluginDownloadInfo != null) {
                        PluginUpdateInfo pluginResult = pluginDownloadInfo.getPluginResult();
                        int i2 = pluginResult.upgradeLevel;
                        boolean z = true;
                        if (i2 == 2) {
                            ReactSharedPreferenceUtils.setUpdateLevel(pluginResult.pluginUpdateName, i2);
                            String str = pluginResult.pluginUpdateName;
                            if (pluginResult.downType != 0) {
                                z = false;
                            }
                            ReactSharedPreferenceUtils.setDefaultDownLoadingStatus(str, z, pluginResult.pluginCommonVersion);
                        } else if (pluginResult.isItForceUpdate.toLowerCase().equals(DYConstants.DY_TRUE)) {
                            String str2 = pluginResult.pluginUpdateName;
                            if (pluginResult.downType != 0) {
                                z = false;
                            }
                            ReactSharedPreferenceUtils.setDefaultDownLoadingStatus(str2, z, pluginResult.pluginCommonVersion);
                        } else {
                            JLog.d(TAG, "React native version download  isRunning = false");
                            String str3 = pluginResult.pluginUpdateName;
                            if (pluginResult.downType != 0) {
                                z = false;
                            }
                            ReactSharedPreferenceUtils.setDefaultDownLoadingStatus(str3, z, pluginResult.pluginCommonVersion);
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public void sendReactUpdateRequest(Map<String, String> map) {
        sendReactUpdateRequest(map, null, null);
    }

    public void sendReactUpdateRequest(Map<String, String> map, RequestCallback requestCallback, String str) {
        sendReactUpdateRequest(map, requestCallback, str, true);
    }

    public void sendReactUpdateRequest(Map<String, String> map, RequestCallback requestCallback, String str, boolean z) {
        sendReactUpdateRequest(map, requestCallback, str, z, false);
    }

    public void sendReactUpdateRequest(Map<String, String> map, final RequestCallback requestCallback, final String str, final boolean z, final boolean z2) {
        JDReactHelper newInstance = JDReactHelper.newInstance();
        JDReactHttpSetting httpSetting = newInstance.getHttpSetting();
        httpSetting.setFunctionId(JDReactHelper.newInstance().getNativeVerionAPI());
        httpSetting.setHost(newInstance.getVirtualHost(JDReactHelper.newInstance().getNativeVerionAPI()));
        httpSetting.putJsonParam("app", "com.jd.app.main");
        httpSetting.setNotifyUser(false);
        httpSetting.setEffect(newInstance.getEffect(JDReactHelper.newInstance().getNativeVerionAPI()));
        httpSetting.setUseFastJsonParser(true);
        try {
            JSONObject jSONObject = new JSONObject(map);
            httpSetting.putJsonParam("local", jSONObject);
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.e(TAG, " request local:" + jSONObject.toString());
            }
        } catch (Exception unused) {
        }
        httpSetting.putJsonParam("entry", Boolean.FALSE);
        httpSetting.putJsonParam("degradeType", "1");
        httpSetting.putJsonParam("rnVersion", "0.59.9");
        httpSetting.putJsonParam("rnClient", "android");
        httpSetting.setPIN(JDReactHelper.newInstance().getNativeVerionAPI());
        httpSetting.setA2(JDReactHelper.newInstance().getNativeVerionAPI());
        if (!TextUtils.isEmpty(NetConfig.sAppCode)) {
            httpSetting.putJsonParam("appCode", NetConfig.sAppCode);
        }
        httpSetting.setListener(new JDReactHttpSetting.HttpCallback() { // from class: com.jingdong.common.jdreactFramework.download.ReactNativeUpdateRequest.1
            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onEnd(JDJSONObject jDJSONObject) {
                boolean z3;
                RequestCallback requestCallback2;
                RequestCallback requestCallback3;
                boolean z4;
                PluginUpdateInfo pluginResult;
                RequestCallback requestCallback4;
                ReactSharedPreferenceUtils.putCheckComplete(true);
                try {
                    if (JDReactHelper.newInstance().isDebug()) {
                        JLog.d(ReactNativeUpdateRequest.TAG, "React native version response " + jDJSONObject.toString());
                    }
                    if (jDJSONObject.optJSONObject("result") != null) {
                        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("result");
                        if (optJSONObject != null) {
                            TrackUtil.trackUpdateNode(1, 0, str);
                            JDJSONArray optJSONArray = optJSONObject.optJSONArray("upgradeInfo");
                            JDJSONArray optJSONArray2 = optJSONObject.optJSONArray("degradeInfo");
                            if (optJSONArray2 != null) {
                                boolean z5 = Build.VERSION.SDK_INT >= 16;
                                try {
                                    JDJSONArray parseArray = JDJSON.parseArray(optJSONArray2.toString().replaceAll("\\r\\n", "").replaceAll(LangUtils.SINGLE_SPACE, ""));
                                    if (parseArray != null && parseArray != null && parseArray.size() > 0) {
                                        for (int i2 = 0; i2 < parseArray.size(); i2++) {
                                            JDJSONObject optJSONObject2 = parseArray.optJSONObject(i2);
                                            if (optJSONObject2 != null && optJSONObject2.optString(JDReactConstant.ModuleCode) != null) {
                                                String optString = optJSONObject2.optString(JDReactConstant.ModuleCode);
                                                ReactModuleAvailabilityUtils.saveModuleBackupUrl(optString, optJSONObject2.optString("degradePath"));
                                                ReactModuleAvailabilityUtils.saveModuleAvailability(optString, !"1".equals(optJSONObject2.optString("isNeedDegrade")) && z5);
                                                JDJSONObject jDJSONObject2 = new JDJSONObject();
                                                jDJSONObject2.put("zipPath", (Object) optJSONObject2.optString("zipPath"));
                                                jDJSONObject2.put("zipPathSignature", (Object) optJSONObject2.optString("zipPathSignature"));
                                                jDJSONObject2.put("versionCode", (Object) optJSONObject2.optString("versionCode"));
                                                ReactModuleAvailabilityUtils.saveModuleBackupZip(optString, jDJSONObject2.toString());
                                                ReactModuleAvailabilityUtils.saveShowDegradeFirst(optString, "1".equals(optJSONObject2.optString("showDegradeFirst")));
                                            }
                                        }
                                    }
                                    ReactModuleAvailabilityUtils.saveLastAvailabilityResult(true);
                                } catch (Exception unused2) {
                                    ReactModuleAvailabilityUtils.saveLastAvailabilityResult(false);
                                }
                            }
                            if (optJSONArray != null) {
                                ReactSharedPreferenceUtils.saveUpdateInfo(optJSONArray.toString());
                                ReactNativeUpdateRequest.this.jsonDownloadInfoList = ReactUpdateModelHelper.setPluginDownloadModel(optJSONArray);
                                if (ReactNativeUpdateRequest.this.jsonDownloadInfoList != null && ReactNativeUpdateRequest.this.jsonDownloadInfoList.size() > 0) {
                                    z4 = false;
                                    for (PluginDownloadInfo pluginDownloadInfo : ReactVersionDiff.getReactDownloadLists(ReactNativeUpdateRequest.this.jsonDownloadInfoList)) {
                                        try {
                                            if (pluginDownloadInfo != null && (pluginResult = pluginDownloadInfo.getPluginResult()) != null && (!z2 || TextUtils.equals(pluginResult.pluginUpdateName, str))) {
                                                int i3 = pluginResult.upgradeLevel;
                                                if (i3 == 2) {
                                                    ReactSharedPreferenceUtils.setUpdateLevel(pluginResult.pluginUpdateName, i3);
                                                    ReactSharedPreferenceUtils.setDefaultDownLoadingStatus(pluginResult.pluginUpdateName, pluginResult.downType == 0, pluginResult.pluginCommonVersion);
                                                } else if (DYConstants.DY_TRUE.equalsIgnoreCase(pluginResult.isItForceUpdate)) {
                                                    ReactNativeUpdateManager.getInstance().addForceDownloadTask(pluginDownloadInfo);
                                                } else if (pluginResult.upgradeLevel == 4) {
                                                    ReactNativeUpdateManager.getInstance().addDownloadTask(pluginDownloadInfo, false);
                                                } else {
                                                    JLog.d(ReactNativeUpdateRequest.TAG, "React native version download isRunning = false");
                                                    if (LifecycleOwner.getInstance().isRunning(pluginResult.pluginUpdateName)) {
                                                        ReactNativeUpdateManager.getInstance().addDownloadTask(pluginDownloadInfo, false);
                                                        JLog.d(ReactNativeUpdateRequest.TAG, "React native version download isRunning = true");
                                                    } else {
                                                        ReactSharedPreferenceUtils.setDefaultDownLoadingStatus(pluginResult.pluginUpdateName, pluginResult.downType == 0, pluginResult.pluginCommonVersion);
                                                    }
                                                }
                                                if (TextUtils.equals(pluginResult.pluginUpdateName, str) && z && (requestCallback4 = requestCallback) != null) {
                                                    requestCallback4.onResult(true);
                                                    z4 = true;
                                                }
                                                if (z2) {
                                                    break;
                                                }
                                            }
                                        } catch (Exception e2) {
                                            e = e2;
                                            z3 = z4;
                                            e.printStackTrace();
                                            ReactSharedPreferenceUtils.putLastCheckCompleteFlag(false);
                                            if (!z3) {
                                                requestCallback2.onResult(false);
                                            }
                                            TrackUtil.trackUpdateNode(1, 3, str);
                                        }
                                    }
                                    z3 = z4;
                                }
                            } else {
                                TrackUtil.trackUpdateNode(1, 2, str);
                            }
                        } else {
                            TrackUtil.trackUpdateNode(1, 3, str);
                        }
                        z4 = false;
                        z3 = z4;
                    } else {
                        TrackUtil.trackUpdateNode(1, 3, str);
                        z3 = false;
                    }
                    try {
                        if (!z) {
                            RequestCallback requestCallback5 = requestCallback;
                            if (requestCallback5 != null) {
                                requestCallback5.onResult(true);
                            }
                        } else if (!z3 && (requestCallback3 = requestCallback) != null) {
                            requestCallback3.onResult(false);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        ReactSharedPreferenceUtils.putLastCheckCompleteFlag(false);
                        if (!z3 && (requestCallback2 = requestCallback) != null) {
                            requestCallback2.onResult(false);
                        }
                        TrackUtil.trackUpdateNode(1, 3, str);
                    }
                } catch (Exception e4) {
                    e = e4;
                    z3 = false;
                }
            }

            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onEnd(File file) {
            }

            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onError() {
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.e(ReactNativeUpdateRequest.TAG, "Get react native module version start onError");
                }
                ReactSharedPreferenceUtils.putLastCheckCompleteFlag(false);
                ReactModuleAvailabilityUtils.saveLastAvailabilityResult(false);
                RequestCallback requestCallback2 = requestCallback;
                if (requestCallback2 != null) {
                    requestCallback2.onResult(false);
                }
                TrackUtil.trackUpdateNode(1, 4, str);
            }

            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onPause() {
            }

            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.common.jdreactFramework.download.JDReactHttpSetting.HttpCallback
            public void onStart() {
                JLog.d(ReactNativeUpdateRequest.TAG, "Get react native module version start");
            }
        });
        try {
            httpSetting.startToload();
        } catch (Exception unused2) {
        }
    }
}
