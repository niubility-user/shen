package com.jd.hybrid.downloader;

import android.content.Context;
import android.os.Build;
import com.jd.libs.hybrid.base.BaseGraySwitch;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public class g {

    /* renamed from: g  reason: collision with root package name */
    private static g f2677g;
    private Context b;

    /* renamed from: c  reason: collision with root package name */
    private final ThreadPoolExecutor f2678c;
    private List<com.jd.hybrid.downloader.l.a> d;

    /* renamed from: f  reason: collision with root package name */
    private final Object f2680f;
    public AtomicInteger a = new AtomicInteger(0);

    /* renamed from: e  reason: collision with root package name */
    private volatile ConcurrentLinkedQueue<Integer> f2679e = new ConcurrentLinkedQueue<>();

    /* loaded from: classes13.dex */
    class a implements HybridSettings.DownloadConditionSettingListener {
        a() {
        }

        @Override // com.jd.libs.hybrid.base.HybridSettings.DownloadConditionSettingListener
        public void onSettingChanged() {
            if (g.this.f()) {
                synchronized (g.this.f2680f) {
                    g.this.f2680f.notifyAll();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements Runnable, Comparable<b> {

        /* renamed from: g  reason: collision with root package name */
        private final FileRequest f2681g;

        /* renamed from: h  reason: collision with root package name */
        private final int f2682h;

        public b(FileRequest fileRequest, int i2) {
            this.f2681g = fileRequest;
            this.f2682h = i2;
        }

        @Override // java.lang.Comparable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            return bVar.f2682h - this.f2682h;
        }

        public void b() {
            synchronized (g.this.f2680f) {
                while (!g.this.f()) {
                    try {
                        g.this.f2680f.wait();
                    } catch (InterruptedException e2) {
                        Log.e("HybridSerialFileDownloader", e2);
                    }
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.f2679e.remove(Integer.valueOf(hashCode()));
            b();
            if (Log.isDebug()) {
                Log.d("JDFileDownloader", "execute file request -> " + this.f2681g.getUrl());
            }
            com.jd.hybrid.downloader.a.a(g.this.b, this.f2681g);
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements Comparable<c> {

        /* renamed from: g  reason: collision with root package name */
        FileRequest f2684g;

        /* renamed from: h  reason: collision with root package name */
        int f2685h;

        public c(FileRequest fileRequest, int i2) {
            this.f2684g = fileRequest;
            this.f2685h = i2;
        }

        @Override // java.lang.Comparable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compareTo(c cVar) {
            return cVar.f2685h - this.f2685h;
        }
    }

    private g(Context context) {
        this.b = context.getApplicationContext();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 15L, TimeUnit.SECONDS, new PriorityBlockingQueue(), new ThreadPoolExecutor.DiscardOldestPolicy());
        this.f2678c = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        Object obj = new Object();
        this.f2680f = obj;
        ArrayList arrayList = new ArrayList(4);
        this.d = arrayList;
        arrayList.add(new com.jd.hybrid.downloader.l.c(obj));
        if (Build.VERSION.SDK_INT >= 23) {
            this.d.add(new com.jd.hybrid.downloader.l.b(this.b, obj));
        }
        HybridSettings.addDownloadConditionListener(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        boolean z = true;
        for (com.jd.hybrid.downloader.l.a aVar : this.d) {
            if (BaseGraySwitch.bugfix12dd3bOn) {
                aVar.b();
            }
            if (!aVar.c()) {
                aVar.e();
                z = false;
            } else if (this.f2679e.isEmpty()) {
                aVar.d();
                aVar.a();
            }
        }
        return z;
    }

    public static g h(Context context) {
        if (f2677g == null) {
            synchronized (g.class) {
                if (f2677g == null) {
                    f2677g = new g(context);
                }
            }
        }
        return f2677g;
    }

    public void e(c cVar) {
        if (Log.isDebug()) {
            Log.d("JDFileDownloader", "==== total file request count ===> " + this.a.incrementAndGet());
        }
        g(cVar.f2684g, cVar.f2685h);
    }

    public void g(FileRequest fileRequest, int i2) {
        b bVar = new b(fileRequest, i2);
        this.f2679e.offer(Integer.valueOf(bVar.hashCode()));
        this.f2678c.execute(bVar);
    }
}
