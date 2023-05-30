package com.jd.lib.cashier.sdk.d.c;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes14.dex */
public class a {

    /* renamed from: f  reason: collision with root package name */
    private static volatile Handler f3232f;

    /* renamed from: h  reason: collision with root package name */
    private static a f3234h;
    private String a;
    private List<b> b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f3236c = false;
    private static Map<String, a> d = new HashMap(3);

    /* renamed from: e  reason: collision with root package name */
    private static long f3231e = 0;

    /* renamed from: g  reason: collision with root package name */
    private static Object f3233g = new Object();

    /* renamed from: i  reason: collision with root package name */
    private static ArrayList<String> f3235i = new ArrayList<>(3);

    /* renamed from: com.jd.lib.cashier.sdk.d.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    class RunnableC0117a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ b f3237g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f3238h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ Object f3239i;

        RunnableC0117a(b bVar, String str, Object obj) {
            this.f3237g = bVar;
            this.f3238h = str;
            this.f3239i = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.f3236c) {
                return;
            }
            this.f3237g.onEvent(this.f3238h, this.f3239i);
        }
    }

    /* loaded from: classes14.dex */
    public interface b {
        String getActionName();

        boolean isValid();

        void onEvent(String str, Object obj);
    }

    private a(String str) {
        this.a = str;
    }

    private synchronized void b() {
        List<b> list = this.b;
        if (list != null) {
            list.clear();
        }
        this.b = null;
    }

    public static synchronized String c() {
        String valueOf;
        synchronized (a.class) {
            long currentTimeMillis = f3231e + System.currentTimeMillis();
            f3231e = currentTimeMillis;
            valueOf = String.valueOf(currentTimeMillis);
        }
        return valueOf;
    }

    public static void d(String str) {
        ArrayList<String> arrayList;
        Map<String, a> map = d;
        if (map != null) {
            a remove = map.remove(str);
            if (remove != null) {
                remove.i();
            }
            if (TextUtils.isEmpty(str) || (arrayList = f3235i) == null) {
                return;
            }
            arrayList.add(str);
        }
    }

    private static synchronized a e() {
        a aVar;
        synchronized (a.class) {
            if (f3234h == null) {
                f3234h = new a("");
            }
            aVar = f3234h;
        }
        return aVar;
    }

    public static synchronized a f(String str) {
        ArrayList<String> arrayList;
        synchronized (a.class) {
            if (!TextUtils.isEmpty(str) && ((arrayList = f3235i) == null || !arrayList.contains(str))) {
                a aVar = d.isEmpty() ? null : d.get(str);
                if (aVar == null) {
                    aVar = new a(str);
                    d.put(str, aVar);
                }
                return aVar;
            }
            return e();
        }
    }

    private boolean h() {
        return TextUtils.isEmpty(g());
    }

    private void i() {
        this.f3236c = true;
        b();
        f3232f = null;
    }

    public static void j(Runnable runnable) {
        if (f3232f == null) {
            synchronized (f3233g) {
                if (f3232f == null) {
                    f3232f = new Handler(Looper.getMainLooper());
                }
            }
        }
        f3232f.post(runnable);
    }

    public String g() {
        return this.a;
    }

    public synchronized void k(String str, String str2, Object obj) {
        List<b> list;
        if (!this.f3236c && !h() && !TextUtils.isEmpty(str) && (list = this.b) != null && !list.isEmpty()) {
            for (b bVar : this.b) {
                if (bVar != null && str.equals(bVar.getActionName()) && bVar.isValid()) {
                    j(new RunnableC0117a(bVar, str2, obj));
                }
            }
        }
    }

    public synchronized void l(b bVar) {
        if (bVar != null) {
            if (!h()) {
                if (this.b == null) {
                    this.b = new CopyOnWriteArrayList();
                }
                this.b.add(bVar);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003a, code lost:
        r2.b.remove(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void m(String str) {
        List<b> list;
        if (!TextUtils.isEmpty(str) && !h() && (list = this.b) != null && !list.isEmpty()) {
            int i2 = 0;
            while (true) {
                if (i2 < this.b.size()) {
                    if (this.b.get(i2) != null && TextUtils.equals(this.b.get(i2).getActionName(), str)) {
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
        }
    }
}
