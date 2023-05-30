package com.jd.lib.cashier.sdk.pay.dialog.k;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.ExitRetainOptionEntity;
import com.jd.lib.cashier.sdk.core.model.GuideInfo;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.dialog.k.e.f;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class b {
    public static final a a = new a(null);

    /* loaded from: classes14.dex */
    public static final class a {
        private a() {
        }

        @JvmStatic
        @NotNull
        public final d a(@NotNull CashierCommonPopConfig cashierCommonPopConfig) {
            GuideInfo guideInfo;
            PopBusinessMap popBusinessMap = cashierCommonPopConfig.businessMap;
            if (popBusinessMap != null) {
                boolean z = true;
                if ((popBusinessMap == null || (guideInfo = popBusinessMap.guideInfo) == null || TextUtils.isEmpty(guideInfo.guideTitle) || TextUtils.isEmpty(guideInfo.guideUrl) || TextUtils.isEmpty(guideInfo.guideOpType)) ? false : true) {
                    return d.STAY_XJK;
                }
                List<ExitRetainOptionEntity> list = popBusinessMap != null ? popBusinessMap.retainBtnList : null;
                if (list != null && !list.isEmpty()) {
                    z = false;
                }
                if (!z) {
                    return d.STAY_DEFAULT;
                }
                return d.COMMON;
            }
            return d.COMMON;
        }

        @JvmStatic
        public final void b(@Nullable FragmentActivity fragmentActivity, @Nullable CashierCommonPopConfig cashierCommonPopConfig, @Nullable com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar) {
            c cVar;
            if (fragmentActivity == null || cashierCommonPopConfig == null || !g0.a(fragmentActivity)) {
                return;
            }
            int i2 = com.jd.lib.cashier.sdk.pay.dialog.k.a.$EnumSwitchMapping$0[a(cashierCommonPopConfig).ordinal()];
            if (i2 == 1) {
                cVar = new com.jd.lib.cashier.sdk.pay.dialog.k.e.c(fragmentActivity);
            } else if (i2 == 2) {
                cVar = new f(fragmentActivity);
            } else if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            } else {
                cVar = new com.jd.lib.cashier.sdk.pay.dialog.k.e.d(fragmentActivity);
            }
            cVar.a(cashierCommonPopConfig, bVar);
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmStatic
    public static final void a(@Nullable FragmentActivity fragmentActivity, @Nullable CashierCommonPopConfig cashierCommonPopConfig, @Nullable com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar) {
        a.b(fragmentActivity, cashierCommonPopConfig, bVar);
    }
}
