package com.jingdong.aura.sdk.update;

import com.jd.dynamic.base.interfaces.IExceptionHandler;

/* loaded from: classes4.dex */
public final class b {
    public static void a(String str, String str2) {
        try {
            a.a().f12243m.onTrace(str, str2, IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD);
        } catch (Throwable unused) {
            com.jingdong.aura.sdk.update.b.c.b("report download bi error");
        }
    }

    public static void b(String str, String str2) {
        try {
            a.a().f12243m.onTrace(str, str2, "update");
        } catch (Throwable unused) {
            com.jingdong.aura.sdk.update.b.c.b("report update bi error");
        }
    }
}
