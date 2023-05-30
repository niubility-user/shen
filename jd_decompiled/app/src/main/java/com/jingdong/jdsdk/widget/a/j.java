package com.jingdong.jdsdk.widget.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.WindowManager;

/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(19)
/* loaded from: classes14.dex */
public final class j implements Application.ActivityLifecycleCallbacks {

    /* renamed from: g  reason: collision with root package name */
    private final ArrayMap<String, Activity> f12956g = new ArrayMap<>();

    /* renamed from: h  reason: collision with root package name */
    private final i f12957h;

    /* renamed from: i  reason: collision with root package name */
    private String f12958i;

    private j(i iVar) {
        this.f12957h = iVar;
    }

    private static String a(Object obj) {
        return obj.getClass().getName() + Integer.toHexString(obj.hashCode());
    }

    private static WindowManager c(Activity activity) {
        return (WindowManager) activity.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static j d(i iVar, Application application) {
        j jVar = new j(iVar);
        application.registerActivityLifecycleCallbacks(jVar);
        return jVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WindowManager b() throws NullPointerException {
        Activity activity;
        String str = this.f12958i;
        if (str != null && (activity = this.f12956g.get(str)) != null) {
            return c(activity);
        }
        throw null;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        String a = a(activity);
        this.f12958i = a;
        this.f12956g.put(a, activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.f12956g.remove(a(activity));
        if (a(activity).equals(this.f12958i)) {
            this.f12958i = null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.f12957h.a();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.f12958i = a(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.f12958i = a(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
