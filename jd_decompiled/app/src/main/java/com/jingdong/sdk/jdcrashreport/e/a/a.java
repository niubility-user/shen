package com.jingdong.sdk.jdcrashreport.e.a;

import android.text.TextUtils;
import com.jingdong.sdk.jdcrashreport.b.u;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/* loaded from: classes7.dex */
public class a {
    private int a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f14906c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f14907e;

    /* renamed from: f  reason: collision with root package name */
    private String f14908f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f14909g = true;

    public a(int i2, String str, String str2, String str3, String str4) {
        this.a = i2;
        this.b = str;
        this.f14906c = str2;
        this.d = str3;
        this.f14907e = str4;
    }

    public String a() {
        return this.b;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(boolean z) {
        this.f14909g = z;
    }

    public String d() {
        return this.f14906c;
    }

    public void e(String str) {
        this.f14907e = str;
    }

    public String f() {
        return this.d;
    }

    public String g() {
        return this.f14907e;
    }

    public String h() {
        if (!TextUtils.isEmpty(this.f14908f)) {
            return this.f14908f;
        }
        if (TextUtils.isEmpty(this.f14907e)) {
            return "";
        }
        try {
            this.f14908f = u.b(this.f14907e.trim().replaceAll("[0-9]+", "").getBytes("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            this.f14908f = "";
        }
        return this.f14908f;
    }

    public boolean i() {
        return this.f14909g;
    }

    public String toString() {
        return String.format(Locale.getDefault(), "pid: %d (%s), anr time: %s, main trace: \n%s", Integer.valueOf(this.a), this.b, this.f14906c, this.f14907e);
    }
}
