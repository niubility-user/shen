package com.jingdong.jdpush_new.mta;

import android.content.SharedPreferences;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdsdk.network.utils.StatSharePreferenceUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class a {
    private static a o;
    public long a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public long f12856c;
    public boolean d = true;

    /* renamed from: e  reason: collision with root package name */
    public boolean f12857e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f12858f;

    /* renamed from: g  reason: collision with root package name */
    public long f12859g;

    /* renamed from: h  reason: collision with root package name */
    public long f12860h;

    /* renamed from: i  reason: collision with root package name */
    public Long f12861i;

    /* renamed from: j  reason: collision with root package name */
    public int f12862j;

    /* renamed from: k  reason: collision with root package name */
    public int f12863k;

    /* renamed from: l  reason: collision with root package name */
    public int f12864l;

    /* renamed from: m  reason: collision with root package name */
    public int f12865m;

    /* renamed from: n  reason: collision with root package name */
    public int f12866n;

    private a() {
    }

    private void a(long j2) {
        this.f12863k = (int) (this.f12863k + (j2 - Math.max(this.a, this.f12856c)));
    }

    public static a b() {
        if (o == null) {
            synchronized (a.class) {
                if (o == null) {
                    o = new a();
                }
            }
        }
        return o;
    }

    public void c(long j2) {
        this.d = false;
        this.f12862j = (int) (this.f12862j + (j2 - this.a));
        if (this.f12858f) {
            a(j2);
        }
        j();
    }

    public void d(long j2) {
        this.d = true;
        this.a = j2;
    }

    public void e() {
        if (this.f12858f && this.d) {
            a(System.currentTimeMillis());
            this.f12858f = false;
        }
    }

    public void f() {
        this.b = System.currentTimeMillis();
        boolean z = this.d;
        this.f12857e = z;
        if (z) {
            this.f12864l++;
        }
    }

    public void g() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f12856c = currentTimeMillis;
        this.f12866n = (int) (this.f12866n + (currentTimeMillis - this.b));
        this.f12865m++;
        if (!this.f12857e) {
            this.f12864l++;
        }
        this.f12858f = true;
        if (this.f12859g == 0) {
            this.f12859g = currentTimeMillis;
            long j2 = this.f12860h;
            if (j2 != 0) {
                this.f12861i = Long.valueOf(j2 - currentTimeMillis);
                g.a("finishedConnectionInTime:" + this.f12861i);
            }
        }
    }

    public void h(long j2) {
        this.f12860h = j2;
        long j3 = this.f12859g;
        if (j3 != 0) {
            this.f12861i = Long.valueOf(j2 - j3);
            g.a("finishedConnectionInTime:" + this.f12861i);
        }
    }

    public void i() {
        SharedPreferences.Editor edit = StatSharePreferenceUtil.getSharedPreferences().edit();
        Long l2 = this.f12861i;
        if (l2 != null) {
            edit.putLong("push_fcit", l2.longValue());
        }
        edit.putInt("push_fgd", this.f12862j).putInt("push_lvd", this.f12863k).putInt("push_lctc", this.f12864l).putInt("push_lcsc", this.f12865m).putInt("push_cct", this.f12866n).commit();
        g.c("foregroundDuration=" + this.f12862j);
        g.c("longConnValidDuration=" + this.f12863k);
        g.c("longConnTryCount=" + this.f12864l);
        g.c("longConnSuccessCount=" + this.f12865m);
        g.c("connectCostTime=" + this.f12866n);
    }

    public void j() {
        try {
            SharedPreferences.Editor edit = StatSharePreferenceUtil.getSharedPreferences().edit();
            Long l2 = this.f12861i;
            if (l2 != null) {
                edit.putLong("push_fcit", l2.longValue());
            }
            edit.putInt("push_fgd", this.f12862j).putInt("push_lvd", this.f12863k).putInt("push_lctc", this.f12864l).putInt("push_lcsc", this.f12865m).putInt("push_cct", this.f12866n).apply();
            if (OKLog.I) {
                g.h("foregroundDuration=" + this.f12862j);
                g.h("longConnValidDuration=" + this.f12863k);
                g.h("longConnTryCount=" + this.f12864l);
                g.h("longConnSuccessCount=" + this.f12865m);
                g.h("connectCostTime=" + this.f12866n);
                g.h("longConnInvalidDuration=" + (this.f12862j - this.f12863k));
                StringBuilder sb = new StringBuilder();
                sb.append("\u524d\u53f0\u957f\u8fde\u63a5\u5728\u7ebf\u7387=");
                Object[] objArr = new Object[1];
                double d = this.f12863k;
                Double.isNaN(d);
                double d2 = d * 1.0d;
                double d3 = this.f12862j;
                Double.isNaN(d3);
                int i2 = 0;
                objArr[0] = Double.valueOf(d2 / d3);
                sb.append(String.format("%.3f", objArr));
                g.h(sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\u8fde\u63a5\u5e73\u5747\u6d88\u8017\u65f6\u957f:");
                int i3 = this.f12865m;
                if (i3 != 0) {
                    i2 = this.f12866n / i3;
                }
                sb2.append(i2);
                g.h(sb2.toString());
                g.c("FINISHED_CONNECTION_IN_TIME\uff1a" + this.f12861i);
            }
        } catch (Throwable th) {
            g.g(th);
        }
    }
}
