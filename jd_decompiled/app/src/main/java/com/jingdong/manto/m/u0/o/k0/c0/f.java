package com.jingdong.manto.m.u0.o.k0.c0;

import android.graphics.Paint;
import com.jingdong.manto.m.u0.n;
import com.jingdong.manto.utils.MantoDensityUtils;

/* loaded from: classes15.dex */
public final class f extends b {
    private static final f b = new f();

    private f() {
    }

    public static f c() {
        return b;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.b
    public final n a() {
        n nVar = new n();
        nVar.setStyle(Paint.Style.STROKE);
        nVar.setAntiAlias(true);
        nVar.setStrokeWidth(MantoDensityUtils.convertToDeviceSizeByInt(1));
        return nVar;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.b
    public final void a(n nVar) {
        nVar.reset();
        nVar.c();
        nVar.setStyle(Paint.Style.STROKE);
        nVar.setAntiAlias(true);
        nVar.setStrokeWidth(MantoDensityUtils.convertToDeviceSizeByInt(1));
        super.a(nVar);
    }
}
