package com.jingdong.manto.m.u0.p;

import android.graphics.Path;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class c implements e {
    private static boolean a(Path path, float f2, float f3, float f4, float f5, float f6, float f7) {
        path.cubicTo(f2, f3, f4, f5, f6, f7);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final String a() {
        return "bezierCurveTo";
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, com.jingdong.manto.m.u0.o.k0.d0.a aVar) {
        com.jingdong.manto.m.u0.o.k0.d0.g gVar = (com.jingdong.manto.m.u0.o.k0.d0.g) aVar;
        if (gVar == null) {
            return false;
        }
        return a(path, gVar.b, gVar.f13771e, gVar.f13770c, gVar.f13772f, gVar.d, gVar.f13773g);
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, JSONArray jSONArray) {
        if (jSONArray.length() < 6) {
            return false;
        }
        return a(path, MantoDensityUtils.convertToDeviceSize2(jSONArray, 0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1), MantoDensityUtils.convertToDeviceSize2(jSONArray, 2), MantoDensityUtils.convertToDeviceSize2(jSONArray, 3), MantoDensityUtils.convertToDeviceSize2(jSONArray, 4), MantoDensityUtils.convertToDeviceSize2(jSONArray, 5));
    }
}
