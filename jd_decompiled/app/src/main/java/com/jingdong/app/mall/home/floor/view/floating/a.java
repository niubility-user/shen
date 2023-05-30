package com.jingdong.app.mall.home.floor.view.floating;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes4.dex */
public abstract class a {
    protected String a = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str, String str2, int i2, long j2) {
        float f2;
        int i3;
        float currentTimeMillis;
        try {
            String[] split = TextUtils.split(f.u(str, ""), "##");
            if (split.length <= 2 || !TextUtils.equals(str2, split[0])) {
                f2 = 0.0f;
                i3 = 1;
            } else {
                i3 = f.t0(split[1], 0) + 1;
                f2 = Float.parseFloat(split[2]);
            }
            currentTimeMillis = (float) (System.currentTimeMillis() / 3600000);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (f2 > 0.0f && j2 > 0 && currentTimeMillis - f2 > ((float) j2)) {
            f.x0(str, "");
            return true;
        } else if (i3 > i2) {
            return false;
        } else {
            f.x0(str, str2.concat("##").concat(i3 + "").concat("##").concat(currentTimeMillis + ""));
            return true;
        }
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean d(String str) {
        this.a = str;
        return true;
    }

    public abstract boolean e();

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f(String str, String str2, int i2, long j2) {
        int i3;
        float f2;
        if (i2 <= 0) {
            return true;
        }
        try {
            String[] split = TextUtils.split(f.u(str, ""), "##");
            if (split.length <= 2 || !TextUtils.equals(str2, split[0])) {
                i3 = 0;
                f2 = 0.0f;
            } else {
                i3 = Integer.parseInt(split[1]);
                f2 = Float.parseFloat(split[2]);
            }
            if (f2 <= 0.0f || j2 <= 0 || ((float) (System.currentTimeMillis() / 3600000)) - f2 <= ((float) j2)) {
                return i3 < i2;
            }
            f.x0(str, "");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String g();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void h(h hVar, View view, com.jingdong.app.mall.home.floor.common.f fVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void i(h hVar, FloatLayout floatLayout);

    public boolean j(a aVar) {
        return this == aVar;
    }

    public boolean k() {
        return true;
    }

    public boolean l(String str) {
        return com.jingdong.app.mall.home.o.a.d.b(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract float m(float f2, float f3, float f4);

    public void n(FloatLayout floatLayout) {
    }

    public void o(boolean z, View view) {
    }

    public void p() {
    }

    public void q(FloatLayout floatLayout, int i2, boolean z) {
    }

    public void r(FloatLayout floatLayout) {
        RelativeLayout u = floatLayout.u();
        if (u != null) {
            u.setAlpha(1.0f);
        }
        SimpleDraweeView v = floatLayout.v();
        if (v != null) {
            v.setRotation(0.0f);
            v.setTranslationX(0.0f);
            v.setAlpha(1.0f);
            v.setScaleX(1.0f);
            v.setScaleY(1.0f);
        }
        SimpleDraweeView r = floatLayout.r();
        if (r != null) {
            r.animate().cancel();
            r.setAlpha(1.0f);
        }
    }

    public void s(String str, String str2, FloatLayout floatLayout) {
        String jSONObject;
        new com.jingdong.app.mall.home.q.a("\u6d6e\u5c42Icon\u66dd\u5149", true, str).b();
        String str3 = "1";
        String str4 = "A";
        String concat = (floatLayout.A ? "1" : "0").concat(CartConstant.KEY_YB_INFO_LINK).concat(floatLayout.v ? "A" : g());
        com.jingdong.app.mall.home.r.c.b t = floatLayout.t();
        if (t == null) {
            try {
                t = com.jingdong.app.mall.home.r.c.b.c(null);
            } catch (Exception e2) {
                f.s0(this, e2);
                jSONObject = "";
            }
        }
        if (!floatLayout.A) {
            str3 = "0";
        }
        t.a("isclose", str3);
        if (!floatLayout.v) {
            str4 = g();
        }
        t.a("status", str4);
        jSONObject = t.toString();
        com.jingdong.app.mall.home.r.c.a.A("Home_FloatingFloorExpo", str2, jSONObject, RecommendMtaUtils.Home_PageId, concat);
    }

    public void t(FloatLayout floatLayout, int i2, boolean z) {
    }
}
