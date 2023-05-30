package com.jingdong.aura.sdk.update.b;

import android.content.Context;
import android.util.DisplayMetrics;

/* loaded from: classes4.dex */
public final class f {
    private static float a = 160.0f;
    private static float b = 160.0f;

    public static DisplayMetrics a(Context context) {
        return com.jingdong.aura.sdk.update.a.a().a.privacyFieldProvider != null ? com.jingdong.aura.sdk.update.a.a().a.privacyFieldProvider.getDisplayMetrics(context) : context.getResources().getDisplayMetrics();
    }

    public static void a(float f2) {
        if (f2 > 0.0f) {
            a = f2;
        }
    }

    public static void b(float f2) {
        if (f2 > 0.0f) {
            b = f2;
        }
    }

    public static int c(float f2) {
        return (int) ((f2 * a) + 0.5f);
    }
}
