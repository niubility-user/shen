package com.jingdong.manto.m.u0.p;

import android.graphics.Path;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class g implements e {
    @Override // com.jingdong.manto.m.u0.p.e
    public final String a() {
        return "moveTo";
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, com.jingdong.manto.m.u0.o.k0.d0.a aVar) {
        com.jingdong.manto.m.u0.o.k0.d0.i iVar = (com.jingdong.manto.m.u0.o.k0.d0.i) aVar;
        if (iVar == null) {
            return false;
        }
        path.moveTo(iVar.b, iVar.f13775c);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, JSONArray jSONArray) {
        if (jSONArray.length() < 2) {
            return false;
        }
        path.moveTo(MantoDensityUtils.convertToDeviceSize2(jSONArray, 0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1));
        return true;
    }
}
