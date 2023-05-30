package com.meizu.cloud.pushsdk.handler.f.h;

import com.jd.lib.un.utils.UnTimeUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class b {
    private long a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private List<String> f15986c;
    private List<String> d;

    /* renamed from: e  reason: collision with root package name */
    private List<a> f15987e;

    /* loaded from: classes14.dex */
    public static class a {
        private String a;
        private String b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String toString() {
            return "ShieldConfig{mModel=" + this.a + "mOs=" + this.b + '}';
        }
    }

    public List<a> a() {
        return this.f15987e;
    }

    public void b(int i2) {
        this.b = i2;
    }

    public void c(long j2) {
        this.a = j2;
    }

    public void d(a aVar) {
        if (this.f15987e == null) {
            this.f15987e = new ArrayList();
        }
        this.f15987e.add(aVar);
    }

    public void e(String str) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.add(str);
    }

    public List<String> f() {
        return this.d;
    }

    public void g(String str) {
        if (this.f15986c == null) {
            this.f15986c = new ArrayList();
        }
        this.f15986c.add(str);
    }

    public List<String> h() {
        return this.f15986c;
    }

    public boolean i() {
        int i2;
        long j2 = this.a;
        return (j2 == 0 || (i2 = this.b) == 0 || j2 + ((long) (i2 * UnTimeUtils.HOUR)) <= System.currentTimeMillis()) ? false : true;
    }

    public String toString() {
        return "PushConfigInfo{mRequestTime=" + this.a + "mIntervalHour=" + this.b + "mShieldPackageList=" + this.d + "mWhitePackageList=" + this.f15986c + "mShieldConfigList=" + this.f15987e + '}';
    }
}
