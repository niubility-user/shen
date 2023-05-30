package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.Paint;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class w implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, String str) {
        com.jingdong.manto.m.u0.n nVar;
        Paint.Join join;
        if ("miter".equalsIgnoreCase(str)) {
            cVar.f13730f.setStrokeJoin(Paint.Join.MITER);
            nVar = cVar.f13729e;
            join = Paint.Join.MITER;
        } else if ("round".equalsIgnoreCase(str)) {
            cVar.f13730f.setStrokeJoin(Paint.Join.ROUND);
            nVar = cVar.f13729e;
            join = Paint.Join.ROUND;
        } else if (!"bevel".equalsIgnoreCase(str)) {
            return true;
        } else {
            cVar.f13730f.setStrokeJoin(Paint.Join.BEVEL);
            nVar = cVar.f13729e;
            join = Paint.Join.BEVEL;
        }
        nVar.setStrokeJoin(join);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setLineJoin";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.t tVar = (com.jingdong.manto.m.u0.o.k0.t) cVar2;
        if (tVar == null) {
            return false;
        }
        return a(cVar, tVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() <= 0) {
            return false;
        }
        return a(cVar, jSONArray.optString(0));
    }
}
