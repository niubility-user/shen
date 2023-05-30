package com.jd.lib.cashier.sdk.f.c;

import android.content.Intent;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.freindpay.bean.ActivityResult;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.d.a.c.a {
    private List<WeakReference<f<ActivityResult>>> a = new ArrayList();

    @Override // com.jd.lib.cashier.sdk.d.a.c.a
    public void a() {
        List<WeakReference<f<ActivityResult>>> list = this.a;
        if (list != null) {
            list.clear();
            this.a = null;
        }
    }

    public void b(f<ActivityResult> fVar) {
        List<WeakReference<f<ActivityResult>>> list = this.a;
        if (list != null) {
            list.add(new WeakReference<>(fVar));
        }
    }

    public void c(int i2, int i3, Intent intent) {
        f<ActivityResult> fVar;
        List<WeakReference<f<ActivityResult>>> list = this.a;
        if (list == null || list.isEmpty()) {
            return;
        }
        ActivityResult activityResult = new ActivityResult(intent, i2, i3);
        for (int i4 = 0; i4 < this.a.size(); i4++) {
            WeakReference<f<ActivityResult>> weakReference = this.a.get(i4);
            if (weakReference != null && (fVar = weakReference.get()) != null) {
                fVar.callBack(activityResult);
            }
        }
    }
}
