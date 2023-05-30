package com.jd.dynamic.a;

import com.jd.dynamic.engine.jni.TypeConvertor;
import com.jd.dynamic.lib.utils.m;

/* loaded from: classes13.dex */
public class b implements d {
    public long a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public long f1708c;

    public b(long j2, long j3, long j4) {
        this.a = j2;
        this.b = j3;
        this.f1708c = j4;
    }

    @Override // com.jd.dynamic.a.d
    public Object a(Object... objArr) {
        if (m.T(this.a)) {
            return null;
        }
        return h.b(this.a, TypeConvertor.JSFunctionCall(this.a, this.f1708c, this.b, h.d(this.a, objArr)));
    }
}
