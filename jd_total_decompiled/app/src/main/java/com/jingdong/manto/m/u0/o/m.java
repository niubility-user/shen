package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class m implements i {
    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "scale";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.i iVar = (com.jingdong.manto.m.u0.o.k0.i) cVar2;
        if (iVar == null) {
            return false;
        }
        canvas.scale(iVar.b, iVar.f13782c);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() < 2) {
            return false;
        }
        canvas.scale((float) jSONArray.optDouble(0), (float) jSONArray.optDouble(1));
        return true;
    }
}
