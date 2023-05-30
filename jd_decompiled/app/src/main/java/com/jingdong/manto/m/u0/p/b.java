package com.jingdong.manto.m.u0.p;

import android.graphics.Path;
import android.graphics.RectF;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class b implements e {
    private static boolean a(Path path, float f2, float f3, float f4, float f5, float f6, boolean z) {
        float f7;
        float f8;
        float f9;
        float f10 = f2 - f4;
        float f11 = f3 - f4;
        float f12 = f2 + f4;
        float f13 = f3 + f4;
        float degrees = (float) Math.toDegrees(f5);
        float degrees2 = (float) Math.toDegrees(f6);
        double d = f4;
        Double.isNaN(d);
        float f14 = (float) (360.0d / (d * 6.283185307179586d));
        if (z) {
            if (degrees - degrees2 >= 360.0f) {
                f9 = -360.0f;
            } else {
                f7 = degrees % 360.0f;
                f8 = degrees2 % 360.0f;
                if (f7 < 0.0f) {
                    f7 += 360.0f;
                }
                if (f8 < 0.0f) {
                    f8 += 360.0f;
                }
                if (f8 >= f7) {
                    f9 = (f8 - f7) - 360.0f;
                }
                f9 = f8 - f7;
            }
        } else if (degrees2 - degrees >= 360.0f) {
            f9 = 360.0f;
        } else {
            f7 = degrees % 360.0f;
            f8 = degrees2 % 360.0f;
            if (f7 < 0.0f) {
                f7 += 360.0f;
            }
            if (f8 < 0.0f) {
                f8 += 360.0f;
            }
            if (f8 < f7) {
                f8 += 360.0f;
            }
            f9 = f8 - f7;
        }
        float f15 = f9 % 360.0f;
        if (f15 > f14 || f15 < (-f14)) {
            path.arcTo(new RectF(f10, f11, f12, f13), degrees, f9, false);
            return true;
        }
        path.arcTo(new RectF(f10, f11, f12, f13), degrees, f9, false);
        path.addArc(new RectF(f10, f11, f12, f13), degrees, f9);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final String a() {
        return "arcTo";
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, com.jingdong.manto.m.u0.o.k0.d0.a aVar) {
        com.jingdong.manto.m.u0.o.k0.d0.f fVar = (com.jingdong.manto.m.u0.o.k0.d0.f) aVar;
        if (fVar == null) {
            return false;
        }
        return a(path, fVar.f13768f, fVar.f13769g, fVar.f13767e, fVar.d, fVar.b, fVar.f13766c);
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, JSONArray jSONArray) {
        if (jSONArray.length() < 6) {
            return false;
        }
        return a(path, MantoDensityUtils.convertToDeviceSize2(jSONArray, 0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1), MantoDensityUtils.convertToDeviceSize2(jSONArray, 2), (float) jSONArray.optDouble(3), (float) jSONArray.optDouble(4), jSONArray.optBoolean(5));
    }
}
