package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import com.tencent.mapsdk.internal.ya;

/* loaded from: classes9.dex */
public class lg extends u9 {

    /* renamed from: i  reason: collision with root package name */
    private static ya.e f16847i = new ya.e(256, 256, Bitmap.Config.ARGB_8888);

    public lg() {
        a(f16847i);
    }

    public lg(byte[] bArr) {
        super(bArr);
        a(f16847i);
    }

    @Override // com.tencent.mapsdk.internal.u9
    public boolean h() {
        Bitmap bitmap = this.b;
        if (bitmap != null && !bitmap.isRecycled() && this.a.get() <= 0) {
            this.b.recycle();
            qa.g(la.r).a("tileBitmap recycle out");
        }
        this.f17316c = null;
        Bitmap bitmap2 = this.b;
        return bitmap2 == null || bitmap2.isRecycled();
    }
}
