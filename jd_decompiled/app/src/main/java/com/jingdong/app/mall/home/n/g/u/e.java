package com.jingdong.app.mall.home.n.g.u;

import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public abstract class e extends com.jingdong.app.mall.home.r.e.b {
    protected com.jingdong.app.mall.home.n.b a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    protected JumpEntity f10365c;
    protected String d;

    /* renamed from: e  reason: collision with root package name */
    protected String f10366e;

    /* renamed from: f  reason: collision with root package name */
    protected com.jingdong.app.mall.home.n.g.v.c f10367f;

    /* renamed from: g  reason: collision with root package name */
    protected int f10368g;

    /* renamed from: h  reason: collision with root package name */
    protected c f10369h;

    /* renamed from: i  reason: collision with root package name */
    protected int f10370i;

    /* renamed from: j  reason: collision with root package name */
    private int f10371j;

    public e(JDJSONObject jDJSONObject, com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject);
        this.f10367f = new com.jingdong.app.mall.home.n.g.v.c();
        this.a = bVar;
        this.f10365c = (JumpEntity) getObject("jump", JumpEntity.class);
        this.d = getJsonString("img");
        this.f10366e = getJsonString("name");
        this.b = getJsonString("iconType");
        this.f10370i = this.a.getFloorWidth();
    }

    public void a(boolean z) {
        com.jingdong.app.mall.home.n.g.v.c e2;
        c cVar = this.f10369h;
        if (cVar == null || (e2 = cVar.e()) == null || e2.A() || t()) {
            return;
        }
        e2.a(this.f10367f);
    }

    public int b() {
        return this.f10370i;
    }

    public String c() {
        return this.b;
    }

    public JumpEntity d() {
        return this.f10365c;
    }

    public int e() {
        return this.f10368g;
    }

    public String f() {
        return null;
    }

    @NonNull
    public c g() {
        c cVar = this.f10369h;
        return cVar == null ? new g(null, com.jingdong.app.mall.home.n.a.C_EMPTY) : cVar;
    }

    public int getFloorHeight() {
        int i2 = this.f10371j;
        return i2 > 0 ? com.jingdong.app.mall.home.floor.common.d.d(i2) : this.a.getFloorHeight();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String h() {
        JumpEntity jumpEntity = this.f10365c;
        return jumpEntity == null ? "" : jumpEntity.getSrvJson();
    }

    public String i() {
        return this.d;
    }

    public String j() {
        return this.f10366e;
    }

    public com.jingdong.app.mall.home.n.g.v.c k() {
        return this.f10367f;
    }

    public com.jingdong.app.mall.home.n.b l() {
        return this.a;
    }

    public boolean m() {
        return true;
    }

    protected abstract void n(com.jingdong.app.mall.home.n.g.v.c cVar);

    public final void o(int i2) {
        this.f10368g = i2;
        this.f10367f.y(this.f10369h, h(), i2);
        p();
        n(this.f10367f);
    }

    protected abstract void p();

    public void q(int i2) {
        this.f10371j = i2;
    }

    public void r(int i2) {
    }

    public final void s(c cVar) {
        this.f10369h = cVar;
    }

    public boolean t() {
        return false;
    }
}
