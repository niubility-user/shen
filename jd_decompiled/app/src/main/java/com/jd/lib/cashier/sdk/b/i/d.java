package com.jd.lib.cashier.sdk.b.i;

import android.content.Context;
import com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class d {
    @JvmStatic
    public static final void a(@Nullable Context context) {
        if (context == null) {
            return;
        }
        if (context instanceof CashierPayActivity) {
            com.jd.lib.cashier.sdk.b.h.c.a(new com.jd.lib.cashier.sdk.b.h.b((CashierPayActivity) context, new com.jd.lib.cashier.sdk.b.h.e.d()));
        } else if (context instanceof FriendPayActivity) {
            com.jd.lib.cashier.sdk.b.h.c.a(new com.jd.lib.cashier.sdk.b.h.a((FriendPayActivity) context, new com.jd.lib.cashier.sdk.b.h.e.b()));
        }
    }
}
