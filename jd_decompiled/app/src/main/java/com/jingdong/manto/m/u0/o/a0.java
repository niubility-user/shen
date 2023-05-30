package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class a0 implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, float f2, float f3, float f4, int i2) {
        cVar.f13730f.setShadowLayer(f4, f2, f3, i2);
        cVar.f13729e.setShadowLayer(f4, f2, f3, i2);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setShadow";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.w wVar = (com.jingdong.manto.m.u0.o.k0.w) cVar2;
        if (wVar == null) {
            return false;
        }
        return a(cVar, wVar.d, wVar.f13788e, wVar.f13787c, wVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() < 4) {
            return false;
        }
        float convertToDeviceSize2 = MantoDensityUtils.convertToDeviceSize2(jSONArray, 0);
        float convertToDeviceSize22 = MantoDensityUtils.convertToDeviceSize2(jSONArray, 1);
        float parseIntFromPosition = MantoDensityUtils.parseIntFromPosition(jSONArray, 2);
        JSONArray optJSONArray = jSONArray.optJSONArray(3);
        if (optJSONArray == null || optJSONArray.length() < 4) {
            return false;
        }
        return a(cVar, convertToDeviceSize2, convertToDeviceSize22, parseIntFromPosition, MantoDensityUtils.parseColor(optJSONArray));
    }
}
