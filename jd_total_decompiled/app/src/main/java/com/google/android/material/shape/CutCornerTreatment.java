package com.google.android.material.shape;

import com.google.android.material.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
/* loaded from: classes12.dex */
public class CutCornerTreatment extends CornerTreatment {
    private final float size;

    public CutCornerTreatment(float f2) {
        this.size = f2;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(float f2, float f3, ShapePath shapePath) {
        shapePath.reset(0.0f, this.size * f3);
        double d = f2;
        double sin = Math.sin(d);
        double d2 = this.size;
        Double.isNaN(d2);
        double d3 = f3;
        Double.isNaN(d3);
        double cos = Math.cos(d);
        double d4 = this.size;
        Double.isNaN(d4);
        Double.isNaN(d3);
        shapePath.lineTo((float) (sin * d2 * d3), (float) (cos * d4 * d3));
    }
}
