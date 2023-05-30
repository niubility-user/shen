package com.jd.dynamic.a;

import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.engine.jni.JavaScriptRuntime;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.t;

/* loaded from: classes13.dex */
public class j extends i {
    public j(long j2) {
        super(j2, -1L);
        long nanoTime = System.nanoTime();
        if (!m.T(u())) {
            JavaScriptRuntime.setJSProperty(u(), "__serialize_object_prefix__", c.f1709c);
            JavaScriptRuntime.setJSProperty(u(), "__serialize_array_prefix__", c.b);
            t.e("JSContext SetJSProperty", "take time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
            nanoTime = System.nanoTime();
            boolean h2 = com.jd.dynamic.b.a.b.o().h();
            long u = u();
            if (h2) {
                JavaScriptRuntime.evalJSResultNPT(u, c.a);
            } else {
                JavaScriptRuntime.evalJSResult(u, c.a);
            }
        }
        t.e("JSContext evalJSResult", "take time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
    }

    public static j r(long j2) {
        long nanoTime = System.nanoTime();
        long[] createJSContextNativeWithActivity = JavaScriptRuntime.createJSContextNativeWithActivity(j2);
        if (createJSContextNativeWithActivity == null || createJSContextNativeWithActivity.length != 2) {
            return t();
        }
        t.e("createJSContext Id", "take time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
        j s = s(createJSContextNativeWithActivity[1]);
        s.b = createJSContextNativeWithActivity[0];
        return s;
    }

    public static j s(long j2) {
        return new j(j2);
    }

    public static j t() {
        long nanoTime = System.nanoTime();
        long a = JavaScriptRuntime.a(com.jd.dynamic.b.a.b.o().j(), com.jd.dynamic.b.a.b.o().k());
        t.e("createJSContext Id", "take time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
        return s(a);
    }

    @Override // com.jd.dynamic.a.i
    public void q() {
        if (com.jd.dynamic.b.a.b.o().m()) {
            this.a = -1L;
        }
        this.b = -1L;
    }

    public long u() {
        return this.a;
    }
}
