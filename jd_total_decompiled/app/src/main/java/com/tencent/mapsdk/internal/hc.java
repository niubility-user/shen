package com.tencent.mapsdk.internal;

import android.content.SharedPreferences;

/* loaded from: classes9.dex */
public abstract class hc {
    private static final String b = "%s-%s-%s,%s-%s-%s,%s-%s-%s";
    public SharedPreferences a = null;

    public int a(String str, int i2) {
        SharedPreferences sharedPreferences = this.a;
        return sharedPreferences == null ? i2 : sharedPreferences.getInt(str, i2);
    }

    public String a() {
        return String.format(b, j4.f16729i, Integer.valueOf(b(l4.a)), d(l4.t), j4.f16732l, Integer.valueOf(b("indoormap_style_version")), d("indoormap_style_md5"), j4.f16733m, Integer.valueOf(b("indoormap_style_night_version")), d("indoormap_style_night_md5"));
    }

    public String a(String str, String str2) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString(str, str2);
    }

    public boolean a(String str) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.getBoolean(str, false);
    }

    public boolean a(String str, long j2) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.edit().putLong(str, j2).commit();
    }

    public boolean a(String str, boolean z) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.edit().putBoolean(str, z).commit();
    }

    public boolean a(String[] strArr) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return false;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (String str : strArr) {
            edit.remove(str);
        }
        return edit.commit();
    }

    public int b(String str) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return -1;
        }
        return sharedPreferences.getInt(str, -1);
    }

    public boolean b() {
        return a(new String[]{l4.a, l4.f16790c, l4.d, l4.q, l4.r, l4.s, l4.t, l4.u, l4.v, l4.w, l4.x, l4.y});
    }

    public boolean b(String str, int i2) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.edit().putInt(str, i2).commit();
    }

    public boolean b(String str, String str2) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.edit().putString(str, str2).commit();
    }

    public long c(String str) {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return -1L;
        }
        return sharedPreferences.getLong(str, -1L);
    }

    public String d(String str) {
        return a(str, (String) null);
    }
}
