package com.jingdong.common.jdreactFramework.utils;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import java.io.File;

/* loaded from: classes5.dex */
public class JDReactCrashReporter {
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
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("jdreact-sdk", "0.59.9");
        JDReactExReporter.post(th, arrayMap);
    }

    public static void post(Throwable th, ArrayMap<String, String> arrayMap) {
        JLog.d("JDReactCrashReporter", "post, " + arrayMap.toString());
        JDReactExReporter.post(th, arrayMap);
    }

    public static void post(String str) {
        post(new JDReactCommonException(str));
    }

    public static void post(String str, ArrayMap<String, String> arrayMap) {
        JDReactExReporter.post(new JDReactCommonException(str), arrayMap);
    }
}
