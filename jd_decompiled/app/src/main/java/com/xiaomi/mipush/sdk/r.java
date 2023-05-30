package com.xiaomi.mipush.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.push.c4;
import com.xiaomi.push.d4;
import java.util.HashSet;
import java.util.Set;

@TargetApi(14)
/* loaded from: classes11.dex */
public class r implements Application.ActivityLifecycleCallbacks {

    /* renamed from: g  reason: collision with root package name */
    private Set<String> f18419g = new HashSet();

    private static void a(Application application) {
        application.registerActivityLifecycleCallbacks(new r());
    }

    public static void b(Context context) {
        a((Application) context.getApplicationContext());
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
        d4 a;
        String packageName;
        String j2;
        int i2;
        Intent intent = activity.getIntent();
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("messageId");
        int intExtra = intent.getIntExtra("eventMessageType", -1);
        if (TextUtils.isEmpty(stringExtra) || intExtra <= 0 || this.f18419g.contains(stringExtra)) {
            return;
        }
        this.f18419g.add(stringExtra);
        if (intExtra == 3000) {
            a = d4.a(activity.getApplicationContext());
            packageName = activity.getPackageName();
            j2 = c4.j(intExtra);
            i2 = 3008;
        } else if (intExtra != 1000) {
            return;
        } else {
            a = d4.a(activity.getApplicationContext());
            packageName = activity.getPackageName();
            j2 = c4.j(intExtra);
            i2 = 1008;
        }
        a.g(packageName, j2, stringExtra, i2, null);
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
