package com.jd.lib.cashier.sdk.h.h;

import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class g {
    /* JADX WARN: Removed duplicated region for block: B:40:0x002e A[RETURN, SYNTHETIC] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean a(@org.jetbrains.annotations.Nullable java.lang.String r1) {
        /*
            if (r1 != 0) goto L3
            goto L30
        L3:
            int r0 = r1.hashCode()
            switch(r0) {
                case -1302687328: goto L26;
                case -1223730147: goto L1d;
                case 306898292: goto L14;
                case 376587341: goto Lb;
                default: goto La;
            }
        La:
            goto L30
        Lb:
            java.lang.String r0 = "BAITIAO"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L30
            goto L2e
        L14:
            java.lang.String r0 = "BAITIAO_QUICK_CARD"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L30
            goto L2e
        L1d:
            java.lang.String r0 = "BAIFENQI"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L30
            goto L2e
        L26:
            java.lang.String r0 = "BAITIAOQUICK"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L30
        L2e:
            r1 = 1
            goto L31
        L30:
            r1 = 0
        L31:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.h.h.g.a(java.lang.String):boolean");
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

    /* JADX WARN: Removed duplicated region for block: B:100:0x008d A[RETURN, SYNTHETIC] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean i(@org.jetbrains.annotations.Nullable java.lang.String r1) {
        /*
            if (r1 != 0) goto L4
            goto L8f
        L4:
            int r0 = r1.hashCode()
            switch(r0) {
                case -2106602916: goto L85;
                case -1302687328: goto L7c;
                case -1223730147: goto L73;
                case -1199741180: goto L6a;
                case -1076038836: goto L61;
                case -527037440: goto L58;
                case 2267: goto L4f;
                case 73916: goto L46;
                case 79953: goto L3d;
                case 86937: goto L34;
                case 306898292: goto L2b;
                case 326166413: goto L21;
                case 376587341: goto L17;
                case 503645376: goto Ld;
                default: goto Lb;
            }
        Lb:
            goto L8f
        Ld:
            java.lang.String r0 = "CREDITINSTALLMENT"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L17:
            java.lang.String r0 = "BAITIAO"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L21:
            java.lang.String r0 = "HONEYPAY"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L2b:
            java.lang.String r0 = "BAITIAO_QUICK_CARD"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L34:
            java.lang.String r0 = "XJK"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L3d:
            java.lang.String r0 = "QBB"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L46:
            java.lang.String r0 = "JXJ"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L4f:
            java.lang.String r0 = "GB"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L58:
            java.lang.String r0 = "FOREIGNBANKCARD"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L61:
            java.lang.String r0 = "BANKCARD"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L6a:
            java.lang.String r0 = "GOUWUJIN"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L73:
            java.lang.String r0 = "BAIFENQI"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L7c:
            java.lang.String r0 = "BAITIAOQUICK"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
            goto L8d
        L85:
            java.lang.String r0 = "JINCAI"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L8f
        L8d:
            r1 = 1
            goto L90
        L8f:
            r1 = 0
        L90:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.h.h.g.i(java.lang.String):boolean");
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

    /* JADX WARN: Removed duplicated region for block: B:58:0x0049 A[RETURN, SYNTHETIC] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean n(@org.jetbrains.annotations.Nullable java.lang.String r1) {
        /*
            if (r1 != 0) goto L3
            goto L4b
        L3:
            int r0 = r1.hashCode()
            switch(r0) {
                case -2106602916: goto L41;
                case -1302687328: goto L38;
                case -1223730147: goto L2f;
                case -1199741180: goto L26;
                case 86937: goto L1d;
                case 306898292: goto L14;
                case 376587341: goto Lb;
                default: goto La;
            }
        La:
            goto L4b
        Lb:
            java.lang.String r0 = "BAITIAO"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L4b
            goto L49
        L14:
            java.lang.String r0 = "BAITIAO_QUICK_CARD"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L4b
            goto L49
        L1d:
            java.lang.String r0 = "XJK"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L4b
            goto L49
        L26:
            java.lang.String r0 = "GOUWUJIN"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L4b
            goto L49
        L2f:
            java.lang.String r0 = "BAIFENQI"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L4b
            goto L49
        L38:
            java.lang.String r0 = "BAITIAOQUICK"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L4b
            goto L49
        L41:
            java.lang.String r0 = "JINCAI"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L4b
        L49:
            r1 = 1
            goto L4c
        L4b:
            r1 = 0
        L4c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.h.h.g.n(java.lang.String):boolean");
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
