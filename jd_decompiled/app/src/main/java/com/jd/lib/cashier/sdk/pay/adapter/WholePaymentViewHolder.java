package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.ui.CashierRegulatorItemView;
import com.jingdong.amon.router.annotation.AnnoConst;
import kotlin.Metadata;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(@org.jetbrains.annotations.NotNull androidx.fragment.app.FragmentActivity r7, @org.jetbrains.annotations.NotNull com.jd.lib.cashier.sdk.h.g.x r8, int r9) {
        /*
            Method dump skipped, instructions count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.adapter.WholePaymentViewHolder.b(androidx.fragment.app.FragmentActivity, com.jd.lib.cashier.sdk.h.g.x, int):void");
    }

    public final void c(@NotNull View.OnClickListener clickListener) {
        this.mPayChannelView.r(clickListener);
    }
}
