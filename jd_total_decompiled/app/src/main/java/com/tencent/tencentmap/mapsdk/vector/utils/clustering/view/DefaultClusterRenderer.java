package com.tencent.tencentmap.mapsdk.vector.utils.clustering.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.Nullable;
import com.tencent.tencentmap.mapsdk.maps.Projection;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.vector.utils.MarkerManager;
import com.tencent.tencentmap.mapsdk.vector.utils.a.f;
import com.tencent.tencentmap.mapsdk.vector.utils.a.g;
import com.tencent.tencentmap.mapsdk.vector.utils.a.i;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.Cluster;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterItem;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterManager;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.Algorithm;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.algo.PreCachingAlgorithmDecorator;
import com.tencent.tencentmap.mapsdk.vector.utils.ui.IconGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes9.dex */
public class DefaultClusterRenderer<T extends ClusterItem> implements ClusterRenderer<T> {
    public static final boolean a;
    public static volatile boolean b;

    /* renamed from: c */
    public static final TimeInterpolator f18033c;
    public final TencentMap d;

    /* renamed from: e */
    public final IconGenerator f18034e;

    /* renamed from: f */
    public final ClusterManager<T> f18035f;

    /* renamed from: g */
    public final float f18036g;

    /* renamed from: i */
    public ShapeDrawable f18038i;

    /* renamed from: j */
    public double f18039j;

    /* renamed from: m */
    public MarkerCache<T> f18042m;
    public Set<? extends Cluster<T>> o;
    public float s;
    public final DefaultClusterRenderer<T>.ViewModifier t;
    public ClusterManager.OnClusterClickListener<T> u;
    public ClusterManager.OnClusterInfoWindowClickListener<T> v;
    public ClusterManager.ClusterInfoWindowAdapter<T> w;
    public ClusterManager.OnClusterItemClickListener<T> x;
    public ClusterManager.OnClusterItemInfoWindowClickListener<T> y;
    public ClusterManager.ClusterItemInfoWindowAdapter<T> z;

    /* renamed from: h */
    public int[] f18037h = {10, 20, 50, 100, 200, 500, 1000};

    /* renamed from: k */
    public Set<MarkerWithPosition> f18040k = Collections.newSetFromMap(new ConcurrentHashMap());

    /* renamed from: l */
    public SparseArray<BitmapDescriptor> f18041l = new SparseArray<>();

    /* renamed from: n */
    public int f18043n = 4;
    public boolean p = true;
    public Map<Marker, Cluster<T>> q = new HashMap();
    public Map<Cluster<T>, Marker> r = new HashMap();

    @TargetApi(12)
    /* loaded from: classes9.dex */
    public class AnimationTask extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        public final MarkerWithPosition a;
        public final Marker b;

        /* renamed from: c */
        public final LatLng f18044c;
        public final LatLng d;

        /* renamed from: e */
        public boolean f18045e;

