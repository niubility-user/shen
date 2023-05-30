package com.xiaomi.push;

import android.os.SystemClock;
import android.util.Pair;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.i0;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes11.dex */
public abstract class o5 {
    private static final AtomicInteger o = new AtomicInteger(0);
    public static boolean p;

    /* renamed from: l  reason: collision with root package name */
    protected p5 f18903l;

    /* renamed from: m  reason: collision with root package name */
    protected XMPushService f18904m;
    protected int a = 0;
    protected long b = -1;

    /* renamed from: c  reason: collision with root package name */
    protected volatile long f18895c = 0;
    private LinkedList<Pair<Integer, Long>> d = new LinkedList<>();

    /* renamed from: e  reason: collision with root package name */
    private final Collection<r5> f18896e = new CopyOnWriteArrayList();

    /* renamed from: f  reason: collision with root package name */
    protected final Map<t5, a> f18897f = new ConcurrentHashMap();

    /* renamed from: g  reason: collision with root package name */
    protected final Map<t5, a> f18898g = new ConcurrentHashMap();

    /* renamed from: h  reason: collision with root package name */
    protected b6 f18899h = null;

    /* renamed from: i  reason: collision with root package name */
    protected String f18900i = "";

    /* renamed from: j  reason: collision with root package name */
    private int f18901j = 2;

    /* renamed from: k  reason: collision with root package name */
    protected final int f18902k = o.getAndIncrement();

    /* renamed from: n  reason: collision with root package name */
    private long f18905n = 0;

    /* loaded from: classes11.dex */
    public static class a {
        private t5 a;
        private c6 b;

        public a(t5 t5Var, c6 c6Var) {
            this.a = t5Var;
            this.b = c6Var;
        }

        public void a(e5 e5Var) {
            this.a.b(e5Var);
        }

        public void b(g6 g6Var) {
            c6 c6Var = this.b;
            if (c6Var == null || c6Var.mo104a(g6Var)) {
                this.a.a(g6Var);
            }
        }
    }

    static {
        p = false;
        try {
            p = Boolean.getBoolean("smack.debugEnabled");
        } catch (Exception unused) {
        }
        u5.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public o5(XMPushService xMPushService, p5 p5Var) {
        this.f18903l = p5Var;
        this.f18904m = xMPushService;
        u();
    }

    private String e(int i2) {
        return i2 == 1 ? "connected" : i2 == 0 ? "connecting" : i2 == 2 ? "disconnected" : "unknown";
    }

    private void g(int i2) {
        synchronized (this.d) {
            if (i2 == 1) {
                this.d.clear();
            } else {
                this.d.add(new Pair<>(Integer.valueOf(i2), Long.valueOf(System.currentTimeMillis())));
                if (this.d.size() > 6) {
                    this.d.remove(0);
                }
            }
        }
    }

    public abstract void A(boolean z);

    public boolean B() {
        return this.f18901j == 0;
    }

    public synchronized void C() {
        this.f18905n = SystemClock.elapsedRealtime();
    }

    public boolean D() {
        return this.f18901j == 1;
    }

    public void E() {
        synchronized (this.d) {
            this.d.clear();
        }
    }

    public int a() {
        return this.a;
    }

    public long b() {
        return this.f18895c;
    }

    public p5 c() {
        return this.f18903l;
    }

    public String d() {
        return this.f18903l.j();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<t5, a> f() {
        return this.f18897f;
    }

    public void h(int i2, int i3, Exception exc) {
        int i4 = this.f18901j;
        if (i2 != i4) {
            g.j.a.a.a.c.o(String.format("update the connection status. %1$s -> %2$s : %3$s ", e(i4), e(i2), com.xiaomi.push.service.m0.a(i3)));
        }
        if (j0.p(this.f18904m)) {
            g(i2);
        }
        if (i2 == 1) {
            this.f18904m.a(10);
            if (this.f18901j != 0) {
                g.j.a.a.a.c.o("try set connected while not connecting.");
            }
            this.f18901j = i2;
            Iterator<r5> it = this.f18896e.iterator();
            while (it.hasNext()) {
                it.next().b(this);
            }
        } else if (i2 == 0) {
            if (this.f18901j != 2) {
                g.j.a.a.a.c.o("try set connecting while not disconnected.");
            }
            this.f18901j = i2;
            Iterator<r5> it2 = this.f18896e.iterator();
            while (it2.hasNext()) {
                it2.next().a(this);
            }
        } else if (i2 == 2) {
            this.f18904m.a(10);
            int i5 = this.f18901j;
            if (i5 == 0) {
                Iterator<r5> it3 = this.f18896e.iterator();
                while (it3.hasNext()) {
                    it3.next().a(this, exc == null ? new CancellationException("disconnect while connecting") : exc);
                }
            } else if (i5 == 1) {
                Iterator<r5> it4 = this.f18896e.iterator();
                while (it4.hasNext()) {
                    it4.next().a(this, i3, exc);
                }
            }
            this.f18901j = i2;
        }
    }

    public void i(r5 r5Var) {
        if (r5Var == null || this.f18896e.contains(r5Var)) {
            return;
        }
        this.f18896e.add(r5Var);
    }

    public void j(t5 t5Var) {
        this.f18897f.remove(t5Var);
    }

    public void k(t5 t5Var, c6 c6Var) {
        if (t5Var == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f18897f.put(t5Var, new a(t5Var, c6Var));
    }

    public abstract void l(g6 g6Var);

    public abstract void m(i0.b bVar);

    public synchronized void n(String str) {
        if (this.f18901j == 0) {
            g.j.a.a.a.c.o("setChallenge hash = " + o0.b(str).substring(0, 8));
            this.f18900i = str;
            h(1, 0, null);
        } else {
            g.j.a.a.a.c.o("ignore setChallenge because connection was disconnected");
        }
    }

    public abstract void o(String str, String str2);

    public abstract void p(e5[] e5VarArr);

    public boolean q() {
        return false;
    }

    public synchronized boolean r(long j2) {
        return this.f18905n >= j2;
    }

    public int s() {
        return this.f18901j;
    }

    public String t() {
        return this.f18903l.h();
    }

    protected void u() {
        String str;
        if (this.f18903l.f() && this.f18899h == null) {
            Class<?> cls = null;
            try {
                str = System.getProperty("smack.debuggerClass");
            } catch (Throwable unused) {
                str = null;
            }
            if (str != null) {
                try {
                    cls = Class.forName(str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (cls == null) {
                this.f18899h = new n5(this);
                return;
            }
            try {
                this.f18899h = (b6) cls.getConstructor(o5.class, Writer.class, Reader.class).newInstance(this);
            } catch (Exception e3) {
                throw new IllegalArgumentException("Can't initialize the configured debugger!", e3);
            }
        }
    }

    public abstract void v(int i2, Exception exc);

    public abstract void w(e5 e5Var);

    public void x(r5 r5Var) {
        this.f18896e.remove(r5Var);
    }

    public void y(t5 t5Var) {
        this.f18898g.remove(t5Var);
    }

    public void z(t5 t5Var, c6 c6Var) {
        if (t5Var == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f18898g.put(t5Var, new a(t5Var, c6Var));
    }
}
