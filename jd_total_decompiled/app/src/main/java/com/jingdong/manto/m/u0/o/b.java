package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class b implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, float f2, float f3, float f4, float f5) {
        if (!canvas.isHardwareAccelerated()) {
            Paint paint = cVar.f13733i;
            float f6 = f2 + f4;
            float f7 = f3 + f5;
            if (paint != null) {
                canvas.drawRect(f2, f3, f6, f7, paint);
                MantoLog.v("ClearRectAction", "clearRect(x : %s, y : %s, w : %s, h : %s) with custom clearPaint", Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5));
                return true;
            }
            canvas.drawRect(f2, f3, f6, f7, cVar.a);
            MantoLog.v("ClearRectAction", "clearRect(x : %s, y : %s, w : %s, h : %s) with default clearPaint", Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5));
            return false;
        } else if (canvas instanceof com.jingdong.manto.m.u0.m) {
            ((com.jingdong.manto.m.u0.m) canvas).a(f2, f3, f2 + f4, f3 + f5);
            MantoLog.v("ClearRectAction", "clearRect(x : %s, y : %s, w : %s, h : %s)", Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5));
            return true;
        } else {
            Paint paint2 = cVar.f13733i;
            if (paint2 == null) {
                MantoLog.v("ClearRectAction", "clearRect(x : %s, y : %s, w : %s, h : %s) failed", Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5));
                return false;
            }
            canvas.drawRect(f2, f3, f2 + f4, f3 + f5, paint2);
            MantoLog.v("ClearRectAction", "clearRect(x : %s, y : %s, w : %s, h : %s) with custom clearPaint", Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5));
            return true;
        }
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "clearRect";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        if (cVar2 == null || !(cVar2 instanceof com.jingdong.manto.m.u0.o.k0.a)) {
            return false;
        }
        com.jingdong.manto.m.u0.o.k0.a aVar = (com.jingdong.manto.m.u0.o.k0.a) cVar2;
        return a(cVar, canvas, aVar.d, aVar.f13744e, aVar.f13743c, aVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() < 4) {
            return false;
        }
        return a(cVar, canvas, MantoDensityUtils.convertToDeviceSize2(jSONArray, 0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1), MantoDensityUtils.convertToDeviceSize2(jSONArray, 2), MantoDensityUtils.convertToDeviceSize2(jSONArray, 3));
    }
}
