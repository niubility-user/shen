package com.jd.libs.hybrid.base.util;

import androidx.annotation.Keep;
import java.util.HashMap;

@Keep
/* loaded from: classes16.dex */
public class PerfMtaUtils {
    private static CustomMtaSender sender;

    @Keep
    /* loaded from: classes16.dex */
    public interface CustomMtaSender {
        void onConfigGot(HashMap<String, String> hashMap);

        void onDownloaded(HashMap<String, String> hashMap);
    }

    public static void onConfigGot(HashMap<String, String> hashMap) {
        CustomMtaSender customMtaSender = sender;
        if (customMtaSender != null) {
            customMtaSender.onConfigGot(hashMap);
        }
    }

    public static void onDownloaded(HashMap<String, String> hashMap) {
        CustomMtaSender customMtaSender = sender;
        if (customMtaSender != null) {
            customMtaSender.onDownloaded(hashMap);
        }
    }

    public static void setCustomMtaSender(CustomMtaSender customMtaSender) {
        sender = customMtaSender;
    }
}
