package com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo;

import com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;

/* loaded from: classes9.dex */
public interface Algorithm<T extends ClusterItem> {
    void addItem(T t);

    void addItems(Collection<T> collection);

    void clearItems();

    Set<? extends Cluster<T>> getClusters(double d);

    Collection<T> getItems();

    void removeItem(T t);
}
