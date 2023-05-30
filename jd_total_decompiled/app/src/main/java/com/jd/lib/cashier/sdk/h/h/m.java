package com.jd.lib.cashier.sdk.h.h;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.h.h.e;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.AllCoupons;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.convert.PaymentChoseHolder;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CommonCouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponAndCutOffs;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCard;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPlan;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyPayEntity;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class m {
    public static final void a(@NotNull FragmentActivity fragmentActivity, @Nullable Payment payment) {
        if (payment != null) {
            c(fragmentActivity);
            g(fragmentActivity, payment);
            if (g.c(payment.code)) {
                f(fragmentActivity, payment);
            } else if (g.a(payment.code)) {
                o(fragmentActivity, payment);
                d(fragmentActivity, payment);
            } else if (Intrinsics.areEqual(CashierPayChannelCode.JD_PAY_CREDIT, payment.code)) {
                k(fragmentActivity, payment);
            } else if (g.h(payment.code)) {
                n(fragmentActivity, payment.accountCode);
            } else if (Intrinsics.areEqual(CashierPayChannelCode.JD_PAY_JINCAI, payment.code)) {
                r(fragmentActivity, payment.productCode);
            } else if (Intrinsics.areEqual("cyberMoney", payment.code)) {
                l(fragmentActivity, payment);
            }
        }
    }

    @Nullable
    public static final PaymentChoseHolder b(@NotNull FragmentActivity fragmentActivity) {
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            return a.copy();
        }
        return null;
    }

    private static final void c(@NotNull FragmentActivity fragmentActivity) {
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        com.jd.lib.cashier.sdk.h.c.b bVar;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null || (bVar = b.Q) == null) {
            return;
        }
        bVar.e(new PaymentChoseHolder());
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x009d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void d(@NotNull FragmentActivity fragmentActivity, Payment payment) {
        String str;
        Iterator it;
        Object obj;
        PlanFeeEntity planFeeEntity;
        String planInfo;
        String str2;
        String str3;
        String str4;
        if (payment != null) {
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = payment.selectedCouponEntity;
            if (!(eVar instanceof CouponEntity)) {
                eVar = null;
            }
            CouponEntity couponEntity = (CouponEntity) eVar;
            if (couponEntity == null) {
                couponEntity = payment.defaultCoupon;
            }
            String str5 = "";
            if (couponEntity == null || (str = couponEntity.getPayMarketingUUID()) == null) {
                str = "";
            }
            if (!TextUtils.equals(str, "doNotUse")) {
                if (couponEntity == null || (str2 = couponEntity.getPayMarketingUUID()) == null) {
                    str2 = "";
                }
                if (!TextUtils.equals(str2, "empty")) {
                    if (!TextUtils.equals(couponEntity != null ? couponEntity.getPayMarketingUUID() : null, "combination")) {
                        if (couponEntity == null || (str3 = couponEntity.getCouponId()) == null) {
                            str3 = "";
                        }
                        if (couponEntity == null || (str4 = couponEntity.getActivityId()) == null) {
                            str4 = "";
                        }
                        q(fragmentActivity, str3, str4);
                        List<IPlanItemViewEntity> list = payment.planFeeEntityList;
                        Intrinsics.checkExpressionValueIsNotNull(list, "currentSelectedPayment.planFeeEntityList");
                        ArrayList arrayList = new ArrayList();
                        for (Object obj2 : list) {
                            if (obj2 instanceof PlanFeeEntity) {
                                arrayList.add(obj2);
                            }
                        }
                        it = arrayList.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it.next();
                            if (((PlanFeeEntity) obj).getSelected()) {
                                break;
                            }
                        }
                        planFeeEntity = (PlanFeeEntity) obj;
                        if (planFeeEntity == null) {
                            planFeeEntity = payment.defaultPlanFee;
                        }
                        if (planFeeEntity != null || (r8 = planFeeEntity.getPlan()) == null) {
                            String str6 = "";
                        }
                        if (planFeeEntity != null && (planInfo = planFeeEntity.getPlanInfo()) != null) {
                            str5 = planInfo;
                        }
                        p(fragmentActivity, str6, str5, planFeeEntity != null ? planFeeEntity.getTradeMap() : null);
                        n nVar = n.b;
                        r.b(nVar.b(), "currentBaiTiaoCoupon = " + couponEntity);
                        r.b(nVar.b(), "currentBaiTiaoPlanFee = " + planFeeEntity);
                    }
                }
            }
            q(fragmentActivity, "", "");
            List<IPlanItemViewEntity> list2 = payment.planFeeEntityList;
            Intrinsics.checkExpressionValueIsNotNull(list2, "currentSelectedPayment.planFeeEntityList");
            ArrayList arrayList2 = new ArrayList();
            while (r3.hasNext()) {
            }
            it = arrayList2.iterator();
            while (true) {
                if (it.hasNext()) {
                }
            }
            planFeeEntity = (PlanFeeEntity) obj;
            if (planFeeEntity == null) {
            }
            if (planFeeEntity != null) {
            }
            String str62 = "";
            if (planFeeEntity != null) {
                str5 = planInfo;
            }
            p(fragmentActivity, str62, str5, planFeeEntity != null ? planFeeEntity.getTradeMap() : null);
            n nVar2 = n.b;
            r.b(nVar2.b(), "currentBaiTiaoCoupon = " + couponEntity);
            r.b(nVar2.b(), "currentBaiTiaoPlanFee = " + planFeeEntity);
        }
    }

    public static final void e(@NotNull FragmentActivity fragmentActivity, @Nullable String str, @Nullable String str2) {
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            a.payMarketingUUID = str;
            a.prizeId = str2;
        }
    }

    private static final void f(@NotNull FragmentActivity fragmentActivity, Payment payment) {
        List<CouponAndCutOffs> list;
        Object obj;
        if (payment != null) {
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = payment.selectedCouponEntity;
            if (!(eVar instanceof CommonCouponEntity)) {
                eVar = null;
            }
            CommonCouponEntity commonCouponEntity = (CommonCouponEntity) eVar;
            if (commonCouponEntity != null) {
                if (!TextUtils.equals(commonCouponEntity.getPayMarketingUUID(), "doNotUse") && !TextUtils.equals(commonCouponEntity.getPayMarketingUUID(), "empty") && !TextUtils.equals(commonCouponEntity.getPayMarketingUUID(), "combination")) {
                    e(fragmentActivity, commonCouponEntity.getPayMarketingUUID(), commonCouponEntity.getPrizeId());
                    return;
                } else {
                    e(fragmentActivity, "", "");
                    return;
                }
            }
            AllCoupons allCoupons = payment.allCoupons;
            if (allCoupons != null && (list = allCoupons.couponAndCutOffs) != null && list.size() > 0) {
                List<CouponAndCutOffs> list2 = payment.allCoupons.couponAndCutOffs;
                Intrinsics.checkExpressionValueIsNotNull(list2, "currentSelectedPayment.allCoupons.couponAndCutOffs");
                Iterator<T> it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (Intrinsics.areEqual(((CouponAndCutOffs) obj).getPayMarketingUUID(), payment.defaultCouponId)) {
                        break;
                    }
                }
                CouponAndCutOffs couponAndCutOffs = (CouponAndCutOffs) obj;
                e(fragmentActivity, couponAndCutOffs != null ? couponAndCutOffs.payMarketingUUID : null, couponAndCutOffs != null ? couponAndCutOffs.prizeId : null);
                return;
            }
            e(fragmentActivity, "", "");
        }
    }

    private static final void g(@NotNull FragmentActivity fragmentActivity, Payment payment) {
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            e.a aVar = e.a;
            a.channelId = aVar.d(payment);
            a.uniqueChannelId = aVar.f(payment);
            a.channelStatus = payment.status;
            a.channelType = payment.code;
            a.jdPayChannel = payment.jdPayChannel;
            a.changetag = payment.changetag;
            a.canUsePaymentAcc = payment.canUsePaymentAcc;
        }
    }

    public static final void h(@NotNull FragmentActivity fragmentActivity, @Nullable String str, boolean z, @NotNull String str2) {
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            a.bankCode = str;
            a.isNewCard = z;
            a.channelId = str2;
        }
    }

    private static final void i(@NotNull FragmentActivity fragmentActivity, String str, String str2) {
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            a.bankPlanRate = str;
            a.merchantFeeSubSideBy = str2;
        }
    }

    public static final void j(@NotNull FragmentActivity fragmentActivity, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Map<String, String> map) {
        p(fragmentActivity, str, str2, map);
        i(fragmentActivity, str3, str4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x002e, code lost:
        if (r0 != null) goto L21;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void k(@NotNull FragmentActivity fragmentActivity, Payment payment) {
        ?? r2;
        Object obj;
        String str;
        String activityId;
        String str2;
        String str3;
        String str4;
        String str5;
        if (payment != null) {
            CreditCard creditCard = payment.currentCreditCardBank;
            if (creditCard == null) {
                List<CreditCard> list = payment.bindingCardArray;
                if (list != null) {
                    Iterator it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            r2 = 0;
                            break;
                        }
                        r2 = it.next();
                        if (((CreditCard) r2).selected) {
                            break;
                        }
                    }
                    creditCard = r2;
                }
                creditCard = new CreditCard();
            } else if (creditCard == null) {
                creditCard = new CreditCard();
            }
            r.b(n.b.b(), "currentCreditCard = " + creditCard);
            String str6 = creditCard.bankCode;
            String str7 = "";
            if (str6 == null) {
                str6 = "";
            }
            boolean z = creditCard.cardAd;
            String str8 = creditCard.channelId;
            if (str8 == null) {
                str8 = "";
            }
            h(fragmentActivity, str6, z, str8);
            List<IPlanItemViewEntity> list2 = payment.planFeeEntityList;
            Intrinsics.checkExpressionValueIsNotNull(list2, "currentSelectedPayment.planFeeEntityList");
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : list2) {
                if (obj2 instanceof CreditCardPlan) {
                    arrayList.add(obj2);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                Boolean bool = ((CreditCardPlan) obj).selected;
                Intrinsics.checkExpressionValueIsNotNull(bool, "it.selected");
                if (bool.booleanValue()) {
                    break;
                }
            }
            CreditCardPlan creditCardPlan = (CreditCardPlan) obj;
            n nVar = n.b;
            r.b(nVar.b(), "creditCard currentCreditCardPlan = " + creditCardPlan);
            j(fragmentActivity, (creditCardPlan == null || (str5 = creditCardPlan.plan) == null) ? "" : str5, (creditCardPlan == null || (str4 = creditCardPlan.planInfo) == null) ? "" : str4, (creditCardPlan == null || (str3 = creditCardPlan.bankPlanRate) == null) ? "" : str3, (creditCardPlan == null || (str2 = creditCardPlan.merchantFeeSubSideBy) == null) ? "" : str2, creditCardPlan != null ? creditCardPlan.tradeMap : null);
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = payment.selectedCouponEntity;
            CouponEntity couponEntity = eVar instanceof CouponEntity ? eVar : null;
            r.b(nVar.b(), "creditCard currentCouponEntity = " + couponEntity);
            if (couponEntity == null || (str = couponEntity.getCouponId()) == null) {
                str = "";
            }
            if (couponEntity != null && (activityId = couponEntity.getActivityId()) != null) {
                str7 = activityId;
            }
            q(fragmentActivity, str, str7);
        }
    }

    private static final void l(@NotNull FragmentActivity fragmentActivity, Payment payment) {
        DigitalMoneyBankCard digitalMoneyBankCard;
        String str;
        DigitalMoneyPayEntity digitalMoneyPayEntity;
        DigitalMoneyPayEntity digitalMoneyPayEntity2;
        DigitalMoneyPayEntity digitalMoneyPayEntity3;
        List<DigitalMoneyBankCard> list;
        Object obj;
        if (payment == null || (digitalMoneyPayEntity3 = payment.virtualPayModel) == null || (list = digitalMoneyPayEntity3.bankCardList) == null) {
            digitalMoneyBankCard = null;
        } else {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((DigitalMoneyBankCard) obj).selected) {
                    break;
                }
            }
            digitalMoneyBankCard = (DigitalMoneyBankCard) obj;
        }
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            a.jumpApp = (payment == null || (digitalMoneyPayEntity2 = payment.virtualPayModel) == null) ? null : digitalMoneyPayEntity2.jumpApp;
            if (digitalMoneyBankCard == null || (str = digitalMoneyBankCard.payToken) == null) {
                str = "";
            }
            a.payToken = str;
            a.requireUUID = (payment == null || (digitalMoneyPayEntity = payment.virtualPayModel) == null) ? null : digitalMoneyPayEntity.requireUUID;
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = digitalMoneyBankCard != null ? digitalMoneyBankCard.selectedCoupon : null;
            CyberMoneyCouponEntity cyberMoneyCouponEntity = eVar instanceof CyberMoneyCouponEntity ? eVar : null;
            if (cyberMoneyCouponEntity != null) {
                if (!TextUtils.equals(cyberMoneyCouponEntity.getPayMarketingUUID(), "doNotUse") && !TextUtils.equals(cyberMoneyCouponEntity.getPayMarketingUUID(), "empty")) {
                    e(fragmentActivity, cyberMoneyCouponEntity.getPayMarketingUUID(), cyberMoneyCouponEntity.prizeId);
                } else {
                    e(fragmentActivity, "", "");
                }
            }
        }
    }

    public static final void m(@NotNull FragmentActivity fragmentActivity) {
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null) {
            return;
        }
        PaymentChoseHolder b2 = b.Q.b();
        b2.appId = b.b;
        b2.from = b.D;
        if (TextUtils.isEmpty(b2.requireUUID)) {
            CashierPayEntity cashierPayEntity = b.K;
            b2.requireUUID = cashierPayEntity != null ? cashierPayEntity.requireUUID : null;
        }
        if (!TextUtils.isEmpty(b.d())) {
            b2.groupOrders = b.d();
        }
        if (TextUtils.isEmpty(b.f3513g)) {
            return;
        }
        b2.combinedOrderId = b.f3513g;
    }

    private static final void n(@NotNull FragmentActivity fragmentActivity, String str) {
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            a.accountCode = str;
        }
    }

    private static final void o(@NotNull FragmentActivity fragmentActivity, Payment payment) {
        String str;
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            if (payment == null || (str = payment.combineType) == null) {
                str = "";
            }
            a.combineType = str;
        }
    }

    public static final void p(@NotNull FragmentActivity fragmentActivity, @Nullable String str, @Nullable String str2, @Nullable Map<String, String> map) {
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            a.planInfo = str2;
            a.planId = str;
            a.tradeMap = map;
        }
    }

    public static final void q(@NotNull FragmentActivity fragmentActivity, @Nullable String str, @Nullable String str2) {
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            a.couponId = str;
            a.activityId = str2;
        }
    }

    private static final void r(@NotNull FragmentActivity fragmentActivity, String str) {
        PaymentChoseHolder a = n.a(fragmentActivity);
        if (a != null) {
            a.productCode = str;
        }
    }
}
