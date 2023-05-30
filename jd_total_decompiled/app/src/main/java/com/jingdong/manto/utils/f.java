package com.jingdong.manto.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* loaded from: classes16.dex */
public final class f {
    public static String a(Context context) {
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(String.format("content://%s%s/%s", context.getPackageName(), ".manto.loginInfoProvider", 2)), null, null, null, null);
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                return query.getString(query.getColumnIndex("result"));
            }
        } catch (Exception e2) {
            MantoLog.e("better", "" + e2);
        }
        return "";
    }

    public static boolean b(Context context) {
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(String.format("content://%s%s/%s", context.getPackageName(), ".manto.loginInfoProvider", 1)), null, null, null, null);
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                return "1".equals(query.getString(query.getColumnIndex("result")));
            }
        } catch (Exception e2) {
            MantoLog.e("better", "" + e2);
        }
        return false;
    }
}
