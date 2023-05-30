package com.jd.lib.cashier.sdk.creditpay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.h.h.g;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\u0004\b!\u0010\"J\u001f\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R*\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR*\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u0017\u001a\u0004\b\u001e\u0010\u0019\"\u0004\b\u001f\u0010\u001b\u00a8\u0006#"}, d2 = {"Lcom/jd/lib/cashier/sdk/creditpay/adapter/CreditPaymentAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jd/lib/cashier/sdk/creditpay/adapter/WholePaymentViewHolder;", "Landroid/view/ViewGroup;", "parent", "", "viewType", "m", "(Landroid/view/ViewGroup;I)Lcom/jd/lib/cashier/sdk/creditpay/adapter/WholePaymentViewHolder;", "getItemCount", "()I", "holder", "position", "", NotifyType.LIGHTS, "(Lcom/jd/lib/cashier/sdk/creditpay/adapter/WholePaymentViewHolder;I)V", "", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "c", "Ljava/util/List;", "dataList", "Lcom/jd/lib/cashier/sdk/core/utils/f;", com.jingdong.jdsdk.a.a.a, "Lcom/jd/lib/cashier/sdk/core/utils/f;", JshopConst.JSHOP_PROMOTIO_H, "()Lcom/jd/lib/cashier/sdk/core/utils/f;", PersonalConstants.ICON_STYLE_N, "(Lcom/jd/lib/cashier/sdk/core/utils/f;)V", "clickCallBack", "b", "getExpoCallBack", "o", "expoCallBack", "<init>", "(Ljava/util/List;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CreditPaymentAdapter extends RecyclerView.Adapter<WholePaymentViewHolder> {
    @Nullable

    /* renamed from: a  reason: from kotlin metadata */
    private f<Payment> clickCallBack;
    @Nullable

    /* renamed from: b  reason: from kotlin metadata */
    private f<Payment> expoCallBack;

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private final List<Payment> dataList;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Payment f3121g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CreditPaymentAdapter f3122h;

        a(Payment payment, CreditPaymentAdapter creditPaymentAdapter, WholePaymentViewHolder wholePaymentViewHolder) {
            this.f3121g = payment;
            this.f3122h = creditPaymentAdapter;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            f<Payment> h2;
            if (!g.g(this.f3121g.status) || (h2 = this.f3122h.h()) == null) {
                return;
            }
            h2.callBack(this.f3121g);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CreditPaymentAdapter(@NotNull List<? extends Payment> list) {
        this.dataList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }

    @Nullable
    public final f<Payment> h() {
        return this.clickCallBack;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NotNull WholePaymentViewHolder holder, int position) {
        Payment payment = this.dataList.get(position);
        holder.b(payment);
        f<Payment> fVar = this.expoCallBack;
        if (fVar != null) {
            fVar.callBack(payment);
        }
        holder.c(new a(payment, this, holder));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public WholePaymentViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_cashier_sdk_item_cashier_whole_payment, (ViewGroup) null, false);
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        return new WholePaymentViewHolder(itemView);
    }

    public final void n(@Nullable f<Payment> fVar) {
        this.clickCallBack = fVar;
    }

    public final void o(@Nullable f<Payment> fVar) {
        this.expoCallBack = fVar;
    }
}
