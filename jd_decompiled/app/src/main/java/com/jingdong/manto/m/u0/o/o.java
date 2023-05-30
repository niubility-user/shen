package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.text.TextUtils;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes15.dex */
public final class o implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        cVar.f13729e.a(str);
        cVar.f13730f.a(str);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setFontFamily";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.l lVar = (com.jingdong.manto.m.u0.o.k0.l) cVar2;
        if (lVar == null) {
            return false;
        }
        return a(cVar, lVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() == 0) {
            return false;
        }
        try {
            return a(cVar, jSONArray.getString(0));
        } catch (JSONException unused) {
            MantoLog.i("setFontFamily", "get 'fontFamily' error.");
            return false;
        }
    }
}
