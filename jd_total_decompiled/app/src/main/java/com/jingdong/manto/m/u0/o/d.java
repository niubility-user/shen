package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import com.jingdong.manto.m.u0.p.h;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class d implements i {
    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "clipPath";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        canvas.clipPath(h.a.a.a((com.jingdong.manto.m.u0.o.k0.d0.d) cVar2));
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        canvas.clipPath(h.a.a.a(jSONArray));
        return true;
    }
}
