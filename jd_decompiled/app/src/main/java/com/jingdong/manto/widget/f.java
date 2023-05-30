package com.jingdong.manto.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.jingdong.manto.utils.MantoLog;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.WeakHashMap;

/* loaded from: classes16.dex */
public class f {

    /* renamed from: e  reason: collision with root package name */
    static final boolean f14360e;

    /* renamed from: f  reason: collision with root package name */
    private static final f f14361f;

    /* renamed from: g  reason: collision with root package name */
    private static final WeakHashMap<Integer, f> f14362g;
    private final WeakReference<Activity> a;
    private final HashSet<WeakReference<d>> b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f14363c;
    public int d;

    /* loaded from: classes16.dex */
    static class b extends f {
        /* JADX WARN: Illegal instructions before constructor call */
        /* JADX WARN: Type inference failed for: r0v0, types: [com.jingdong.manto.widget.f$a, android.app.Activity] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private b() {
            /*
                r1 = this;
                r0 = 0
                r1.<init>(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.widget.f.b.<init>():void");
        }

        @Override // com.jingdong.manto.widget.f
        public final void a(d dVar) {
        }
    }

    @SuppressLint({"NewApi"})
    /* loaded from: classes16.dex */
    class c implements View.OnApplyWindowInsetsListener {
        c() {
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            f.this.d = windowInsets.getSystemWindowInsetTop();
            f fVar = f.this;
            f.a(fVar, fVar.d);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

    /* loaded from: classes16.dex */
    public interface d {
        void a(int i2);
    }

    static {
        f14360e = Build.VERSION.SDK_INT >= 21 && !MantoStatusBarUtil.hasSmartBar();
        f14361f = new b();
        f14362g = new WeakHashMap<>();
    }

    private f(Activity activity) {
        this.b = new HashSet<>();
        this.f14363c = false;
        this.d = 0;
        this.a = new WeakReference<>(activity);
    }

    public static f a(Activity activity) {
        if (!f14360e || activity == null) {
            return f14361f;
        }
        WeakHashMap<Integer, f> weakHashMap = f14362g;
        f fVar = weakHashMap.get(Integer.valueOf(activity.hashCode()));
        if (fVar != null) {
            return fVar;
        }
        f fVar2 = new f(activity);
        weakHashMap.put(Integer.valueOf(activity.hashCode()), fVar2);
        return fVar2;
    }

    static void a(f fVar, int i2) {
        Iterator<WeakReference<d>> it = fVar.b.iterator();
        while (it.hasNext()) {
            d dVar = it.next().get();
            if (dVar != null) {
                dVar.a(i2);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void a(d dVar) {
        if (!this.f14363c) {
            this.f14363c = true;
            Activity activity = this.a.get();
            if (activity != null && activity.getWindow() != null) {
                try {
                    ((ViewGroup) activity.getWindow().getDecorView()).getChildAt(0).setOnApplyWindowInsetsListener(new c());
                } catch (Exception e2) {
                    MantoLog.e("StatusBarHeightWatcher", "setOnApplyWindowInsetsListener e=%s", e2);
                }
            }
        }
        if (dVar != null) {
            this.b.add(new WeakReference<>(dVar));
            int i2 = this.d;
            if (i2 > 0) {
                dVar.a(i2);
            }
        }
    }
}
