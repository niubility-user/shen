package com.jd.dynamic.a.a;

import com.jd.dynamic.engine.jni.TypeConvertor;
import com.jd.dynamic.lib.utils.m;

/* loaded from: classes13.dex */
public class e {
    public long a;
    public long b;

    public e(long j2, long j3) {
        this.a = j2;
        this.b = j3;
    }

    public boolean a() {
        if (m.T(this.a)) {
            return false;
        }
        return TypeConvertor.isJSString(this.a, this.b);
    }

    public String b() {
        return m.T(this.a) ? "" : TypeConvertor.JSValue2String(this.a, this.b);
    }

    public boolean c() {
        return m.T(this.a) || TypeConvertor.isJSNull(this.a, this.b) || TypeConvertor.isJSUndefined(this.a, this.b);
    }

    public boolean d() {
        if (m.T(this.a)) {
            return false;
        }
        return TypeConvertor.isJSObject(this.a, this.b);
    }

    public boolean e() {
        if (m.T(this.a)) {
            return false;
        }
        return TypeConvertor.isJSNumber(this.a, this.b);
    }

    public boolean f() {
        if (m.T(this.a)) {
            return false;
        }
        return TypeConvertor.isJSBoolean(this.a, this.b);
    }

    public boolean g() {
        if (m.T(this.a)) {
            return false;
        }
        return TypeConvertor.isJSFunction(this.a, this.b);
    }

    public double h() {
        if (m.T(this.a)) {
            return -1.0d;
        }
        return TypeConvertor.JSValue2Double(this.a, this.b);
    }

    public boolean i() {
        if (m.T(this.a)) {
            return false;
        }
        return TypeConvertor.JSValue2Boolean(this.a, this.b);
    }

    public String j() {
        return m.T(this.a) ? "" : TypeConvertor.createJSONString(this.a, this.b);
    }

    public void k() {
        if (m.T(this.a)) {
            return;
        }
        TypeConvertor.JSValueProtect(this.a, this.b);
    }

    public void l() {
        if (m.T(this.a)) {
            return;
        }
        TypeConvertor.JSValueUnProtect(this.a, this.b);
    }
}
