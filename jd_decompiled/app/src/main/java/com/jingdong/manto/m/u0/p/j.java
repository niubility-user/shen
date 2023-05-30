package com.jingdong.manto.m.u0.p;

import android.graphics.Path;
import com.jingdong.manto.m.u0.o.k0.d0.k;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class j implements e {
    private static boolean a(Path path, float f2, float f3, float f4, float f5) {
        path.addRect(f2, f3, f2 + f4, f3 + f5, Path.Direction.CW);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final String a() {
        return "rect";
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, com.jingdong.manto.m.u0.o.k0.d0.a aVar) {
        k kVar = (k) aVar;
        if (kVar == null) {
            return false;
        }
        return a(path, kVar.d, kVar.f13779e, kVar.f13778c, kVar.b);
    }

    @Override // com.jingdong.manto.m.u0.p.e
    public final boolean a(Path path, JSONArray jSONArray) {
        if (jSONArray.length() < 4) {
            return false;
        }
        return a(path, MantoDensityUtils.convertToDeviceSize2(jSONArray, 0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1), MantoDensityUtils.convertToDeviceSize2(jSONArray, 2), MantoDensityUtils.convertToDeviceSize2(jSONArray, 3));
    }
}
