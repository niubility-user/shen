package com.jd.security.jdguard.core;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes17.dex */
public abstract class c {
    protected Context a;
    protected String b;

    /* renamed from: c */
    protected String f6896c;
    protected String d;

    /* renamed from: e */
    protected boolean f6897e = false;

    /* renamed from: f */
    private com.jd.security.jdguard.c f6898f;

    /* renamed from: g */
    private ScheduledExecutorService f6899g;

    public c(com.jd.security.jdguard.c cVar) {
        String str = "";
        if (cVar == null) {
            com.jd.security.jdguard.f.d.e(new RuntimeException("can not init adapter"));
            return;
        }
        this.f6898f = cVar;
        Context f2 = cVar.f();
        this.a = f2;
        try {
            String[] list = f2.getAssets().list("");
            if (BaseInfo.getAndroidSDKVersion() >= 24) {
                List list2 = (List) Arrays.stream(list).filter(new Predicate() { // from class: com.jd.security.jdguard.core.b
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        boolean endsWith;
                        endsWith = ((String) obj).endsWith(".jdg.xbt");
                        return endsWith;
                    }
                }).collect(Collectors.toList());
                if (!list2.isEmpty()) {
                    str = (String) list2.get(0);
                }
            } else {
                int length = list.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    String str2 = list[i2];
                    if (str2.endsWith(".jdg.xbt")) {
                        str = str2;
                        break;
                    }
                    i2++;
                }
            }
            if (TextUtils.isEmpty(str)) {
                this.b = cVar.d();
                this.f6896c = cVar.g();
                this.d = cVar.h();
                return;
            }
            this.b = str.substring(0, str.lastIndexOf(".jdg.xbt"));
            this.f6896c = this.b + ".jdg.jpg";
            this.d = this.b + ".jdg.xbt";
        } catch (Throwable unused) {
        }
    }

    /* renamed from: m */
    public /* synthetic */ void n() {
        if (this.f6897e) {
            return;
        }
        b();
        boolean c2 = c();
        this.f6897e = c2;
        if (c2) {
            a();
        }
    }

    protected abstract void a();

    protected abstract void b();

    protected abstract boolean c();

    public String d() {
        return this.b;
    }

    public com.jd.security.jdguard.c e() {
        return this.f6898f;
    }

    public Context f() {
        return this.a;
    }

    public String g() {
        return this.f6896c;
    }

    public String h() {
        return this.d;
    }

    public ScheduledExecutorService i() {
        if (this.f6899g == null) {
            synchronized (com.jd.security.jdguard.b.class) {
                if (this.f6899g == null) {
                    this.f6899g = Executors.newScheduledThreadPool(3);
                }
            }
        }
        return this.f6899g;
    }

    public abstract boolean j();

    public void k() {
        if (j()) {
            com.jd.security.jdguard.f.d.a("this is main process init");
            if (this.f6897e) {
                return;
            }
            synchronized (c.class) {
                i().execute(new Runnable() { // from class: com.jd.security.jdguard.core.a
                    {
                        c.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        c.this.n();
                    }
                });
            }
        }
    }

    public boolean l() {
        return this.f6897e;
    }
}
