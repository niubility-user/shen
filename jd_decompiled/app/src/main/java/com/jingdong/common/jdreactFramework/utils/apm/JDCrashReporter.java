package com.jingdong.common.jdreactFramework.utils.apm;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDCrashReporter {
    private static final String TAG = "JDCrashReporter";

    private static String parseAppName(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return map.get("appName");
    }

    private static String parseCommitId(Map<String, String> map) {
        return (map == null || map.isEmpty() || TextUtils.isEmpty(map.get(JDReactConstant.COMMITID))) ? "" : map.get(JDReactConstant.COMMITID);
    }

    private static String parseModuleName(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return map.get("moduleName");
    }

    private static String parseModuleVersion(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return map.get("moduleVersion");
    }

    public static void post(Throwable th, ArrayMap arrayMap) {
        String parseModuleName = parseModuleName(arrayMap);
        String parseModuleVersion = parseModuleVersion(arrayMap);
        String parseCommitId = parseCommitId(arrayMap);
        JdCrashReport.postRNException(parseModuleName, parseModuleVersion, parseCommitId, th);
        JLog.e(TAG, " moduleName\uff1a" + parseModuleName + " moduleVersion\uff1a " + parseModuleVersion + " commitId\uff1a" + parseCommitId);
    }
}
