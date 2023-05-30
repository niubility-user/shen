package com.jingdong.jdpush_new.j;

import android.content.Context;
import android.content.SharedPreferences;
import com.jd.lib.push.utils.PushMessageUtils;

/* loaded from: classes12.dex */
public class i {
    private static i a;

    public static String c(Context context, int i2) {
        return PushMessageUtils.getMIRegId(context, i2);
    }

    public static i d() {
        if (a == null) {
            synchronized (i.class) {
                if (a == null) {
                    a = new i();
                }
            }
        }
        return a;
    }

    public static SharedPreferences e(Context context) {
        return context.getSharedPreferences("jspush_data", 0);
    }

    public static void h(Context context, int i2, String str) {
        PushMessageUtils.saveMIRegId(context, str, i2);
    }

    public Object a(Context context, String str, Object obj) {
        return b(context, "jspush_data", str, obj);
    }

    public Object b(Context context, String str, String str2, Object obj) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        if (obj instanceof String) {
            return sharedPreferences.getString(str2, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str2, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str2, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str2, ((Float) obj).floatValue()));
        }
        return obj instanceof Long ? Long.valueOf(sharedPreferences.getLong(str2, ((Long) obj).longValue())) : "";
    }

    public void f(Context context, String str, Object obj) {
        g(context, "jspush_data", str, obj);
    }

    public void g(Context context, String str, String str2, Object obj) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        if (obj instanceof String) {
            edit.putString(str2, (String) obj);
        } else if (obj instanceof Integer) {
            edit.putInt(str2, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str2, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str2, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            edit.putLong(str2, ((Long) obj).longValue());
        } else {
            edit.putString(str2, obj.toString());
        }
        edit.apply();
    }
}
