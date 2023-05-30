package com.jingdong.app.mall.home.v.c;

import android.graphics.Paint;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.view.b.f.d;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.gray.JDGrayModelUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class a {
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static final AtomicBoolean b = new AtomicBoolean(false);

    /* renamed from: c */
    private static final AtomicBoolean f10979c = new AtomicBoolean(false);
    private static final Paint d = new Paint();

    /* renamed from: com.jingdong.app.mall.home.v.c.a$a */
    /* loaded from: classes4.dex */
    public class C0332a extends b {

        /* renamed from: g */
        final /* synthetic */ boolean f10980g;

        C0332a(boolean z) {
            this.f10980g = z;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            JDGrayModelUtils.getInstance().setModel(this.f10980g ? 1 : 0);
        }
    }

    public static void a(View view) {
        h(view, b.get());
    }

    public static void b(View view, boolean z) {
        h(view, z && b.get());
    }

    public static Paint c(boolean z) {
        JDGrayModelUtils jDGrayModelUtils = JDGrayModelUtils.getInstance();
        Paint paint = d;
        jDGrayModelUtils.setPaintGray(paint, z && b.get());
        return paint;
    }

    public static void d(JDJSONObject jDJSONObject, boolean z) {
        boolean z2 = jDJSONObject.optInt("blackHomeFlag", 1) == 0;
        AtomicBoolean atomicBoolean = a;
        if (!atomicBoolean.get()) {
            atomicBoolean.set(b.get() ^ z2);
        }
        AtomicBoolean atomicBoolean2 = b;
        atomicBoolean2.set(z2 || f10979c.get());
        if (z && d.f("homeGray_Times", 1)) {
            return;
        }
        g(atomicBoolean2.get());
    }

    public static boolean e() {
        return b.get();
    }

    public static void f(boolean z) {
        f10979c.set(z);
    }

    public static void g(boolean z) {
        if (z) {
            d.a("homeGray_Times", 10);
        }
        f.E0(new C0332a(z));
    }

    public static void h(View view, boolean z) {
        if (view == null || !a.get()) {
            return;
        }
        JDGrayModelUtils.getInstance().setViewGray(view, z);
    }
}
