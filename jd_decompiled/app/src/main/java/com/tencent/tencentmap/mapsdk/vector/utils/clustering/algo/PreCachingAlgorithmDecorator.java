package com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo;

import androidx.collection.LruCache;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes9.dex */
public class PreCachingAlgorithmDecorator<T extends ClusterItem> implements Algorithm<T> {
    public final Algorithm<T> a;
    public final LruCache<Integer, Set<? extends Cluster<T>>> b = new LruCache<>(5);

    /* renamed from: c  reason: collision with root package name */
    public final ReadWriteLock f18032c = new ReentrantReadWriteLock();

    /* loaded from: classes9.dex */
    private class PrecacheRunnable implements Runnable {
        public final int a;

        public PrecacheRunnable(int i2) {
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep((long) ((Math.random() * 500.0d) + 500.0d));
            } catch (InterruptedException unused) {
            }
            PreCachingAlgorithmDecorator.this.a(this.a);
        }
    }

    public PreCachingAlgorithmDecorator(Algorithm<T> algorithm) {
        this.a = algorithm;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void addItem(T t) {
        this.a.addItem(t);
        a();
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void addItems(Collection<T> collection) {
        this.a.addItems(collection);
        a();
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void clearItems() {
        this.a.clearItems();
        a();
    }

    public Algorithm getAlgorithm() {
        return this.a;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public Set<? extends Cluster<T>> getClusters(double d) {
        int i2 = (int) d;
        Set<? extends Cluster<T>> a = a(i2);
        int i3 = i2 + 1;
        if (this.b.get(Integer.valueOf(i3)) == null) {
            new Thread(new PrecacheRunnable(i3)).start();
        }
        int i4 = i2 - 1;
        if (this.b.get(Integer.valueOf(i4)) == null) {
            new Thread(new PrecacheRunnable(i4)).start();
        }
        return a;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public Collection<T> getItems() {
        return this.a.getItems();
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm
    public void removeItem(T t) {
        this.a.removeItem(t);
        a();
    }

    public final void a() {
        this.b.evictAll();
    }

    public final Set<? extends Cluster<T>> a(int i2) {
        this.f18032c.readLock().lock();
        Set<? extends Cluster<T>> set = this.b.get(Integer.valueOf(i2));
        this.f18032c.readLock().unlock();
        if (set == null) {
            this.f18032c.writeLock().lock();
            set = this.b.get(Integer.valueOf(i2));
            if (set == null) {
                set = this.a.getClusters(i2);
                this.b.put(Integer.valueOf(i2), set);
            }
            this.f18032c.writeLock().unlock();
        }
        return set;
    }
}
