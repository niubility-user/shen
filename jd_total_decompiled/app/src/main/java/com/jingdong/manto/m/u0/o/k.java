package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class k implements i {
    private static boolean a(Canvas canvas, float f2) {
        double d = f2;
        Double.isNaN(d);
        canvas.rotate((float) ((d / 3.141592653589793d) * 180.0d));
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "rotate";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.h hVar = (com.jingdong.manto.m.u0.o.k0.h) cVar2;
        if (hVar == null) {
            return false;
        }
        return a(canvas, hVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() <= 0) {
            return false;
        }
        return a(canvas, (float) jSONArray.optDouble(0));
    }
}
