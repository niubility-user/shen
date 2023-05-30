package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import com.tencent.map.lib.models.AccessibleTouchItem;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;

/* loaded from: classes9.dex */
public class ff extends AccessibleTouchItem {
    private o0 a;
    private xi b;

    public ff(xi xiVar, o0 o0Var) {
        this.b = xiVar;
        this.a = o0Var;
    }

    private Rect a(Rect rect) {
        if (rect == null) {
            return null;
        }
        int i2 = rect.left;
        int i3 = rect.right;
        int i4 = rect.top;
        int i5 = rect.bottom;
        int i6 = (i3 + i2) / 2;
        int i7 = (i4 + i5) / 2;
        int i8 = i5 - i4;
        if (i3 - i2 < b7.w() * 40.0f) {
            float f2 = i6;
            int w = (int) (f2 - (b7.w() * 20.0f));
            i3 = (int) (f2 + (b7.w() * 20.0f));
            i2 = w;
        }
        if (i8 < b7.w() * 40.0f) {
            float f3 = i7;
            i4 = (int) (f3 - (b7.w() * 20.0f));
            i5 = (int) (f3 + (b7.w() * 20.0f));
        }
        return new Rect(i2, i4, i3, i5);
    }

    @Override // com.tencent.map.lib.models.AccessibleTouchItem
    public Rect getBounds() {
        o0 o0Var = this.a;
        if (o0Var == null) {
            return null;
        }
        return a(o0Var.g());
    }

    @Override // com.tencent.map.lib.models.AccessibleTouchItem
    public String getContentDescription() {
        o0 o0Var = this.a;
        if (o0Var == null) {
            return null;
        }
        return o0Var.getContentDescription();
    }

    @Override // com.tencent.map.lib.models.AccessibleTouchItem
    public void onClick() {
        xi xiVar = this.b;
        if (xiVar != null) {
            TencentMap.OnMarkerClickListener onMarkerClickListener = xiVar.a0;
            o0 o0Var = this.a;
            if (o0Var == null || onMarkerClickListener == null) {
                return;
            }
            onMarkerClickListener.onMarkerClick(o0Var);
        }
    }
}
