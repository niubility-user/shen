package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.b;
import org.json.JSONArray;

/* loaded from: classes15.dex */
public final class e implements i {

    /* loaded from: classes15.dex */
    public class a implements b.d {
        a(e eVar) {
        }

        @Override // com.jingdong.manto.utils.b.d
        public final void a() {
        }
    }

    /* loaded from: classes15.dex */
    public class b implements b.d {
        b(e eVar) {
        }

        @Override // com.jingdong.manto.utils.b.d
        public final void a() {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(com.jingdong.manto.m.u0.c r17, android.graphics.Canvas r18, int r19, java.lang.String r20, float r21, float r22, float r23, float r24, int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.u0.o.e.a(com.jingdong.manto.m.u0.c, android.graphics.Canvas, int, java.lang.String, float, float, float, float, int, int, int, int):boolean");
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "drawImage";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.b bVar = (com.jingdong.manto.m.u0.o.k0.b) cVar2;
        if (bVar == null) {
            return false;
        }
        return a(cVar, canvas, bVar.b, bVar.f13749c, bVar.d, bVar.f13750e, bVar.f13751f, bVar.f13752g, bVar.f13753h, bVar.f13754i, bVar.f13755j, bVar.f13756k);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        int length = jSONArray.length();
        if (length < 3) {
            return false;
        }
        return a(cVar, canvas, length, jSONArray.optString(0), MantoDensityUtils.convertToDeviceSize2(jSONArray, 1), MantoDensityUtils.convertToDeviceSize2(jSONArray, 2), MantoDensityUtils.convertToDeviceSize2(jSONArray, 3), MantoDensityUtils.convertToDeviceSize2(jSONArray, 4), jSONArray.optInt(5), jSONArray.optInt(6), jSONArray.optInt(7), jSONArray.optInt(8));
    }
}
