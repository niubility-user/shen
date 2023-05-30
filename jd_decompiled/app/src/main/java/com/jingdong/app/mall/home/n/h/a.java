package com.jingdong.app.mall.home.n.h;

import android.graphics.Bitmap;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.view.CaContentLayout;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.view.view.MallFloorCategory;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class a {
    private static int a = 600;
    private static int b = 300;

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, C0310a> f10437c = new ConcurrentHashMap();
    private static Map<String, SoftReference<Bitmap>> d = new ConcurrentHashMap();

    /* renamed from: com.jingdong.app.mall.home.n.h.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public static class C0310a {
        private long a;
        private long b;

        /* renamed from: c  reason: collision with root package name */
        private JDJSONObject f10438c;

        void a() {
            this.f10438c = null;
        }

        public JDJSONObject b() {
            return this.f10438c;
        }

        public long c() {
            return this.a;
        }

        boolean d() {
            return this.f10438c != null && (SystemClock.elapsedRealtime() - this.b) / 1000 < ((long) a.a);
        }

        void e(JDJSONObject jDJSONObject) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.a = elapsedRealtime;
            this.b = elapsedRealtime;
            this.f10438c = jDJSONObject;
        }

        void f() {
            this.b = SystemClock.elapsedRealtime();
        }
    }

    public static void b() {
        f10437c.clear();
    }

    public static void c(CategoryEntity.CaItem caItem) {
        C0310a c0310a;
        if (caItem == null || caItem.isTopTab() || (c0310a = f10437c.get(caItem.getPcId())) == null) {
            return;
        }
        c0310a.a();
    }

    public static C0310a d(CategoryEntity.CaItem caItem) {
        C0310a c0310a;
        if (caItem == null || caItem.isTopTab() || (c0310a = f10437c.get(caItem.getPcId())) == null || !c0310a.d()) {
            return null;
        }
        return c0310a;
    }

    public static Bitmap e(String str) {
        SoftReference<Bitmap> softReference;
        if (TextUtils.isEmpty(str) || (softReference = d.get(str)) == null) {
            return null;
        }
        return softReference.get();
    }

    public static boolean f(long j2) {
        CategoryEntity.CaItem i2 = CaContentLayout.i();
        if (i2 != null && !i2.isTopTab()) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - j2;
            if (elapsedRealtime / 1000 > b) {
                com.jingdong.app.mall.home.widget.b lastCreateView = t.FLOOR_CATEGORY.getLastCreateView();
                if (lastCreateView instanceof MallFloorCategory) {
                    com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(i2.getSrvString());
                    c2.a("ts", String.valueOf(elapsedRealtime));
                    com.jingdong.app.mall.home.r.c.a.s("Home_ClassifyTabRefresh", "", c2.toString());
                    ((MallFloorCategory) lastCreateView).onBackPressed();
                    return true;
                }
            }
        }
        return false;
    }

    public static void g(CategoryEntity.CaItem caItem, JDJSONObject jDJSONObject) {
        if (caItem == null || caItem.isTopTab()) {
            return;
        }
        C0310a c0310a = f10437c.get(caItem.getPcId());
        if (c0310a == null) {
            c0310a = new C0310a();
            f10437c.put(caItem.getPcId(), c0310a);
        }
        c0310a.e(jDJSONObject);
    }

    public static void h(String str, Bitmap bitmap) {
        if (TextUtils.isEmpty(str) || bitmap == null || bitmap.isRecycled()) {
            return;
        }
        d.put(str, new SoftReference<>(bitmap));
    }

    public static void i(CategoryEntity.CaItem caItem) {
        C0310a c0310a;
        if (caItem == null || caItem.isTopTab() || (c0310a = f10437c.get(caItem.getPcId())) == null) {
            return;
        }
        c0310a.f();
    }

    public static void j(String str) {
        int h2 = c.h(str, 0);
        if (h2 <= 0) {
            h2 = 600;
        }
        a = h2;
    }

    public static void k(String str) {
        int h2 = c.h(str, 0);
        if (h2 <= 0) {
            h2 = 300;
        }
        b = h2;
    }
}
