package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes15.dex */
public final class v implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, float[] fArr, float f2) {
        com.jingdong.manto.m.u0.n nVar;
        DashPathEffect dashPathEffect;
        if (fArr != null && f2 != Float.MIN_VALUE) {
            if (fArr.length >= 2) {
                cVar.f13729e.setPathEffect(new DashPathEffect(fArr, f2));
            } else {
                if (fArr.length == 1) {
                    float[] fArr2 = {fArr[0], fArr2[0]};
                    nVar = cVar.f13729e;
                    dashPathEffect = new DashPathEffect(fArr2, f2);
                } else {
                    nVar = cVar.f13729e;
                    dashPathEffect = null;
                }
                nVar.setPathEffect(dashPathEffect);
            }
        }
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setLineDash";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.s sVar = (com.jingdong.manto.m.u0.o.k0.s) cVar2;
        if (sVar == null) {
            return false;
        }
        return a(cVar, sVar.b, sVar.f13783c);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() < 2) {
            return false;
        }
        try {
            JSONArray jSONArray2 = jSONArray.getJSONArray(0);
            if (jSONArray2 == null) {
                return false;
            }
            int length = jSONArray2.length();
            float[] fArr = new float[length];
            for (int i2 = 0; i2 < length; i2++) {
                fArr[i2] = MantoDensityUtils.convertToDeviceSize2(jSONArray2, i2);
            }
            return jSONArray.opt(1) == null ? a(cVar, fArr, 0.0f) : a(cVar, fArr, MantoDensityUtils.convertToDeviceSize2(jSONArray, 1));
        } catch (JSONException unused) {
            return false;
        }
    }
}
