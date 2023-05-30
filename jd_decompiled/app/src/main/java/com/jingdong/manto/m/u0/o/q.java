package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.text.TextUtils;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes15.dex */
public final class q implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, String str) {
        com.jingdong.manto.m.u0.n nVar;
        int i2;
        if (TextUtils.equals(str, "oblique") || TextUtils.equals(str, "italic")) {
            nVar = cVar.f13729e;
            i2 = 2;
        } else if (!TextUtils.equals(str, "normal")) {
            return true;
        } else {
            nVar = cVar.f13729e;
            i2 = 0;
        }
        nVar.a(i2);
        cVar.f13730f.a(i2);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setFontStyle";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.n nVar = (com.jingdong.manto.m.u0.o.k0.n) cVar2;
        if (nVar == null) {
            return false;
        }
        return a(cVar, nVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() == 0) {
            return false;
        }
        try {
            return a(cVar, jSONArray.getString(0));
        } catch (JSONException unused) {
            MantoLog.i("setFontStyle", "get 'fontStyle' error.");
            return false;
        }
    }
}
