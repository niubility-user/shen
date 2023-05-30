package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.Paint;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class u implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, String str) {
        com.jingdong.manto.m.u0.n nVar;
        Paint.Cap cap;
        if ("butt".equalsIgnoreCase(str)) {
            cVar.f13730f.setStrokeCap(Paint.Cap.BUTT);
            nVar = cVar.f13729e;
            cap = Paint.Cap.BUTT;
        } else if ("round".equalsIgnoreCase(str)) {
            cVar.f13730f.setStrokeCap(Paint.Cap.ROUND);
            nVar = cVar.f13729e;
            cap = Paint.Cap.ROUND;
        } else if (!"square".equalsIgnoreCase(str)) {
            return true;
        } else {
            cVar.f13730f.setStrokeCap(Paint.Cap.SQUARE);
            nVar = cVar.f13729e;
            cap = Paint.Cap.SQUARE;
        }
        nVar.setStrokeCap(cap);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setLineCap";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.r rVar = (com.jingdong.manto.m.u0.o.k0.r) cVar2;
        if (rVar == null) {
            return false;
        }
        return a(cVar, rVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() <= 0) {
            return false;
        }
        return a(cVar, jSONArray.optString(0));
    }
}
