package com.jingdong.manto.m.u0.o.k0.c0;

import android.graphics.Paint;
import com.jingdong.manto.m.u0.n;
import com.jingdong.manto.utils.MantoDensityUtils;

/* loaded from: classes15.dex */
public final class e extends b {
    private static e b = new e();

    private e() {
    }

    public static e c() {
        return b;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.b
    public final n a() {
        n nVar = new n();
        nVar.setStyle(Paint.Style.FILL);
        nVar.setAntiAlias(true);
        nVar.setStrokeWidth(MantoDensityUtils.convertToDeviceSizeByInt(1));
        return nVar;
    }

    @Override // com.jingdong.manto.m.u0.o.k0.c0.b
    public final void a(n nVar) {
        nVar.reset();
        nVar.c();
        nVar.setStyle(Paint.Style.FILL);
        nVar.setAntiAlias(true);
        nVar.setStrokeWidth(MantoDensityUtils.convertToDeviceSizeByInt(1));
        super.a(nVar);
    }
}
