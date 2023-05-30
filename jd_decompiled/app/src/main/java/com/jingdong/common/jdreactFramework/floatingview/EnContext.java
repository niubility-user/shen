package com.jingdong.common.jdreactFramework.floatingview;

import android.app.Application;

/* loaded from: classes5.dex */
public class EnContext {
    private static final Application INSTANCE;

    static {
        Application application;
        Throwable th;
        try {
            try {
                application = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                if (application == null) {
                    try {
                        throw new IllegalStateException("Static initialization of Applications must be on main thread.");
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        try {
                            application = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
                        } catch (Exception unused) {
                            e.printStackTrace();
                        }
                        INSTANCE = application;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                application = null;
            } catch (Throwable th2) {
                application = null;
                th = th2;
                INSTANCE = application;
                throw th;
            }
            INSTANCE = application;
        } catch (Throwable th3) {
            th = th3;
            INSTANCE = application;
            throw th;
        }
    }

    public static Application get() {
        return INSTANCE;
    }
}
