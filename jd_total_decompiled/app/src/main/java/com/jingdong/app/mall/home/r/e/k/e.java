package com.jingdong.app.mall.home.r.e.k;

import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class e {
    private String a;
    private JumpEntity b;

    public e(f fVar) {
        this.a = fVar.getJsonString("trendInfo");
        this.b = fVar.getJump();
    }

    public JumpEntity a() {
        return this.b;
    }

    public String b() {
        return this.a;
    }
}
