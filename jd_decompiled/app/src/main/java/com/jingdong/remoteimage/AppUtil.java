package com.jingdong.remoteimage;

import android.app.Application;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes7.dex */
class AppUtil {
    private static volatile Application sApplication;

    AppUtil() {
    }

    public static synchronized Application getApplication() {
        Application application;
        synchronized (AppUtil.class) {
            if (sApplication == null) {
                sApplication = JdSdk.getInstance().getApplication();
            }
            application = sApplication;
        }
        return application;
    }

    public static synchronized void init(Application application) {
        synchronized (AppUtil.class) {
            sApplication = application;
        }
    }
}
