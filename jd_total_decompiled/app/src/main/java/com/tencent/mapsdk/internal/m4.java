package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import android.view.View;

/* loaded from: classes9.dex */
public abstract class m4 implements o4 {
    public abstract View[] c();

    @Override // com.tencent.mapsdk.internal.o4
    public Rect d() {
        View[] c2 = c();
        Rect rect = new Rect();
        for (View view : c2) {
            rect.union(f7.a(view));
        }
        return rect;
    }
}
