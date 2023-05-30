package com.tencent.mapsdk.core;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.tencent.mapsdk.internal.d7;
import com.tencent.mapsdk.internal.s;
import com.tencent.mapsdk.internal.u;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;

@Keep
/* loaded from: classes9.dex */
public class MapDelegateFactoryImpl implements s {
    @Override // com.tencent.mapsdk.internal.s
    @Keep
    public MapDelegate createDelegate(@NonNull Context context, @NonNull TencentMapOptions tencentMapOptions, @NonNull ViewGroup viewGroup) {
        TencentMapOptions.IMapKernel mapKernel = tencentMapOptions.getMapKernel();
        Class<?> a = d7.a("com.tencent.mapsdk.navi.MapKernelNavi", mapKernel.getClass().getClassLoader());
        Class<?> a2 = d7.a("com.tencent.mapsdk.compat.MapKernelCompat", mapKernel.getClass().getClassLoader());
        MapDelegate mapDelegate = (MapDelegate) ((a2 == null || a2 != mapKernel.getClass()) ? (a == null || a != mapKernel.getClass()) ? d7.a(d7.a("com.tencent.mapsdk.vector.VectorMapDelegateProxy", MapDelegate.class, u.f().d()), context, tencentMapOptions, viewGroup) : d7.a(d7.a("com.tencent.mapsdk.navi.VectorMapNaviDelegateProxy", MapDelegate.class, u.f().d()), context, tencentMapOptions, viewGroup) : d7.a(d7.a("com.tencent.mapsdk.compat.VectorMapCompatDelegateProxy", MapDelegate.class, u.f().d()), context, tencentMapOptions, viewGroup));
        if (mapDelegate != null) {
            mapDelegate.onCreated();
        }
        return mapDelegate;
    }
}
