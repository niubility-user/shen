package com.jingdong.app.mall.home.n.g;

import com.jd.framework.json.JDJSONObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class n extends com.jingdong.app.mall.home.n.g.u.b {
    private int s;

    public n(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar) {
        super(jDJSONObject, aVar);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean B() {
        return false;
    }

    public final int G() {
        return this.s;
    }

    public final void H(int i2) {
        this.s = i2;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public int getFloorHeight() {
        int i2 = this.s;
        if (i2 == 2) {
            return com.jingdong.app.mall.home.floor.common.d.d(150);
        }
        return (i2 == 3 || i2 == 4) ? com.jingdong.app.mall.home.n.c.f10328n - com.jingdong.app.mall.home.floor.common.d.d(96) : super.getFloorHeight();
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public void x(int i2, int i3, int i4, int i5) {
        super.w(i2);
    }
}
