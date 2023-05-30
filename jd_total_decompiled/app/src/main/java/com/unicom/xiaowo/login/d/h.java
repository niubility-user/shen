package com.unicom.xiaowo.login.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;

/* loaded from: classes11.dex */
public class h {
    public static void a(Context context, String str) {
        a(context, "auth02", str);
    }

    public static String b(Context context, String str) {
        try {
            return context.getSharedPreferences("cu_auth", 0).getString(str, "");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(Context context) {
        String b = b(context, "auth02");
        if (TextUtils.isEmpty(b)) {
            String a = g.a(UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis());
            a(context, a);
            return a;
        }
        return b;
    }

    public static void a(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("cu_auth", 0).edit();
            edit.putString(str, str2);
            edit.commit();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
