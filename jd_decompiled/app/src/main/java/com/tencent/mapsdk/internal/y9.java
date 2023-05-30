package com.tencent.mapsdk.internal;

import android.content.Context;
import android.util.TypedValue;

/* loaded from: classes9.dex */
public class y9 {
    public static int a(Context context, int i2) {
        return (int) TypedValue.applyDimension(1, i2, context.getResources().getDisplayMetrics());
    }
}
