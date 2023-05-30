package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import com.tencent.tencentmap.mapsdk.maps.TencentMapContext;

/* loaded from: classes9.dex */
public class nc extends TextView {

    /* renamed from: g  reason: collision with root package name */
    private boolean f16888g;

    public nc(Context context, TencentMapContext tencentMapContext) {
        super(context);
        a(tencentMapContext);
    }

    public void a(TencentMapContext tencentMapContext) {
        if (this.f16888g || tencentMapContext == null) {
            return;
        }
        this.f16888g = true;
        Typeface typeface = tencentMapContext.getTypeface();
        if (typeface != null) {
            setTypeface(typeface);
        }
    }
}
