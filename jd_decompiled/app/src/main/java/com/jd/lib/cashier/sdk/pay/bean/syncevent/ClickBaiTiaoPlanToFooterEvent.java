package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickBaiTiaoPlanToFooterEvent;", "", "", "realPayAmount", "Ljava/lang/String;", "getRealPayAmount", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class ClickBaiTiaoPlanToFooterEvent {
    @Nullable
    private final String realPayAmount;

    public ClickBaiTiaoPlanToFooterEvent(@Nullable String str) {
        this.realPayAmount = str;
    }

    @Nullable
    public final String getRealPayAmount() {
        return this.realPayAmount;
    }
}
