package com.jingdong.manto.m.u0.p;

import android.graphics.Path;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class f implements e {
    @Override // com.jingdong.manto.m.u0.p.e
    public final String a() {
        return "lineTo";
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, com.jingdong.manto.m.u0.o.k0.d0.a aVar) {
        com.jingdong.manto.m.u0.o.k0.d0.h hVar = (com.jingdong.manto.m.u0.o.k0.d0.h) aVar;
        if (hVar == null) {
            return false;
        }
        path.lineTo(hVar.b, hVar.f13774c);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, JSONArray jSONArray) {
        if (jSONArray.length() < 2) {
            return false;
        }
        path.lineTo(MantoDensityUtils.convertToDeviceSize2(jSONArray, 0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1));
        return true;
    }
}
