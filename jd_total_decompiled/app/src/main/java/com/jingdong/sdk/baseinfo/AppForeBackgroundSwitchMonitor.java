package com.jingdong.sdk.baseinfo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.jd.android.sdk.coreinfo.util.Logger;

/* loaded from: classes.dex */
public class AppForeBackgroundSwitchMonitor {
    private static boolean a;
    private static int b;

    /* renamed from: c  reason: collision with root package name */
    private static final Application.ActivityLifecycleCallbacks f14675c = new Application.ActivityLifecycleCallbacks() { // from class: com.jingdong.sdk.baseinfo.AppForeBackgroundSwitchMonitor.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            if (AppForeBackgroundSwitchMonitor.b == 0) {
                AppForeBackgroundSwitchMonitor.a();
            }
            AppForeBackgroundSwitchMonitor.d();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            AppForeBackgroundSwitchMonitor.e();
            if (AppForeBackgroundSwitchMonitor.b == 0) {
                AppForeBackgroundSwitchMonitor.b();
            }
        }
    };

    static void a() {
        Logger.d("BaseInfo.AppMonitor", "onBackToForeground");
    }

    static void b() {
        try {
            Logger.d("BaseInfo.AppMonitor", "onForeToBackground");
            com.jingdong.sdk.baseinfo.b.b.d();
        } catch (Exception e2) {
            Logger.e("BaseInfo.AppMonitor", "", e2);
        }
    }

    static /* synthetic */ int d() {
        int i2 = b;
        b = i2 + 1;
        return i2;
    }

    static /* synthetic */ int e() {
        int i2 = b;
        b = i2 - 1;
        return i2;
    }

    public static synchronized void init(Application application) {
        synchronized (AppForeBackgroundSwitchMonitor.class) {
            if (a) {
                return;
            }
            if (application != null) {
                application.registerActivityLifecycleCallbacks(f14675c);
                a = true;
            }
        }
    }

    public static boolean isAppForegroundRunning() {
        return b > 0;
    }
}
