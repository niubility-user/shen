package com.jingdong.app.mall.home.deploy.view.layout.year;

import android.graphics.Rect;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearBaseView;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.o.a.m;
import com.jingdong.app.mall.home.p.b.e.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public abstract class YearModel<V extends YearBaseView> extends BaseModel<V> {
    public static final int[] r = {-15066598, -15066598};
    public static final int[] s = {-381927, -381927};
    public static final int[] t = {-1286, -1286};
    public static final Rect u = new Rect(0, 0, 0, 0);

    /* renamed from: m */
    public f f9101m;

    /* renamed from: n */
    private ArrayList<YearSkuItem> f9102n = new ArrayList<>();
    private String o;
    private JumpEntity p;
    private JumpEntity q;

    private void i0() {
        try {
            JDJSONObject jsonObject = this.f9101m.getJsonObject("banner");
            if (jsonObject == null) {
                return;
            }
            this.o = jsonObject.getString("img");
            JDJSONObject jSONObject = jsonObject.getJSONObject("jump");
            this.q = jSONObject == null ? null : (JumpEntity) jSONObject.toJavaObject(JumpEntity.class);
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
    }

    private void j0() {
        try {
            JDJSONObject jsonObject = this.f9101m.getJsonObject("bgInfo");
            if (jsonObject == null) {
                return;
            }
            JDJSONObject jSONObject = jsonObject.getJSONObject("jump");
            this.p = jSONObject == null ? null : (JumpEntity) jSONObject.toJavaObject(JumpEntity.class);
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    protected void A(View view) {
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        f e2 = e();
        this.f9101m = e2;
        if (e2 == null) {
            return;
        }
        this.f9102n = YearSkuItem.i(e2.getJsonArr(CartConstant.KEY_ITEMS));
        j0();
        i0();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void E(View view, b bVar) {
        b0();
        int[] j2 = j("mar", 0);
        if (j2 != null && j2.length > 3) {
            this.f8880j.E(j2[0], j2[1], j2[2], j2[3]);
        }
        if (((YearBaseView) this.f8882l).getParent() == view) {
            com.jingdong.app.mall.home.floor.common.f.d(this.f8882l, this.f8880j, true);
        } else {
            super.E(view, bVar);
        }
    }

    public String J() {
        return this.o;
    }

    public JumpEntity K() {
        return this.q;
    }

    public JumpEntity L() {
        return this.p;
    }

    public JumpEntity M() {
        return this.f9101m.getJump();
    }

    public YearSkuItem N(int i2) {
        try {
            return this.f9102n.get(Math.min(i2, r0.size() - 1));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String O() {
        return m.a(this.f9101m);
    }

    public int[] P() {
        return com.jingdong.app.mall.home.floor.common.i.m.n(this.f9101m.getJsonString("subTitleBgColor"), t, true);
    }

    public String Q() {
        return this.f9101m.getJsonString("subTitleBgImg");
    }

    public int[] R() {
        return a.e(this.f9101m.getJsonString("subTitleColor"), s);
    }

    public int S() {
        int jsonInt = this.f9101m.getJsonInt("subTitleFontSize", 0);
        if (jsonInt <= 0) {
            jsonInt = 22;
        }
        return Math.max(Math.min(jsonInt, 26), 20);
    }

    public String T() {
        return this.f9101m.getJsonString("tagImg");
    }

    public int U() {
        return this.f9101m.getJsonInt("tagImgWidth");
    }

    public String V() {
        return this.f9101m.getJsonString("title");
    }

    public int[] W() {
        return X(r);
    }

    public int[] X(int[] iArr) {
        return com.jingdong.app.mall.home.floor.common.i.m.n(this.f9101m.getJsonString(Constant.KEY_TITLE_COLOR), iArr, true);
    }

    public String Y() {
        return this.f9101m.getJsonString("titleImg");
    }

    public int Z() {
        h d = this.f8877g.d();
        if (d != null && "1".equals(d.getJsonString("fontShape"))) {
            return this.f9101m.getJsonInt("titleImgWidth");
        }
        return 0;
    }

    public int a0() {
        return m.b(this.f9101m);
    }

    protected abstract void b0();

    public boolean c0() {
        return 1 == this.f9101m.getJsonInt("subTitleBoldSwitch");
    }

    public void d0(View view) {
        JumpEntity K = K();
        if (K == null) {
            f0(view, 10);
        } else {
            l.q(view, K, "", 10, 1);
        }
    }

    public void e0(View view) {
        f0(view, 0);
    }

    public void f0(View view, int i2) {
        JumpEntity L = L();
        if (L == null) {
            g0(view, 0, i2);
        } else {
            l.q(view, L, "", i2, 1);
        }
    }

    public void g0(View view, int i2, int i3) {
        YearSkuItem N = N(i2);
        if (N == null) {
            return;
        }
        JumpEntity d = N.d();
        int i4 = 1;
        if (d == null) {
            d = M();
            i4 = 1 + i2;
        }
        l.q(view, d, N.c(), i3, i4);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseModel
    /* renamed from: h0 */
    public void y(V v) {
        v.b(this);
    }
}
