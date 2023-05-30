package com.jd.dynamic.a.a;

import com.jd.dynamic.engine.jni.TypeConvertor;
import com.jd.dynamic.lib.utils.m;

/* loaded from: classes13.dex */
public class d {
    public long a;
    public long b;

    public d(long j2, long j3) {
        this.a = j2;
        this.b = j3;
    }

    public long a(String str) {
        if (m.T(this.a)) {
            return -1L;
        }
        return TypeConvertor.JSValueGetProperty(this.a, this.b, str);
    }
}
