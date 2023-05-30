package com.tencent.tencentmap.mapsdk.vector.utils.clustering;

import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterItem;
import java.util.Collection;

/* loaded from: classes9.dex */
public interface Cluster<T extends ClusterItem> {
    Collection<T> getItems();

    LatLng getPosition();

    int getSize();
}
