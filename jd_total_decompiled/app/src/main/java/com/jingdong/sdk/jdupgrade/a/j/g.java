package com.jingdong.sdk.jdupgrade.a.j;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.jingdong.sdk.jdupgrade.UpgradeConfig;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public class g {
    private static SoftReference<Activity> a;

    /* loaded from: classes7.dex */
    public static class a implements Application.ActivityLifecycleCallbacks {

        /* renamed from: g */
        private volatile AtomicInteger f15099g = new AtomicInteger();

        /* renamed from: h */
        private UpgradeConfig f15100h;

        public a(UpgradeConfig upgradeConfig) {
            this.f15100h = upgradeConfig;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (com.jingdong.sdk.jdupgrade.a.c.Q()) {
                return;
            }
            b.a(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            com.jingdong.sdk.jdupgrade.a.c.Q();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            com.jingdong.sdk.jdupgrade.a.c.Q();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            if (com.jingdong.sdk.jdupgrade.a.c.Q() || !g.b(this.f15100h, activity)) {
                return;
            }
            SoftReference unused = g.a = new SoftReference(activity);
            b.a(true);
            if (com.jingdong.sdk.jdupgrade.inner.ui.c.f15145i) {
                com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.a(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (com.jingdong.sdk.jdupgrade.a.c.Q() || !g.b(this.f15100h, activity)) {
                return;
            }
            this.f15099g.getAndIncrement();
            b.a(this.f15099g.intValue() > 0);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (com.jingdong.sdk.jdupgrade.a.c.Q() || !g.b(this.f15100h, activity)) {
                return;
            }
            if (this.f15099g.intValue() > 0 && b.a(activity.getClass().getName())) {
                this.f15099g.getAndDecrement();
            }
            b.a(this.f15099g.intValue() > 0);
        }
    }

    public static Activity a() {
        SoftReference<Activity> softReference = a;
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }

    public static void a(Activity activity) {
        a = new SoftReference<>(activity);
        b.b(true);
    }

    public static void a(Application application, UpgradeConfig upgradeConfig) {
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(new a(upgradeConfig));
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean b(UpgradeConfig upgradeConfig, Activity activity) {
        if (upgradeConfig == null) {
            return true;
        }
        Class<? extends Activity>[] whitePages = upgradeConfig.getWhitePages();
        Class<? extends Activity>[] blackPages = upgradeConfig.getBlackPages();
        boolean contains = (whitePages == null || whitePages.length <= 0) ? true : Arrays.asList(whitePages).contains(activity.getClass());
        return (blackPages == null || blackPages.length <= 0) ? contains : !Arrays.asList(blackPages).contains(activity.getClass());
    }
}
