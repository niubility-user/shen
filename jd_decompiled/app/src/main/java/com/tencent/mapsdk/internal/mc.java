package com.tencent.mapsdk.internal;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.tencent.tencentmap.mapsdk.maps.TencentMapContext;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class mc extends Paint {
    private WeakReference<TencentMapContext> a;

    public mc(TencentMapContext tencentMapContext) {
        this.a = new WeakReference<>(tencentMapContext);
    }

    @Override // android.graphics.Paint
    public Typeface setTypeface(Typeface typeface) {
        TencentMapContext tencentMapContext;
        WeakReference<TencentMapContext> weakReference = this.a;
        if (weakReference == null || (tencentMapContext = weakReference.get()) == null) {
            return super.setTypeface(typeface);
        }
        Typeface typeface2 = tencentMapContext.getTypeface();
        return (typeface2 == null || !(Typeface.DEFAULT == typeface || Typeface.DEFAULT_BOLD == typeface)) ? super.setTypeface(typeface) : super.setTypeface(typeface2);
    }
}
