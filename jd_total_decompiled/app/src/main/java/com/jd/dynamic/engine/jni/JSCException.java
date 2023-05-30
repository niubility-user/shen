package com.jd.dynamic.engine.jni;

import com.jd.dynamic.a.a.b;
import com.jd.dynamic.a.a.c;
import com.jd.dynamic.a.j;
import com.jd.dynamic.lib.utils.m;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes13.dex */
public class JSCException {
    private static ConcurrentHashMap<Long, List<b>> contextCallbacks = new ConcurrentHashMap<>();

    /* loaded from: classes13.dex */
    public interface JSExceptionCallback {
        void onException(long j2, String str, String str2);
    }

    public static void addJSContextExceptionCallback(j jVar, b bVar) {
        if (m.T(jVar.u())) {
            return;
        }
        List<b> list = contextCallbacks.get(Long.valueOf(jVar.u()));
        if (list == null) {
            list = new ArrayList<>();
            contextCallbacks.put(Long.valueOf(jVar.u()), list);
        }
        list.add(bVar);
    }

    public static void dispatchExceptionCallback(long j2, String str, Exception exc) {
        List<b> list = contextCallbacks.get(Long.valueOf(j2));
        if (list != null) {
            for (b bVar : list) {
                if (bVar != null) {
                    bVar.a(str, exc);
                }
            }
        }
    }

    public static void init() {
        initJSException(new JSExceptionCallback() { // from class: com.jd.dynamic.engine.jni.a
            @Override // com.jd.dynamic.engine.jni.JSCException.JSExceptionCallback
            public final void onException(long j2, String str, String str2) {
                JSCException.dispatchExceptionCallback(j2, str, new c(str2));
            }
        });
    }

    public static native void initJSException(JSExceptionCallback jSExceptionCallback);

    public static void release(long j2) {
        try {
            List<b> list = contextCallbacks.get(Long.valueOf(j2));
            if (list != null) {
                list.clear();
            }
            contextCallbacks.remove(Long.valueOf(j2));
        } catch (Exception unused) {
        }
    }
}
