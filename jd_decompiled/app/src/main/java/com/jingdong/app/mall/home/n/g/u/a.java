package com.jingdong.app.mall.home.n.g.u;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;

/* loaded from: classes4.dex */
public abstract class a extends c {
    public a(JDJSONObject jDJSONObject, com.jingdong.app.mall.home.n.a aVar) {
        super(jDJSONObject, aVar);
    }

    public void C(JDJSONObject jDJSONObject) {
        this.a.w(jDJSONObject, true);
        this.a.s(getJsonInt("cardType") == 2 ? "Category_Selected_Content_Expo" : "Category_Selected_Product_Expo", "\u7011\u5e03\u6d41sku");
        this.a.p(getJsonInt("cardType") == 2 ? "Category_Selected_Content" : "Category_Selected_Product");
        this.a.u(getJsonString("srvJson", "{}"));
        this.a.D("pdLoc", String.valueOf(h()));
        com.jingdong.app.mall.home.n.g.v.c cVar = this.a;
        cVar.a(cVar);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public final int getFloorHeight() {
        return -2;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public final boolean m() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean n() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public final void p(CategoryEntity.CaItem caItem) {
        super.p(caItem);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public final void x(int i2, int i3, int i4, int i5) {
        super.x(i2, 0, i2, i2 << 1);
    }
}
