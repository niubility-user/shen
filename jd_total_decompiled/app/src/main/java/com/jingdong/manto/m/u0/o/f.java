package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.Path;
import com.jingdong.manto.m.u0.p.h;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class f implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, Path path) {
        canvas.drawPath(path, cVar.f13730f);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "fillPath";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        return a(cVar, canvas, h.a.a.a((com.jingdong.manto.m.u0.o.k0.d0.d) cVar2));
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        return a(cVar, canvas, h.a.a.a(jSONArray));
    }
}
