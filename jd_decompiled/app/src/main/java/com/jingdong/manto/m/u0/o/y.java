package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class y implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, float f2) {
        cVar.f13730f.setStrokeMiter(f2);
        cVar.f13729e.setStrokeMiter(f2);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setMiterLimit";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.j jVar = (com.jingdong.manto.m.u0.o.k0.j) cVar2;
        if (jVar == null) {
            return false;
        }
        return a(cVar, jVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() <= 0) {
            return false;
        }
        return a(cVar, (float) jSONArray.optDouble(0));
    }
}
