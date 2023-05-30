package com.jd.manto.hd.bluetooth.peripheral;

import androidx.annotation.RequiresApi;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.MantoCore;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiresApi(api = 21)
/* loaded from: classes17.dex */
public class f {

    /* renamed from: e  reason: collision with root package name */
    private static f f6687e = new f();
    private Map<Long, e> a = new ConcurrentHashMap();
    private volatile boolean b;

    /* renamed from: c  reason: collision with root package name */
    private AppLifeCycle.Listener f6688c;
    private Reference<MantoCore> d;

    /* loaded from: classes17.dex */
    class a extends AppLifeCycle.Listener {
        a() {
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppDestroy() {
            super.onAppDestroy();
            f.this.b = true;
            synchronized (f.this.a) {
                try {
                    Iterator it = f.this.a.values().iterator();
                    while (it.hasNext()) {
                        ((e) it.next()).e();
                    }
                    f.this.a.clear();
                } catch (Throwable unused) {
                }
            }
            f.this.b = false;
        }
    }

    public static f b() {
        return f6687e;
    }

    public long a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        this.a.put(Long.valueOf(currentTimeMillis), new e(currentTimeMillis));
        if (this.f6688c == null) {
            a aVar = new a();
            this.f6688c = aVar;
            AppLifeCycle.add(str, aVar);
        }
        return currentTimeMillis;
    }

    public MantoCore a() {
        Reference<MantoCore> reference = this.d;
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    public void a(MantoCore mantoCore) {
        this.d = new SoftReference(mantoCore);
    }

    public boolean a(long j2) {
        e b;
        if (!this.a.containsKey(Long.valueOf(j2)) || (b = b(j2)) == null || this.b) {
            return false;
        }
        b.e();
        this.a.remove(Long.valueOf(j2));
        return true;
    }

    public e b(long j2) {
        if (this.b) {
            return null;
        }
        return this.a.get(Long.valueOf(j2));
    }

    public boolean c() {
        return this.a.size() > 9;
    }
}
