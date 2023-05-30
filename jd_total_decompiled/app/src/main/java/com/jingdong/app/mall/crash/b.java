package com.jingdong.app.mall.crash;

import android.app.Activity;
import android.text.TextUtils;
import com.jingdong.aura.wrapper.listener.AuraDebugTimeListener;

/* loaded from: classes3.dex */
public class b implements AuraDebugTimeListener {
    private static volatile b b;
    private c a = null;

    private b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b();
            }
            bVar = b;
        }
        return bVar;
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraDebugTimeListener
    public void afterCallActivityOnCreate(Activity activity) {
        String b2 = this.a.b();
        if (TextUtils.isEmpty(b2) || !b2.equals(activity.getComponentName().getClassName())) {
            return;
        }
        activity.getWindow().getDecorView().post(this.a);
    }

    @Override // com.jingdong.aura.wrapper.listener.AuraDebugTimeListener
    public void beforeCallActivityOnCreate(Activity activity) {
        this.a = new c();
        this.a.c(System.currentTimeMillis(), activity.getComponentName().getClassName());
    }
}
