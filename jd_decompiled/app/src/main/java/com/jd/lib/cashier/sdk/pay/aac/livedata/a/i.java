package com.jd.lib.cashier.sdk.pay.aac.livedata.a;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class i {
    @NotNull
    private final List<com.jd.lib.cashier.sdk.d.a.e.a> a;
    @NotNull
    private final List<com.jd.lib.cashier.sdk.d.a.e.a> b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    private final List<Payment> f3787c;
    @Nullable
    private final Payment d;

    /* JADX WARN: Multi-variable type inference failed */
    public i(@NotNull List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, @NotNull List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list2, @NotNull List<? extends Payment> list3, @Nullable Payment payment) {
        this.a = list;
        this.b = list2;
        this.f3787c = list3;
        this.d = payment;
    }

    @Nullable
    public final Payment a() {
        return this.d;
    }

    @NotNull
    public final List<com.jd.lib.cashier.sdk.d.a.e.a> b() {
        return this.b;
    }

    @NotNull
    public final List<Payment> c() {
        return this.f3787c;
    }

    @NotNull
    public final List<com.jd.lib.cashier.sdk.d.a.e.a> d() {
        return this.a;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof i) {
                i iVar = (i) obj;
                return Intrinsics.areEqual(this.a, iVar.a) && Intrinsics.areEqual(this.b, iVar.b) && Intrinsics.areEqual(this.f3787c, iVar.f3787c) && Intrinsics.areEqual(this.d, iVar.d);
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        List<com.jd.lib.cashier.sdk.d.a.e.a> list = this.a;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        List<com.jd.lib.cashier.sdk.d.a.e.a> list2 = this.b;
        int hashCode2 = (hashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
        List<Payment> list3 = this.f3787c;
        int hashCode3 = (hashCode2 + (list3 != null ? list3.hashCode() : 0)) * 31;
        Payment payment = this.d;
        return hashCode3 + (payment != null ? payment.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "CashierPaySucHttpEvent(payChannelTemplateList=" + this.a + ", jdOtherPayChannelList=" + this.b + ", jdPayChannelList=" + this.f3787c + ", currentSelectedCashierPayment=" + this.d + ")";
    }
}
