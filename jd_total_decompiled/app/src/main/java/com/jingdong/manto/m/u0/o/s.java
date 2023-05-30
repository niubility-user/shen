package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes15.dex */
public final class s implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, int i2) {
        float f2 = i2 / 255.0f;
        cVar.f13729e.a(f2, true);
        cVar.f13730f.a(f2, true);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setGlobalAlpha";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.p pVar = (com.jingdong.manto.m.u0.o.k0.p) cVar2;
        if (pVar == null) {
            return false;
        }
        return a(cVar, pVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        try {
            return a(cVar, jSONArray.getInt(0));
        } catch (JSONException e2) {
            MantoLog.e("SetGlobalAlphaAction", "getGlobalAlpha value error. exception : %s", e2);
            return false;
        }
    }
}
