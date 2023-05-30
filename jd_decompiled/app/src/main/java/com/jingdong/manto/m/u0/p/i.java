package com.jingdong.manto.m.u0.p;

import android.graphics.Path;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class i implements e {
    private static boolean a(Path path, float f2, float f3, float f4, float f5) {
        path.quadTo(f2, f3, f4, f5);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final String a() {
        return "quadraticCurveTo";
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, com.jingdong.manto.m.u0.o.k0.d0.a aVar) {
        com.jingdong.manto.m.u0.o.k0.d0.j jVar = (com.jingdong.manto.m.u0.o.k0.d0.j) aVar;
        if (aVar == null) {
            return false;
        }
        return a(path, jVar.b, jVar.d, jVar.f13776c, jVar.f13777e);
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, JSONArray jSONArray) {
        if (jSONArray.length() < 4) {
            return false;
        }
        return a(path, MantoDensityUtils.convertToDeviceSize2(jSONArray, 0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1), MantoDensityUtils.convertToDeviceSize2(jSONArray, 2), MantoDensityUtils.convertToDeviceSize2(jSONArray, 3));
    }
}
