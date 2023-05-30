package com.tencent.mapsdk.internal;

import android.graphics.PointF;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListenerList;

/* loaded from: classes9.dex */
public class vi implements v4 {
    private xi a;

    public vi(xi xiVar) {
        this.a = xiVar;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean a() {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean a(float f2) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean a(float f2, float f3) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean a(PointF pointF, PointF pointF2, double d, double d2) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean a(PointF pointF, PointF pointF2, float f2) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean b() {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean b(float f2) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean b(float f2, float f3) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public void c() {
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean c(float f2, float f3) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean d() {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean d(float f2, float f3) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onDoubleTap(float f2, float f3) {
        boolean z;
        TencentMapGestureListenerList tencentMapGestureListenerList;
        xi xiVar = this.a;
        if (xiVar != null && (z = xiVar.p) && (tencentMapGestureListenerList = xiVar.o) != null && z) {
            return tencentMapGestureListenerList.onDoubleTap(f2, f3);
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onDown(float f2, float f3) {
        xi xiVar = this.a;
        if (xiVar != null && xiVar.p) {
            xiVar.t++;
            TencentMapGestureListenerList tencentMapGestureListenerList = xiVar.o;
            if (tencentMapGestureListenerList != null) {
                return tencentMapGestureListenerList.onDown(f2, f3);
            }
            return false;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onFling(float f2, float f3) {
        TencentMapGestureListenerList tencentMapGestureListenerList;
        xi xiVar = this.a;
        if (xiVar == null || !xiVar.p || (tencentMapGestureListenerList = xiVar.o) == null) {
            return false;
        }
        return tencentMapGestureListenerList.onFling(f2, f3);
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onLongPress(float f2, float f3) {
        xi xiVar = this.a;
        if (xiVar != null && xiVar.p) {
            xiVar.f(f2, f3);
            TencentMapGestureListenerList tencentMapGestureListenerList = this.a.o;
            if (tencentMapGestureListenerList != null) {
                return tencentMapGestureListenerList.onLongPress(f2, f3);
            }
            return false;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onScroll(float f2, float f3) {
        TencentMapGestureListenerList tencentMapGestureListenerList;
        xi xiVar = this.a;
        if (xiVar == null || !xiVar.p || (tencentMapGestureListenerList = xiVar.o) == null) {
            return false;
        }
        return tencentMapGestureListenerList.onScroll(f2, f3);
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onSingleTap(float f2, float f3) {
        xi xiVar = this.a;
        if (xiVar != null && xiVar.p) {
            if (xiVar.e(f2, f3)) {
                return true;
            }
            if (!this.a.d(f2, f3)) {
                this.a.b(f2, f3);
            }
            xi xiVar2 = this.a;
            TencentMapGestureListenerList tencentMapGestureListenerList = xiVar2.o;
            if (tencentMapGestureListenerList == null || !xiVar2.p) {
                return false;
            }
            return tencentMapGestureListenerList.onSingleTap(f2, f3);
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onUp(float f2, float f3) {
        int i2;
        xi xiVar = this.a;
        if (xiVar != null && xiVar.p) {
            int i3 = xiVar.t;
            if (i3 > 0) {
                i2 = i3 - 1;
                xiVar.t = i2;
            } else {
                i2 = 0;
            }
            xiVar.t = i2;
            if (xiVar.s && this.a.r) {
                xi xiVar2 = this.a;
                if (xiVar2.t == 0) {
                    CameraPosition J = xiVar2.J();
                    if (J == null) {
                        return false;
                    }
                    this.a.s = false;
                    this.a.onCameraChangeFinished(J);
                }
            }
            TencentMapGestureListenerList tencentMapGestureListenerList = this.a.o;
            if (tencentMapGestureListenerList != null) {
                return tencentMapGestureListenerList.onUp(f2, f3);
            }
            return false;
        }
        return false;
    }
}
