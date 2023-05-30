package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.text.TextUtils;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes15.dex */
public final class r implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, String str) {
        if (TextUtils.equals(str, "normal")) {
            cVar.f13729e.setFakeBoldText(false);
            cVar.f13730f.setFakeBoldText(false);
        } else if (TextUtils.equals(str, "bold")) {
            cVar.f13729e.setFakeBoldText(true);
            cVar.f13730f.setFakeBoldText(true);
        }
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setFontWeight";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.o oVar = (com.jingdong.manto.m.u0.o.k0.o) cVar2;
        if (oVar == null) {
            return false;
        }
        return a(cVar, oVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() == 0) {
            return false;
        }
        try {
            return a(cVar, jSONArray.getString(0));
        } catch (JSONException unused) {
            MantoLog.i("setFontWeight", "get 'fontWeight' error.");
            return false;
        }
    }
}
