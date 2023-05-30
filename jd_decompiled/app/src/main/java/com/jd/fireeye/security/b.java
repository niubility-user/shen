package com.jd.fireeye.security;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class b {
    public static HashMap a(Context context) {
        Cursor cursor;
        HashMap hashMap = new HashMap();
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            cursor = context.getContentResolver().query(Uri.parse("content://com.huawei.appmarket.commondata/item/5"), null, null, new String[]{context.getPackageName()}, null);
            if (cursor != null) {
                try {
                    cursor.moveToFirst();
                    if (cursor.getColumnCount() > 4) {
                        String string = cursor.getString(1);
                        String string2 = cursor.getString(2);
                        cursor.getString(3);
                        String string3 = cursor.getString(4);
                        String str = "referrer=" + string3;
                        String str2 = "clickTime=" + string;
                        String str3 = "installTime=" + string2;
                        if (!TextUtils.isEmpty(string3)) {
                            try {
                                hashMap.put("referrer", new JSONObject(string3).getString("channel"));
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                        }
                        hashMap.put("clicktime", string);
                        hashMap.put("installtime", string2);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return hashMap;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return hashMap;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }
}
