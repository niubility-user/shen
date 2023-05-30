package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.jingdong.manto.m.u0.n;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class h0 implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, String str, float f2, float f3, float f4) {
        float abs;
        com.jingdong.manto.m.u0.n nVar;
        Paint.FontMetrics fontMetrics = cVar.f13729e.getFontMetrics();
        n.a aVar = cVar.f13729e.a;
        if (aVar == n.a.TOP) {
            abs = Math.abs(fontMetrics.ascent);
        } else if (aVar != n.a.MIDDLE) {
            if (aVar == n.a.BOTTOM) {
                f3 -= Math.abs(fontMetrics.descent);
            }
            nVar = cVar.f13729e;
            float measureText = nVar.measureText(str);
            if (f4 > 0.0f || f4 >= measureText) {
                canvas.drawText(str, f2, f3, nVar);
                return true;
            }
            canvas.save();
            canvas.translate(f2, f3);
            canvas.scale(f4 / measureText, 1.0f);
            canvas.drawText(str, 0.0f, 0.0f, nVar);
            canvas.restore();
            return true;
        } else {
            float f5 = fontMetrics.descent;
            abs = Math.abs((((-fontMetrics.ascent) + f5) / 2.0f) - f5);
        }
        f3 += abs;
        nVar = cVar.f13729e;
        float measureText2 = nVar.measureText(str);
        if (f4 > 0.0f) {
        }
        canvas.drawText(str, f2, f3, nVar);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "strokeText";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.d dVar = (com.jingdong.manto.m.u0.o.k0.d) cVar2;
        if (dVar == null) {
            return false;
        }
        return a(cVar, canvas, dVar.f13761c, dVar.d, dVar.f13762e, dVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() < 3) {
            return false;
        }
        return a(cVar, canvas, jSONArray.optString(0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1), MantoDensityUtils.convertToDeviceSize2(jSONArray, 2), MantoDensityUtils.convertToDeviceSize2(jSONArray, 3));
    }
}
