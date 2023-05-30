package com.jingdong.app.mall.home.floor.common;

import android.app.Activity;
import android.graphics.Point;
import android.util.SparseIntArray;
import android.view.WindowManager;
import com.jingdong.common.DpiUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes4.dex */
public class d {
    private static final String a = "d";
    private static volatile int[] b = new int[128];

    /* renamed from: c */
    private static ReadWriteLock f9276c = new ReentrantReadWriteLock();
    private static SparseIntArray d = new SparseIntArray();

    /* renamed from: e */
    private static ConcurrentHashMap<Class, a> f9277e = new ConcurrentHashMap<>();

    /* renamed from: f */
    public static volatile int f9278f;

    /* renamed from: g */
    public static volatile int f9279g;

    /* loaded from: classes4.dex */
    public interface a extends b {
    }

    /* loaded from: classes4.dex */
    public interface b {
        void onScreenChanged(int i2);
    }

    static {
        h(c().x, c().y);
    }

    static int a(int i2, int i3) {
        return (int) (((i3 * i2) / 750.0f) + 0.5f);
    }

    public static int b(Activity activity, int i2) {
        int min = Math.min(DpiUtil.getAppWidth(activity), (int) R2.attr.miaoShaPriceTextColor);
        float height = DpiUtil.getHeight(activity);
        if (height > 0.0f && height / min < 1.4f) {
            min = (int) (height / 1.6f);
        }
        return (int) (((min * i2) / 750.0f) + 0.5f);
    }

    public static Point c() {
        try {
            Object systemService = JdSdk.getInstance().getApplication().getSystemService("window");
            com.jingdong.app.mall.home.o.a.f.n(systemService);
            WindowManager windowManager = (WindowManager) systemService;
            if (windowManager != null) {
                Point point2 = new Point();
                windowManager.getDefaultDisplay().getSize(point2);
                return point2;
            }
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(d.class, e2);
        }
        return new Point(DPIUtil.getWidth(), DPIUtil.getHeight());
    }

    public static int d(int i2) {
        try {
            try {
                f9276c.readLock().lock();
                int i3 = (i2 >= 128 || i2 <= 0) ? d.get(i2) : b[i2];
                if (i3 > 0) {
                    return i3;
                }
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.s0(d.class, e2);
            }
            f9276c.readLock().unlock();
            int a2 = a(i2, f9279g);
            try {
                try {
                    f9276c.writeLock().lock();
                    if (i2 < 128 && i2 > 0) {
                        b[i2] = a2;
                    } else {
                        d.put(i2, a2);
                    }
                } catch (Exception e3) {
                    com.jingdong.app.mall.home.o.a.f.s0(d.class, e3);
                }
                return a2;
            } finally {
                f9276c.writeLock().unlock();
            }
        } finally {
            f9276c.readLock().unlock();
        }
    }

    public static boolean e(int i2) {
        return i2 > 0 && i2 != f9279g;
    }

    private static void f(int i2) {
        for (Map.Entry<Class, a> entry : f9277e.entrySet()) {
            if (entry != null) {
                entry.getValue().onScreenChanged(i2);
            }
        }
    }

    public static void g(Activity activity) {
        if (activity == null) {
            return;
        }
        h(DpiUtil.getAppWidth(activity), DpiUtil.getHeight(activity));
    }

    public static boolean h(int i2, int i3) {
        if (i2 > 0 && i2 != f9279g) {
            try {
                com.jingdong.app.mall.home.o.a.f.r0(a, "preWidth: " + f9279g + "  currentWidth: " + i2);
                f9276c.writeLock().lock();
                boolean z = i2 != f9279g;
                f9279g = i2;
                if (i2 == DPIUtil.getWidth()) {
                    i3 = DPIUtil.getHeight();
                }
                f9278f = i3;
                if (z) {
                    b = new int[128];
                    d.clear();
                    f(i2);
                }
                return z;
            } finally {
                f9276c.writeLock().unlock();
            }
        }
        f9278f = i3;
        return false;
    }

    public static int i(int i2) {
        return f9279g <= 0 ? i2 : (int) (((i2 * R2.attr.decimalNumber) / f9279g) + 0.5f);
    }
}
