package com.jingdong.common.jdreactFramework.utils.apm;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jingdong.common.jdreactFramework.utils.JDReactCommonException;
import com.jingdong.common.jdreactFramework.utils.JLog;
import java.io.File;

/* loaded from: classes5.dex */
public class ApmExceptionReporter {
    private static final String TAG = "ApmExceptionReporter";

    public static void checkFileAndReport(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (new File(str).exists()) {
                return;
            }
            post("react_file_not_exists:" + str + " module:" + str2);
            return;
        }
        post("react_file_empty module:" + str2);
    }

    public static void post(Throwable th) {
        if (th == null) {
            return;
        }
        JLog.d(TAG, "post 1");
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("jdreact-sdk", "0.55.9");
        JDReactApmReporter.post(th, arrayMap);
    }

    public static void post(Throwable th, ArrayMap arrayMap) {
        JDReactApmReporter.post(th, arrayMap);
    }

    public static void post(String str) {
        JLog.d(TAG, "post 3, " + str);
        post(new JDReactCommonException(str));
    }

    public static void post(String str, ArrayMap<String, String> arrayMap) {
        JLog.d(TAG, "post 4, " + arrayMap.toString());
        JDReactApmReporter.post(new JDReactCommonException(str), arrayMap);
    }
}