        /* renamed from: f */
        public MarkerManager f18046f;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f18045e) {
                DefaultClusterRenderer.this.r.remove((Cluster) DefaultClusterRenderer.this.q.get(this.b));
                DefaultClusterRenderer.this.f18042m.remove(this.b);
                DefaultClusterRenderer.this.q.remove(this.b);
                this.f18046f.a(this.b);
            }
            this.a.b = this.d;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            LatLng latLng = this.d;
            double d = latLng.latitude;
            LatLng latLng2 = this.f18044c;
            double d2 = latLng2.latitude;
            double d3 = animatedFraction;
            Double.isNaN(d3);
            double d4 = ((d - d2) * d3) + d2;
            double d5 = latLng.longitude - latLng2.longitude;
            if (Math.abs(d5) > 180.0d) {
                d5 -= Math.signum(d5) * 360.0d;
            }
            Double.isNaN(d3);
            this.b.setPosition(new LatLng(d4, (d5 * d3) + this.f18044c.longitude));
        }

        public void perform() {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setInterpolator(DefaultClusterRenderer.f18033c);
            ofFloat.addUpdateListener(this);
            ofFloat.addListener(this);
            ofFloat.start();
        }

        public void removeOnAnimationComplete(MarkerManager markerManager) {
            this.f18046f = markerManager;
            this.f18045e = true;
        }

        public AnimationTask(MarkerWithPosition markerWithPosition, LatLng latLng, LatLng latLng2) {
            DefaultClusterRenderer.this = r1;
            this.a = markerWithPosition;
            this.b = markerWithPosition.a;
            this.f18044c = latLng;
            this.d = latLng2;
        }
    }

    /* loaded from: classes9.dex */
    public class CreateMarkerTask {
        public final Cluster<T> a;
        public final Set<MarkerWithPosition> b;

        /* renamed from: c */
        public final LatLng f18048c;

        public CreateMarkerTask(Cluster<T> cluster, Set<MarkerWithPosition> set, LatLng latLng) {
            DefaultClusterRenderer.this = r1;
            this.a = cluster;
            this.b = set;
            this.f18048c = latLng;
        }

        public final void a(DefaultClusterRenderer<T>.MarkerModifier markerModifier) {
            MarkerWithPosition markerWithPosition;
            if (!DefaultClusterRenderer.this.a(this.a)) {
                for (T t : this.a.getItems()) {
                    Marker marker = DefaultClusterRenderer.this.f18042m.get((MarkerCache) t);
                    if (marker == null) {
                        MarkerOptions anchor = new MarkerOptions(t.getPosition()).anchor(0.5f, 0.5f);
                        LatLng latLng = this.f18048c;
                        if (latLng != null) {
                            anchor.position(latLng);
                        }
                        DefaultClusterRenderer.this.onBeforeClusterItemRendered(t, anchor);
                        marker = DefaultClusterRenderer.this.f18035f.getMarkerCollection().a(anchor);
                        markerWithPosition = new MarkerWithPosition(marker);
                        DefaultClusterRenderer.this.f18042m.put(t, marker);
                        LatLng latLng2 = this.f18048c;
                        if (latLng2 != null) {
                            markerModifier.animate(markerWithPosition, latLng2, t.getPosition());
                        }
                    } else {
                        markerWithPosition = new MarkerWithPosition(marker);
                    }
                    DefaultClusterRenderer.this.a((DefaultClusterRenderer) t, marker);
                    this.b.add(markerWithPosition);
                }
                return;
            }
            LatLng latLng3 = this.f18048c;
            if (latLng3 == null) {
                latLng3 = this.a.getPosition();
            }
            MarkerOptions anchor2 = new MarkerOptions(latLng3).anchor(0.5f, 0.5f);
            DefaultClusterRenderer.this.onBeforeClusterRendered(this.a, anchor2);
            Marker a = DefaultClusterRenderer.this.f18035f.getClusterMarkerCollection().a(anchor2);
            DefaultClusterRenderer.this.q.put(a, this.a);
            DefaultClusterRenderer.this.r.put(this.a, a);
            MarkerWithPosition markerWithPosition2 = new MarkerWithPosition(a);
            LatLng latLng4 = this.f18048c;
            if (latLng4 != null) {
                markerModifier.animate(markerWithPosition2, latLng4, this.a.getPosition());
            }
            DefaultClusterRenderer.this.a(this.a, a);
            this.b.add(markerWithPosition2);
        }
    }

    /* loaded from: classes9.dex */
    public static class MarkerCache<T> {
        public Map<T, Marker> a;
        public Map<Marker, T> b;

        public MarkerCache() {
            this.a = new HashMap();
            this.b = new HashMap();
        }

        public Marker get(T t) {
            return this.a.get(t);
        }

        public void put(T t, Marker marker) {
            this.a.put(t, marker);
            this.b.put(marker, t);
        }

        public void remove(Marker marker) {
            T t = this.b.get(marker);
            this.b.remove(marker);
            this.a.remove(t);
        }

        public T get(Marker marker) {
            return this.b.get(marker);
        }
    }

    @SuppressLint({"HandlerLeak"})
    /* loaded from: classes9.dex */
    public class MarkerModifier extends Handler implements MessageQueue.IdleHandler {
        public final Lock a;
        public final Condition b;

        /* renamed from: c */
        public Queue<DefaultClusterRenderer<T>.CreateMarkerTask> f18049c;
        public Queue<DefaultClusterRenderer<T>.CreateMarkerTask> d;

        /* renamed from: e */
        public Queue<Marker> f18050e;

        /* renamed from: f */
        public Queue<Marker> f18051f;

        /* renamed from: g */
        public Queue<DefaultClusterRenderer<T>.AnimationTask> f18052g;

        /* renamed from: h */
        public boolean f18053h;

        @TargetApi(11)
        public final void a() {
            try {
                if (!this.f18051f.isEmpty()) {
                    a(this.f18051f.poll());
                } else if (!this.f18052g.isEmpty()) {
                    this.f18052g.poll().perform();
                } else if (!this.d.isEmpty()) {
                    this.d.poll().a(this);
                } else if (!this.f18049c.isEmpty()) {
                    this.f18049c.poll().a(this);
                } else if (!this.f18050e.isEmpty()) {
                    a(this.f18050e.poll());
                }
            } catch (NullPointerException e2) {
                if (DefaultClusterRenderer.b) {
                    cancel();
                } else {
                    e2.printStackTrace();
                }
            }
        }

        public void add(boolean z, DefaultClusterRenderer<T>.CreateMarkerTask createMarkerTask) {
            this.a.lock();
            sendEmptyMessage(0);
            if (z) {
                this.d.add(createMarkerTask);
            } else {
                this.f18049c.add(createMarkerTask);
            }
            this.a.unlock();
        }

        public void animate(MarkerWithPosition markerWithPosition, LatLng latLng, LatLng latLng2) {
            this.a.lock();
            this.f18052g.add(new AnimationTask(markerWithPosition, latLng, latLng2));
            this.a.unlock();
        }

        @TargetApi(11)
        public void animateThenRemove(MarkerWithPosition markerWithPosition, LatLng latLng, LatLng latLng2) {
            this.a.lock();
            DefaultClusterRenderer<T>.AnimationTask animationTask = new AnimationTask(markerWithPosition, latLng, latLng2);
            animationTask.removeOnAnimationComplete(DefaultClusterRenderer.this.f18035f.getMarkerManager());
            this.f18052g.add(animationTask);
            this.a.unlock();
        }

        public void cancel() {
            this.d.clear();
            this.f18052g.clear();
            this.f18049c.clear();
            this.f18051f.clear();
            this.f18050e.clear();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (!this.f18053h) {
                Looper.myQueue().addIdleHandler(this);
                this.f18053h = true;
            }
            removeMessages(0);
            this.a.lock();
            for (int i2 = 0; i2 < 10; i2++) {
                try {
                    a();
                } finally {
                    this.a.unlock();
                }
            }
            if (!isBusy()) {
                this.f18053h = false;
                Looper.myQueue().removeIdleHandler(this);
                this.b.signalAll();
            } else {
                sendEmptyMessageDelayed(0, 10L);
            }
        }

        public boolean isBusy() {
            boolean z;
            try {
                this.a.lock();
                if (this.f18049c.isEmpty() && this.d.isEmpty() && this.f18051f.isEmpty() && this.f18050e.isEmpty()) {
                    if (this.f18052g.isEmpty()) {
                        z = false;
                        return z;
                    }
                }
                z = true;
                return z;
            } finally {
                this.a.unlock();
            }
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            sendEmptyMessage(0);
            return true;
        }

        public void remove(boolean z, Marker marker) {
            this.a.lock();
            sendEmptyMessage(0);
            if (z) {
                this.f18051f.add(marker);
            } else {
                this.f18050e.add(marker);
            }
            this.a.unlock();
        }

        public void waitUntilFree() {
            while (isBusy()) {
                sendEmptyMessage(0);
                this.a.lock();
                try {
                    try {
                        if (isBusy()) {
                            this.b.await();
                        }
                    } catch (InterruptedException e2) {
                        throw new RuntimeException(e2);
                    }
                } finally {
                    this.a.unlock();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MarkerModifier() {
            super(Looper.getMainLooper());
            DefaultClusterRenderer.this = r1;
            ReentrantLock reentrantLock = new ReentrantLock();
            this.a = reentrantLock;
            this.b = reentrantLock.newCondition();
            this.f18049c = new LinkedList();
            this.d = new LinkedList();
            this.f18050e = new LinkedList();
            this.f18051f = new LinkedList();
            this.f18052g = new LinkedList();
        }

        public final void a(Marker marker) {
            DefaultClusterRenderer.this.r.remove((Cluster) DefaultClusterRenderer.this.q.get(marker));
            DefaultClusterRenderer.this.f18042m.remove(marker);
            DefaultClusterRenderer.this.q.remove(marker);
            DefaultClusterRenderer.this.f18035f.getMarkerManager().a(marker);
        }
    }

    /* loaded from: classes9.dex */
    public static class MarkerWithPosition {
        public final Marker a;
        public LatLng b;

        public boolean equals(Object obj) {
            if (obj instanceof MarkerWithPosition) {
                return this.a.equals(((MarkerWithPosition) obj).a);
            }
            return false;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public MarkerWithPosition(Marker marker) {
            this.a = marker;
            this.b = marker.getPosition();
        }
    }

    /* loaded from: classes9.dex */
    public class RenderTask implements Runnable {
        public final Set<? extends Cluster<T>> a;
        public Runnable b;

        /* renamed from: c */
        public Projection f18055c;
        public g d;

        /* renamed from: e */
        public float f18056e;

        /* renamed from: f */
        public double f18057f;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0, types: [com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.DefaultClusterRenderer$1] */
        /* JADX WARN: Type inference failed for: r3v1, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r3v2, types: [java.util.List, java.util.ArrayList] */
        @Override // java.lang.Runnable
        @SuppressLint({"NewApi"})
        public void run() {
            ArrayList arrayList;
            Marker marker;
            if (!this.a.equals(DefaultClusterRenderer.this.o) || DefaultClusterRenderer.this.p) {
                if (DefaultClusterRenderer.this.p) {
                    DefaultClusterRenderer.this.p = false;
                }
                ?? r3 = 0;
                MarkerModifier markerModifier = new MarkerModifier();
                float f2 = this.f18056e;
                boolean z = f2 > DefaultClusterRenderer.this.s;
                float f3 = f2 - DefaultClusterRenderer.this.s;
                Set<MarkerWithPosition> set = DefaultClusterRenderer.this.f18040k;
                LatLngBounds latLngBounds = this.f18055c.getVisibleRegion().latLngBounds;
                if (DefaultClusterRenderer.this.o == null || !DefaultClusterRenderer.a) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList();
                    for (Cluster<T> cluster : DefaultClusterRenderer.this.o) {
                        if (DefaultClusterRenderer.this.a(cluster) && latLngBounds.contains(cluster.getPosition())) {
                            arrayList.add(this.d.a(cluster.getPosition()));
                        }
                    }
                }
                Set newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
                for (Cluster<T> cluster2 : this.a) {
                    if (DefaultClusterRenderer.this.o != null && DefaultClusterRenderer.this.o.contains(cluster2) && DefaultClusterRenderer.this.a(cluster2) && (marker = (Marker) DefaultClusterRenderer.this.r.get(cluster2)) != null) {
                        newSetFromMap.add(new MarkerWithPosition(marker));
                    } else {
                        boolean contains = latLngBounds.contains(cluster2.getPosition());
                        if (z && contains && DefaultClusterRenderer.a) {
                            f b = DefaultClusterRenderer.b(arrayList, this.d.a(cluster2.getPosition()), this.f18057f);
                            if (b != null) {
                                markerModifier.add(true, new CreateMarkerTask(cluster2, newSetFromMap, this.d.a(b)));
                            } else {
                                markerModifier.add(true, new CreateMarkerTask(cluster2, newSetFromMap, null));
                            }
                        } else {
                            markerModifier.add(contains, new CreateMarkerTask(cluster2, newSetFromMap, null));
                        }
                    }
                }
                markerModifier.waitUntilFree();
                set.removeAll(newSetFromMap);
                if (DefaultClusterRenderer.a) {
                    r3 = new ArrayList();
                    for (Cluster<T> cluster3 : this.a) {
                        if (DefaultClusterRenderer.this.a(cluster3) && latLngBounds.contains(cluster3.getPosition())) {
                            r3.add(this.d.a(cluster3.getPosition()));
                        }
                    }
                }
                for (MarkerWithPosition markerWithPosition : set) {
                    boolean contains2 = latLngBounds.contains(markerWithPosition.b);
                    if (z || f3 <= -3.0f || !contains2 || !DefaultClusterRenderer.a) {
                        markerModifier.remove(contains2, markerWithPosition.a);
                    } else {
                        f b2 = DefaultClusterRenderer.b(r3, this.d.a(markerWithPosition.b), this.f18057f);
                        if (b2 != null) {
                            markerModifier.animateThenRemove(markerWithPosition, markerWithPosition.b, this.d.a(b2));
                        } else {
                            markerModifier.remove(true, markerWithPosition.a);
                        }
                    }
                }
                markerModifier.waitUntilFree();
                DefaultClusterRenderer.this.f18040k = newSetFromMap;
                DefaultClusterRenderer.this.o = this.a;
                DefaultClusterRenderer.this.s = f2;
                this.b.run();
                return;
            }
            this.b.run();
        }

        public void setCallback(Runnable runnable) {
            this.b = runnable;
        }

        public void setMapZoom(float f2) {
            this.f18056e = f2;
            this.d = new g(Math.pow(2.0d, Math.min(f2, DefaultClusterRenderer.this.s) - 1.0f) * 256.0d);
        }

        public void setProjection(Projection projection) {
            this.f18055c = projection;
        }

        public RenderTask(Set<? extends Cluster<T>> set, double d) {
            DefaultClusterRenderer.this = r1;
            this.a = set;
            this.f18057f = d;
        }
    }

    @SuppressLint({"HandlerLeak"})
    /* loaded from: classes9.dex */
    public class ViewModifier extends Handler {
        public boolean a;
        public DefaultClusterRenderer<T>.RenderTask b;

        public ViewModifier() {
            DefaultClusterRenderer.this = r1;
            this.a = false;
            this.b = null;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Projection projection;
            DefaultClusterRenderer<T>.RenderTask renderTask;
            if (message.what == 1) {
                this.a = false;
                if (this.b != null) {
                    sendEmptyMessage(0);
                    return;
                }
                return;
            }
            removeMessages(0);
            if (this.a || this.b == null || (projection = DefaultClusterRenderer.this.d.getProjection()) == null) {
                return;
            }
            synchronized (this) {
                renderTask = this.b;
                this.b = null;
                this.a = true;
            }
            renderTask.setCallback(new Runnable() { // from class: com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.DefaultClusterRenderer.ViewModifier.1
                {
                    ViewModifier.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    ViewModifier.this.sendEmptyMessage(1);
                }
            });
            renderTask.setProjection(projection);
            try {
                renderTask.setMapZoom(DefaultClusterRenderer.this.d.getCameraPosition().zoom);
                new Thread(renderTask).start();
            } catch (NullPointerException e2) {
                if (DefaultClusterRenderer.b) {
                    e2.printStackTrace();
                }
            }
        }

        public void queue(Set<? extends Cluster<T>> set) {
            synchronized (this) {
                DefaultClusterRenderer defaultClusterRenderer = DefaultClusterRenderer.this;
                this.b = new RenderTask(set, defaultClusterRenderer.f18039j);
            }
            sendEmptyMessage(0);
        }
    }

    static {
        a = Build.VERSION.SDK_INT >= 11;
        b = false;
        f18033c = new DecelerateInterpolator();
    }

    public DefaultClusterRenderer(Context context, TencentMap tencentMap, ClusterManager<T> clusterManager) {
        Algorithm algorithm;
        this.f18042m = new MarkerCache<>();
        this.t = new ViewModifier();
        this.d = tencentMap;
        float f2 = context.getResources().getDisplayMetrics().density;
        this.f18036g = f2;
        IconGenerator iconGenerator = new IconGenerator(context);
        this.f18034e = iconGenerator;
        iconGenerator.setContentView(a(context));
        iconGenerator.setTextAppearance(16973886, -1118482, 16.0f, 1);
        iconGenerator.setBackground(d());
        this.f18035f = clusterManager;
        if (clusterManager.getAlgorithm() instanceof PreCachingAlgorithmDecorator) {
            algorithm = ((PreCachingAlgorithmDecorator) clusterManager.getAlgorithm()).getAlgorithm();
        } else {
            algorithm = clusterManager.getAlgorithm();
        }
        if (algorithm instanceof NonHierarchicalDistanceBasedAlgorithm) {
            double maxDistanceAtZoom = ((NonHierarchicalDistanceBasedAlgorithm) algorithm).getMaxDistanceAtZoom();
            Double.isNaN(maxDistanceAtZoom);
            double d = f2;
            Double.isNaN(d);
            this.f18039j = (maxDistanceAtZoom + 0.5d) * d;
            return;
        }
        this.f18039j = f2 * 35.0f;
    }

    public void a(Cluster<T> cluster, Marker marker) {
    }

    public void a(T t, Marker marker) {
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer
    public void cancel() {
        b = true;
        onRemove();
    }

    public int getBucket(Cluster<T> cluster) {
        int size = cluster.getSize();
        int[] iArr = this.f18037h;
        if (iArr == null) {
            return size;
        }
        int i2 = 0;
        if (size <= iArr[0]) {
            return size;
        }
        while (true) {
            int[] iArr2 = this.f18037h;
            if (i2 < iArr2.length - 1) {
                int i3 = i2 + 1;
                if (size < iArr2[i3]) {
                    return iArr2[i2];
                }
                i2 = i3;
            } else {
                return iArr2[iArr2.length - 1];
            }
        }
    }

    public int[] getBuckets() {
        return this.f18037h;
    }

    public Cluster<T> getCluster(Marker marker) {
        return this.q.get(marker);
    }

    public T getClusterItem(Marker marker) {
        return this.f18042m.get(marker);
    }

    public int getColor(int i2) {
        float min = 300.0f - Math.min(i2, 300.0f);
        return Color.HSVToColor(new float[]{((min * min) / 90000.0f) * 220.0f, 1.0f, 0.6f});
    }

    public Marker getMarker(T t) {
        return this.f18042m.get((MarkerCache<T>) t);
    }

    public int getMinClusterSize() {
        return this.f18043n;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer
    public void onAdd() {
        this.f18035f.getMarkerCollection().a(new TencentMap.OnMarkerClickListener() { // from class: com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.DefaultClusterRenderer.1
            {
                DefaultClusterRenderer.this = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                return DefaultClusterRenderer.this.x != null && DefaultClusterRenderer.this.x.onClusterItemClick((ClusterItem) DefaultClusterRenderer.this.f18042m.get(marker));
            }
        });
        this.f18035f.getMarkerCollection().a(new TencentMap.OnInfoWindowClickListener() { // from class: com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.DefaultClusterRenderer.2
            {
                DefaultClusterRenderer.this = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
            public void onInfoWindowClick(Marker marker) {
                if (DefaultClusterRenderer.this.y != null) {
                    DefaultClusterRenderer.this.y.onClusterItemInfoWindowClick((ClusterItem) DefaultClusterRenderer.this.f18042m.get(marker));
                }
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
            public void onInfoWindowClickLocation(int i2, int i3, int i4, int i5) {
            }
        });
        this.f18035f.getMarkerCollection().a(new TencentMap.InfoWindowAdapter() { // from class: com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.DefaultClusterRenderer.3
            {
                DefaultClusterRenderer.this = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
            public View getInfoContents(Marker marker) {
                if (DefaultClusterRenderer.this.z != null) {
                    return DefaultClusterRenderer.this.z.getInfoContents((ClusterItem) DefaultClusterRenderer.this.f18042m.get(marker));
                }
                return null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
            public View getInfoWindow(Marker marker) {
                if (DefaultClusterRenderer.this.z != null) {
                    return DefaultClusterRenderer.this.z.getInfoWindow((ClusterItem) DefaultClusterRenderer.this.f18042m.get(marker));
                }
                return null;
            }
        });
        this.f18035f.getClusterMarkerCollection().a(new TencentMap.OnMarkerClickListener() { // from class: com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.DefaultClusterRenderer.4
            {
                DefaultClusterRenderer.this = this;
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                return DefaultClusterRenderer.this.u != null && DefaultClusterRenderer.this.u.onClusterClick((Cluster) DefaultClusterRenderer.this.q.get(marker));
            }
        });
        this.f18035f.getClusterMarkerCollection().a(new TencentMap.OnInfoWindowClickListener() { // from class: com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.DefaultClusterRenderer.5
            {
                DefaultClusterRenderer.this = this;
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
            public void onInfoWindowClick(Marker marker) {
                if (DefaultClusterRenderer.this.v != null) {
                    DefaultClusterRenderer.this.v.onClusterInfoWindowClick((Cluster) DefaultClusterRenderer.this.q.get(marker));
                }
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
            public void onInfoWindowClickLocation(int i2, int i3, int i4, int i5) {
            }
        });
        this.f18035f.getClusterMarkerCollection().a(new TencentMap.InfoWindowAdapter() { // from class: com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.DefaultClusterRenderer.6
            {
                DefaultClusterRenderer.this = this;
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
            public View getInfoContents(Marker marker) {
                if (DefaultClusterRenderer.this.w != null) {
                    return DefaultClusterRenderer.this.w.getInfoContents((Cluster) DefaultClusterRenderer.this.q.get(marker));
                }
                return null;
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
            public View getInfoWindow(Marker marker) {
                if (DefaultClusterRenderer.this.w != null) {
                    return DefaultClusterRenderer.this.w.getInfoWindow((Cluster) DefaultClusterRenderer.this.q.get(marker));
                }
                return null;
            }
        });
    }

    public void onBeforeClusterItemRendered(T t, MarkerOptions markerOptions) {
    }

    public void onBeforeClusterRendered(Cluster<T> cluster, MarkerOptions markerOptions) {
        int bucket = getBucket(cluster);
        BitmapDescriptor bitmapDescriptor = this.f18041l.get(bucket);
        if (bitmapDescriptor == null) {
            this.f18038i.getPaint().setColor(getColor(bucket));
            bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(this.f18034e.makeIcon(a(bucket)));
            this.f18041l.put(bucket, bitmapDescriptor);
        }
        markerOptions.icon(bitmapDescriptor);
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer
    public void onClustersChanged(Set<? extends Cluster<T>> set) {
        this.t.queue(set);
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer
    public void onRemove() {
        this.f18035f.getMarkerCollection().a((TencentMap.OnMarkerClickListener) null);
        this.f18035f.getClusterMarkerCollection().a((TencentMap.OnMarkerClickListener) null);
    }

    public void setBuckets(@Nullable int[] iArr) {
        this.f18037h = iArr;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer
    public void setInfoWindowAdapter(ClusterManager.ClusterInfoWindowAdapter clusterInfoWindowAdapter) {
        this.w = clusterInfoWindowAdapter;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer
    public void setItemInfoWindowAdapter(ClusterManager.ClusterItemInfoWindowAdapter clusterItemInfoWindowAdapter) {
        this.z = clusterItemInfoWindowAdapter;
    }

    public void setMinClusterSize(int i2) {
        this.f18043n = i2;
        ClusterManager<T> clusterManager = this.f18035f;
        if (clusterManager != null) {
            this.p = true;
            clusterManager.cluster();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer
    public void setOnClusterClickListener(ClusterManager.OnClusterClickListener<T> onClusterClickListener) {
        this.u = onClusterClickListener;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer
    public void setOnClusterInfoWindowClickListener(ClusterManager.OnClusterInfoWindowClickListener<T> onClusterInfoWindowClickListener) {
        this.v = onClusterInfoWindowClickListener;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer
    public void setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<T> onClusterItemClickListener) {
        this.x = onClusterItemClickListener;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.ClusterRenderer
    public void setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<T> onClusterItemInfoWindowClickListener) {
        this.y = onClusterItemInfoWindowClickListener;
    }

    public final LayerDrawable d() {
        this.f18038i = new ShapeDrawable(new OvalShape());
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(-2130706433);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shapeDrawable, this.f18038i});
        int i2 = (int) (this.f18036g * 3.0f);
        layerDrawable.setLayerInset(1, i2, i2, i2, i2);
        return layerDrawable;
    }

    public Marker getMarker(Cluster<T> cluster) {
        return this.r.get(cluster);
    }

    public static f b(List<f> list, f fVar, double d) {
        f fVar2 = null;
        if (list != null && !list.isEmpty()) {
            double d2 = d * d;
            for (f fVar3 : list) {
                double a2 = a(fVar3, fVar);
                if (a2 < d2) {
                    fVar2 = fVar3;
                    d2 = a2;
                }
            }
        }
        return fVar2;
    }

    public final i a(Context context) {
        i iVar = new i(context);
        iVar.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        iVar.setId(IconGenerator.SQUARE_TEXT_VIEW_ID);
        int i2 = (int) (this.f18036g * 12.0f);
        iVar.setPadding(i2, i2, i2, i2);
        return iVar;
    }

    public String a(int i2) {
        int[] iArr = this.f18037h;
        if (iArr == null) {
            return String.valueOf(i2);
        }
        if (i2 < iArr[0]) {
            return String.valueOf(i2);
        }
        return String.valueOf(i2) + MqttTopic.SINGLE_LEVEL_WILDCARD;
    }

    public boolean a(Cluster<T> cluster) {
        return cluster.getSize() > this.f18043n;
    }

    public static double a(f fVar, f fVar2) {
        double d = fVar.a - fVar2.a;
        double d2 = fVar.b - fVar2.b;
        return (d * d) + (d2 * d2);
    }
}
