package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;

/* loaded from: classes11.dex */
public class p0 {

    /* renamed from: c  reason: collision with root package name */
    private static p0 f19171c;
    private Context a;
    private int b = 0;

    private p0(Context context) {
        this.a = context.getApplicationContext();
    }

    public static p0 c(Context context) {
        if (f19171c == null) {
            f19171c = new p0(context);
        }
        return f19171c;
    }

    @SuppressLint({"NewApi"})
    public int a() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        try {
            this.b = Settings.Global.getInt(this.a.getContentResolver(), "device_provisioned", 0);
        } catch (Exception unused) {
        }
        return this.b;
    }

    @SuppressLint({"NewApi"})
    public Uri b() {
        return Settings.Global.getUriFor("device_provisioned");
    }

    public boolean d() {
        String str = com.xiaomi.push.b.a;
        return str.contains("xmsf") || str.contains("xiaomi") || str.contains("miui");
    }
}
