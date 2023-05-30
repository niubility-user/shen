package com.huawei.hms.base.log;

import android.content.Context;
import android.util.Log;

/* loaded from: classes12.dex */
public class b {
    private String b;
    private int a = 4;

    /* renamed from: c  reason: collision with root package name */
    private d f1228c = new c();

    private void b() {
    }

    public void a(Context context, int i2, String str) {
        this.a = i2;
        this.b = str;
        this.f1228c.a(context, "HMSCore");
    }

    public void b(int i2, String str, String str2, Throwable th) {
        try {
            if (a(i2)) {
                e a = a(i2, str, str2, th);
                String str3 = a.c() + a.a();
                this.f1228c.a(str3, i2, str, str2 + '\n' + Log.getStackTraceString(th));
            }
        } catch (OutOfMemoryError unused) {
            b();
        }
    }

    public d a() {
        return this.f1228c;
    }

    public void a(d dVar) {
        this.f1228c = dVar;
    }

    public boolean a(int i2) {
        return i2 >= this.a;
    }

    public void a(int i2, String str, String str2) {
        try {
            if (a(i2)) {
                e a = a(i2, str, str2, null);
                this.f1228c.a(a.c() + a.a(), i2, str, str2);
            }
        } catch (OutOfMemoryError unused) {
            b();
        }
    }

    public void a(String str, String str2) {
        try {
            e a = a(4, str, str2, null);
            this.f1228c.a(a.c() + '\n' + a.a(), 4, str, str2);
        } catch (OutOfMemoryError unused) {
            b();
        }
    }

    private e a(int i2, String str, String str2, Throwable th) {
        e eVar = new e(8, this.b, i2, str);
        eVar.a((e) str2);
        eVar.a(th);
        return eVar;
    }
}
