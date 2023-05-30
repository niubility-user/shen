package com.jd.lib.cashier.sdk.pay.aac.livedata.a;

import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoPayPlanResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class b {
    @Nullable
    private final p a;
    @NotNull
    private final BaiTiaoPayPlanResponse b;

    public b(@Nullable p pVar, @NotNull BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        this.a = pVar;
        this.b = baiTiaoPayPlanResponse;
    }

    @NotNull
    public final BaiTiaoPayPlanResponse a() {
        return this.b;
    }

    @Nullable
    public final p b() {
        return this.a;
    }
}
