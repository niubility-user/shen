package com.tencent.mapsdk.core;

import android.view.ViewGroup;
import androidx.annotation.Keep;
import com.tencent.mapsdk.internal.v1;
import com.tencent.tencentmap.mapsdk.maps.BaseMapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapContext;

@Keep
/* loaded from: classes9.dex */
public interface MapDelegate<C extends TencentMapContext, M extends TencentMap, V extends v1> extends BaseMapView.MapViewProxy {
    M createMap(C c2);

    V createMapView(C c2, ViewGroup viewGroup);

    C getMapContext();

    V getMapRenderView();
}
