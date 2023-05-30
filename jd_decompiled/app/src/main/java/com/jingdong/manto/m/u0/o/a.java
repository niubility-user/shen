package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.RectF;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class a implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, float f2, float f3, float f4, float f5, float f6) {
        RectF rectF = new RectF(f2 - f4, f3 - f4, f2 + f4, f3 + f4);
        double d = f5;
        Double.isNaN(d);
        double d2 = f6;
        Double.isNaN(d2);
        canvas.drawArc(rectF, (float) ((d / 3.141592653589793d) * 180.0d), (float) ((d2 / 3.141592653589793d) * 180.0d), true, cVar.f13729e);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "arc";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.d0.b bVar = (com.jingdong.manto.m.u0.o.k0.d0.b) cVar2;
        if (bVar == null) {
            return false;
        }
        return a(cVar, canvas, bVar.f13764e, bVar.f13765f, bVar.d, bVar.b, bVar.f13763c);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() < 5) {
            return false;
        }
        return a(cVar, canvas, MantoDensityUtils.convertToDeviceSize2(jSONArray, 0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1), MantoDensityUtils.convertToDeviceSize2(jSONArray, 2), (float) jSONArray.optDouble(3), (float) jSONArray.optDouble(4));
    }
}
