package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlay;

/* loaded from: classes9.dex */
public class md extends sc<nd> implements vc, IntersectionOverlay {
    public md(ld ldVar, nd ndVar) {
        super(ldVar, ndVar);
    }

    @Override // com.tencent.mapsdk.internal.vc
    public int d() {
        return ((ld) this.f17244i).a(this.f17243h);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlay
    public void setBounds(Rect rect) {
        ((nd) this.f17245j).setBounds(rect);
        a((md) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlay
    public void setDarkMode(boolean z) {
        ((nd) this.f17245j).enableDarkMode(z);
        a((md) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlay
    public void setData(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        ((nd) this.f17245j).setData(bArr);
        a((md) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlay
    public void setDistance(int i2) {
        ((nd) this.f17245j).setDistance(i2);
        a((md) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlay
    public void setRoundedCorner(boolean z) {
        ((nd) this.f17245j).enableRoundedCorner(z);
        a((md) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlay
    public void setVisibility(boolean z) {
        ((nd) this.f17245j).setVisibility(z);
        a((md) this.f17245j);
    }
}
