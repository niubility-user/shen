package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.Util;
import com.tencent.mapsdk.internal.li;
import com.tencent.mapsdk.shell.events.ReportEvent;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes9.dex */
public final class u implements ii, ji, ki {

    /* renamed from: e  reason: collision with root package name */
    private static final u f17290e = new u();
    private s b;
    private ji a = new a();

    /* renamed from: c  reason: collision with root package name */
    private ii f17291c = new b();
    private ki d = new c();

    /* loaded from: classes9.dex */
    public class a implements ji {
        public a() {
        }

        @Override // com.tencent.mapsdk.internal.ji
        public int a() {
            return 0;
        }

        @Override // com.tencent.mapsdk.internal.ji
        public Class a(String str) {
            return Util.findClass(str, d());
        }

        @Override // com.tencent.mapsdk.internal.ji
        public <T> Class<? extends T> a(String str, Class<T> cls) {
            return Util.findClass(str, cls, d());
        }

        @Override // com.tencent.mapsdk.internal.ji
        public Object a(Class cls, String str, Class[] clsArr, Object[] objArr) {
            return Util.invokeStaticMethod(cls, str, clsArr, objArr);
        }

        @Override // com.tencent.mapsdk.internal.ji
        public <T> T a(Class<T> cls, Object... objArr) {
            return (T) Util.newInstance(cls, objArr);
        }

        @Override // com.tencent.mapsdk.internal.ji
        public Object a(Object obj, String str, Class[] clsArr, Object[] objArr) {
            return Util.invokeMethod(obj, str, clsArr, objArr);
        }

        @Override // com.tencent.mapsdk.internal.ji
        public Object a(Object obj, String str, Object... objArr) {
            return Util.invokeMethod(obj, str, objArr);
        }

        @Override // com.tencent.mapsdk.internal.li.a
        public void a(Context context, String str) {
        }

        @Override // com.tencent.mapsdk.internal.ji
        public Object b(String str) {
            return Util.newInstance(a(str), new Object[0]);
        }

        @Override // com.tencent.mapsdk.internal.li.a
        public void close() {
        }

        @Override // com.tencent.mapsdk.internal.ji
        public ClassLoader d() {
            return getClass().getClassLoader();
        }

        @Override // com.tencent.mapsdk.internal.ji
        public File e() {
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public class b implements ii {
        public b() {
        }

        @Override // com.tencent.mapsdk.internal.ii
        public void a(int i2, Map<String, String> map) {
        }

        @Override // com.tencent.mapsdk.internal.li.a
        public void a(Context context, String str) {
        }

        @Override // com.tencent.mapsdk.internal.li.a
        public void close() {
        }
    }

    /* loaded from: classes9.dex */
    public class c implements ki {
        public c() {
        }

        @Override // com.tencent.mapsdk.internal.li.a
        public void a(Context context, String str) {
        }

        @Override // com.tencent.mapsdk.internal.ki
        public void a(ReportEvent reportEvent) {
        }

        @Override // com.tencent.mapsdk.internal.ki
        public void b() {
        }

        @Override // com.tencent.mapsdk.internal.ki
        public void c() {
        }

        @Override // com.tencent.mapsdk.internal.li.a
        public void close() {
        }
    }

    /* loaded from: classes9.dex */
    public class d implements Runnable {
        public final /* synthetic */ Context a;
        public final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ Handler f17292c;
        public final /* synthetic */ Callback d;

        /* loaded from: classes9.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Callback callback = d.this.d;
                if (callback != null) {
                    callback.callback(null);
                }
            }
        }

        public d(Context context, String str, Handler handler, Callback callback) {
            this.a = context;
            this.b = str;
            this.f17292c = handler;
            this.d = callback;
        }

        @Override // java.lang.Runnable
        public void run() {
            u.this.a(this.a, this.b);
            this.f17292c.post(new a());
        }
    }

    private u() {
    }

    public static u f() {
        return f17290e;
    }

    @Override // com.tencent.mapsdk.internal.ji
    public int a() {
        return this.a.a();
    }

    @Override // com.tencent.mapsdk.internal.ji
    public Class a(String str) {
        return this.a.a(str);
    }

    @Override // com.tencent.mapsdk.internal.ji
    public <T> Class<? extends T> a(String str, Class<T> cls) {
        return this.a.a(str, cls);
    }

    @Override // com.tencent.mapsdk.internal.ji
    public Object a(Class cls, String str, Class[] clsArr, Object[] objArr) {
        return this.a.a(cls, str, clsArr, objArr);
    }

    @Override // com.tencent.mapsdk.internal.ji
    public <T> T a(Class<T> cls, Object... objArr) {
        return (T) this.a.a(cls, objArr);
    }

    @Override // com.tencent.mapsdk.internal.ji
    public Object a(Object obj, String str, Class[] clsArr, Object[] objArr) {
        return this.a.a(obj, str, clsArr, objArr);
    }

    @Override // com.tencent.mapsdk.internal.ji
    public Object a(Object obj, String str, Object... objArr) {
        return this.a.a(obj, str, objArr);
    }

    @Override // com.tencent.mapsdk.internal.ii
    public void a(int i2, Map<String, String> map) {
        this.f17291c.a(i2, map);
    }

    @Override // com.tencent.mapsdk.internal.li.a
    public void a(Context context, String str) {
        li.a(context);
        if (!li.p.isEmpty()) {
            Iterator<li.b> it = li.p.iterator();
            while (it.hasNext()) {
                String str2 = "com.tencent.mapsdk." + it.next().a;
                Object b2 = Util.findClass(str2, u.class.getClassLoader()) != null ? this.a.b(str2) : null;
                if (b2 instanceof li.a) {
                    ((li.a) b2).a(context, str);
                }
                if (b2 instanceof ji) {
                    this.a = (ji) b2;
                } else if (b2 instanceof ki) {
                    this.d = (ki) b2;
                } else if (b2 instanceof ii) {
                    this.f17291c = (ii) b2;
                }
            }
        }
        this.a.a();
        this.b = (s) this.a.b("com.tencent.mapsdk.core.MapDelegateFactoryImpl");
    }

    public void a(Context context, String str, Callback<Void> callback) {
        new Thread(new d(context, str, new Handler(Looper.getMainLooper()), callback), "tms-plugin").start();
    }

    @Override // com.tencent.mapsdk.internal.ki
    public void a(ReportEvent reportEvent) {
        this.d.a(reportEvent);
    }

    @Override // com.tencent.mapsdk.internal.ji
    public Object b(String str) {
        return this.a.b(str);
    }

    @Override // com.tencent.mapsdk.internal.ki
    public void b() {
        this.d.b();
    }

    @Override // com.tencent.mapsdk.internal.ki
    public void c() {
        this.d.c();
    }

    @Override // com.tencent.mapsdk.internal.li.a
    public void close() {
        this.a.close();
        this.f17291c.close();
        this.d.close();
    }

    @Override // com.tencent.mapsdk.internal.ji
    public ClassLoader d() {
        return this.a.d();
    }

    @Override // com.tencent.mapsdk.internal.ji
    public File e() {
        return this.a.e();
    }

    public File g() {
        return this.a.e();
    }

    public s h() {
        return this.b;
    }
}
