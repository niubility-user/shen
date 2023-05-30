package com.jd.dynamic.engine.jni;

import com.jd.dynamic.DYConstants;
import com.jd.dynamic.a.a.a.n;
import com.jd.dynamic.a.h;
import com.jd.dynamic.b.a.b;
import com.jd.dynamic.lib.utils.m;

/* loaded from: classes13.dex */
public class JSCBridge {
    public static long startTime;
    private long jsContext;
    private a mCallback;
    private n timer;

    /* loaded from: classes13.dex */
    public interface a {
        Object a(String str, String str2, Object... objArr);
    }

    public JSCBridge(long j2, a aVar, boolean z) {
        this.jsContext = j2;
        this.mCallback = aVar;
        if (m.T(j2)) {
            return;
        }
        DYConstants.DYLog("xpj2233 init bridge result is : " + initBridge(j2, z));
        prepareNativeFunctionWithParams(j2);
        this.timer = new n(j2);
    }

    public static native long evalEvents(long j2, String str, String str2, int i2, boolean z);

    private native boolean initBridge(long j2, boolean z);

    private long invoke(String str, String str2, long... jArr) {
        if (m.T(this.jsContext)) {
            return -1L;
        }
        try {
            return h.a(this.jsContext, this.mCallback.a(str, str2, h.e(this.jsContext, jArr)));
        } catch (Exception unused) {
            return TypeConvertor.makeUndefined(this.jsContext);
        }
    }

    private native void prepareNativeFunctionWithParams(long j2);

    private void setTimeout(long... jArr) {
        if (h.e(this.jsContext, jArr).length < 2) {
            return;
        }
        startTime = System.currentTimeMillis();
        this.timer.c(jArr[0], (int) ((Double) r0[1]).doubleValue());
    }

    public void release() {
        if (!m.T(this.jsContext)) {
            releaseJSCBridge(this.jsContext);
        }
        this.timer.b();
        if (b.o().m()) {
            this.jsContext = -1L;
        }
        this.mCallback = null;
    }

    public native void releaseJSCBridge(long j2);

    public void resetData() {
        this.timer.b();
    }
}
