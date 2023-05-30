package com.jd.lib.cashier.sdk.h.h;

import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.RecChannel;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class c {
    @JvmStatic
    public static final void a(@Nullable FragmentActivity fragmentActivity, @Nullable CouponEntity couponEntity, @NotNull Function1<? super j, Unit> function1) {
        String str;
        String str2;
        List<CouponEntity> arrayList;
        List<CouponEntity> combinationCouponList;
        boolean areEqual;
        String couponId;
        boolean startsWith$default;
        List<CouponEntity> combinationCouponList2;
        List<CouponEntity> combinationCouponList3;
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        Payment payment = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null) ? null : b.O;
        String str3 = "";
        if (payment == null || (str = e.a.d(payment)) == null) {
            str = "";
        }
        CouponEntity b2 = e.a.b(payment);
        boolean z = false;
        int size = (b2 == null || (combinationCouponList3 = b2.getCombinationCouponList()) == null) ? 0 : combinationCouponList3.size();
        int size2 = (couponEntity == null || (combinationCouponList2 = couponEntity.getCombinationCouponList()) == null) ? 0 : combinationCouponList2.size();
        if (size == size2) {
            try {
                if (size == 0 && size2 == 0) {
                    if (couponEntity != null && (couponId = couponEntity.getCouponId()) != null) {
                        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(couponId, "nothing", false, 2, null);
                        if (startsWith$default) {
                            areEqual = Intrinsics.areEqual(b2 != null ? b2.getActivityId() : null, couponEntity.getActivityId());
                            z = areEqual;
                        }
                    }
                    areEqual = Intrinsics.areEqual(b2 != null ? b2.getCouponId() : null, couponEntity != null ? couponEntity.getCouponId() : null);
                    z = areEqual;
                } else {
                    if (couponEntity == null || (arrayList = couponEntity.getCombinationCouponList()) == null) {
                        arrayList = new ArrayList<>();
                    }
                    int i2 = 0;
                    for (CouponEntity couponEntity2 : arrayList) {
                        if (b2 != null && (combinationCouponList = b2.getCombinationCouponList()) != null) {
                            Iterator<T> it = combinationCouponList.iterator();
                            while (it.hasNext()) {
                                if (Intrinsics.areEqual((CouponEntity) it.next(), couponEntity2)) {
                                    i2++;
                                }
                            }
                        }
                    }
                    if (size == i2) {
                        z = true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        if (payment != null && (str2 = payment.code) != null) {
            str3 = str2;
        }
        function1.invoke(new j(str3, str, z));
    }

    @JvmStatic
    public static final void b(@NotNull FragmentActivity fragmentActivity, @NotNull Function1<? super h, Unit> function1) {
        String payMarketingUUID;
        String str;
        String str2;
        String str3;
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        String d;
        CashierPayViewModel x2;
        com.jd.lib.cashier.sdk.h.c.a b2;
        CashierPayEntity cashierPayEntity;
        String skuId;
        CashierPayViewModel x3;
        com.jd.lib.cashier.sdk.h.c.a b3;
        String str4;
        CashierPayViewModel x4;
        com.jd.lib.cashier.sdk.h.c.a b4;
        CashierPayEntity cashierPayEntity2;
        String checkIsNewUser;
        CashierPayViewModel x5;
        com.jd.lib.cashier.sdk.h.c.a b5;
        FragmentActivity fragmentActivity2 = fragmentActivity;
        boolean z = fragmentActivity2 instanceof CashierPayActivity;
        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!z ? null : fragmentActivity2);
        Payment payment = (cashierPayActivity == null || (x5 = cashierPayActivity.x()) == null || (b5 = x5.b()) == null) ? null : b5.O;
        CashierPayActivity cashierPayActivity2 = (CashierPayActivity) (!z ? null : fragmentActivity2);
        String str5 = (cashierPayActivity2 == null || (x4 = cashierPayActivity2.x()) == null || (b4 = x4.b()) == null || (cashierPayEntity2 = b4.K) == null || (checkIsNewUser = cashierPayEntity2.checkIsNewUser()) == null) ? "" : checkIsNewUser;
        CashierPayActivity cashierPayActivity3 = (CashierPayActivity) (!z ? null : fragmentActivity2);
        String str6 = (cashierPayActivity3 == null || (x3 = cashierPayActivity3.x()) == null || (b3 = x3.b()) == null || (str4 = b3.f3511e) == null) ? "" : str4;
        CashierPayActivity cashierPayActivity4 = (CashierPayActivity) (!z ? null : fragmentActivity2);
        String str7 = (cashierPayActivity4 == null || (x2 = cashierPayActivity4.x()) == null || (b2 = x2.b()) == null || (cashierPayEntity = b2.K) == null || (skuId = cashierPayEntity.getSkuId()) == null) ? "" : skuId;
        String str8 = (payment == null || (d = e.a.d(payment)) == null) ? "" : d;
        if (!z) {
            fragmentActivity2 = null;
        }
        CashierPayActivity cashierPayActivity5 = (CashierPayActivity) fragmentActivity2;
        RecChannel recChannel = (cashierPayActivity5 == null || (x = cashierPayActivity5.x()) == null || (b = x.b()) == null) ? null : b.P;
        com.jd.lib.cashier.sdk.pay.dialog.e c2 = e.a.c(payment);
        function1.invoke(new h((payment == null || (str3 = payment.code) == null) ? "" : str3, str8, Intrinsics.areEqual(payment != null ? payment.status : null, "5"), str5, str6, str7, (recChannel == null || (str2 = recChannel.code) == null) ? "" : str2, (payment == null || (str = payment.changetag) == null) ? "" : str, (c2 == null || (payMarketingUUID = c2.getPayMarketingUUID()) == null) ? "" : payMarketingUUID));
    }

    @JvmStatic
    public static final void c(@Nullable FragmentActivity fragmentActivity, @NotNull Function1<? super i, Unit> function1) {
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        String str;
        CashierPayViewModel x2;
        com.jd.lib.cashier.sdk.h.c.a b2;
        CashierPayEntity cashierPayEntity;
        String checkIsNewUser;
        CashierPayViewModel x3;
        com.jd.lib.cashier.sdk.h.c.a b3;
        CashierPayEntity cashierPayEntity2;
        String str2;
        String d;
        CashierPayViewModel x4;
        com.jd.lib.cashier.sdk.h.c.a b4;
        if (fragmentActivity != null) {
            boolean z = fragmentActivity instanceof CashierPayActivity;
            CashierPayActivity cashierPayActivity = (CashierPayActivity) (!z ? null : fragmentActivity);
            Payment payment = (cashierPayActivity == null || (x4 = cashierPayActivity.x()) == null || (b4 = x4.b()) == null) ? null : b4.O;
            String str3 = (payment == null || (d = e.a.d(payment)) == null) ? "" : d;
            boolean areEqual = Intrinsics.areEqual(payment != null ? payment.status : null, "5");
            boolean g2 = g.g(payment != null ? payment.status : null);
            CashierPayActivity cashierPayActivity2 = (CashierPayActivity) (!z ? null : fragmentActivity);
            String str4 = (cashierPayActivity2 == null || (x3 = cashierPayActivity2.x()) == null || (b3 = x3.b()) == null || (cashierPayEntity2 = b3.K) == null || (str2 = cashierPayEntity2.defaultStrategy) == null) ? "" : str2;
            CashierPayActivity cashierPayActivity3 = (CashierPayActivity) (!z ? null : fragmentActivity);
            String str5 = (cashierPayActivity3 == null || (x2 = cashierPayActivity3.x()) == null || (b2 = x2.b()) == null || (cashierPayEntity = b2.K) == null || (checkIsNewUser = cashierPayEntity.checkIsNewUser()) == null) ? "" : checkIsNewUser;
            if (!z) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity4 = (CashierPayActivity) fragmentActivity;
            String str6 = (cashierPayActivity4 == null || (x = cashierPayActivity4.x()) == null || (b = x.b()) == null || (str = b.f3511e) == null) ? "" : str;
            boolean e2 = e.a.e(payment);
            if (payment != null) {
                try {
                    String str7 = payment.code;
                    function1.invoke(new i(str7 != null ? str7 : "", str3, areEqual, e2, g2, str4, str5, str6));
                } catch (Exception unused) {
                }
            }
        }
    }

    @JvmStatic
    @JvmOverloads
    public static final void d(@Nullable Payment payment, @NotNull Function1<? super k, Unit> function1) {
        String d;
        boolean areEqual = Intrinsics.areEqual(payment != null ? payment.status : null, "5");
        boolean z = !Intrinsics.areEqual(payment != null ? payment.status : null, "3");
        String str = (payment == null || (d = e.a.d(payment)) == null) ? "" : d;
        boolean e2 = e.a.e(payment);
        if (payment != null) {
            try {
                String str2 = payment.code;
                function1.invoke(new k(str2 != null ? str2 : "", str, areEqual, e2, z));
            } catch (Exception unused) {
            }
        }
    }
}
