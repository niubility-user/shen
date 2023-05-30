package com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo;

import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes9.dex */
public class StaticCluster<T extends ClusterItem> implements Cluster<T> {
    public final LatLng a;
    public final List<T> b = new ArrayList();

    public StaticCluster(LatLng latLng) {
        this.a = latLng;
    }

    public boolean add(T t) {
        return this.b.add(t);
    }

    public boolean equals(Object obj) {
        if (obj instanceof StaticCluster) {
            StaticCluster staticCluster = (StaticCluster) obj;
            return staticCluster.a.equals(this.a) && staticCluster.b.equals(this.b);
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster
    public Collection<T> getItems() {
        return this.b;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster
    public LatLng getPosition() {
        return this.a;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster
    public int getSize() {
        return this.b.size();
    }

    public boolean remove(T t) {
        return this.b.remove(t);
    }

    public String toString() {
        return "StaticCluster{mCenter=" + this.a + ", mItems.size=" + this.b.size() + '}';
    }
}
