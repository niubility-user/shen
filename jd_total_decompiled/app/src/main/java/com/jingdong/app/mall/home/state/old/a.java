package com.jingdong.app.mall.home.state.old;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.v.b;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.eldermode.util.OnElderModeChangeListener;

/* loaded from: classes4.dex */
public class a {
    private static boolean a;
    private static boolean b;

    /* renamed from: c */
    private static boolean f10878c;
    private static boolean d;

    /* renamed from: e */
    private static b.a f10879e;

    /* renamed from: com.jingdong.app.mall.home.state.old.a$a */
    /* loaded from: classes4.dex */
    public class C0326a implements OnElderModeChangeListener {
        C0326a() {
        }

        @Override // com.jingdong.sdk.eldermode.util.OnElderModeChangeListener
        public void onElderModeChanged(int i2) {
            a.b();
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ Activity f10880g;

        /* renamed from: h */
        final /* synthetic */ ElderGuideLayout f10881h;

        b(Activity activity, ElderGuideLayout elderGuideLayout) {
            this.f10880g = activity;
            this.f10881h = elderGuideLayout;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.h(this.f10880g, this.f10881h);
        }
    }

    static {
        boolean z = false;
        a = f.M("HOME_ELDER_GUIDE", 0) == 0;
        b = JDElderModeUtils.isElderMode();
        if (f.M("HOME_ELDER_KEY", 0) == 1 && b) {
            z = true;
        }
        f10878c = z;
    }

    public static void b() {
        boolean e2 = e();
        if (f10879e == null || b == e2) {
            return;
        }
        b = e();
        f10879e.b();
    }

    public static void c() {
        f10878c = !f10878c;
        d = true;
        b.a aVar = f10879e;
        if (aVar != null) {
            aVar.b();
        }
    }

    public static int d() {
        return e() ? 1 : 0;
    }

    public static boolean e() {
        if (d) {
            return f10878c;
        }
        return JDElderModeUtils.isElderMode();
    }

    public static boolean f() {
        return f10878c;
    }

    public static boolean g() {
        return a && e() && !com.jingdong.app.mall.home.v.d.a.e() && f.l0();
    }

    public static void h(Activity activity, ElderGuideLayout elderGuideLayout) {
        NavigationButton D;
        if (!JDHomeFragment.O0() || activity == null || Build.VERSION.SDK_INT < 21 || (D = f.D(4)) == null) {
            return;
        }
        View decorView = activity.getWindow().getDecorView();
        if (decorView instanceof ViewGroup) {
            int[] iArr = new int[2];
            D.getLocationOnScreen(iArr);
            int height = D.getHeight() >> 1;
            int width = iArr[0] + (D.getWidth() >> 1);
            int i2 = iArr[1] + height;
            if (width > decorView.getWidth() || i2 > decorView.getHeight()) {
                return;
            }
            elderGuideLayout.e(decorView, width, i2, height - 5);
        }
    }

    public static void i(boolean z) {
        f10878c = z;
        f.y0("HOME_ELDER_KEY", z ? 1 : 0);
    }

    public static void j(b.a aVar) {
        f10879e = aVar;
        JDElderModeUtils.addElderModeChangeListener(new C0326a());
        b();
    }

    public static ElderGuideLayout k(Activity activity) {
        a = false;
        f.y0("HOME_ELDER_GUIDE", 1);
        ElderGuideLayout elderGuideLayout = new ElderGuideLayout(activity);
        f.F0(new b(activity, elderGuideLayout), 500L);
        return elderGuideLayout;
    }
}
