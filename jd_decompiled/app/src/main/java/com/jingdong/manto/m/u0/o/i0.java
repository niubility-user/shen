package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.text.TextUtils;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes15.dex */
public final class i0 implements i {
    private static boolean a(Canvas canvas, float f2, float f3, float f4, float f5, float f6, float f7) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setValues(new float[]{f2, f4, f6, f3, f5, f7, 0.0f, 0.0f, 1.0f});
        canvas.concat(matrix);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "transform";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.a0 a0Var = (com.jingdong.manto.m.u0.o.k0.a0) cVar2;
        if (a0Var == null) {
            return false;
        }
        return a(canvas, a0Var.f13747f, a0Var.f13745c, a0Var.d, a0Var.f13748g, a0Var.f13746e, a0Var.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() < 6) {
            return false;
        }
        try {
            return TextUtils.equals("1", com.jingdong.manto.utils.m.a("canvasTransform", "1")) ? a(canvas, (float) jSONArray.getDouble(0), (float) jSONArray.getDouble(1), (float) jSONArray.getDouble(2), (float) jSONArray.getDouble(3), MantoDensityUtils.parseToPixFromPosition(jSONArray, 4), MantoDensityUtils.parseToPixFromPosition(jSONArray, 5)) : a(canvas, (float) jSONArray.getDouble(0), (float) jSONArray.getDouble(1), (float) jSONArray.getDouble(2), (float) jSONArray.getDouble(3), MantoDensityUtils.parseIntFromPosition(jSONArray, 4), MantoDensityUtils.parseIntFromPosition(jSONArray, 5));
        } catch (JSONException unused) {
            return false;
        }
    }
}
