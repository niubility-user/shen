package com.xiaomi.push.service;

import android.content.Context;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes11.dex */
public class i0 {

    /* renamed from: c  reason: collision with root package name */
    private static i0 f19095c;
    private ConcurrentHashMap<String, HashMap<String, b>> a = new ConcurrentHashMap<>();
    private List<a> b = new ArrayList();

    /* loaded from: classes11.dex */
    public interface a {
        void a();
    }

    /* loaded from: classes11.dex */
    public static class b {
        public String a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f19096c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public boolean f19097e;

        /* renamed from: f  reason: collision with root package name */
        public String f19098f;

        /* renamed from: g  reason: collision with root package name */
        public String f19099g;

        /* renamed from: h  reason: collision with root package name */
        public String f19100h;

        /* renamed from: i  reason: collision with root package name */
        public String f19101i;

        /* renamed from: j  reason: collision with root package name */
        public String f19102j;

        /* renamed from: k  reason: collision with root package name */
        public h2 f19103k;

        /* renamed from: l  reason: collision with root package name */
        public Context f19104l;
        private XMPushService p;
        Messenger r;

        /* renamed from: m  reason: collision with root package name */
        c f19105m = c.unbind;

        /* renamed from: n  reason: collision with root package name */
        private int f19106n = 0;
        private final CopyOnWriteArrayList<a> o = new CopyOnWriteArrayList<>();
        c q = null;
        private boolean s = false;
        private XMPushService.c t = new XMPushService.c(this);
        IBinder.DeathRecipient u = null;
        final C0824b v = new C0824b();

