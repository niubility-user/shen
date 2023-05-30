package com.jingdong.jdma.g;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.jdma.common.utils.LogUtil;
import com.jingdong.jdma.common.utils.c;
import com.jingdong.jdma.common.utils.i;
import com.jingdong.jdma.common.utils.l;
import com.jingdong.jdma.common.utils.m;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes12.dex */
public class a {

    /* renamed from: i  reason: collision with root package name */
    private static final double f12750i = Math.pow(10.0d, 11.0d);

    /* renamed from: c  reason: collision with root package name */
    private long f12751c;

    /* renamed from: e  reason: collision with root package name */
    private volatile long f12752e;

    /* renamed from: f  reason: collision with root package name */
    private volatile long f12753f;

    /* renamed from: g  reason: collision with root package name */
    private volatile long f12754g;

    /* renamed from: h  reason: collision with root package name */
    private b f12755h;
    private AtomicLong a = new AtomicLong();
    private AtomicLong b = new AtomicLong();
    private AtomicLong d = new AtomicLong();

    public a(Context context) {
        a(context);
        this.f12755h = new b();
    }

    private void a(Context context) {
        String str;
        if (i.a(context)) {
            this.d.set(0L);
            this.b.set(0L);
            l a = l.a(context);
            String a2 = a.a("open_count", "");
            String a3 = a.a("bigdata_open_count", "");
            String a4 = a.a("first_session_time", "");
            String a5 = a.a("previous_session_time", "");
            String a6 = a.a("visit_create_time", "");
            if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a4) && !TextUtils.isEmpty(a5) && !TextUtils.isEmpty(a6)) {
                long b = m.b(a2);
                str = "visit_create_time";
                double d = f12750i;
                if (b < d) {
                    this.f12751c = b + 1;
                } else {
                    this.f12751c = 1L;
                }
                this.a.set(m.b(a3));
                if (this.a.get() < d) {
                    this.a.incrementAndGet();
                } else {
                    this.a.set(1L);
                }
                this.f12752e = m.b(a4);
                this.f12753f = m.b(a6);
                this.f12754g = System.currentTimeMillis();
            } else {
                str = "visit_create_time";
                this.f12751c = 1L;
                this.a.set(1L);
                this.f12752e = System.currentTimeMillis();
                this.f12753f = System.currentTimeMillis();
                this.f12754g = System.currentTimeMillis();
            }
            a.b("open_count", "" + this.f12751c);
            a.b("bigdata_open_count", "" + this.a.get());
            a.b("first_session_time", "" + this.f12752e);
            a.b("previous_session_time", "" + this.f12753f);
            a.b(str, "" + this.f12754g);
        }
    }

    public long b() {
        return this.f12752e;
    }

    public long c() {
        return this.f12753f;
    }

    public long d() {
        return this.d.get();
    }

    public long e() {
        LogUtil.w("JDMA_SessionManager", "getPvSid->" + this.f12751c);
        return this.f12751c;
    }

    public long f() {
        return this.b.get();
    }

    public long g() {
        return this.a.get();
    }

    public long h() {
        return this.f12754g;
    }

    public void i() {
        this.f12755h.a();
    }

    public void b(long j2) {
        LogUtil.w("JDMA_SessionManager", "setPvSid->" + j2);
        this.f12751c = j2;
        c.a(1L);
    }

    public void c(long j2) {
        this.b.set(j2);
    }

    public void d(long j2) {
        this.a.set(j2);
    }

    public void b(Context context) {
        if (i.a(context) && this.f12755h.b()) {
            if (this.a.get() < f12750i) {
                this.a.incrementAndGet();
            } else {
                this.a.set(1L);
            }
            this.b.set(0L);
            this.f12753f = m.b(l.a(context).a("visit_create_time", "" + System.currentTimeMillis()));
            this.f12754g = System.currentTimeMillis();
            l.a(context).b("bigdata_open_count", "" + this.a.get());
            l.a(context).b("previous_session_time", "" + this.f12753f);
            l.a(context).b("visit_create_time", "" + this.f12754g);
        }
    }

    public void a() {
        double d = f12750i;
        if (this.d.get() < d) {
            this.d.incrementAndGet();
        } else {
            this.d.set(1L);
        }
        if (this.b.get() < d) {
            this.b.incrementAndGet();
        } else {
            this.b.set(1L);
        }
    }

    public void a(long j2) {
        this.d.set(j2);
    }
}
