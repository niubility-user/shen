package com.jd.lib.cashier.sdk.pay.bean.syncevent;

import com.jd.lib.cashier.sdk.pay.bean.RecChannel;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\t\u0010\bR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\n"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ShowRecommendViewEvent;", "", "Lcom/jd/lib/cashier/sdk/pay/bean/RecChannel;", "currentSelectRecChannel", "Lcom/jd/lib/cashier/sdk/pay/bean/RecChannel;", "getCurrentSelectRecChannel", "()Lcom/jd/lib/cashier/sdk/pay/bean/RecChannel;", "setCurrentSelectRecChannel", "(Lcom/jd/lib/cashier/sdk/pay/bean/RecChannel;)V", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class ShowRecommendViewEvent {
    @Nullable
    private RecChannel currentSelectRecChannel;

    public ShowRecommendViewEvent(@Nullable RecChannel recChannel) {
        this.currentSelectRecChannel = recChannel;
    }

    @Nullable
    public final RecChannel getCurrentSelectRecChannel() {
        return this.currentSelectRecChannel;
    }

    public final void setCurrentSelectRecChannel(@Nullable RecChannel recChannel) {
        this.currentSelectRecChannel = recChannel;
    }
}
