package com.jingdong.app.mall.home.floor.bottompop;

import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.r.e.d;

/* loaded from: classes4.dex */
public class b {
    private static a b;
    private BottomPopLayout a;

    public static boolean a() {
        a aVar = b;
        return aVar != null && aVar.c();
    }

    public static void f(d dVar) {
        if (dVar == null || dVar.isCacheData) {
            return;
        }
        try {
            b = new a(dVar.mParentModel.d(0));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b(RelativeLayout relativeLayout) {
        a aVar = b;
        if (aVar == null || !aVar.c() || relativeLayout == null) {
            return;
        }
        try {
            if (this.a == null) {
                this.a = new BottomPopLayout(relativeLayout);
            }
            this.a.k(b);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void c() {
        BottomPopLayout bottomPopLayout = this.a;
        if (bottomPopLayout != null) {
            bottomPopLayout.j();
        }
    }

    public void d(RelativeLayout relativeLayout, boolean z) {
        a aVar = b;
        if (aVar == null || !aVar.c() || z || relativeLayout == null || !b.d()) {
            return;
        }
        b(relativeLayout);
    }

    public void e() {
        m.K(this.a);
        b = null;
    }
}
