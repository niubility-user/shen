package com.jingdong.union.a;

import android.content.Context;
import com.jingdong.union.common.config.JdUnionBase;

/* loaded from: classes12.dex */
public class a {
    private static float a(Context context) {
        float density = JdUnionBase.getDensity().getDensity();
        if (density <= 0.0f) {
            return 2.0f;
        }
        return density;
    }

    public static int b(Context context, float f2) {
        double a = a(context) * f2;
        Double.isNaN(a);
        return (int) (a + 0.5d);
    }
}
