package com.xiaomi.push;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;

/* loaded from: classes11.dex */
public class p2 implements Application.ActivityLifecycleCallbacks {

    /* renamed from: g */
    private String f18930g;

    /* renamed from: h */
    private String f18931h;

    /* renamed from: i */
    private Context f18932i;

    public p2(Context context, String str) {
        this.f18930g = "";
        this.f18932i = context;
        this.f18930g = str;
    }

    private void a(String str) {
        k7 k7Var = new k7();
        k7Var.a(str);
        k7Var.a(System.currentTimeMillis());
        k7Var.a(e7.ActivityActiveTimeStamp);
        x2.d(this.f18932i, k7Var);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        String localClassName = activity.getLocalClassName();
        if (TextUtils.isEmpty(this.f18930g) || TextUtils.isEmpty(localClassName)) {
            return;
        }
        this.f18931h = "";
        if (!TextUtils.isEmpty("") && !TextUtils.equals(this.f18931h, localClassName)) {
            this.f18930g = "";
            return;
        }
        a(this.f18932i.getPackageName() + "|" + localClassName + ":" + this.f18930g + DYConstants.DY_REGEX_COMMA + String.valueOf(System.currentTimeMillis() / 1000));
        this.f18930g = "";
        this.f18931h = "";
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (TextUtils.isEmpty(this.f18931h)) {
            this.f18931h = activity.getLocalClassName();
        }
        this.f18930g = String.valueOf(System.currentTimeMillis() / 1000);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
