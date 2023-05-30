package com.jingdong.sdk.baseinfo.c;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jd.android.sdk.coreinfo.util.Logger;

/* loaded from: classes.dex */
public final class a {
    private static String a;

    public static synchronized String a(Context context) {
        synchronized (a.class) {
            String str = a;
            if (str != null) {
                return str;
            }
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver != null) {
                Uri parse = Uri.parse("content://" + context.getPackageName() + ".baseinfo.PrivacyInfoProvider/baseinfo_db2");
                String a2 = c.a("aid");
                if (!TextUtils.isEmpty(a2)) {
                    Cursor query = contentResolver.query(parse, new String[]{"_value_"}, "_key_=?", new String[]{a2}, null);
                    if (query != null) {
                        if (query.moveToNext()) {
                            a = c.b(query.getString(query.getColumnIndex("_value_")));
                        }
                        query.close();
                    } else {
                        Logger.e("BaseInfoCP", "cursor is null");
                    }
                }
                if (a == null) {
                    a = CoreInfo.Device.getAndroidId(context);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_key_", c.a("aid"));
                    contentValues.put("_value_", c.a(a));
                    contentResolver.insert(parse, contentValues);
                }
            }
            if (a == null) {
                a = CoreInfo.Device.getAndroidId(context);
                Logger.e("BaseInfoCP", "get value from system : " + a);
            }
            return a;
        }
    }

    public static synchronized String b(Context context) {
        synchronized (a.class) {
            String str = a;
            if (str != null) {
                return str;
            }
            String a2 = c.a("aid");
            SharedPreferences sharedPreferences = context.getSharedPreferences("mpaas_baseinfo", 0);
            String string = sharedPreferences.getString(a2, "");
            if (TextUtils.isEmpty(string)) {
                String androidId = CoreInfo.Device.getAndroidId(context);
                a = androidId;
                sharedPreferences.edit().putString(a2, c.a(androidId)).commit();
            } else {
                a = c.b(string);
            }
            return a;
        }
    }
}
