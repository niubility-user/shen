package com.jingdong.manto.m.f1;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* loaded from: classes15.dex */
final class a {
    private static String a(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow("_data");
            cursor.moveToFirst();
            return cursor.getString(columnIndexOrThrow);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static String a(Context context, String str) {
        if (context == null || str == null) {
            return null;
        }
        return str.startsWith("file") ? str.substring(str.indexOf(":") + 3) : str.startsWith("content") ? a(context, Uri.parse(str)) : str;
    }
}
