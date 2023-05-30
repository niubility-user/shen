package com.jingdong.app.mall.home.deploy.view.base;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.p.b.d.d;
import com.jingdong.app.mall.home.r.e.h;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public abstract class BaseModel<V extends View> {
    private boolean b;

    /* renamed from: c */
    private boolean f8874c;
    private boolean d;

    /* renamed from: f */
    protected d f8876f;

    /* renamed from: g */
    protected com.jingdong.app.mall.home.p.b.e.b f8877g;

    /* renamed from: h */
    protected h f8878h;

    /* renamed from: i */
    protected com.jingdong.app.mall.home.p.b.e.a f8879i;

    /* renamed from: j */
    protected f f8880j;

    /* renamed from: k */
    protected String f8881k;

    /* renamed from: l */
    protected V f8882l;
    private final AtomicBoolean a = new AtomicBoolean(false);

    /* renamed from: e */
    protected Map<String, Object> f8875e = new ConcurrentHashMap();

    private void B(com.jingdong.app.mall.home.p.b.e.a aVar) {
        JDJSONObject jDJSONObject = aVar.srcJson;
        for (String str : jDJSONObject.keySet()) {
            this.f8875e.put(str, jDJSONObject.get(str));
        }
    }

    private float[] d(int[] iArr) {
        if (iArr == null || iArr.length < 4) {
            return null;
        }
        return new float[]{iArr[0], iArr[0], iArr[1], iArr[1], iArr[2], iArr[2], iArr[3], iArr[3]};
    }

    private void x(com.jingdong.app.mall.home.p.b.e.a aVar) {
        com.jingdong.app.mall.home.p.b.e.a g2 = aVar.g();
        if (g2 != null) {
            x(g2);
        }
        B(aVar);
    }

    private void z(b bVar) {
        Object obj = this.f8875e.get("child");
        if (obj instanceof JDJSONArray) {
            bVar.f((JDJSONArray) obj);
        }
    }

    protected void A(View view) {
        int[] j2 = j("wh", 0);
        if (j2 != null && j2.length > 1) {
            this.f8880j.R(j2[0], j2[1]);
        }
        int[] j3 = j("mar", 0);
        if (j3 != null && j3.length > 3) {
            this.f8880j.E(j3[0], j3[1], j3[2], j3[3]);
        }
        int[] j4 = j("pad", 0);
        if (j4 != null && j4.length > 3) {
            this.f8880j.J(j4[0], j4[1], j4[2], j4[3]);
        }
        int c2 = c(DYConstants.DY_BG_COLOR);
        if (c2 != 0) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(c2);
            float[] d = d(j("bgRou", 0));
            if (d != null) {
                gradientDrawable.setCornerRadii(d);
            }
            view.setBackgroundDrawable(gradientDrawable);
            return;
        }
        view.setBackgroundColor(0);
    }

    protected abstract void C();

    public void D(b bVar, com.jingdong.app.mall.home.p.b.e.a aVar) {
        this.f8879i = aVar;
        x(aVar);
        com.jingdong.app.mall.home.o.a.f.r0("DEPLOY_Parser", "mNodeMap = " + this.f8875e.toString());
        z(bVar);
        this.f8881k = String.valueOf(this.f8875e.get("id"));
    }

    public void E(View view, com.jingdong.app.mall.home.p.b.e.b bVar) {
        RelativeLayout.LayoutParams layoutParams;
        if (view instanceof RelativeLayout) {
            RelativeLayout.LayoutParams u = this.f8880j.u(this.f8882l);
            a.c(q("loc"), u);
            layoutParams = u;
        } else if (view instanceof LinearLayout) {
            LinearLayout.LayoutParams i2 = this.f8880j.i(this.f8882l);
            a.b("", i2);
            layoutParams = i2;
        } else {
            layoutParams = null;
        }
        if (layoutParams != null) {
            this.f8882l.setLayoutParams(layoutParams);
            m.b((ViewGroup) view, this.f8882l, -1);
        }
    }

    public void F(boolean z) {
        this.f8874c = z;
    }

    public void G(d dVar) {
        this.f8876f = dVar;
    }

    public void H(boolean z) {
        this.b = z;
    }

    public void I(boolean z) {
        this.d = z;
    }

    public void a(b bVar, View view, com.jingdong.app.mall.home.p.b.e.b bVar2) {
        if (!(view instanceof ViewGroup) || bVar2 == null || this.f8879i == null) {
            return;
        }
        try {
            this.f8877g = bVar2;
            this.f8878h = bVar2.d();
            bVar2.a(this.f8881k, bVar);
            this.f8882l = (V) bVar.d();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.f8882l == null) {
            return;
        }
        if (this.f8880j == null) {
            this.f8880j = new f(0, 0);
        }
        if (!this.a.getAndSet(true)) {
            C();
        }
        A(this.f8882l);
        E(view, bVar2);
        y(this.f8882l);
    }

    public boolean b(String str) {
        return com.jingdong.app.mall.home.p.b.f.a.a(q(str));
    }

    public int c(String str) {
        return com.jingdong.app.mall.home.p.b.f.a.b(q(str));
    }

    public com.jingdong.app.mall.home.r.e.f e() {
        return this.f8877g.c(this.f8879i.d());
    }

    public String f(String str) {
        com.jingdong.app.mall.home.r.e.f c2 = this.f8877g.c(this.f8879i.d());
        return c2 == null ? "" : c2.getJsonString(str);
    }

    public h g() {
        return this.f8878h;
    }

    public int h() {
        com.jingdong.app.mall.home.p.b.e.a aVar = this.f8879i;
        if (aVar == null) {
            return 0;
        }
        return aVar.c();
    }

    public int i(String str, int i2) {
        return com.jingdong.app.mall.home.p.b.f.a.c(q(str), i2);
    }

    @Nullable
    public int[] j(String str, int i2) {
        return com.jingdong.app.mall.home.p.b.f.a.d(q(str), i2);
    }

    public d k() {
        return this.f8876f;
    }

    public f l() {
        return this.f8880j;
    }

    public int m(int i2, int i3) {
        return t() ? i3 : i2;
    }

    public com.jingdong.app.mall.home.p.b.e.a n() {
        return this.f8879i;
    }

    public com.jingdong.app.mall.home.p.b.e.b o() {
        return this.f8877g;
    }

    public int p() {
        com.jingdong.app.mall.home.p.b.e.a aVar = this.f8879i;
        if (aVar == null) {
            return 0;
        }
        return aVar.h();
    }

    public String q(String str) {
        Object obj = this.f8875e.get(str);
        if (obj == null) {
            return "";
        }
        com.jingdong.app.mall.home.o.a.f.n(obj);
        return (String) obj;
    }

    public boolean r() {
        return this.f8874c;
    }

    public boolean s() {
        com.jingdong.app.mall.home.p.b.e.b bVar = this.f8877g;
        return bVar != null && bVar.f();
    }

    public boolean t() {
        return h() == 2;
    }

    public boolean u() {
        return this.b;
    }

    public boolean v() {
        return p() == 1;
    }

    public boolean w() {
        return this.d;
    }

    protected abstract void y(V v);
}
