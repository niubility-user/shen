package com.jingdong.aura;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes4.dex */
public class DownGradeUtils {
    public static final long END_TIME = 1643652000000L;
    public static final long START_TIME = 1643623200000L;
    private static final String TAG = "DownGradeUtils";
    public static Uri uri;

    public static synchronized boolean isDownGrade() {
        synchronized (DownGradeUtils.class) {
        }
        return false;
    }

    private static boolean isXTimeWrapper() {
        try {
            return SwitchQueryFetcher.isXTime();
        } catch (Throwable unused) {
            return localXTime();
        }
    }

    private static boolean localXTime() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis >= START_TIME && currentTimeMillis <= END_TIME;
    }

    private static boolean otherProcessDowngrade() {
        Cursor cursor = null;
        try {
            try {
                uri = Uri.parse("content://" + JdSdk.getInstance().getApplication().getPackageName() + ".xtimeprovider");
                Cursor query = JdSdk.getInstance().getApplication().getContentResolver().query(uri, null, null, null, null);
                if (query == null) {
                    if (Log.D) {
                        Log.e(TAG, "cursor is null");
                    }
                    boolean isXTimeWrapper = isXTimeWrapper();
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                    return isXTimeWrapper;
                }
                Bundle extras = query.getExtras();
                if (extras == null) {
                    if (Log.D) {
                        Log.e(TAG, "bundle is null");
                    }
                    boolean isXTimeWrapper2 = isXTimeWrapper();
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                    return isXTimeWrapper2;
                }
                if (Log.D) {
                    Log.i(TAG, "bundle:" + extras.getBoolean(XTimeCursor.XTIME_KEY));
                }
                boolean z = extras.getBoolean(XTimeCursor.XTIME_KEY, isXTimeWrapper());
                if (query != null && !query.isClosed()) {
                    query.close();
                }
                return z;
            } catch (Throwable unused) {
                if (Log.D) {
                    Log.e(TAG, "provider error");
                }
                return isXTimeWrapper();
            }
        } finally {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
}
