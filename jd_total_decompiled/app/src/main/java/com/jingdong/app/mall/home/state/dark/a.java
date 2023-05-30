package com.jingdong.app.mall.home.state.dark;

import com.jingdong.app.mall.home.v.b;
import com.jingdong.common.utils.DeepDarkChangeManager;

/* loaded from: classes4.dex */
public class a {
    private static boolean a;
    private static b.a b;

    /* renamed from: c  reason: collision with root package name */
    private static int f10866c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.state.dark.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0325a implements DeepDarkChangeManager.OnUIModeChangeListener {
        C0325a() {
        }

        @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
        public void onUIModeChanged(int i2) {
            a.b();
        }
    }

    static {
        a = 1 == DeepDarkChangeManager.getInstance().getUIMode();
        f10866c = 419430400;
    }

    public static boolean a() {
        boolean z = DeepDarkChangeManager.getInstance().getUIMode() == 1 && com.jingdong.app.mall.home.v.d.a.h();
        if (z == a || b == null) {
            return false;
        }
        a = z;
        return true;
    }

    public static void b() {
        if (a()) {
            b.c();
        }
    }

    public static void c() {
        DeepDarkChangeManager.getInstance().saveDeepDarkSwitch((1 == DeepDarkChangeManager.getInstance().getUIMode() ? 1 : 0) ^ 1);
        b();
    }

    public static int d() {
        return -15461870;
    }

    public static int e(int i2, int i3) {
        if (i2 == -1) {
            i2 = -1250068;
        }
        return a ? i2 : i3;
    }

    public static int f() {
        return f10866c | 1315346;
    }

    public static int g(boolean z) {
        return (a && z) ? -14869733 : -1;
    }

    public static boolean h() {
        return a;
    }

    public static void i(int i2) {
        if (i2 < 10 || i2 > 30) {
            i2 = 10;
        }
        f10866c = ((i2 * 255) / 100) << 24;
    }

    public static void j(b.a aVar) {
        b = aVar;
        b();
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(new C0325a());
    }
}
