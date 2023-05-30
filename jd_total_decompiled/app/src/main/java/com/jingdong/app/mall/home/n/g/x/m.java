package com.jingdong.app.mall.home.n.g.x;

import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class m extends com.jingdong.app.mall.home.n.g.u.f {
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    private final int[] f10430k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    private String f10431l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    private String f10432m;

    public m(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
        this.f10430k = new int[2];
    }

    @Override // com.jingdong.app.mall.home.n.g.u.f, com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.p("Category_Main_UnionRanking_List");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.f, com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        this.f10431l = getJsonString("bgImg");
        int[] p = com.jingdong.app.mall.home.floor.common.i.m.p(getJsonString(DYConstants.DY_BG_COLOR), 0, false);
        if (p != null) {
            if (!(p.length == 0)) {
                int[] iArr = this.f10430k;
                iArr[0] = p[0];
                iArr[1] = p.length > 1 ? p[1] : p[0];
                getJsonString("name");
                this.f10366e = getJsonString("rankName");
                this.f10432m = getJsonString("saleNumText");
            }
        }
        int[] iArr2 = this.f10430k;
        int i2 = (int) 4279900698L;
        iArr2[0] = i2;
        iArr2[1] = i2;
        getJsonString("name");
        this.f10366e = getJsonString("rankName");
        this.f10432m = getJsonString("saleNumText");
    }

    @Nullable
    public final String u() {
        return this.f10431l;
    }

    @NotNull
    public final int[] v() {
        return this.f10430k;
    }

    @Nullable
    public final String w() {
        return this.f10432m;
    }
}
