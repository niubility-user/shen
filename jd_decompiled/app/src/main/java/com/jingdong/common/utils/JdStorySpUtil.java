package com.jingdong.common.utils;

import android.content.SharedPreferences;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;

/* loaded from: classes6.dex */
public class JdStorySpUtil {
    public static synchronized SharedPreferences getJdStorySharedPreferences() {
        SharedPreferences sharedPreferences;
        synchronized (JdStorySpUtil.class) {
            sharedPreferences = JdSdk.getInstance().getApplication().getSharedPreferences(Constants.SHARE_PREFRENCE_NAME, 0);
        }
        return sharedPreferences;
    }

    public static String getString(String str) {
        if (str == null || !getJdStorySharedPreferences().contains(str)) {
            return null;
        }
        return getJdStorySharedPreferences().getString(str, null);
    }
}
