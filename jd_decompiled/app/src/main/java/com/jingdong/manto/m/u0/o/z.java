package com.jingdong.manto.m.u0.o;

import android.graphics.Bitmap;
import android.graphics.Canvas;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(com.jingdong.manto.m.u0.c r11, android.graphics.Canvas r12, android.graphics.Bitmap r13, int r14, int r15, int r16, int r17) {
        /*
            r0 = r11
            r7 = r12
            r8 = r13
            r9 = r14
            r10 = r15
            boolean r1 = r12.isHardwareAccelerated()
            if (r1 == 0) goto L23
            boolean r1 = r7 instanceof com.jingdong.manto.m.u0.m
            if (r1 == 0) goto L1e
            r1 = r7
            com.jingdong.manto.m.u0.m r1 = (com.jingdong.manto.m.u0.m) r1
            float r2 = (float) r9
            float r3 = (float) r10
            int r4 = r9 + r16
            float r4 = (float) r4
            int r5 = r10 + r17
            float r5 = (float) r5
            r1.a(r2, r3, r4, r5)
            goto L40
        L1e:
            android.graphics.Paint r1 = r0.f13733i
            if (r1 == 0) goto L40
            goto L27
        L23:
            android.graphics.Paint r1 = r0.f13733i
            if (r1 == 0) goto L35
        L27:
            r6 = r1
            float r2 = (float) r9
            float r3 = (float) r10
            int r1 = r9 + r16
            float r4 = (float) r1
            int r1 = r10 + r17
            float r5 = (float) r1
        L30:
            r1 = r12
            r1.drawRect(r2, r3, r4, r5, r6)
            goto L40
        L35:
            float r2 = (float) r9
            float r3 = (float) r10
            int r1 = r9 + r16
            float r4 = (float) r1
            int r1 = r10 + r17
            float r5 = (float) r1
            android.graphics.Paint r6 = r0.a
            goto L30
        L40:
            r1 = 0
            if (r8 == 0) goto L6b
            boolean r2 = r13.isRecycled()
            if (r2 == 0) goto L4a
            goto L6b
        L4a:
            android.graphics.Rect r2 = new android.graphics.Rect
            int r3 = r13.getWidth()
            int r4 = r13.getHeight()
            r2.<init>(r1, r1, r3, r4)
            android.graphics.RectF r1 = new android.graphics.RectF
            float r3 = (float) r9
            float r4 = (float) r10
            int r5 = r9 + r16
            float r5 = (float) r5
            int r6 = r10 + r17
            float r6 = (float) r6
            r1.<init>(r3, r4, r5, r6)
            com.jingdong.manto.m.u0.n r0 = r0.f13729e
            r12.drawBitmap(r13, r2, r1, r0)
            r0 = 1
            return r0
        L6b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.u0.o.z.a(com.jingdong.manto.m.u0.c, android.graphics.Canvas, android.graphics.Bitmap, int, int, int, int):boolean");
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
