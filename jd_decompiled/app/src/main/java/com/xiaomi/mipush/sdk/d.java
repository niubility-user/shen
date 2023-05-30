package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.a8;

/* loaded from: classes11.dex */
public class d {
    public static void a(Context context) {
        context.getSharedPreferences("mipush_extra", 0).edit().putBoolean("is_xmsf_sup_decrypt", ((long) a8.l(context)) >= 50002000).apply();
    }
}
