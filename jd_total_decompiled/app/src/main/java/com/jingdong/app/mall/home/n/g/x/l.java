package com.jingdong.app.mall.home.n.g.x;

import android.graphics.Rect;
import com.jd.framework.json.JDJSONObject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class l extends com.jingdong.app.mall.home.n.g.u.e {

    /* renamed from: k */
    private Rect f10426k;

    /* renamed from: l */
    private int f10427l;

    /* renamed from: m */
    private int f10428m;

    /* renamed from: n */
    private int f10429n;

    public l(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
        this.f10429n = -328966;
    }

    private final void x() {
        com.jingdong.app.mall.home.n.g.u.c parentModel = g();
        Intrinsics.checkExpressionValueIsNotNull(parentModel, "parentModel");
        com.jingdong.app.mall.home.n.a f2 = parentModel.f();
        if (f2 != null) {
            int i2 = k.$EnumSwitchMapping$0[f2.ordinal()];
            if (i2 == 1) {
                this.f10426k = new Rect(12, 50, 0, 0);
                return;
            } else if (i2 == 2 || i2 == 3) {
                this.f10429n = -1;
                this.f10426k = new Rect(6, 0, 0, 0);
                return;
            }
        }
        this.f10426k = new Rect(0, 0, 0, 0);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        this.f10428m = com.jingdong.app.mall.home.n.h.c.h(getJsonString("width"), 0);
        this.f10427l = com.jingdong.app.mall.home.n.h.c.h(getJsonString("height"), 0);
        x();
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    public boolean t() {
        return true;
    }

    public final void u(@NotNull com.jingdong.app.mall.home.floor.common.f fVar) {
        fVar.F(this.f10426k);
        int i2 = this.f10427l;
        if (i2 > 0) {
            fVar.C(i2);
        }
    }

    public final int v() {
        return this.f10429n;
    }

    public final int w(int i2) {
        int i3 = this.f10428m;
        return i3 > 0 ? i3 : i2;
    }
}
