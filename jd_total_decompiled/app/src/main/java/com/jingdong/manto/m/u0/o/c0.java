package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.jd.dynamic.DYConstants;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class c0 implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, String str) {
        com.jingdong.manto.m.u0.n nVar;
        Paint.Align align;
        if ("left".equalsIgnoreCase(str)) {
            cVar.f13729e.setTextAlign(Paint.Align.LEFT);
            nVar = cVar.f13730f;
            align = Paint.Align.LEFT;
        } else if ("right".equalsIgnoreCase(str)) {
            cVar.f13729e.setTextAlign(Paint.Align.RIGHT);
            nVar = cVar.f13730f;
            align = Paint.Align.RIGHT;
        } else if (!DYConstants.DY_CENTER.equalsIgnoreCase(str)) {
            return true;
        } else {
            cVar.f13729e.setTextAlign(Paint.Align.CENTER);
            nVar = cVar.f13730f;
            align = Paint.Align.CENTER;
        }
        nVar.setTextAlign(align);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setTextAlign";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.y yVar = (com.jingdong.manto.m.u0.o.k0.y) cVar2;
        if (yVar == null) {
            return false;
        }
        return a(cVar, yVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() <= 0) {
            return false;
        }
        return a(cVar, jSONArray.optString(0));
    }
}
