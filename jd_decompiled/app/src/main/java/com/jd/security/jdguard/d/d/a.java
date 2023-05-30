package com.jd.security.jdguard.d.d;

import android.content.Context;
import android.text.TextUtils;
import com.jd.security.jdguard.d.c.d;
import com.jd.security.jdguard.d.c.f;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes17.dex */
public abstract class a {
    protected b a;
    private AtomicBoolean b = new AtomicBoolean(false);

    /* renamed from: c */
    private AtomicBoolean f6920c = new AtomicBoolean(false);
    private String d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.security.jdguard.d.d.a$a */
    /* loaded from: classes17.dex */
    public class RunnableC0214a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ c f6921g;

        RunnableC0214a(c cVar) {
            a.this = r1;
            this.f6921g = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!a.this.f6920c.get()) {
                a aVar = a.this;
                aVar.g(this.f6921g, -5, String.format("[%s] another scan thread has started", aVar.a.f6924e));
                return;
            }
            System.currentTimeMillis();
            try {
                try {
                    Object k2 = a.this.k();
                    a aVar2 = a.this;
                    b bVar = aVar2.a;
                    aVar2.f(bVar.b, bVar.a, bVar.f6923c, k2);
                    a aVar3 = a.this;
                    aVar3.d = aVar3.j(k2);
                } catch (Throwable unused) {
                    a aVar4 = a.this;
                    aVar4.g(this.f6921g, -1, String.format("[%s] scan exception", aVar4.a.f6924e));
                }
            } finally {
                a.this.f6920c.set(false);
                a aVar5 = a.this;
                aVar5.h(this.f6921g, 1, aVar5.d);
            }
        }
    }

    protected abstract String d();

    public void e(b bVar) {
        if (bVar == null || this.b.get()) {
            return;
        }
        this.a = bVar;
        this.b.set(true);
    }

    protected abstract void f(Context context, f fVar, d dVar, Object obj);

    protected void g(c cVar, int i2, String str) {
        if (cVar != null) {
            cVar.onFailed(i2, str);
        }
    }

    public void h(c cVar, int i2, String str) {
        if (cVar != null) {
            cVar.onResult(i2, str);
        }
    }

    public String i(c cVar) {
        if (!this.b.get()) {
            String d = d();
            h(cVar, 3, d);
            g(cVar, -2, String.format("scanner not init, return default {%s}", d));
            return d;
        }
        System.currentTimeMillis();
        String str = this.d;
        if (str == null) {
            String d2 = d();
            h(cVar, 4, d2);
            return d2;
        }
        h(cVar, 2, str);
        return this.d;
    }

    protected abstract String j(Object obj);

    protected abstract Object k();

    public void l(c cVar, boolean z) {
        try {
            if (!this.b.get()) {
                g(cVar, -2, "scanner not init yet, return default");
                h(cVar, 3, d());
                return;
            }
            b bVar = this.a;
            f fVar = bVar.a;
            if (fVar != null && bVar.d != null) {
                if (!fVar.enable()) {
                    g(cVar, -4, "scanner switch no enable, return default");
                    h(cVar, 3, d());
                    return;
                }
                if (System.currentTimeMillis() - this.a.a.d() <= this.a.a.b() * 60 * 1000) {
                    if (!TextUtils.isEmpty(this.d)) {
                        h(cVar, 2, this.d);
                        return;
                    }
                } else {
                    this.a.a.e(System.currentTimeMillis());
                }
                if (this.f6920c.get()) {
                    g(cVar, -5, String.format("[%s] another scan thread has started", this.a.f6924e));
                    return;
                }
                RunnableC0214a runnableC0214a = new RunnableC0214a(cVar);
                this.f6920c.set(true);
                if (!z) {
                    b bVar2 = this.a;
                    bVar2.d.schedule(runnableC0214a, bVar2.a.c(), TimeUnit.SECONDS);
                    return;
                }
                this.a.d.schedule(runnableC0214a, 0L, TimeUnit.SECONDS);
                return;
            }
            g(cVar, -3, "scanner internal error: params null");
            h(cVar, 3, d());
        } catch (Throwable th) {
            g(cVar, -6, "scanner internal error:" + th.getMessage());
            h(cVar, 3, d());
        }
    }
}
