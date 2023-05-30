package com.tencent.mapsdk.internal;

import com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.heatmap.GradientVectorOverlayProvider;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.mapsdk.internal.f4;
import com.tencent.mapsdk.internal.h4;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class mh implements kh {
    @Override // com.tencent.mapsdk.internal.kh
    public BaseOverlayProvider a(f4 f4Var) {
        f4.c.d dVar;
        if ((f4Var instanceof h4) && f4Var.a()) {
            h4 h4Var = (h4) f4Var;
            GradientVectorOverlayProvider gradientVectorOverlayProvider = new GradientVectorOverlayProvider();
            ArrayList arrayList = new ArrayList();
            for (f4.a.C0792a.AbstractC0793a abstractC0793a : h4Var.b.b.b) {
                if (abstractC0793a instanceof f4.a.C0792a.e) {
                    arrayList.addAll(((f4.a.C0792a.e) abstractC0793a).f16497c);
                }
            }
            gradientVectorOverlayProvider.weightedData(arrayList);
            int size = h4Var.b.f16649c.f16650c.a.a.b.b.size();
            int[] iArr = new int[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = h4Var.b.f16649c.f16650c.a.a.b.b.get(i2).intValue();
            }
            float[] fArr = new float[h4Var.b.f16649c.f16650c.a.a.b.a.size()];
            for (int i3 = 0; i3 < size; i3++) {
                fArr[i3] = h4Var.b.f16649c.f16650c.a.a.b.a.get(i3).floatValue();
            }
            gradientVectorOverlayProvider.gradient(iArr, fArr);
            gradientVectorOverlayProvider.radius(h4Var.b.f16649c.f16650c.a.a.a);
            gradientVectorOverlayProvider.setMaxHeight(h4Var.b.f16649c.d.f16654k.get(1).floatValue());
            h4.a.C0799a.C0800a c0800a = h4Var.b.f16649c.d;
            gradientVectorOverlayProvider.setIntensityRange((float) c0800a.f16652i, (float) c0800a.f16651h);
            h4.a.C0799a.C0800a c0800a2 = h4Var.b.f16649c.d;
            gradientVectorOverlayProvider.zoomRange(c0800a2.f16500f, c0800a2.f16499e);
            gradientVectorOverlayProvider.zIndex(h4Var.b.f16649c.d.b);
            gradientVectorOverlayProvider.displayLevel(h4Var.b.f16649c.d.a);
            gradientVectorOverlayProvider.enable3D(h4Var.b.f16649c.d.f16653j);
            gradientVectorOverlayProvider.opacity((float) h4Var.b.f16649c.d.d);
            gradientVectorOverlayProvider.visibility(!h4Var.b.f16649c.d.f16498c);
            h4.a.C0799a c0799a = h4Var.b.f16649c;
            if (!c0799a.d.f16655l || (dVar = c0799a.f16650c.a.a.f16656c) == null) {
                gradientVectorOverlayProvider.setAnimateDuration(0);
            } else {
                gradientVectorOverlayProvider.setAnimateDuration((int) (dVar.a * 1000.0d));
            }
            return gradientVectorOverlayProvider;
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.kh
    public f4 a(f4 f4Var, String str) {
        return f4Var;
    }

    @Override // com.tencent.mapsdk.internal.kh
    public f4 a(byte[] bArr) {
        return (f4) JsonUtils.parseToModel(new String(bArr), h4.class, new Object[0]);
    }
}