        /* loaded from: classes11.dex */
        public interface a {
            void a(c cVar, c cVar2, int i2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.xiaomi.push.service.i0$b$b  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0824b extends XMPushService.j {

            /* renamed from: h  reason: collision with root package name */
            int f19107h;

            /* renamed from: i  reason: collision with root package name */
            int f19108i;

            /* renamed from: j  reason: collision with root package name */
            String f19109j;

            /* renamed from: k  reason: collision with root package name */
            String f19110k;

            public C0824b() {
                super(0);
            }

            @Override // com.xiaomi.push.service.XMPushService.j
            public void a() {
                if (b.this.l(this.f19107h, this.f19108i, this.f19110k)) {
                    b.this.g(this.f19107h, this.f19108i, this.f19109j, this.f19110k);
                    return;
                }
                g.j.a.a.a.c.y(" ignore notify client :" + b.this.f19100h);
            }

            @Override // com.xiaomi.push.service.XMPushService.j
            public String b() {
                return "notify job";
            }

            public XMPushService.j c(int i2, int i3, String str, String str2) {
                this.f19107h = i2;
                this.f19108i = i3;
                this.f19110k = str2;
                this.f19109j = str;
                return this;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public class c implements IBinder.DeathRecipient {
            final b a;
            final Messenger b;

            c(b bVar, Messenger messenger) {
                this.a = bVar;
                this.b = messenger;
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                g.j.a.a.a.c.y("peer died, chid = " + this.a.f19100h);
                b.this.p.a(new k0(this, 0), 0L);
                if ("9".equals(this.a.f19100h) && "com.xiaomi.xmsf".equals(b.this.p.getPackageName())) {
                    b.this.p.a(new l0(this, 0), 60000L);
                }
            }
        }

        public b() {
        }

        public b(XMPushService xMPushService) {
            this.p = xMPushService;
            i(new j0(this));
        }

        public static String e(String str) {
            int lastIndexOf;
            return (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf("/")) == -1) ? "" : str.substring(lastIndexOf + 1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(int i2, int i3, String str, String str2) {
            c cVar = this.f19105m;
            this.q = cVar;
            if (i2 == 2) {
                this.f19103k.f(this.f19104l, this, i3);
            } else if (i2 == 3) {
                this.f19103k.g(this.f19104l, this, str2, str);
            } else if (i2 == 1) {
                boolean z = cVar == c.binded;
                if (!z && "wait".equals(str2)) {
                    this.f19106n++;
                } else if (z) {
                    this.f19106n = 0;
                    if (this.r != null) {
                        try {
                            this.r.send(Message.obtain(null, 16, this.p.f224a));
                        } catch (RemoteException unused) {
                        }
                    }
                }
                this.f19103k.h(this.p, this, z, i3, str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean l(int i2, int i3, String str) {
            boolean z;
            StringBuilder sb;
            String str2;
            c cVar = this.q;
            if (cVar == null || !(z = this.s)) {
                return true;
            }
            if (cVar == this.f19105m) {
                sb = new StringBuilder();
                str2 = " status recovered, don't notify client:";
            } else if (this.r != null && z) {
                g.j.a.a.a.c.y("Peer alive notify status to client:" + this.f19100h);
                return true;
            } else {
                sb = new StringBuilder();
                str2 = "peer died, ignore notify ";
            }
            sb.append(str2);
            sb.append(this.f19100h);
            g.j.a.a.a.c.y(sb.toString());
            return false;
        }

        private boolean o(int i2, int i3, String str) {
            if (i2 == 1) {
                return (this.f19105m == c.binded || !this.p.m159c() || i3 == 21 || (i3 == 7 && "wait".equals(str))) ? false : true;
            } else if (i2 != 2) {
                if (i2 != 3) {
                    return false;
                }
                return !"wait".equals(str);
            } else {
                return this.p.m159c();
            }
        }

        public long a() {
            return (((long) ((Math.random() * 20.0d) - 10.0d)) + ((this.f19106n + 1) * 15)) * 1000;
        }

        public String d(int i2) {
            return i2 != 1 ? i2 != 2 ? i2 != 3 ? "unknown" : "KICK" : "CLOSE" : "OPEN";
        }

        void f() {
            try {
                Messenger messenger = this.r;
                if (messenger != null && this.u != null) {
                    messenger.getBinder().unlinkToDeath(this.u, 0);
                }
            } catch (Exception unused) {
            }
            this.q = null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void h(Messenger messenger) {
            f();
            try {
                if (messenger != null) {
                    this.r = messenger;
                    this.s = true;
                    this.u = new c(this, messenger);
                    messenger.getBinder().linkToDeath(this.u, 0);
                } else {
                    g.j.a.a.a.c.y("peer linked with old sdk chid = " + this.f19100h);
                }
            } catch (Exception e2) {
                g.j.a.a.a.c.y("peer linkToDeath err: " + e2.getMessage());
                this.r = null;
                this.s = false;
            }
        }

        public void i(a aVar) {
            this.o.add(aVar);
        }

        public void k(c cVar, int i2, int i3, String str, String str2) {
            boolean z;
            Iterator<a> it = this.o.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next != null) {
                    next.a(this.f19105m, cVar, i3);
                }
            }
            c cVar2 = this.f19105m;
            int i4 = 0;
            if (cVar2 != cVar) {
                g.j.a.a.a.c.o(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", cVar2, cVar, d(i2), m0.a(i3), str, str2, this.f19100h));
                this.f19105m = cVar;
            }
            if (this.f19103k == null) {
                g.j.a.a.a.c.D("status changed while the client dispatcher is missing");
            } else if (cVar == c.binding) {
            } else {
                if (this.q != null && (z = this.s)) {
                    i4 = (this.r == null || !z) ? 10100 : 1000;
                }
                this.p.b(this.v);
                if (o(i2, i3, str2)) {
                    g(i2, i3, str, str2);
                    return;
                }
                XMPushService xMPushService = this.p;
                C0824b c0824b = this.v;
                c0824b.c(i2, i3, str, str2);
                xMPushService.a(c0824b, i4);
            }
        }

        public void n(a aVar) {
            this.o.remove(aVar);
        }
    }

    /* loaded from: classes11.dex */
    public enum c {
        unbind,
        binding,
        binded
    }

    private i0() {
    }

    public static synchronized i0 c() {
        i0 i0Var;
        synchronized (i0.class) {
            if (f19095c == null) {
                f19095c = new i0();
            }
            i0Var = f19095c;
        }
        return i0Var;
    }

    private String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf(DYConstants.DY_REGEX_AT);
        return indexOf > 0 ? str.substring(0, indexOf) : str;
    }

    public synchronized int a() {
        return this.a.size();
    }

    public synchronized b b(String str, String str2) {
        HashMap<String, b> hashMap = this.a.get(str);
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(d(str2));
    }

    public synchronized ArrayList<b> e() {
        ArrayList<b> arrayList;
        arrayList = new ArrayList<>();
        Iterator<HashMap<String, b>> it = this.a.values().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().values());
        }
        return arrayList;
    }

    public synchronized Collection<b> f(String str) {
        if (this.a.containsKey(str)) {
            return ((HashMap) this.a.get(str).clone()).values();
        }
        return new ArrayList();
    }

    public synchronized List<String> g(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<HashMap<String, b>> it = this.a.values().iterator();
        while (it.hasNext()) {
            for (b bVar : it.next().values()) {
                if (str.equals(bVar.a)) {
                    arrayList.add(bVar.f19100h);
                }
            }
        }
        return arrayList;
    }

    public synchronized void h() {
        Iterator<b> it = e().iterator();
        while (it.hasNext()) {
            it.next().f();
        }
        this.a.clear();
    }

    public synchronized void i(Context context) {
        Iterator<HashMap<String, b>> it = this.a.values().iterator();
        while (it.hasNext()) {
            Iterator<b> it2 = it.next().values().iterator();
            while (it2.hasNext()) {
                it2.next().k(c.unbind, 1, 3, null, null);
            }
        }
    }

    public synchronized void j(Context context, int i2) {
        Iterator<HashMap<String, b>> it = this.a.values().iterator();
        while (it.hasNext()) {
            Iterator<b> it2 = it.next().values().iterator();
            while (it2.hasNext()) {
                it2.next().k(c.unbind, 2, i2, null, null);
            }
        }
    }

    public synchronized void k(a aVar) {
        this.b.add(aVar);
    }

    public synchronized void l(b bVar) {
        HashMap<String, b> hashMap = this.a.get(bVar.f19100h);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.a.put(bVar.f19100h, hashMap);
        }
        hashMap.put(d(bVar.b), bVar);
        g.j.a.a.a.c.o("add active client. " + bVar.a);
        Iterator<a> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public synchronized void m(String str) {
        HashMap<String, b> hashMap = this.a.get(str);
        if (hashMap != null) {
            Iterator<b> it = hashMap.values().iterator();
            while (it.hasNext()) {
                it.next().f();
            }
            hashMap.clear();
            this.a.remove(str);
        }
        Iterator<a> it2 = this.b.iterator();
        while (it2.hasNext()) {
            it2.next().a();
        }
    }

    public synchronized void n(String str, String str2) {
        HashMap<String, b> hashMap = this.a.get(str);
        if (hashMap != null) {
            b bVar = hashMap.get(d(str2));
            if (bVar != null) {
                bVar.f();
            }
            hashMap.remove(d(str2));
            if (hashMap.isEmpty()) {
                this.a.remove(str);
            }
        }
        Iterator<a> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public synchronized void o() {
        this.b.clear();
    }
}
