package com.tencent.mapsdk.internal;

import com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.datamodels.FromToLatLng;
import com.tencent.map.sdk.utilities.visualization.od.ArcLineOverlayProvider;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.mapsdk.internal.b4;
import com.tencent.mapsdk.internal.f4;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class ih implements kh {
    @Override // com.tencent.mapsdk.internal.kh
    public BaseOverlayProvider a(f4 f4Var) {
        if ((f4Var instanceof b4) && f4Var.a()) {
            b4 b4Var = (b4) f4Var;
            ArcLineOverlayProvider arcLineOverlayProvider = new ArcLineOverlayProvider();
            if (b4Var.b.b.b != null) {
                ArrayList arrayList = new ArrayList();
                for (f4.a.C0792a.AbstractC0793a abstractC0793a : b4Var.b.b.b) {
                    if (abstractC0793a instanceof f4.a.C0792a.b) {
                        f4.a.C0792a.b bVar = (f4.a.C0792a.b) abstractC0793a;
                        arrayList.add(new FromToLatLng(bVar.f16490c.get(0), bVar.f16490c.get(1)));
                    }
                }
                arcLineOverlayProvider.data(arrayList);
            }
            f4.c.e eVar = b4Var.b.f16283c.f16284c.a.a.f16289c;
            if (eVar != null) {
                int size = eVar.b.size();
                int[] iArr = new int[size];
                for (int i2 = 0; i2 < size; i2++) {
                    iArr[i2] = b4Var.b.f16283c.f16284c.a.a.f16289c.b.get(i2).intValue();
                }
                arcLineOverlayProvider.gradient(iArr);
            }
            arcLineOverlayProvider.width(b4Var.b.f16283c.f16284c.a.a.a);
            arcLineOverlayProvider.radian((float) (b4Var.b.f16283c.f16284c.a.a.b * 90.0d));
            b4.a.C0785a.C0786a c0786a = b4Var.b.f16283c.d;
            arcLineOverlayProvider.zoomRange(c0786a.f16500f, c0786a.f16499e);
            arcLineOverlayProvider.zIndex(b4Var.b.f16283c.d.b);
            arcLineOverlayProvider.displayLevel(b4Var.b.f16283c.d.a);
            arcLineOverlayProvider.enable3D(b4Var.b.f16283c.d.f16285h);
            arcLineOverlayProvider.opacity((float) b4Var.b.f16283c.d.d);
            arcLineOverlayProvider.visibility(!b4Var.b.f16283c.d.f16498c);
            b4.a.C0785a.C0786a c0786a2 = b4Var.b.f16283c.d;
            if (c0786a2.f16286i) {
                arcLineOverlayProvider.setAnimateDuration((int) (c0786a2.f16287j.f16288c * 1000.0d));
                arcLineOverlayProvider.setHighlightDuration((int) (b4Var.b.f16283c.d.f16287j.a * 1000.0d));
                arcLineOverlayProvider.animateColor(b4Var.b.f16283c.d.f16287j.b);
            } else {
                arcLineOverlayProvider.setAnimateDuration(0);
            }
            return arcLineOverlayProvider;
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.kh
    public f4 a(f4 f4Var, String str) {
        return f4Var;
    }

    @Override // com.tencent.mapsdk.internal.kh
    public f4 a(byte[] bArr) {
        return (f4) JsonUtils.parseToModel(new String(bArr), b4.class, new Object[0]);
    }
}
