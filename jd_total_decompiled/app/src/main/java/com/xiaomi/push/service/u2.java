package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class u2 {

    /* renamed from: e  reason: collision with root package name */
    private static u2 f19192e;
    private Context a;
    private List<String> b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private final List<String> f19193c = new ArrayList();
    private final List<String> d = new ArrayList();

    private u2(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        if (applicationContext == null) {
            this.a = context;
        }
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("mipush_app_info", 0);
        for (String str : sharedPreferences.getString("unregistered_pkg_names", "").split(DYConstants.DY_REGEX_COMMA)) {
            if (TextUtils.isEmpty(str)) {
                this.b.add(str);
            }
        }
        for (String str2 : sharedPreferences.getString("disable_push_pkg_names", "").split(DYConstants.DY_REGEX_COMMA)) {
            if (!TextUtils.isEmpty(str2)) {
                this.f19193c.add(str2);
            }
        }
        for (String str3 : sharedPreferences.getString("disable_push_pkg_names_cache", "").split(DYConstants.DY_REGEX_COMMA)) {
            if (!TextUtils.isEmpty(str3)) {
                this.d.add(str3);
            }
        }
    }

    public static u2 a(Context context) {
        if (f19192e == null) {
            f19192e = new u2(context);
        }
        return f19192e;
    }

    public void b(String str) {
        synchronized (this.b) {
            if (!this.b.contains(str)) {
                this.b.add(str);
                this.a.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", com.xiaomi.push.p0.d(this.b, DYConstants.DY_REGEX_COMMA)).commit();
            }
        }
    }

    public boolean c(String str) {
        boolean contains;
        synchronized (this.b) {
            contains = this.b.contains(str);
        }
        return contains;
    }

    public void d(String str) {
        synchronized (this.f19193c) {
            if (!this.f19193c.contains(str)) {
                this.f19193c.add(str);
                this.a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names", com.xiaomi.push.p0.d(this.f19193c, DYConstants.DY_REGEX_COMMA)).commit();
            }
        }
    }

    public boolean e(String str) {
        boolean contains;
        synchronized (this.f19193c) {
            contains = this.f19193c.contains(str);
        }
        return contains;
    }

    public void f(String str) {
        synchronized (this.d) {
            if (!this.d.contains(str)) {
                this.d.add(str);
                this.a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names_cache", com.xiaomi.push.p0.d(this.d, DYConstants.DY_REGEX_COMMA)).commit();
            }
        }
    }

    public boolean g(String str) {
        boolean contains;
        synchronized (this.d) {
            contains = this.d.contains(str);
        }
        return contains;
    }

    public void h(String str) {
        synchronized (this.b) {
            if (this.b.contains(str)) {
                this.b.remove(str);
                this.a.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", com.xiaomi.push.p0.d(this.b, DYConstants.DY_REGEX_COMMA)).commit();
            }
        }
    }

    public void i(String str) {
        synchronized (this.f19193c) {
            if (this.f19193c.contains(str)) {
                this.f19193c.remove(str);
                this.a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names", com.xiaomi.push.p0.d(this.f19193c, DYConstants.DY_REGEX_COMMA)).commit();
            }
        }
    }

    public void j(String str) {
        synchronized (this.d) {
            if (this.d.contains(str)) {
                this.d.remove(str);
                this.a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names_cache", com.xiaomi.push.p0.d(this.d, DYConstants.DY_REGEX_COMMA)).commit();
            }
        }
    }
}
