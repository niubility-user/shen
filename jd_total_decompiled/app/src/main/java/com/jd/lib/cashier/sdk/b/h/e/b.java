package com.jd.lib.cashier.sdk.b.h.e;

import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.freindpay.aac.viewmodel.FriendPayViewModel;
import com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class b extends com.jd.lib.cashier.sdk.core.utils.a {
    @Override // com.jd.lib.cashier.sdk.core.utils.a
    public <T extends FragmentActivity> void a(@NotNull T t) {
        FriendPayViewModel x;
        if (g0.a(t)) {
            FriendPayActivity friendPayActivity = (FriendPayActivity) (!(t instanceof FriendPayActivity) ? null : t);
            if (friendPayActivity == null || (x = friendPayActivity.x()) == null) {
                return;
            }
            x.d(t);
        }
    }
}
