package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;

/* loaded from: classes9.dex */
public final class fd extends sc<hd> implements GroundOverlay {
    public fd(gd gdVar, hd hdVar) {
        super(gdVar, hdVar);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay
    public void setAlpha(float f2) {
        if (((hd) this.f17245j).b() != null) {
            ((hd) this.f17245j).b().alpha(f2);
        }
        ((hd) this.f17245j).setAlpha(f2);
        a((fd) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay
    public void setAnchor(float f2, float f3) {
        if (((hd) this.f17245j).b() != null) {
            ((hd) this.f17245j).b().anchor(f2, f3);
        }
        ((hd) this.f17245j).a();
        a((fd) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay
    public void setBitmap(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            return;
        }
        if (((hd) this.f17245j).b() != null) {
            ((hd) this.f17245j).b().bitmap(bitmapDescriptor);
        }
        tc<T> tcVar = this.f17244i;
        if (tcVar != 0) {
            ((hd) this.f17245j).setBitmap(bitmapDescriptor.getBitmap(tcVar.e()));
        }
        a((fd) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay
    public void setLatLongBounds(LatLngBounds latLngBounds) {
        if (((hd) this.f17245j).b() != null) {
            ((hd) this.f17245j).b().latLngBounds(latLngBounds);
        }
        ((hd) this.f17245j).setLatLngBounds(latLngBounds);
        a((fd) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay
    public void setLevel(int i2) {
        if (((hd) this.f17245j).b() != null) {
            ((hd) this.f17245j).b().level(i2);
        }
        ((hd) this.f17245j).setLevel(i2);
        a((fd) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay
    public void setPosition(LatLng latLng) {
        if (((hd) this.f17245j).b() != null) {
            ((hd) this.f17245j).b().position(latLng);
        }
        ((hd) this.f17245j).a();
        a((fd) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay
    public void setVisibility(boolean z) {
        if (((hd) this.f17245j).b() != null) {
            ((hd) this.f17245j).b().visible(z);
        }
        ((hd) this.f17245j).setVisibility(z);
        a((fd) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay
    public void setZindex(int i2) {
        if (((hd) this.f17245j).b() != null) {
            ((hd) this.f17245j).b().zIndex(i2);
        }
        ((hd) this.f17245j).setZIndex(i2);
        a((fd) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay
    public void setZoom(float f2) {
        if (((hd) this.f17245j).b() != null) {
            ((hd) this.f17245j).b().zoom(f2);
        }
        ((hd) this.f17245j).a();
        a((fd) this.f17245j);
    }
}
