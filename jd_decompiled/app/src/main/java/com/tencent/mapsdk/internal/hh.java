package com.tencent.mapsdk.internal;

import com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.aggregation.AggregationOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.aggregation.HoneyCombVectorOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.aggregation.SquareVectorOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.mapsdk.internal.a4;
import com.tencent.mapsdk.internal.f4;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class hh implements kh {
    @Override // com.tencent.mapsdk.internal.kh
    public BaseOverlayProvider a(f4 f4Var) {
        f4.c.d dVar;
        if ((f4Var instanceof a4) && f4Var.a()) {
            a4 a4Var = (a4) f4Var;
            AggregationOverlayProvider honeyCombVectorOverlayProvider = a4Var.b.f16222c.f16223c.a.a.f16229c.equals("hexagon") ? new HoneyCombVectorOverlayProvider() : a4Var.b.f16222c.f16223c.a.a.f16229c.equals("grid") ? new SquareVectorOverlayProvider() : null;
            if (honeyCombVectorOverlayProvider == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (f4.a.C0792a.AbstractC0793a abstractC0793a : a4Var.b.b.b) {
                if (abstractC0793a instanceof f4.a.C0792a.e) {
                    arrayList.addAll(((f4.a.C0792a.e) abstractC0793a).f16497c);
                }
            }
            AggregationOverlayProvider nodes = honeyCombVectorOverlayProvider.nodes((WeightedLatLng[]) arrayList.toArray(new WeightedLatLng[0]));
            int size = a4Var.b.f16222c.f16223c.a.a.d.b.size();
            int[] iArr = new int[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = a4Var.b.f16222c.f16223c.a.a.d.b.get(i2).intValue();
            }
            double[] dArr = new double[a4Var.b.f16222c.f16223c.a.a.d.a.size()];
            for (int i3 = 0; i3 < size; i3++) {
                dArr[i3] = a4Var.b.f16222c.f16223c.a.a.d.a.get(i3).doubleValue();
            }
            nodes.colors(iArr, dArr);
            nodes.size(a4Var.b.f16222c.f16223c.a.a.b);
            nodes.gap(a4Var.b.f16222c.f16223c.a.a.a);
            nodes.setHeightRange(a4Var.b.f16222c.d.f16227k.get(0).doubleValue(), a4Var.b.f16222c.d.f16227k.get(1).doubleValue());
            a4.a.C0780a.C0781a c0781a = a4Var.b.f16222c.d;
            nodes.setIntensityRange(c0781a.f16225i, c0781a.f16224h);
            a4.a.C0780a.C0781a c0781a2 = a4Var.b.f16222c.d;
            nodes.zoomRange(c0781a2.f16500f, c0781a2.f16499e);
            nodes.zIndex(a4Var.b.f16222c.d.b);
            nodes.displayLevel(a4Var.b.f16222c.d.a);
            nodes.enable3D(a4Var.b.f16222c.d.f16226j);
            nodes.opacity((float) a4Var.b.f16222c.d.d);
            nodes.visibility(!a4Var.b.f16222c.d.f16498c);
            a4.a.C0780a c0780a = a4Var.b.f16222c;
            if (!c0780a.d.f16228l || (dVar = c0780a.f16223c.a.a.f16230e) == null) {
                nodes.setAnimateDuration(0);
            } else {
                nodes.setAnimateDuration((int) (dVar.a * 1000.0d));
            }
            return nodes;
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.kh
    public f4 a(f4 f4Var, String str) {
        return f4Var;
    }

    @Override // com.tencent.mapsdk.internal.kh
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public a4 a(byte[] bArr) {
        return (a4) JsonUtils.parseToModel(new String(bArr), a4.class, new Object[0]);
    }
}
