package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class j0 implements i {
    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "translate";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.b0 b0Var = (com.jingdong.manto.m.u0.o.k0.b0) cVar2;
        if (b0Var == null) {
            return false;
        }
        canvas.translate(b0Var.b, b0Var.f13757c);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() < 2) {
            return false;
        }
        canvas.translate(MantoDensityUtils.convertToDeviceSize2(jSONArray, 0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1));
        return true;
    }
}
