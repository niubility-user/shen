package com.jd.lib.cashier.sdk.pay.aac.livedata.a;

import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoCouponResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class a {
    @Nullable
    private final Boolean a;
    @NotNull
    private final BaiTiaoCouponResponse b;

    public a(@Nullable Boolean bool, @NotNull BaiTiaoCouponResponse baiTiaoCouponResponse) {
        this.a = bool;
        this.b = baiTiaoCouponResponse;
    }

    @NotNull
    public final BaiTiaoCouponResponse a() {
        return this.b;
    }

    @Nullable
    public final Boolean b() {
        return this.a;
    }
}
