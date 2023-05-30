package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class x implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, float f2) {
        cVar.f13730f.setStrokeWidth(f2);
        cVar.f13729e.setStrokeWidth(f2);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setLineWidth";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.u uVar = (com.jingdong.manto.m.u0.o.k0.u) cVar2;
        if (uVar == null) {
            return false;
        }
        return a(cVar, uVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() <= 0) {
            return false;
        }
        return a(cVar, MantoDensityUtils.parseToPixFromPosition(jSONArray, 0));
    }
}
