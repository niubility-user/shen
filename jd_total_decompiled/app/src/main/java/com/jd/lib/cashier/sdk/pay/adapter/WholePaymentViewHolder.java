package com.jd.lib.cashier.sdk.pay.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.ui.CashierRegulatorItemView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.h.h.g;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.TopFloor;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J%\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eR\u001e\u0010\u0013\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0016\u001a\n \u0010*\u0004\u0018\u00010\u00140\u00148\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0015\u00a8\u0006\u001b"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/WholePaymentViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "clickListener", "", "c", "(Landroid/view/View$OnClickListener;)V", "Landroidx/fragment/app/FragmentActivity;", AnnoConst.Constructor_Context, "Lcom/jd/lib/cashier/sdk/h/g/x;", "payChannelTemplate", "", "position", "b", "(Landroidx/fragment/app/FragmentActivity;Lcom/jd/lib/cashier/sdk/h/g/x;I)V", "Lcom/jd/lib/cashier/sdk/core/ui/CashierRegulatorItemView;", "kotlin.jvm.PlatformType", com.jingdong.jdsdk.a.a.a, "Lcom/jd/lib/cashier/sdk/core/ui/CashierRegulatorItemView;", "mPayChannelView", "Landroid/widget/TextView;", "Landroid/widget/TextView;", "mServiceProviderTipView", "Landroid/view/View;", "paymentItemView", "<init>", "(Landroid/view/View;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class WholePaymentViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a  reason: from kotlin metadata */
    private final CashierRegulatorItemView mPayChannelView;

    /* renamed from: b  reason: from kotlin metadata */
    private final TextView mServiceProviderTipView;

    public WholePaymentViewHolder(@NotNull View view) {
        super(view);
        this.mPayChannelView = (CashierRegulatorItemView) view.findViewById(R.id.lib_cashier_whole_payment_item);
        this.mServiceProviderTipView = (TextView) view.findViewById(R.id.lib_cashier_whole_service_provider_tip);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void b(@NotNull FragmentActivity context, @NotNull x payChannelTemplate, int position) {
        com.jd.lib.cashier.sdk.pay.dialog.e eVar;
        String str;
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        CashierPayEntity cashierPayEntity;
        TopFloor topFloor;
        String str2;
        Payment a = payChannelTemplate.a();
        this.mPayChannelView.E(a.logo);
        if (Intrinsics.areEqual(a.code, CashierPayChannelCode.JD_PAY_HONEY)) {
            this.mPayChannelView.s(a.channelNamePre);
        } else {
            this.mPayChannelView.s(a.channelName);
        }
        this.mPayChannelView.G(a.tip);
        this.mPayChannelView.F(a.statusDesc);
        List<String> list = a.iconList;
        if (list != null) {
            Intrinsics.checkExpressionValueIsNotNull(list, "payment.iconList");
            if ((!list.isEmpty()) != false) {
                if (a.iconList.size() >= 2) {
                    this.mPayChannelView.x(a.iconList.get(0));
                    this.mPayChannelView.C(a.iconList.get(1));
                } else if (!g.h(a.code)) {
                    this.mPayChannelView.x(a.iconList.get(0));
                } else {
                    this.mPayChannelView.A();
                }
                this.mPayChannelView.u(a.channelNameTail);
                this.mPayChannelView.t(a.channelNameMiddle);
                eVar = a.selectedCouponEntity;
                String str3 = null;
                if (eVar == null) {
                    if (eVar == null || (str2 = eVar.getTitleName()) == null) {
                        str2 = "";
                    }
                    this.mPayChannelView.v(str2, false, null);
                } else {
                    this.mPayChannelView.v(a.preferentiaNum, false, null);
                }
                this.mPayChannelView.K();
                this.mPayChannelView.p(a.moreInfoTip);
                str = a.rightIconType;
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
                        this.mPayChannelView.k(a.selected);
                    }
                    if (Intrinsics.areEqual("3", a.status)) {
                        this.mPayChannelView.d();
                    }
                    CashierRegulatorItemView cashierRegulatorItemView = this.mPayChannelView;
                    cashierRegulatorItemView.J(cashierRegulatorItemView.c(a.status, g.b(a.code)));
                    this.mPayChannelView.a(Intrinsics.areEqual("3", a.status));
                    if (TextUtils.isEmpty(a.jdServiceProviderTip)) {
                        TextView mServiceProviderTipView = this.mServiceProviderTipView;
                        Intrinsics.checkExpressionValueIsNotNull(mServiceProviderTipView, "mServiceProviderTipView");
                        mServiceProviderTipView.setVisibility(8);
                    } else {
                        TextView mServiceProviderTipView2 = this.mServiceProviderTipView;
                        Intrinsics.checkExpressionValueIsNotNull(mServiceProviderTipView2, "mServiceProviderTipView");
                        mServiceProviderTipView2.setText(a.jdServiceProviderTip);
                        TextView mServiceProviderTipView3 = this.mServiceProviderTipView;
                        Intrinsics.checkExpressionValueIsNotNull(mServiceProviderTipView3, "mServiceProviderTipView");
                        mServiceProviderTipView3.setVisibility(0);
                        this.mServiceProviderTipView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
                    }
                    this.mPayChannelView.S();
                    if (Intrinsics.areEqual("GRADUALLY_PAY", a.code)) {
                        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(context instanceof CashierPayActivity) ? null : context);
                        if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b = x.b()) != null && (cashierPayEntity = b.K) != null && (topFloor = cashierPayEntity.topFloor) != null) {
                            str3 = topFloor.payprice;
                        }
                        com.jd.lib.cashier.sdk.h.e.a.d().b0(context, str3 != null ? str3 : "");
                        return;
                    }
                    com.jd.lib.cashier.sdk.h.e.a.d().I(context, a.code, com.jd.lib.cashier.sdk.h.h.e.a.f(a), a.selected);
                    return;
                }
                this.mPayChannelView.g();
                this.mPayChannelView.f();
                if (Intrinsics.areEqual("3", a.status)) {
                }
                CashierRegulatorItemView cashierRegulatorItemView2 = this.mPayChannelView;
                cashierRegulatorItemView2.J(cashierRegulatorItemView2.c(a.status, g.b(a.code)));
                this.mPayChannelView.a(Intrinsics.areEqual("3", a.status));
                if (TextUtils.isEmpty(a.jdServiceProviderTip)) {
                }
                this.mPayChannelView.S();
                if (Intrinsics.areEqual("GRADUALLY_PAY", a.code)) {
                }
            }
        }
        this.mPayChannelView.B();
        this.mPayChannelView.u(a.channelNameTail);
        this.mPayChannelView.t(a.channelNameMiddle);
        eVar = a.selectedCouponEntity;
        String str32 = null;
        if (eVar == null) {
        }
        this.mPayChannelView.K();
        this.mPayChannelView.p(a.moreInfoTip);
        str = a.rightIconType;
        if (str != null) {
        }
        this.mPayChannelView.g();
        this.mPayChannelView.f();
        if (Intrinsics.areEqual("3", a.status)) {
        }
        CashierRegulatorItemView cashierRegulatorItemView22 = this.mPayChannelView;
        cashierRegulatorItemView22.J(cashierRegulatorItemView22.c(a.status, g.b(a.code)));
        this.mPayChannelView.a(Intrinsics.areEqual("3", a.status));
        if (TextUtils.isEmpty(a.jdServiceProviderTip)) {
        }
        this.mPayChannelView.S();
        if (Intrinsics.areEqual("GRADUALLY_PAY", a.code)) {
        }
    }

    public final void c(@NotNull View.OnClickListener clickListener) {
        this.mPayChannelView.r(clickListener);
    }
}
