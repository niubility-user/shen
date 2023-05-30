package com.tencent.tencentmap.mapsdk.vector.utils.clustering;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.vector.utils.MarkerManager;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterItem;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.PreCachingAlgorithmDecorator;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.DefaultClusterRenderer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes9.dex */
public class ClusterManager<T extends ClusterItem> implements TencentMap.OnCameraChangeListener, TencentMap.OnMarkerClickListener, TencentMap.OnInfoWindowClickListener, TencentMap.InfoWindowAdapter {
    public final MarkerManager a;
    public final MarkerManager.Collection b;

    /* renamed from: c  reason: collision with root package name */
    public final MarkerManager.Collection f18016c;
    public Algorithm<T> d;

    /* renamed from: e  reason: collision with root package name */
    public final ReadWriteLock f18017e;

    /* renamed from: f  reason: collision with root package name */
    public ClusterRenderer<T> f18018f;

    /* renamed from: g  reason: collision with root package name */
    public TencentMap f18019g;

    /* renamed from: h  reason: collision with root package name */
    public CameraPosition f18020h;

    /* renamed from: i  reason: collision with root package name */
    public ClusterManager<T>.a f18021i;

    /* renamed from: j  reason: collision with root package name */
    public final ReadWriteLock f18022j;

    /* renamed from: k  reason: collision with root package name */
    public boolean f18023k;

    /* renamed from: l  reason: collision with root package name */
    public OnClusterItemClickListener<T> f18024l;

    /* renamed from: m  reason: collision with root package name */
    public OnClusterInfoWindowClickListener<T> f18025m;

    /* renamed from: n  reason: collision with root package name */
    public ClusterInfoWindowAdapter<T> f18026n;
    public OnClusterItemInfoWindowClickListener<T> o;
    public OnClusterClickListener<T> p;
    public ClusterItemInfoWindowAdapter<T> q;

    /* loaded from: classes9.dex */
    public interface ClusterInfoWindowAdapter<T extends ClusterItem> {
        View getInfoContents(Cluster<T> cluster);

        View getInfoWindow(Cluster<T> cluster);

        View getInfoWindowPressState(Cluster<T> cluster);
    }

    /* loaded from: classes9.dex */
    public interface ClusterItemInfoWindowAdapter<T extends ClusterItem> {
        View getInfoContents(T t);

        View getInfoWindow(T t);

        View getInfoWindowPressState(T t);
    }

    /* loaded from: classes9.dex */
    public interface OnClusterClickListener<T extends ClusterItem> {
        boolean onClusterClick(Cluster<T> cluster);
    }

    /* loaded from: classes9.dex */
    public interface OnClusterInfoWindowClickListener<T extends ClusterItem> {
        void onClusterInfoWindowClick(Cluster<T> cluster);
    }

    /* loaded from: classes9.dex */
    public interface OnClusterItemClickListener<T extends ClusterItem> {
        boolean onClusterItemClick(T t);
    }

    /* loaded from: classes9.dex */
    public interface OnClusterItemInfoWindowClickListener<T extends ClusterItem> {
        void onClusterItemInfoWindowClick(T t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class b implements Cluster<T> {
        public T a;
        public Set<T> b;

        public b(T t) {
            this.a = t;
            this.b = Collections.singleton(t);
        }

        @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster
        public Collection<T> getItems() {
            return this.b;
        }

        @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster
        public LatLng getPosition() {
            return this.a.getPosition();
        }

        @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster
        public int getSize() {
            return 1;
        }
    }

    public ClusterManager(Context context, TencentMap tencentMap) {
        this(context, tencentMap, new MarkerManager(tencentMap));
    }

    public void addItem(T t) {
        this.f18017e.writeLock().lock();
        try {
            this.d.addItem(t);
        } finally {
            this.f18017e.writeLock().unlock();
        }
    }

    public void addItems(Collection<T> collection) {
        this.f18017e.writeLock().lock();
        try {
            this.d.addItems(collection);
        } finally {
            this.f18017e.writeLock().unlock();
        }
    }

    public void cancel() {
        clearItems();
        if (this.f18021i.cancel(true)) {
            return;
        }
        this.f18018f.cancel();
    }

    public void clearItems() {
        this.f18017e.writeLock().lock();
        try {
            this.d.clearItems();
        } finally {
            this.f18017e.writeLock().unlock();
        }
    }

    public void cluster() {
        this.f18022j.writeLock().lock();
        try {
            this.f18021i.cancel(true);
            ClusterManager<T>.a aVar = new a();
            this.f18021i = aVar;
            if (Build.VERSION.SDK_INT < 11) {
                aVar.execute(Float.valueOf(this.f18019g.getCameraPosition().zoom));
            } else {
                aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Float.valueOf(this.f18019g.getCameraPosition().zoom));
            }
        } finally {
            this.f18022j.writeLock().unlock();
        }
    }

    public Algorithm<T> getAlgorithm() {
        return this.d;
    }

