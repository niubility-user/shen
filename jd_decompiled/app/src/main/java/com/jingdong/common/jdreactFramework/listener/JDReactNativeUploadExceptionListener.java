package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSharedDataModule;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JDReactCommonException;
import com.jingdong.common.jdreactFramework.utils.JDReactCrashReporter;
import com.jingdong.common.jdreactFramework.utils.apm.ApmExceptionReporter;
import com.jingdong.common.jdreactFramework.utils.apm.JDCrashReporter;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeUploadExceptionListener implements NativeUploadExceptionListener, JDFlutterCall {
    public static final String UPLOADEXCEPTIONCHANNEL = "com.jd.jdflutter/uploadException";

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("uploadException")) {
            uploadException(hashMap.containsKey("desc") ? (String) hashMap.get("desc") : "");
            jDFlutterCallResult.success(DYConstants.DY_TRUE);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeUploadExceptionListener
    public boolean uploadException(String str) {
        String data = JDReactNativeSharedDataModule.getData("reportLog");
        if (TextUtils.isEmpty(data)) {
            return false;
        }
        if ((TextUtils.isEmpty(data) || Boolean.valueOf(data).booleanValue()) && !TextUtils.isEmpty(str)) {
            JDReactCrashReporter.post(new JDReactCommonException(str));
            ApmExceptionReporter.post(new JDReactCommonException(str));
            return true;
        }
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeUploadExceptionListener
    public boolean uploadExceptionNew(HashMap hashMap) {
        PluginVersion pluginDir;
        try {
            String str = hashMap.containsKey("channel") ? (String) hashMap.get("channel") : "";
            String str2 = hashMap.containsKey("moduleName") ? (String) hashMap.get("moduleName") : "";
            String str3 = hashMap.containsKey("crashInfo") ? (String) hashMap.get("crashInfo") : "";
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                ArrayMap arrayMap = new ArrayMap();
                arrayMap.put("moduleName", str2);
                Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
                String str4 = (currentMyActivity == null || (pluginDir = ReactNativeFileManager.getPluginDir(currentMyActivity, str2)) == null) ? "" : pluginDir.pluginVersion;
                arrayMap.put("appName", "");
                if (!TextUtils.isEmpty(str4)) {
                    arrayMap.put("moduleVersion", str4);
                }
                if ("2".equals(str)) {
                    ApmExceptionReporter.post(new JDReactCommonException(str3), arrayMap);
                    return true;
                } else if ("1".equals(str)) {
                    JDCrashReporter.post(new JDReactCommonException(str3), arrayMap);
                    return true;
                } else {
                    JDCrashReporter.post(new JDReactCommonException(str3), arrayMap);
                    ApmExceptionReporter.post(new JDReactCommonException(str3), arrayMap);
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
