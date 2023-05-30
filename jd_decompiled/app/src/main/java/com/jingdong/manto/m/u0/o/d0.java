package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.m.u0.n;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class d0 implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, String str) {
        com.jingdong.manto.m.u0.n nVar;
        n.a aVar;
        MantoLog.i("SetTextBaselineAction", "SetTextBaselineAction, align:%s", str);
        if ("top".equalsIgnoreCase(str)) {
            nVar = cVar.f13729e;
            aVar = n.a.TOP;
        } else if (DYConstants.DY_MIDDLE.equalsIgnoreCase(str)) {
            nVar = cVar.f13729e;
            aVar = n.a.MIDDLE;
        } else if (!"bottom".equalsIgnoreCase(str)) {
            if ("normal".equalsIgnoreCase(str)) {
                nVar = cVar.f13729e;
                aVar = n.a.NORMAL;
            }
            return true;
        } else {
            nVar = cVar.f13729e;
            aVar = n.a.BOTTOM;
        }
        nVar.a = aVar;
        cVar.f13730f.a = aVar;
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setTextBaseline";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.z zVar = (com.jingdong.manto.m.u0.o.k0.z) cVar2;
        if (zVar == null) {
            return false;
        }
        return a(cVar, zVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() <= 0) {
            return false;
        }
        return a(cVar, jSONArray.optString(0));
    }
}
