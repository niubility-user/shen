package com.jingdong.app.mall.log;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* loaded from: classes4.dex */
public class d implements Application.ActivityLifecycleCallbacks {

    /* renamed from: g  reason: collision with root package name */
    private c f11186g;

    /* renamed from: h  reason: collision with root package name */
    private String f11187h;

    /* renamed from: i  reason: collision with root package name */
    private LogStrategyParam f11188i;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class b {
        private static d a = new d();
    }

    public static d b() {
        return b.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a() {
        return this.f11187h;
    }

    public void c(LogStrategyParam logStrategyParam) {
        this.f11188i = logStrategyParam;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        LogStrategyParam logStrategyParam = this.f11188i;
        if (logStrategyParam == null || !logStrategyParam.create) {
            return;
        }
        this.f11186g.b(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        LogStrategyParam logStrategyParam = this.f11188i;
        if (logStrategyParam == null || !logStrategyParam.destroy) {
            return;
        }
        this.f11186g.c(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        LogStrategyParam logStrategyParam = this.f11188i;
        if (logStrategyParam == null || !logStrategyParam.pause) {
            return;
        }
        this.f11186g.d(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.f11187h = activity.getClass().getName();
        LogStrategyParam logStrategyParam = this.f11188i;
        if (logStrategyParam == null || !logStrategyParam.resume) {
            return;
        }
        this.f11186g.e(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        LogStrategyParam logStrategyParam = this.f11188i;
        if (logStrategyParam == null || !logStrategyParam.sIS) {
            return;
        }
        this.f11186g.f(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        LogStrategyParam logStrategyParam = this.f11188i;
        if (logStrategyParam == null || !logStrategyParam.start) {
            return;
        }
        this.f11186g.g(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        LogStrategyParam logStrategyParam = this.f11188i;
        if (logStrategyParam == null || !logStrategyParam.stop) {
            return;
        }
        this.f11186g.h(activity);
    }

    private d() {
        this.f11186g = new c();
    }
}
