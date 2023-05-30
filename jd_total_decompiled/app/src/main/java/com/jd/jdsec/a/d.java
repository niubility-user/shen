package com.jd.jdsec.a;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* loaded from: classes13.dex */
public class d {
    private static boolean a;
    private static int b;

    /* renamed from: c  reason: collision with root package name */
    private static final Application.ActivityLifecycleCallbacks f2721c = new a();

    /* loaded from: classes13.dex */
    class a implements Application.ActivityLifecycleCallbacks {
        a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            d.a();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            d.c();
        }
    }

    static /* synthetic */ int a() {
        int i2 = b;
        b = i2 + 1;
        return i2;
    }

    public static synchronized void b(Application application) {
        synchronized (d.class) {
            if (a) {
                return;
            }
            if (application != null) {
                application.registerActivityLifecycleCallbacks(f2721c);
                a = true;
            }
        }
    }

    static /* synthetic */ int c() {
        int i2 = b;
        b = i2 - 1;
        return i2;
    }
}
