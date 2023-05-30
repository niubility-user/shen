package com.jingdong.app.mall.home.n.g.u;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;

/* loaded from: classes4.dex */
public abstract class b extends c {

    /* renamed from: l */
    private int f10354l;

    /* renamed from: m */
    private int f10355m;

    /* renamed from: n */
    protected boolean f10356n;
    protected boolean o;
    private CategoryEntity.FloorDecorateInfo p;
    protected JDJSONArray q;
    private com.jingdong.app.mall.home.n.g.w.e r;

    public b(JDJSONObject jDJSONObject, com.jingdong.app.mall.home.n.a aVar) {
        super(jDJSONObject, aVar);
        this.f10363h = aVar;
        if (jDJSONObject == null) {
            return;
        }
        boolean z = "1".equals(getJsonString("showHead")) || "2".equals(getJsonString("showHead")) || "3".equals(getJsonString("showHead"));
        this.f10356n = z;
        if (z) {
            com.jingdong.app.mall.home.n.g.w.e eVar = new com.jingdong.app.mall.home.n.g.w.e(jDJSONObject, this.f10363h);
            this.r = eVar;
            eVar.n(this.a);
            this.r.p(A());
        }
        this.q = getJsonArr("content");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public final boolean A() {
        return this.f10356n && this.o;
    }

    public JDJSONArray C() {
        return this.q;
    }

    public com.jingdong.app.mall.home.n.g.w.e D() {
        return this.r;
    }

    public final void E(int i2, int i3) {
        this.f10354l = i2;
        this.f10355m = i3;
        com.jingdong.app.mall.home.n.g.w.e eVar = this.r;
        if (eVar != null) {
            eVar.o(i2);
        }
    }

    public final boolean F() {
        return this.f10356n;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public String b() {
        CategoryEntity.FloorDecorateInfo floorDecorateInfo = this.p;
        if (floorDecorateInfo == null) {
            return null;
        }
        return floorDecorateInfo.getBottomDecorateUrl();
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public final int i() {
        return (!this.f10356n || this.o) ? this.f10355m : this.f10354l;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public String j() {
        CategoryEntity.FloorDecorateInfo floorDecorateInfo = this.p;
        if (floorDecorateInfo == null) {
            return null;
        }
        return floorDecorateInfo.getTopDecorateUrl();
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public final void p(CategoryEntity.CaItem caItem) {
        this.a.v(this.srcJson);
        super.p(caItem);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public void t(String str) {
        CategoryEntity.DecorateInfo c2 = com.jingdong.app.mall.home.n.e.c();
        if (c2 == null) {
            return;
        }
        CategoryEntity.FloorDecorateInfo decorateByType = c2.getDecorateByType(str);
        this.p = decorateByType;
        com.jingdong.app.mall.home.n.g.w.e eVar = this.r;
        if (eVar != null) {
            eVar.m(decorateByType);
        }
    }
}
