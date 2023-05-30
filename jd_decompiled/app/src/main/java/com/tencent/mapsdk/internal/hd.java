package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import com.tencent.map.lib.models.GroundOverlayInfo;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.GroundOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;

/* loaded from: classes9.dex */
public class hd extends GroundOverlayInfo implements uc {
    private GroundOverlayOptions a;

    public hd(Context context, GroundOverlayOptions groundOverlayOptions) {
        if (groundOverlayOptions == null) {
            return;
        }
        this.a = groundOverlayOptions;
        BitmapDescriptor bitmap = groundOverlayOptions.getBitmap();
        if (bitmap != null) {
            Bitmap bitmap2 = bitmap.getBitmap(context);
            if (bitmap2 != null) {
                this.mBitmapWidth = bitmap2.getWidth();
                this.mBitmapHeight = bitmap2.getHeight();
            }
            this.mBitmap = bitmap2;
        }
        this.mAlpha = groundOverlayOptions.getAlpha();
        this.mVisibility = groundOverlayOptions.isVisible();
        this.mLevel = groundOverlayOptions.getLevel();
        this.mZIndex = groundOverlayOptions.getZIndex();
        if (groundOverlayOptions.getPosition() != null) {
            a();
        }
        if (groundOverlayOptions.getLatLngBounds() != null) {
            this.mLatLngBounds = (LatLngBounds) c7.a(groundOverlayOptions.getLatLngBounds());
        }
    }

    public void a() {
        if (this.a.getPosition() == null) {
            return;
        }
        float max = Math.max(0.0f, Math.min(1.0f, this.a.getAnchorU()));
        float max2 = Math.max(0.0f, Math.min(1.0f, this.a.getAnchorV()));
        double pow = Math.pow(2.0d, 20.0f - Math.max(3.0f, Math.min(22.0f, this.a.getZoom())));
        z5 z5Var = new z5(2.68435456E8d);
        o5 c2 = z5Var.c(this.a.getPosition());
        double d = c2.a;
        double d2 = this.mBitmapWidth * max;
        Double.isNaN(d2);
        double d3 = d - (d2 * pow);
        double d4 = c2.b;
        double d5 = this.mBitmapHeight * max2;
        Double.isNaN(d5);
        o5 o5Var = new o5(d3, d4 - (d5 * pow));
        double d6 = c2.a;
        double d7 = this.mBitmapWidth;
        double d8 = max;
        Double.isNaN(d8);
        Double.isNaN(d7);
        double d9 = d6 + (d7 * (1.0d - d8) * pow);
        double d10 = c2.b;
        double d11 = this.mBitmapHeight;
        double d12 = max2;
        Double.isNaN(d12);
        Double.isNaN(d11);
        this.mLatLngBounds = LatLngBounds.builder().include(z5Var.b(o5Var)).include(z5Var.b(new o5(d9, d10 + (d11 * (1.0d - d12) * pow)))).build();
    }

    public GroundOverlayOptions b() {
        return this.a;
    }
}
