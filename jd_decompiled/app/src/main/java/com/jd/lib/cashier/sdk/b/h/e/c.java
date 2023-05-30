package com.jd.lib.cashier.sdk.b.h.e;

import com.jd.lib.cashier.sdk.freindpay.aac.viewmodel.FriendPayViewModel;
import com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class c extends a<FriendPayActivity> {
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    private final FriendPayActivity f2876c;

    public c(@NotNull FriendPayActivity friendPayActivity) {
        super(friendPayActivity);
        this.f2876c = friendPayActivity;
    }

    @Override // com.jd.lib.cashier.sdk.b.h.e.a
    @NotNull
    public LinkedBlockingQueue<com.jd.lib.cashier.sdk.core.utils.a> e() {
        FriendPayViewModel x = this.f2876c.x();
        Intrinsics.checkExpressionValueIsNotNull(x, "activity.viewModel");
        com.jd.lib.cashier.sdk.f.c.b b = x.b();
        Intrinsics.checkExpressionValueIsNotNull(b, "activity.viewModel.state");
        LinkedBlockingQueue<com.jd.lib.cashier.sdk.core.utils.a> a = b.a();
        Intrinsics.checkExpressionValueIsNotNull(a, "activity.viewModel.state.cashierTaskQueue");
        return a;
    }
}
