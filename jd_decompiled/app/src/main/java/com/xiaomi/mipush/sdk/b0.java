package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.y0;

/* loaded from: classes11.dex */
public class b0 {
    public static a a(Context context, r0 r0Var) {
        return b(context, r0Var);
    }

    private static a b(Context context, r0 r0Var) {
        y0.a b = y0.b(r0Var);
        if (b == null || TextUtils.isEmpty(b.a) || TextUtils.isEmpty(b.b)) {
            return null;
        }
        return (a) com.xiaomi.push.k0.g(b.a, b.b, context);
    }
}
