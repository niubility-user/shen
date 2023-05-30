package com.jingdong.manto.m.u0.p;

import android.graphics.Path;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class d implements e {
    @Override // com.jingdong.manto.m.u0.p.e
    public final String a() {
        return "closePath";
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, com.jingdong.manto.m.u0.o.k0.d0.a aVar) {
        path.close();
        return true;
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, JSONArray jSONArray) {
        path.close();
        return true;
    }
}
