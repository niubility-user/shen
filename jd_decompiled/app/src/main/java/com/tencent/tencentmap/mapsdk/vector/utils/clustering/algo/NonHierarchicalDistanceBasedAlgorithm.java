package com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo;

import android.content.Context;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.vector.utils.a.e;
import com.tencent.tencentmap.mapsdk.vector.utils.a.f;
import com.tencent.tencentmap.mapsdk.vector.utils.a.g;
import com.tencent.tencentmap.mapsdk.vector.utils.a.h;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes9.dex */
public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem> implements Algorithm<T> {
    public static final g a = new g(1.0d);
    public Context b;
    public int d;

    /* renamed from: c */
    public int f18028c = 35;

    /* renamed from: e */
    public final Collection<QuadItem<T>> f18029e = new ArrayList();

    /* renamed from: f */
    public final h<QuadItem<T>> f18030f = new h<>(0.0d, 1.0d, 0.0d, 1.0d);

    /* loaded from: classes9.dex */
    public static class QuadItem<T extends ClusterItem> implements h.a, Cluster<T> {
        public final T a;
        public final f b;

        /* renamed from: c */
        public final LatLng f18031c;
        public Set<T> d;

        public boolean equals(Object obj) {
            if (obj instanceof QuadItem) {
                return ((QuadItem) obj).a.equals(this.a);
            }
            return false;
        }

        @Override // com.tencent.tencentmap.mapsdk.vector.utils.a.h.a
        public f getPoint() {
            return this.b;
        }

        @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster
        public LatLng getPosition() {
            return this.f18031c;
        }

        @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster
        public int getSize() {
            return 1;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public QuadItem(T t) {
            this.a = t;
            LatLng position = t.getPosition();
            this.f18031c = position;
            this.b = NonHierarchicalDistanceBasedAlgorithm.a.a(position);
            this.d = Collections.singleton(t);
        }

        @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster
        public Set<T> getItems() {
            return this.d;
        }
    }

    public NonHierarchicalDistanceBasedAlgorithm(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        this.d = (int) ((applicationContext.getResources().getDisplayMetrics().density * this.f18028c) + 0.5f);
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void addItem(T t) {
        QuadItem<T> quadItem = new QuadItem<>(t);
        synchronized (this.f18030f) {
            this.f18029e.add(quadItem);
            this.f18030f.a((h<QuadItem<T>>) quadItem);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void addItems(Collection<T> collection) {
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            addItem(it.next());
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void clearItems() {
        synchronized (this.f18030f) {
            this.f18029e.clear();
            this.f18030f.a();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public Set<? extends Cluster<T>> getClusters(double d) {
        double d2 = this.d;
        int i2 = 1;
        double pow = Math.pow(2.0d, ((int) d) + 1);
        Double.isNaN(d2);
        double d3 = (d2 / pow) / 256.0d;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        synchronized (this.f18030f) {
            for (QuadItem<T> quadItem : this.f18029e) {
                if (!hashSet.contains(quadItem)) {
                    Collection<QuadItem<T>> a2 = this.f18030f.a(a(quadItem.getPoint(), d3));
                    if (a2.size() == i2) {
                        hashSet2.add(quadItem);
                        hashSet.add(quadItem);
                        hashMap.put(quadItem, Double.valueOf(0.0d));
                    } else {
                        StaticCluster staticCluster = new StaticCluster(quadItem.a.getPosition());
                        hashSet2.add(staticCluster);
                        for (QuadItem<T> quadItem2 : a2) {
                            Double d4 = (Double) hashMap.get(quadItem2);
                            double d5 = d3;
                            double a3 = a(quadItem2.getPoint(), quadItem.getPoint());
                            if (d4 != null) {
                                if (d4.doubleValue() < a3) {
                                    d3 = d5;
                                } else {
                                    ((StaticCluster) hashMap2.get(quadItem2)).remove(quadItem2.a);
                                }
                            }
                            hashMap.put(quadItem2, Double.valueOf(a3));
                            staticCluster.add(quadItem2.a);
                            hashMap2.put(quadItem2, staticCluster);
                            d3 = d5;
                        }
                        hashSet.addAll(a2);
                        d3 = d3;
                        i2 = 1;
                    }
                }
            }
        }
        return hashSet2;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public Collection<T> getItems() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f18030f) {
            Iterator<QuadItem<T>> it = this.f18029e.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().a);
            }
        }
        return arrayList;
    }

    public int getMaxDistanceAtZoom() {
        return this.f18028c;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void removeItem(T t) {
        QuadItem<T> quadItem = new QuadItem<>(t);
        synchronized (this.f18030f) {
            this.f18029e.remove(quadItem);
            this.f18030f.b(quadItem);
        }
    }

    public void setMaxDistanceAtZoom(int i2) {
        this.f18028c = i2;
        this.d = (int) ((this.b.getResources().getDisplayMetrics().density * this.f18028c) + 0.5f);
    }

    public final double a(f fVar, f fVar2) {
        double d = fVar.a - fVar2.a;
        double d2 = fVar.b - fVar2.b;
        return (d * d) + (d2 * d2);
    }

    public final e a(f fVar, double d) {
        double d2 = d / 2.0d;
        double d3 = fVar.a;
        double d4 = d3 - d2;
        double d5 = d3 + d2;
        double d6 = fVar.b;
        return new e(d4, d5, d6 - d2, d6 + d2);
    }
}
