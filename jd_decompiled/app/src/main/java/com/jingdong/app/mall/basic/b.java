package com.jingdong.app.mall.basic;

import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class b {
    private static final String d = "b";

    /* renamed from: e  reason: collision with root package name */
    private static b f8005e;
    private List<JDTaskModule> a = new ArrayList();
    private JDTaskModule b;

    /* renamed from: c  reason: collision with root package name */
    private JDTaskModule f8006c;

    private b() {
    }

    public static b c() {
        if (f8005e == null) {
            f8005e = new b();
        }
        return f8005e;
    }

    private void h(JDTaskModule jDTaskModule) {
        JDTaskModule jDTaskModule2;
        if (!jDTaskModule.b || (jDTaskModule2 = this.f8006c) == null) {
            return;
        }
        d(jDTaskModule2);
    }

    public void a() {
        this.a.clear();
    }

    public JDTaskModule b() {
        return this.b;
    }

    public void d(JDTaskModule jDTaskModule) {
        this.a.add(jDTaskModule);
        if (Log.D) {
            Log.d(d, "push() history add size = " + f());
        }
    }

    public void e(JDTaskModule jDTaskModule) {
        if (Log.D) {
            Log.d(d, "setCurrent()");
        }
        this.f8006c = this.b;
        g();
        h(jDTaskModule);
        this.b = jDTaskModule;
    }

    public int f() {
        return this.a.size();
    }

    public void g() {
        JDTaskModule jDTaskModule = this.b;
        if (jDTaskModule == null || !jDTaskModule.f7947c) {
            return;
        }
        d(jDTaskModule);
    }
}