    public MarkerManager.Collection getClusterMarkerCollection() {
        return this.f18016c;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
    public View getInfoContents(Marker marker) {
        return getMarkerManager().getInfoContents(marker);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
    public View getInfoWindow(Marker marker) {
        return getMarkerManager().getInfoWindow(marker);
    }

    public MarkerManager.Collection getMarkerCollection() {
        return this.b;
    }

    public MarkerManager getMarkerManager() {
        return this.a;
    }

    public ClusterRenderer<T> getRenderer() {
        return this.f18018f;
    }

    public boolean isClusterEnabled() {
        return this.f18023k;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
    public void onCameraChange(CameraPosition cameraPosition) {
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
    public void onCameraChangeFinished(CameraPosition cameraPosition) {
        if (this.f18023k) {
            ClusterRenderer<T> clusterRenderer = this.f18018f;
            if (clusterRenderer instanceof TencentMap.OnCameraChangeListener) {
                ((TencentMap.OnCameraChangeListener) clusterRenderer).onCameraChangeFinished(cameraPosition);
            }
            CameraPosition cameraPosition2 = this.f18019g.getCameraPosition();
            CameraPosition cameraPosition3 = this.f18020h;
            if (cameraPosition3 == null || cameraPosition3.zoom != cameraPosition2.zoom) {
                this.f18020h = this.f18019g.getCameraPosition();
                cluster();
            }
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
    public void onInfoWindowClick(Marker marker) {
        getMarkerManager().onInfoWindowClick(marker);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
    public void onInfoWindowClickLocation(int i2, int i3, int i4, int i5) {
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerClickListener
    public boolean onMarkerClick(Marker marker) {
        return getMarkerManager().onMarkerClick(marker);
    }

    public void removeItem(T t) {
        this.f18017e.writeLock().lock();
        try {
            this.d.removeItem(t);
        } finally {
            this.f18017e.writeLock().unlock();
        }
    }

    public void setAlgorithm(Algorithm<T> algorithm) {
        this.f18017e.writeLock().lock();
        try {
            Algorithm<T> algorithm2 = this.d;
            if (algorithm2 != null) {
                algorithm.addItems(algorithm2.getItems());
            }
            this.d = new PreCachingAlgorithmDecorator(algorithm);
            this.f18017e.writeLock().unlock();
            cluster();
        } catch (Throwable th) {
            this.f18017e.writeLock().unlock();
            throw th;
        }
    }

    public void setClusterEnabled(boolean z) {
        if (this.f18023k ^ z) {
            this.f18023k = z;
            cluster();
        }
    }

    public void setClusterInfoWindowAdapter(ClusterInfoWindowAdapter<T> clusterInfoWindowAdapter) {
        this.f18026n = clusterInfoWindowAdapter;
        this.f18018f.setInfoWindowAdapter(clusterInfoWindowAdapter);
    }

    public void setClusterItemInfoWindowAdapter(ClusterItemInfoWindowAdapter<T> clusterItemInfoWindowAdapter) {
        this.q = clusterItemInfoWindowAdapter;
        this.f18018f.setItemInfoWindowAdapter(clusterItemInfoWindowAdapter);
    }

    public void setOnClusterClickListener(OnClusterClickListener<T> onClusterClickListener) {
        this.p = onClusterClickListener;
        this.f18018f.setOnClusterClickListener(onClusterClickListener);
    }

    public void setOnClusterInfoWindowClickListener(OnClusterInfoWindowClickListener<T> onClusterInfoWindowClickListener) {
        this.f18025m = onClusterInfoWindowClickListener;
        this.f18018f.setOnClusterInfoWindowClickListener(onClusterInfoWindowClickListener);
    }

    public void setOnClusterItemClickListener(OnClusterItemClickListener<T> onClusterItemClickListener) {
        this.f18024l = onClusterItemClickListener;
        this.f18018f.setOnClusterItemClickListener(onClusterItemClickListener);
    }

    public void setOnClusterItemInfoWindowClickListener(OnClusterItemInfoWindowClickListener<T> onClusterItemInfoWindowClickListener) {
        this.o = onClusterItemInfoWindowClickListener;
        this.f18018f.setOnClusterItemInfoWindowClickListener(onClusterItemInfoWindowClickListener);
    }

    public void setRenderer(ClusterRenderer<T> clusterRenderer) {
        this.f18018f.setOnClusterClickListener(null);
        this.f18018f.setOnClusterItemClickListener(null);
        this.f18016c.a();
        this.b.a();
        this.f18018f.onRemove();
        this.f18018f = clusterRenderer;
        clusterRenderer.onAdd();
        this.f18018f.setOnClusterClickListener(this.p);
        this.f18018f.setOnClusterInfoWindowClickListener(this.f18025m);
        this.f18018f.setOnClusterItemClickListener(this.f18024l);
        this.f18018f.setOnClusterItemInfoWindowClickListener(this.o);
        cluster();
    }

    public ClusterManager(Context context, TencentMap tencentMap, MarkerManager markerManager) {
        this.f18017e = new ReentrantReadWriteLock();
        this.f18022j = new ReentrantReadWriteLock();
        this.f18023k = true;
        this.f18019g = tencentMap;
        this.a = markerManager;
        this.f18016c = markerManager.a();
        this.b = markerManager.a();
        this.f18018f = new DefaultClusterRenderer(context, tencentMap, this);
        this.d = new PreCachingAlgorithmDecorator(new NonHierarchicalDistanceBasedAlgorithm(context));
        this.f18021i = new a();
        this.f18018f.onAdd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class a extends AsyncTask<Float, Void, Set<? extends Cluster<T>>> {
        public a() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Set<? extends Cluster<T>> doInBackground(Float... fArr) {
            ClusterManager.this.f18017e.readLock().lock();
            try {
                if (ClusterManager.this.f18023k && fArr != null) {
                    return ClusterManager.this.d.getClusters(fArr[0].floatValue());
                }
                HashSet hashSet = new HashSet();
                Iterator<T> it = ClusterManager.this.d.getItems().iterator();
                while (it.hasNext()) {
                    hashSet.add(new b(it.next()));
                }
                return hashSet;
            } finally {
                ClusterManager.this.f18017e.readLock().unlock();
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Set<? extends Cluster<T>> set) {
            ClusterManager.this.f18018f.onClustersChanged(set);
        }
    }
}
