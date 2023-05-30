package com.jingdong.common.jdreactFramework.download;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateRequest;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactMetadataValidator;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import com.jingdong.common.utils.LangUtils;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class ReactNativeUpdate {
    private static final String TAG = "ReactNativeUpdate";
    private static ReactNativeUpdate helper;
    private static boolean isReactSoInProcess;
    private Map<String, String> hashPluginLists = new ArrayMap();
    private Context mContext;

    private ReactNativeUpdate(Context context) {
        this.mContext = context;
    }

    private synchronized void check(long j2) {
        try {
            String appVersion = ReactSharedPreferenceUtils.getAppVersion(JDReactConstant.REACT_APP_BUILD_NUMBER_FOR_UPDATE);
            String appBuildNumber = getAppBuildNumber();
            if (!TextUtils.isEmpty(appBuildNumber) && !TextUtils.equals(appBuildNumber, appVersion)) {
                ReactSharedPreferenceUtils.savePreloadPackage(JDReactConstant.SHARE_PREFRENCE_NAME, "");
                ReactSharedPreferenceUtils.saveAppVersion(JDReactConstant.REACT_APP_BUILD_NUMBER_FOR_UPDATE, appBuildNumber);
                realToUpdate();
                return;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!ReactSharedPreferenceUtils.getLastCheckCompleteFlag()) {
            realToUpdate();
            return;
        }
        long time = new Date().getTime() - ReactSharedPreferenceUtils.getLastCheckTime();
        if (time > j2 || time < 0) {
            realToUpdate();
        }
    }

    public static void delete(File file) {
        try {
            if (file.exists()) {
                JLog.d(TAG, "delete:" + file.getAbsolutePath());
                if (file.isFile()) {
                    file.delete();
                    return;
                } else if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null && listFiles.length > 0) {
                        for (File file2 : listFiles) {
                            delete(file2);
                        }
                    }
                    file.delete();
                    return;
                } else {
                    return;
                }
            }
            JLog.d(TAG, "\u6240\u5220\u9664\u7684\u6587\u4ef6\u4e0d\u5b58\u5728\uff01\n");
        } catch (Exception unused) {
            JLog.e(TAG, "unable to delete the folder!");
        }
    }

    private String getAppBuildNumber() {
        try {
            return String.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode);
        } catch (Exception unused) {
            return null;
        }
    }

    public static synchronized ReactNativeUpdate getInstance() {
        ReactNativeUpdate reactNativeUpdate;
        synchronized (ReactNativeUpdate.class) {
            ReactNativeUpdate reactNativeUpdate2 = helper;
            if (reactNativeUpdate2 == null) {
                helper = new ReactNativeUpdate(JDReactHelper.newInstance().getApplicationContext());
            } else if (reactNativeUpdate2.getContext() == null) {
                helper = new ReactNativeUpdate(JDReactHelper.newInstance().getApplicationContext());
            }
            reactNativeUpdate = helper;
        }
        return reactNativeUpdate;
    }

    private void realToUpdate() {
        ReactSharedPreferenceUtils.putLastCheckCompleteFlag(true);
        ReactSharedPreferenceUtils.putLastCheckTime();
        this.hashPluginLists = ReactVersionUtils.getMergedPluginVersionLists();
        ReactNativeUpdateRequest.getInstance(this.mContext).sendReactUpdateRequest(this.hashPluginLists);
    }

    private void unzipSO() throws Exception {
        File file = JDReactConstant.ReactDownloadPathOld;
        if (file != null && file.exists()) {
            delete(file);
        }
        String appBuildNumber = getAppBuildNumber();
        if (TextUtils.isEmpty(appBuildNumber)) {
            return;
        }
        ReactSharedPreferenceUtils.saveAppVersion(JDReactConstant.REACT_APP_BUILD_NUMBER, appBuildNumber);
    }

    public void checkModuleAvailability(long j2) {
        checkModuleAvailability(j2, null);
    }

    public void checkUpdate() {
        try {
            Class.forName("com.facebook.react.ReactInstanceManager");
            check(300000L);
        } catch (ClassNotFoundException unused) {
            ReactModuleAvailabilityUtils.saveModuleAvailability(JDReactConstant.AVAILABILITY_SIGNRANK, false);
            ReactModuleAvailabilityUtils.saveModuleAvailability(JDReactConstant.AVAILABILITY_MOVIE, false);
            ReactModuleAvailabilityUtils.saveModuleAvailability(JDReactConstant.AVAILABILITY_PAYSUCCESS, false);
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public void reactUnzipSo() {
        if (isReactSoInProcess) {
            return;
        }
        try {
            isReactSoInProcess = true;
            String appVersion = ReactSharedPreferenceUtils.getAppVersion(JDReactConstant.REACT_APP_BUILD_NUMBER);
            if (TextUtils.isEmpty(appVersion)) {
                unzipSO();
            } else {
                String appBuildNumber = getAppBuildNumber();
                if (!TextUtils.isEmpty(appBuildNumber) && !appBuildNumber.trim().equals(appVersion.trim())) {
                    unzipSO();
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            isReactSoInProcess = false;
            throw th;
        }
        isReactSoInProcess = false;
    }

    public void checkModuleAvailability(long j2, final ReactNativeUpdateRequest.RequestCallback requestCallback) {
        final boolean z = Build.VERSION.SDK_INT >= 16;
        if (new Date().getTime() - ReactModuleAvailabilityUtils.getLastCheckAvailabilityTime() > j2) {
            ReactModuleAvailabilityUtils.saveLastCheckAvailabilityTime();
            ReactMetadataValidator.getReactMetaData(new ReactMetadataValidator.ReactMetaDataCallback() { // from class: com.jingdong.common.jdreactFramework.download.ReactNativeUpdate.1
                {
                    ReactNativeUpdate.this = this;
                }

                @Override // com.jingdong.common.jdreactFramework.utils.ReactMetadataValidator.ReactMetaDataCallback
                public void onFail() {
                    ReactModuleAvailabilityUtils.saveLastAvailabilityResult(false);
                    ReactNativeUpdateRequest.RequestCallback requestCallback2 = requestCallback;
                    if (requestCallback2 != null) {
                        requestCallback2.onResult(false);
                    }
                }

                @Override // com.jingdong.common.jdreactFramework.utils.ReactMetadataValidator.ReactMetaDataCallback
                public void onSuccess(JDJSONObject jDJSONObject) {
                    JDJSONArray parseArray;
                    try {
                        JDJSONObject parseObject = JDJSON.parseObject(jDJSONObject.optJSONObject("result").toString().replaceAll("\\r\\n", "").replaceAll(LangUtils.SINGLE_SPACE, ""));
                        if (parseObject != null && (parseArray = JDJSON.parseArray(parseObject.optString("dataValue"))) != null && parseArray.size() > 0) {
                            for (int i2 = 0; i2 < parseArray.size(); i2++) {
                                JDJSONObject optJSONObject = parseArray.optJSONObject(i2);
                                if (optJSONObject != null && optJSONObject.optString("moduleName") != null) {
                                    ReactModuleAvailabilityUtils.saveModuleBackupUrl(optJSONObject.optString("moduleName"), optJSONObject.optString("reactnativeBackupUrl"));
                                    ReactModuleAvailabilityUtils.saveModuleAvailability(optJSONObject.optString("moduleName"), ReactMetadataValidator.checkRules(optJSONObject.getJSONArray("enabledRules")) && z);
                                    JDJSONObject optJSONObject2 = optJSONObject.optJSONObject("sharedData");
                                    if (optJSONObject2 != null) {
                                        Iterator<String> it = optJSONObject2.keySet().iterator();
                                        while (it.hasNext()) {
                                            String obj = it.next().toString();
                                            ReactModuleAvailabilityUtils.saveSharedData(obj, optJSONObject2.optString(obj));
                                        }
                                    }
                                }
                            }
                        }
                        ReactModuleAvailabilityUtils.saveLastAvailabilityResult(true);
                        ReactNativeUpdateRequest.RequestCallback requestCallback2 = requestCallback;
                        if (requestCallback2 != null) {
                            requestCallback2.onResult(true);
                        }
                    } catch (Exception unused) {
                        ReactModuleAvailabilityUtils.saveLastAvailabilityResult(false);
                        ReactNativeUpdateRequest.RequestCallback requestCallback3 = requestCallback;
                        if (requestCallback3 != null) {
                            requestCallback3.onResult(false);
                        }
                    }
                }
            });
        }
    }
}
