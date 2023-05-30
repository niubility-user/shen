package com.jd.lib.cashier.sdk.h.h;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.pay.bean.AllCoupons;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.BaiTiaoPlanInfo;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponAndCutOffs;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCard;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyPayEntity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class e {
    public static final a a = new a(null);

    /* loaded from: classes14.dex */
    public static final class a {
        private a() {
        }

        private final Payment h(List<? extends Payment> list) {
            if (list != null && !list.isEmpty()) {
                for (Payment payment : list) {
                    if (o(payment) && payment.jdPay) {
                        return payment;
                    }
                }
            }
            return null;
        }

        private final Payment i(List<? extends Payment> list) {
            if (list != null && !list.isEmpty()) {
                for (Payment payment : list) {
                    if (o(payment) && payment.jdPay && payment.defaultSelected) {
                        return payment;
                    }
                }
            }
            return null;
        }

        private final Payment j(List<? extends Payment> list) {
            if (list == null || list.isEmpty()) {
                return null;
            }
            for (Payment payment : list) {
                if (o(payment) && payment.jdPay) {
                    return payment;
                }
            }
            return null;
        }

        private final Payment m(List<? extends Payment> list) {
            if (list == null || list.isEmpty()) {
                return null;
            }
            for (Payment payment : list) {
                if (o(payment) && payment.defaultSelected) {
                    return payment;
                }
            }
            return null;
        }

        @JvmStatic
        @NotNull
        public final Payment a(@Nullable CashierPayEntity cashierPayEntity) {
            if (cashierPayEntity == null) {
                return new Payment();
            }
            List<Payment> list = cashierPayEntity.jdPayChannelList;
            List<Payment> list2 = cashierPayEntity.payChannelList;
            List<Payment> list3 = cashierPayEntity.otherPayChannelList;
            List<Payment> list4 = cashierPayEntity.jdOtherPayChannelList;
            ArrayList<Payment> arrayList = new ArrayList();
            if (list != null && (!list.isEmpty()) != false) {
                arrayList.addAll(list);
            }
            if (list2 != null && (!list2.isEmpty()) != false) {
                arrayList.addAll(list2);
            }
            if (list4 != null && (!list4.isEmpty()) != false) {
                arrayList.addAll(list4);
            }
            if (list3 != null && (!list3.isEmpty()) != false) {
                arrayList.addAll(list3);
            }
            if (arrayList.size() > 0) {
                for (Payment payment : arrayList) {
                    if (o(payment) && payment.defaultSelected) {
                        return payment;
                    }
                }
            }
            return new Payment();
        }

        @JvmStatic
        @Nullable
        public final CouponEntity b(@Nullable Payment payment) {
            if (payment != null && g.a(payment.code)) {
                CouponEntity couponEntity = payment.baiTiaoPlanInfoCoupon;
                return couponEntity != null ? couponEntity : payment.defaultCoupon;
            }
            return null;
        }

        @JvmStatic
        @Nullable
        public final com.jd.lib.cashier.sdk.pay.dialog.e c(@Nullable Payment payment) {
            AllCoupons allCoupons;
            List<CouponAndCutOffs> list;
            if (payment != null && g.c(payment.code) && (allCoupons = payment.allCoupons) != null && (list = allCoupons.couponAndCutOffs) != null && list.size() > 0) {
                for (CouponAndCutOffs couponAndCutOffs : payment.allCoupons.couponAndCutOffs) {
                    if (Intrinsics.areEqual(couponAndCutOffs.getPayMarketingUUID(), payment.defaultCouponId)) {
                        return couponAndCutOffs;
                    }
                }
            }
            return null;
        }

        @JvmStatic
        @NotNull
        public final String d(@NotNull Payment payment) {
            List<DigitalMoneyBankCard> list;
            String str = payment.code;
            if (str != null) {
                int hashCode = str.hashCode();
                Object obj = null;
                if (hashCode != -155096089) {
                    if (hashCode == 503645376 && str.equals(CashierPayChannelCode.JD_PAY_CREDIT)) {
                        List<CreditCard> list2 = payment.bindingCardArray;
                        if (list2 == null) {
                            return "";
                        }
                        Iterator<T> it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Object next = it.next();
                            if (((CreditCard) next).selected) {
                                obj = next;
                                break;
                            }
                        }
                        CreditCard creditCard = (CreditCard) obj;
                        if (creditCard == null || (r6 = creditCard.channelId) == null) {
                            return "";
                        }
                    }
                } else if (str.equals("cyberMoney")) {
                    DigitalMoneyPayEntity digitalMoneyPayEntity = payment.virtualPayModel;
                    if (TextUtils.equals(digitalMoneyPayEntity != null ? digitalMoneyPayEntity.jumpApp : null, "jdAppSdk")) {
                        DigitalMoneyPayEntity digitalMoneyPayEntity2 = payment.virtualPayModel;
                        if (digitalMoneyPayEntity2 == null || (r6 = digitalMoneyPayEntity2.channelId) == null) {
                            return "";
                        }
                    } else {
                        DigitalMoneyPayEntity digitalMoneyPayEntity3 = payment.virtualPayModel;
                        if (digitalMoneyPayEntity3 == null || (list = digitalMoneyPayEntity3.bankCardList) == null) {
                            return "";
                        }
                        Iterator<T> it2 = list.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            Object next2 = it2.next();
                            if (((DigitalMoneyBankCard) next2).selected) {
                                obj = next2;
                                break;
                            }
                        }
                        DigitalMoneyBankCard digitalMoneyBankCard = (DigitalMoneyBankCard) obj;
                        if (digitalMoneyBankCard == null || (r6 = digitalMoneyBankCard.channelId) == null) {
                            return "";
                        }
                    }
                }
                return r6;
            }
            String str2 = payment.channelId;
            if (str2 == null) {
                return "";
            }
            return str2;
        }

        @JvmStatic
        public final boolean e(@Nullable Payment payment) {
            List<DigitalMoneyBankCard> list;
            Object obj;
            if (payment == null) {
                return false;
            }
            String str = payment.code;
            if (str != null && str.hashCode() == -155096089 && str.equals("cyberMoney")) {
                DigitalMoneyPayEntity digitalMoneyPayEntity = payment.virtualPayModel;
                if (digitalMoneyPayEntity == null || (list = digitalMoneyPayEntity.bankCardList) == null) {
                    return false;
                }
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
                DigitalMoneyBankCard digitalMoneyBankCard = (DigitalMoneyBankCard) obj;
                if (digitalMoneyBankCard != null) {
                    return digitalMoneyBankCard.defaultSelected;
                }
                return false;
            }
            return payment.defaultSelected;
        }

        @JvmStatic
        @NotNull
        public final String f(@NotNull Payment payment) {
            List<DigitalMoneyBankCard> list;
            String str = payment.code;
            if (str != null) {
                int hashCode = str.hashCode();
                Object obj = null;
                if (hashCode != -155096089) {
                    if (hashCode == 503645376 && str.equals(CashierPayChannelCode.JD_PAY_CREDIT)) {
                        List<CreditCard> list2 = payment.bindingCardArray;
                        if (list2 == null) {
                            return "";
                        }
                        Iterator<T> it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Object next = it.next();
                            if (((CreditCard) next).selected) {
                                obj = next;
                                break;
                            }
                        }
                        CreditCard creditCard = (CreditCard) obj;
                        if (creditCard == null || (r6 = creditCard.uniqueChannelId) == null) {
                            return "";
                        }
                    }
                } else if (str.equals("cyberMoney")) {
                    DigitalMoneyPayEntity digitalMoneyPayEntity = payment.virtualPayModel;
                    if (TextUtils.equals(digitalMoneyPayEntity != null ? digitalMoneyPayEntity.jumpApp : null, "jdAppSdk")) {
                        DigitalMoneyPayEntity digitalMoneyPayEntity2 = payment.virtualPayModel;
                        if (digitalMoneyPayEntity2 == null || (r6 = digitalMoneyPayEntity2.uniqueChannelId) == null) {
                            return "";
                        }
                    } else {
                        DigitalMoneyPayEntity digitalMoneyPayEntity3 = payment.virtualPayModel;
                        if (digitalMoneyPayEntity3 == null || (list = digitalMoneyPayEntity3.bankCardList) == null) {
                            return "";
                        }
                        Iterator<T> it2 = list.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            Object next2 = it2.next();
                            if (((DigitalMoneyBankCard) next2).selected) {
                                obj = next2;
                                break;
                            }
                        }
                        DigitalMoneyBankCard digitalMoneyBankCard = (DigitalMoneyBankCard) obj;
                        if (digitalMoneyBankCard == null || (r6 = digitalMoneyBankCard.uniqueChannelId) == null) {
                            return "";
                        }
                    }
                }
                return r6;
            }
            String str2 = payment.uniqueChannelId;
            if (str2 == null) {
                return "";
            }
            return str2;
        }

        @JvmStatic
        @Nullable
        public final CreditCard g(@Nullable Payment payment) {
            List<CreditCard> list;
            Object obj = null;
            if (!Intrinsics.areEqual(CashierPayChannelCode.JD_PAY_CREDIT, payment != null ? payment.code : null) || (list = payment.bindingCardArray) == null) {
                return null;
            }
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (((CreditCard) next).selected) {
                    obj = next;
                    break;
                }
            }
            return (CreditCard) obj;
        }

        @JvmStatic
        @Nullable
        public final com.jd.lib.cashier.sdk.pay.dialog.e k(@NotNull Payment payment) {
            List<CouponAndCutOffs> list;
            if (g.c(payment.code)) {
                AllCoupons allCoupons = payment.allCoupons;
                if (allCoupons != null && (list = allCoupons.couponAndCutOffs) != null && list.size() > 0) {
                    for (CouponAndCutOffs couponAndCutOffs : payment.allCoupons.couponAndCutOffs) {
                        if (Intrinsics.areEqual(couponAndCutOffs.getPayMarketingUUID(), payment.defaultCouponId)) {
                            return couponAndCutOffs;
                        }
                    }
                }
            } else if (g.a(payment.code)) {
                BaiTiaoPlanInfo baiTiaoPlanInfo = payment.baitiaoPlanInfo;
                if ((baiTiaoPlanInfo != null ? baiTiaoPlanInfo.selectedCoupon : null) != null) {
                    CouponEntity couponEntity = baiTiaoPlanInfo != null ? baiTiaoPlanInfo.selectedCoupon : null;
                    payment.baiTiaoPlanInfoCoupon = couponEntity;
                    return couponEntity;
                }
                return payment.defaultCoupon;
            }
            return null;
        }

        @JvmStatic
        @Nullable
        public final Payment l(@Nullable List<? extends Payment> list, @Nullable List<? extends Payment> list2) {
            if (list == null || list.isEmpty()) {
                return null;
            }
            Payment m2 = m(list);
            if (m2 == null) {
                m2 = i(list2);
            }
            if (m2 == null) {
                m2 = j(list);
            }
            return m2 == null ? h(list2) : m2;
        }

        @JvmStatic
        @NotNull
        public final Payment n(@Nullable CashierPayEntity cashierPayEntity) {
            if (cashierPayEntity == null) {
                return new Payment();
            }
            List<Payment> list = cashierPayEntity.jdPayChannelList;
            List<Payment> list2 = cashierPayEntity.payChannelList;
            List<Payment> list3 = cashierPayEntity.otherPayChannelList;
            ArrayList<Payment> arrayList = new ArrayList();
            if (list != null && (!list.isEmpty()) != false) {
                arrayList.addAll(list);
            }
            if (list2 != null && (!list2.isEmpty()) != false) {
                arrayList.addAll(list2);
            }
            if (list3 != null && (!list3.isEmpty()) != false) {
                arrayList.addAll(list3);
            }
            if (arrayList.size() > 0) {
                for (Payment payment : arrayList) {
                    if (o(payment) && payment.defaultSelected) {
                        return payment;
                    }
                }
            }
            return new Payment();
        }

        @JvmStatic
        public final boolean o(@NotNull Payment payment) {
            return (Intrinsics.areEqual("7", payment.status) || Intrinsics.areEqual("3", payment.status) || Intrinsics.areEqual("9", payment.status) || Intrinsics.areEqual("moreInfo", payment.code) || Intrinsics.areEqual("newJdpay", payment.code)) ? false : true;
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmStatic
    @NotNull
    public static final Payment a(@Nullable CashierPayEntity cashierPayEntity) {
        return a.a(cashierPayEntity);
    }

    @JvmStatic
    @NotNull
    public static final String b(@NotNull Payment payment) {
        return a.f(payment);
    }

    @JvmStatic
    @Nullable
    public static final CreditCard c(@Nullable Payment payment) {
        return a.g(payment);
    }

    @JvmStatic
    @Nullable
    public static final com.jd.lib.cashier.sdk.pay.dialog.e d(@NotNull Payment payment) {
        return a.k(payment);
    }

    @JvmStatic
    @Nullable
    public static final Payment e(@Nullable List<? extends Payment> list, @Nullable List<? extends Payment> list2) {
        return a.l(list, list2);
    }

    @JvmStatic
    @NotNull
    public static final Payment f(@Nullable CashierPayEntity cashierPayEntity) {
        return a.n(cashierPayEntity);
    }

    @JvmStatic
    public static final boolean g(@NotNull Payment payment) {
        return a.o(payment);
    }
}
