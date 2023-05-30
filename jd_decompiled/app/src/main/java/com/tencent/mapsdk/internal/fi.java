package com.tencent.mapsdk.internal;

import android.content.Context;
import android.view.ViewGroup;
import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;

/* loaded from: classes9.dex */
public class fi extends xi {
    public fi(Context context, TencentMapOptions tencentMapOptions, ViewGroup viewGroup) {
        super(context, tencentMapOptions, viewGroup);
    }

    @Override // com.tencent.mapsdk.internal.ej, com.tencent.mapsdk.internal.p1
    /* renamed from: a */
    public VectorMap b(qc qcVar) {
        return new ei(qcVar);
    }
}
