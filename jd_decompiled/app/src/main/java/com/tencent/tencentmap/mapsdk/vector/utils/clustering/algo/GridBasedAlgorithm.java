package com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo;

import androidx.collection.LongSparseArray;
import com.tencent.tencentmap.mapsdk.vector.utils.a.f;
import com.tencent.tencentmap.mapsdk.vector.utils.a.g;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterItem;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes9.dex */
public class GridBasedAlgorithm<T extends ClusterItem> implements Algorithm<T> {
    public final Set<T> a = Collections.synchronizedSet(new HashSet());

    public static long a(long j2, double d, double d2) {
        double d3 = j2;
        double floor = Math.floor(d);
        Double.isNaN(d3);
        return (long) ((d3 * floor) + Math.floor(d2));
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void addItem(T t) {
        this.a.add(t);
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void addItems(Collection<T> collection) {
        this.a.addAll(collection);
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void clearItems() {
        this.a.clear();
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public Set<? extends Cluster<T>> getClusters(double d) {
        long j2;
        long ceil = (long) Math.ceil((Math.pow(2.0d, d) * 256.0d) / 100.0d);
        g gVar = new g(ceil);
        HashSet hashSet = new HashSet();
        LongSparseArray longSparseArray = new LongSparseArray();
        synchronized (this.a) {
            for (T t : this.a) {
                f a = gVar.a(t.getPosition());
                long a2 = a(ceil, a.a, a.b);
                StaticCluster staticCluster = (StaticCluster) longSparseArray.get(a2);
                if (staticCluster == null) {
                    j2 = ceil;
                    staticCluster = new StaticCluster(gVar.a(new f(Math.floor(a.a) + 0.5d, Math.floor(a.b) + 0.5d)));
                    longSparseArray.put(a2, staticCluster);
                    hashSet.add(staticCluster);
                } else {
                    j2 = ceil;
                }
                staticCluster.add(t);
                ceil = j2;
            }
        }
        return hashSet;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public Collection<T> getItems() {
        return this.a;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void removeItem(T t) {
        this.a.remove(t);
    }
}
