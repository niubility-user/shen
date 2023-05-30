package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class b0 implements i {
    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setStrokeStyle";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.x xVar = (com.jingdong.manto.m.u0.o.k0.x) cVar2;
        if (xVar == null) {
            return false;
        }
        return xVar.a(cVar, canvas);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        JSONArray optJSONArray;
        com.jingdong.manto.m.u0.n nVar;
        Shader radialGradient;
        JSONArray optJSONArray2;
        if (jSONArray.length() < 2) {
            return false;
        }
        String optString = jSONArray.optString(0);
        if ("linear".equalsIgnoreCase(optString)) {
            if (jSONArray.length() >= 3 && (optJSONArray2 = jSONArray.optJSONArray(1)) != null && optJSONArray2.length() >= 4) {
                float convertToDeviceSize2 = MantoDensityUtils.convertToDeviceSize2(optJSONArray2, 0);
                float convertToDeviceSize22 = MantoDensityUtils.convertToDeviceSize2(optJSONArray2, 1);
                float convertToDeviceSize23 = MantoDensityUtils.convertToDeviceSize2(optJSONArray2, 2);
                float convertToDeviceSize24 = MantoDensityUtils.convertToDeviceSize2(optJSONArray2, 3);
                JSONArray optJSONArray3 = jSONArray.optJSONArray(2);
                if (optJSONArray3 != null && optJSONArray3.length() != 0) {
                    int[] iArr = new int[optJSONArray3.length()];
                    float[] fArr = new float[optJSONArray3.length()];
                    for (int i2 = 0; i2 < optJSONArray3.length(); i2++) {
                        JSONArray optJSONArray4 = optJSONArray3.optJSONArray(i2);
                        if (optJSONArray4.length() >= 2) {
                            fArr[i2] = (float) optJSONArray4.optDouble(0);
                            iArr[i2] = MantoDensityUtils.parseColor(optJSONArray4.optJSONArray(1));
                        }
                    }
                    nVar = cVar.f13729e;
                    radialGradient = new LinearGradient(convertToDeviceSize2, convertToDeviceSize22, convertToDeviceSize23, convertToDeviceSize24, iArr, fArr, Shader.TileMode.CLAMP);
                }
            }
            return false;
        } else if (!"radial".equalsIgnoreCase(optString)) {
            if ("normal".equalsIgnoreCase(optString)) {
                JSONArray optJSONArray5 = jSONArray.optJSONArray(1);
                if (optJSONArray5 == null || optJSONArray5.length() < 4) {
                    return false;
                }
                cVar.f13729e.setColor(MantoDensityUtils.parseColor(optJSONArray5));
            }
            return true;
        } else if (jSONArray.length() < 3 || (optJSONArray = jSONArray.optJSONArray(1)) == null || optJSONArray.length() < 3) {
            return false;
        } else {
            float convertToDeviceSize25 = MantoDensityUtils.convertToDeviceSize2(optJSONArray, 1);
            float convertToDeviceSize26 = MantoDensityUtils.convertToDeviceSize2(optJSONArray, 2);
            float convertToDeviceSize27 = MantoDensityUtils.convertToDeviceSize2(optJSONArray, 3);
            JSONArray optJSONArray6 = jSONArray.optJSONArray(2);
            int[] iArr2 = new int[optJSONArray6.length()];
            float[] fArr2 = new float[optJSONArray6.length()];
            for (int i3 = 0; i3 < optJSONArray6.length(); i3++) {
                JSONArray optJSONArray7 = optJSONArray6.optJSONArray(i3);
                if (optJSONArray7.length() >= 2) {
                    fArr2[i3] = (float) optJSONArray7.optDouble(0);
                    iArr2[i3] = MantoDensityUtils.parseColor(optJSONArray7.optJSONArray(1));
                }
            }
            nVar = cVar.f13729e;
            radialGradient = new RadialGradient(convertToDeviceSize25, convertToDeviceSize26, convertToDeviceSize27, iArr2, fArr2, Shader.TileMode.CLAMP);
        }
        nVar.setShader(radialGradient);
        return true;
    }
}
