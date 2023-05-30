package com.jingdong.manto.m.u0.o;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class z implements i {
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r1 != null) goto L12;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, Bitmap bitmap, int i2, int i3, int i4, int i5) {
        Paint paint;
        float f2;
        float f3;
        float f4;
        float f5;
        Paint paint2;
        if (canvas.isHardwareAccelerated()) {
            if (canvas instanceof com.jingdong.manto.m.u0.m) {
                ((com.jingdong.manto.m.u0.m) canvas).a(i2, i3, i2 + i4, i3 + i5);
            } else {
                paint = cVar.f13733i;
            }
            if (bitmap != null || bitmap.isRecycled()) {
                return false;
            }
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new RectF(i2, i3, i2 + i4, i3 + i5), cVar.f13729e);
            return true;
        }
        paint = cVar.f13733i;
        if (paint == null) {
            f2 = i2;
            f3 = i3;
            f4 = i2 + i4;
            f5 = i3 + i5;
            paint2 = cVar.a;
            canvas.drawRect(f2, f3, f4, f5, paint2);
            if (bitmap != null) {
            }
            return false;
        }
        paint2 = paint;
        f2 = i2;
        f3 = i3;
        f4 = i2 + i4;
        f5 = i3 + i5;
        canvas.drawRect(f2, f3, f4, f5, paint2);
        if (bitmap != null) {
        }
        return false;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "__setPixels";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.v vVar = (com.jingdong.manto.m.u0.o.k0.v) cVar2;
        if (vVar == null) {
            return false;
        }
        return a(cVar, canvas, vVar.b, vVar.f13785e, vVar.f13786f, vVar.d, vVar.f13784c);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        try {
            return a(cVar, canvas, (Bitmap) jSONArray.get(4), MantoDensityUtils.parseToPixFromPosition(jSONArray, 0), MantoDensityUtils.parseToPixFromPosition(jSONArray, 1), MantoDensityUtils.parseToPixFromPosition(jSONArray, 2), MantoDensityUtils.parseToPixFromPosition(jSONArray, 3));
        } catch (Exception e2) {
            MantoLog.w("SetPixelsAction", "get bitmap data error, ", e2);
            return false;
        }
    }
}
