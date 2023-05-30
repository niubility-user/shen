package com.jd.lib.cashier.sdk.h.h;

import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class g {
    /* JADX WARN: Removed duplicated region for block: B:62:0x002e A[RETURN, SYNTHETIC] */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean a(@Nullable String str) {
        if (str != null) {
            switch (str.hashCode()) {
                case -1302687328:
                    if (str.equals(CashierPayChannelCode.JD_PAY_BT_QUICK)) {
                        return true;
                    }
                    break;
                case -1223730147:
                    if (str.equals("BAIFENQI")) {
                    }
                    break;
                case 306898292:
                    if (str.equals("BAITIAO_QUICK_CARD")) {
                    }
                    break;
                case 376587341:
                    if (str.equals(CashierPayChannelCode.JD_PAY_BT)) {
                    }
                    break;
            }
        }
        return false;
    }

    @JvmStatic
    public static final boolean b(@Nullable String str) {
        return str != null && str.hashCode() == -1076038836 && str.equals(CashierPayChannelCode.JD_PAY_BANKCARD);
    }

    @JvmStatic
    public static final boolean c(@Nullable String str) {
        int hashCode;
        return str != null && ((hashCode = str.hashCode()) == -2106602916 ? str.equals(CashierPayChannelCode.JD_PAY_JINCAI) : !(hashCode == -1076038836 ? !str.equals(CashierPayChannelCode.JD_PAY_BANKCARD) : !(hashCode == 86937 && str.equals(CashierPayChannelCode.JD_PAY_XJK))));
    }

    @JvmStatic
    public static final boolean d(@Nullable String str) {
        return str != null && str.hashCode() == 503645376 && str.equals(CashierPayChannelCode.JD_PAY_CREDIT);
    }

    @JvmStatic
    public static final boolean e(@Nullable String str) {
        return str != null && str.hashCode() == 3065333 && str.equals(CashierPayChannelCode.UNION_PAY);
    }

    @JvmStatic
    public static final boolean f(@Nullable String str) {
        return str != null && str.hashCode() == -155096089 && str.equals("cyberMoney");
    }

    @JvmStatic
    public static final boolean g(@Nullable String str) {
        return (Intrinsics.areEqual("9", str) ^ true) && (Intrinsics.areEqual("3", str) ^ true);
    }

    @JvmStatic
    public static final boolean h(@Nullable String str) {
        int hashCode;
        return str != null && ((hashCode = str.hashCode()) == -1199741180 ? str.equals("GOUWUJIN") : hashCode == 73916 && str.equals(CashierPayChannelCode.JD_PAY_JXJ));
    }

    /* JADX WARN: Removed duplicated region for block: B:152:0x008d A[RETURN, SYNTHETIC] */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean i(@Nullable String str) {
        if (str != null) {
            switch (str.hashCode()) {
                case -2106602916:
                    if (str.equals(CashierPayChannelCode.JD_PAY_JINCAI)) {
                        return true;
                    }
                    break;
                case -1302687328:
                    if (str.equals(CashierPayChannelCode.JD_PAY_BT_QUICK)) {
                    }
                    break;
                case -1223730147:
                    if (str.equals("BAIFENQI")) {
                    }
                    break;
                case -1199741180:
                    if (str.equals("GOUWUJIN")) {
                    }
                    break;
                case -1076038836:
                    if (str.equals(CashierPayChannelCode.JD_PAY_BANKCARD)) {
                    }
                    break;
                case -527037440:
                    if (str.equals(CashierPayChannelCode.JD_PAY_OVERSEA_BANK)) {
                    }
                    break;
                case R2.attr.windowFixedHeightMinor /* 2267 */:
                    if (str.equals(CashierPayChannelCode.JD_PAY_GB)) {
                    }
                    break;
                case 73916:
                    if (str.equals(CashierPayChannelCode.JD_PAY_JXJ)) {
                    }
                    break;
                case 79953:
                    if (str.equals(CashierPayChannelCode.JD_PAY_QBB)) {
                    }
                    break;
                case 86937:
                    if (str.equals(CashierPayChannelCode.JD_PAY_XJK)) {
                    }
                    break;
                case 306898292:
                    if (str.equals("BAITIAO_QUICK_CARD")) {
                    }
                    break;
                case 326166413:
                    if (str.equals(CashierPayChannelCode.JD_PAY_HONEY)) {
                    }
                    break;
                case 376587341:
                    if (str.equals(CashierPayChannelCode.JD_PAY_BT)) {
                    }
                    break;
                case 503645376:
                    if (str.equals(CashierPayChannelCode.JD_PAY_CREDIT)) {
                    }
                    break;
            }
        }
        return false;
    }

    @JvmStatic
    public static final boolean j(@Nullable Payment payment, @Nullable List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list) {
        if (list != null) {
            for (com.jd.lib.cashier.sdk.d.a.e.a aVar : list) {
                if (!(aVar instanceof x)) {
                    aVar = null;
                }
                x xVar = (x) aVar;
                boolean areEqual = Intrinsics.areEqual(payment, xVar != null ? xVar.a() : null);
                if (areEqual) {
                    return areEqual;
                }
            }
            return false;
        }
        return false;
    }

    @JvmStatic
    public static final boolean k(@Nullable String str) {
        return str != null && str.hashCode() == 1998702679 && str.equals("medicalPay");
    }

    @JvmStatic
    public static final boolean l(@Nullable String str) {
        return str != null && str.hashCode() == -219258461 && str.equals("moreInfo");
    }

    @JvmStatic
    public static final boolean m(@Nullable String str) {
        return str != null && str.hashCode() == 166724297 && str.equals("octopusPay");
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x0049 A[RETURN, SYNTHETIC] */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean n(@Nullable String str) {
        if (str != null) {
            switch (str.hashCode()) {
                case -2106602916:
                    if (str.equals(CashierPayChannelCode.JD_PAY_JINCAI)) {
                        return true;
                    }
                    break;
                case -1302687328:
                    if (str.equals(CashierPayChannelCode.JD_PAY_BT_QUICK)) {
                    }
                    break;
                case -1223730147:
                    if (str.equals("BAIFENQI")) {
                    }
                    break;
                case -1199741180:
                    if (str.equals("GOUWUJIN")) {
                    }
                    break;
                case 86937:
                    if (str.equals(CashierPayChannelCode.JD_PAY_XJK)) {
                    }
                    break;
                case 306898292:
                    if (str.equals("BAITIAO_QUICK_CARD")) {
                    }
                    break;
                case 376587341:
                    if (str.equals(CashierPayChannelCode.JD_PAY_BT)) {
                    }
                    break;
            }
        }
        return false;
    }

    @JvmStatic
    public static final boolean o(@Nullable Payment payment, @Nullable List<? extends Payment> list) {
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                boolean areEqual = Intrinsics.areEqual(payment, (Payment) it.next());
                if (areEqual) {
                    return areEqual;
                }
            }
            return false;
        }
        return false;
    }

    @JvmStatic
    public static final boolean p(@Nullable String str) {
        return str != null && str.hashCode() == -791575966 && str.equals("weixin");
    }
}
