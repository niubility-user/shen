package com.jd.lib.cashier.sdk.creditpay.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.ui.CashierItemView;
import com.jd.lib.cashier.sdk.h.h.g;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponAndCutOffs;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import com.jingdong.jdsdk.a.a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nR\u001e\u0010\u000f\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000e\u00a8\u0006\u0014"}, d2 = {"Lcom/jd/lib/cashier/sdk/creditpay/adapter/WholePaymentViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "clickListener", "", "c", "(Landroid/view/View$OnClickListener;)V", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "payment", "b", "(Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "Lcom/jd/lib/cashier/sdk/core/ui/CashierItemView;", "kotlin.jvm.PlatformType", a.a, "Lcom/jd/lib/cashier/sdk/core/ui/CashierItemView;", "mPayChannelView", "Landroid/view/View;", "paymentItemView", "<init>", "(Landroid/view/View;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class WholePaymentViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a  reason: from kotlin metadata */
    private final CashierItemView mPayChannelView;

    public WholePaymentViewHolder(@NotNull View view) {
        super(view);
        this.mPayChannelView = (CashierItemView) view.findViewById(R.id.lib_cashier_whole_payment_item);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void b(@NotNull Payment payment) {
        CouponAndCutOffs couponAndCutOffs;
        String str;
        String str2;
        this.mPayChannelView.E(payment.logo);
        if (Intrinsics.areEqual(payment.code, CashierPayChannelCode.JD_PAY_HONEY)) {
            this.mPayChannelView.s(payment.channelNamePre);
        } else {
            this.mPayChannelView.s(payment.channelName);
        }
        this.mPayChannelView.G(payment.tip);
        this.mPayChannelView.F(payment.statusDesc);
        List<String> list = payment.iconList;
        if (list != null) {
            Intrinsics.checkExpressionValueIsNotNull(list, "payment.iconList");
            if ((!list.isEmpty()) != false) {
                if (payment.iconList.size() >= 2) {
                    this.mPayChannelView.x(payment.iconList.get(0));
                    this.mPayChannelView.C(payment.iconList.get(1));
                } else if (!g.h(payment.code)) {
                    this.mPayChannelView.x(payment.iconList.get(0));
                } else {
                    this.mPayChannelView.A();
                }
                this.mPayChannelView.u(payment.channelNameTail);
                this.mPayChannelView.t(payment.channelNameMiddle);
                couponAndCutOffs = payment.selectedCommonCoupon;
                if (couponAndCutOffs == null) {
                    if (couponAndCutOffs == null || (str2 = couponAndCutOffs.getTitleName()) == null) {
                        str2 = "";
                    }
                    Intrinsics.checkExpressionValueIsNotNull(str2, "payment.selectedCommonCoupon?.titleName ?: \"\"");
                    this.mPayChannelView.v(str2, false, null);
                } else {
                    this.mPayChannelView.v(payment.preferentiaNum, false, null);
                }
                str = payment.rightIconType;
                if (str != null) {
                    int hashCode = str.hashCode();
                    if (hashCode != 49) {
                        if (hashCode == 50 && str.equals("2")) {
                            this.mPayChannelView.g();
                            this.mPayChannelView.O();
                        }
                    } else if (str.equals("1")) {
                        this.mPayChannelView.P();
                        this.mPayChannelView.f();
                        this.mPayChannelView.k(payment.defaultSelected);
                    }
                    if (Intrinsics.areEqual("3", payment.status)) {
                        this.mPayChannelView.d();
                    }
                    this.mPayChannelView.a(Intrinsics.areEqual("3", payment.status));
                }
                this.mPayChannelView.g();
                this.mPayChannelView.f();
                if (Intrinsics.areEqual("3", payment.status)) {
                }
                this.mPayChannelView.a(Intrinsics.areEqual("3", payment.status));
            }
        }
        this.mPayChannelView.B();
        this.mPayChannelView.u(payment.channelNameTail);
        this.mPayChannelView.t(payment.channelNameMiddle);
        couponAndCutOffs = payment.selectedCommonCoupon;
        if (couponAndCutOffs == null) {
        }
        str = payment.rightIconType;
        if (str != null) {
        }
        this.mPayChannelView.g();
        this.mPayChannelView.f();
        if (Intrinsics.areEqual("3", payment.status)) {
        }
        this.mPayChannelView.a(Intrinsics.areEqual("3", payment.status));
    }

    public final void c(@NotNull View.OnClickListener clickListener) {
        this.mPayChannelView.r(clickListener);
    }
}
