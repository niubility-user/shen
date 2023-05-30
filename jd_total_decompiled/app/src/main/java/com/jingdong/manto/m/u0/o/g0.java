package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class g0 implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, float f2, float f3, float f4, float f5) {
        canvas.drawRect(f2, f3, f2 + f4, f3 + f5, cVar.f13729e);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "strokeRect";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.c cVar3 = (com.jingdong.manto.m.u0.o.k0.c) cVar2;
        if (cVar3 == null) {
            return false;
        }
        return a(cVar, canvas, cVar3.d, cVar3.f13759e, cVar3.f13758c, cVar3.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() < 4) {
            return false;
        }
        return a(cVar, canvas, MantoDensityUtils.convertToDeviceSize2(jSONArray, 0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1), MantoDensityUtils.convertToDeviceSize2(jSONArray, 2), MantoDensityUtils.convertToDeviceSize2(jSONArray, 3));
    }
}
