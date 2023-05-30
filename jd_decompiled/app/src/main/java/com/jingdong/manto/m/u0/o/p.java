package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class p implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, int i2) {
        float f2 = i2;
        cVar.f13730f.setTextSize(f2);
        cVar.f13729e.setTextSize(f2);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setFontSize";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.m mVar = (com.jingdong.manto.m.u0.o.k0.m) cVar2;
        if (mVar == null) {
            return false;
        }
        return a(cVar, mVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() <= 0) {
            return false;
        }
        return a(cVar, MantoDensityUtils.parseToPixFromPosition(jSONArray, 0));
    }
}
