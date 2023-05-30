package com.jd.lib.cashier.sdk.core.aac;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public class a {
    public void a(Context context, String str) {
        if (context != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                p.l(context, str);
            } catch (Exception e2) {
                r.d(getClass().getSimpleName(), e2.getMessage());
            }
        }
    }

    public void b(Context context) {
        if (context != null) {
            try {
                p.m(context);
            } catch (Exception e2) {
                r.d(getClass().getSimpleName(), e2.getMessage());
            }
        }
    }
}
