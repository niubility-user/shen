package performance.jd.jdreportperformance.entity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* loaded from: classes.dex */
public final class a implements Application.ActivityLifecycleCallbacks {

    /* renamed from: c  reason: collision with root package name */
    private static volatile a f20466c;
    private boolean a = false;
    private int b = 0;

    public static a a() {
        if (f20466c == null) {
            synchronized (a.class) {
                if (f20466c == null) {
                    f20466c = new a();
                }
            }
        }
        return f20466c;
    }

    public String b() {
        return this.a ? "1" : "0";
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
        int i2 = this.b + 1;
        this.b = i2;
        if (i2 == 1) {
            this.a = false;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        int i2 = this.b - 1;
        this.b = i2;
        if (i2 == 0) {
            this.a = true;
        }
    }
}
