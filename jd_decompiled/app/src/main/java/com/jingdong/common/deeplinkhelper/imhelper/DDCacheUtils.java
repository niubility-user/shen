package com.jingdong.common.deeplinkhelper.imhelper;

import android.content.SharedPreferences;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.JDSharedPreferences;

/* loaded from: classes5.dex */
public class DDCacheUtils {
    private static final String CACHE_NAME = "dd_cache";
    private static final String UNREAD_COUNT = "getUnreadCount";
    private static SharedPreferences mSharedPreferences;

    public static synchronized SharedPreferences getSharedPreferences() {
        SharedPreferences sharedPreferences;
        synchronized (DDCacheUtils.class) {
            if (mSharedPreferences == null) {
                mSharedPreferences = new JDSharedPreferences(JdSdk.getInstance().getApplicationContext(), CACHE_NAME, 0);
            }
            sharedPreferences = mSharedPreferences;
        }
        return sharedPreferences;
    }

    public static String getUnReadCount(String str) {
        return getSharedPreferences().getString(UNREAD_COUNT + str, "");
    }

    public static void putUnReadCount(String str, String str2) {
        getSharedPreferences().edit().putString(UNREAD_COUNT + str, str2).apply();
    }
}
