package com.jd.lib.cashier.sdk.creditpay.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.ui.CashierItemView;
import com.jingdong.jdsdk.a.a;
import kotlin.Metadata;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(@org.jetbrains.annotations.NotNull com.jd.lib.cashier.sdk.pay.bean.Payment r5) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.creditpay.adapter.WholePaymentViewHolder.b(com.jd.lib.cashier.sdk.pay.bean.Payment):void");
    }

    public final void c(@NotNull View.OnClickListener clickListener) {
        this.mPayChannelView.r(clickListener);
    }
}
