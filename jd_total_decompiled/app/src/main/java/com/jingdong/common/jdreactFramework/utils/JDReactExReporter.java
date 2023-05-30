package com.jingdong.common.jdreactFramework.utils;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDReactExReporter {
    private static final String TAG = "JDReactExReporter";

    private static String extractParams(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        String str = map.get("moduleName");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str + CartConstant.KEY_YB_INFO_LINK + map.get("appName") + CartConstant.KEY_YB_INFO_LINK + map.get("moduleVersion");
    }

    public static void post(Throwable th, Map<String, String> map) {
    }
}
