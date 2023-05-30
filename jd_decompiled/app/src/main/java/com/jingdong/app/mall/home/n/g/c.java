package com.jingdong.app.mall.home.n.g;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.platform.business.personal.R2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class c extends com.jingdong.app.mall.home.n.g.u.b {
    private int s;
    @Nullable
    private String t;
    @Nullable
    private JumpEntity u;

    public c(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar) {
        super(jDJSONObject, aVar);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean B() {
        return false;
    }

    @Nullable
    public final String G() {
        return this.t;
    }

    @Nullable
    public final JumpEntity H() {
        return this.u;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public int getFloorHeight() {
        return this.s;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean n() {
        return this.s > com.jingdong.app.mall.home.floor.common.d.d(100) && this.s < com.jingdong.app.mall.home.floor.common.d.d(R2.attr.blendSrc) && !TextUtils.isEmpty(this.t);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.t("Category_Main_MarketBanner_Expo", "\u5355\u5e27\u901a\u680f");
        cVar.p("Category_Main_MarketBanner");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        JDJSONArray jDJSONArray = this.q;
        if (jDJSONArray == null || jDJSONArray.size() <= 0) {
            return;
        }
        JDJSONObject jSONObject = this.q.getJSONObject(0);
        this.u = (JumpEntity) com.jingdong.app.mall.home.r.e.b.getObject(jSONObject, "jump", JumpEntity.class);
        this.t = com.jingdong.app.mall.home.r.e.b.getJsonString(jSONObject, "img", "");
        this.s = com.jingdong.app.mall.home.floor.common.d.d(com.jingdong.app.mall.home.n.h.c.h(getJsonString("height"), 0));
    }
}
