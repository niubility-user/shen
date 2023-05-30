package com.jd.dynamic.a;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.dynamic.engine.jni.TypeConvertor;
import com.jd.dynamic.lib.utils.m;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class i {
    public long a;

    /* renamed from: c */
    public long f1715c;
    private com.jd.dynamic.a.a.d d;

    /* renamed from: e */
    private com.jd.dynamic.a.a.e f1716e;
    public long b = -1;

    /* renamed from: f */
    private volatile boolean f1717f = true;

    public i(long j2, long j3) {
        this.a = j2;
        this.f1715c = j3;
        this.d = new com.jd.dynamic.a.a.d(j2, j3);
        this.f1716e = new com.jd.dynamic.a.a.e(j2, j3);
    }

    public i b(String str) {
        return new i(this.a, this.d.a(str));
    }

    public Object c(long j2, String str, String str2, String str3) {
        if (m.T(this.a)) {
            return null;
        }
        boolean i2 = com.jd.dynamic.b.a.b.o().i();
        long j3 = this.a;
        long j4 = this.f1715c;
        if (!i2) {
            return h.b(this.a, TypeConvertor.JSTransDataCall(j3, j4, j2, str, str2, str3, com.jd.dynamic.b.a.b.o().h()));
        }
        String JSTransDataCallToString = TypeConvertor.JSTransDataCallToString(j3, j4, j2, str, str2, str3, com.jd.dynamic.b.a.b.o().h());
        if (!TextUtils.isEmpty(JSTransDataCallToString) && JSTransDataCallToString.startsWith(c.f1709c)) {
            try {
                return new JSONObject(JSTransDataCallToString.replace(c.f1709c, ""));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public boolean d() {
        if (m.T(this.a)) {
            return false;
        }
        return TypeConvertor.isJSValueValid(this.a, this.f1715c);
    }

    public boolean e() {
        return this.f1716e.e();
    }

    public boolean f() {
        return this.f1716e.f();
    }

    public boolean g() {
        return this.f1716e.a();
    }

    public boolean h() {
        return this.f1716e.g();
    }

    public boolean i() {
        return this.f1716e.c();
    }

    public boolean j() {
        return this.f1716e.d();
    }

    public double k() {
        return this.f1716e.h();
    }

    public boolean l() {
        return this.f1716e.i();
    }

    public String m() {
        return this.f1716e.j();
    }

    public String n() {
        return this.f1716e.b();
    }

    public void o() {
        if (this.f1717f) {
            this.f1717f = false;
            this.f1716e.k();
        }
    }

    public void p() {
        if (this.f1717f) {
            return;
        }
        this.f1717f = true;
        this.f1716e.l();
    }

    public void q() {
        p();
        if (com.jd.dynamic.b.a.b.o().m()) {
            this.a = -1L;
            this.b = -1L;
            this.f1716e.a = -1L;
            this.d.a = -1L;
        }
    }

    @NonNull
    public String toString() {
        return "c = " + this.a + " v= " + this.f1715c;
    }
}
