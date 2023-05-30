package com.jd.lib.cashier.sdk.pay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCard;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0019\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fR0\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00198\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001f"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/CreditCardBankProAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jd/lib/cashier/sdk/pay/adapter/CreditCardBankViewHolderPro;", "Landroid/view/ViewGroup;", "parent", "", "viewType", "m", "(Landroid/view/ViewGroup;I)Lcom/jd/lib/cashier/sdk/pay/adapter/CreditCardBankViewHolderPro;", "getItemCount", "()I", "holder", "position", "", NotifyType.LIGHTS, "(Lcom/jd/lib/cashier/sdk/pay/adapter/CreditCardBankViewHolderPro;I)V", "Lkotlin/Function1;", "Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCard;", com.jingdong.jdsdk.a.a.a, "Lkotlin/jvm/functions/Function1;", JshopConst.JSHOP_PROMOTIO_H, "()Lkotlin/jvm/functions/Function1;", PersonalConstants.ICON_STYLE_N, "(Lkotlin/jvm/functions/Function1;)V", "onItemClickListener", "", "b", "Ljava/util/List;", "dataList", "<init>", "(Ljava/util/List;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CreditCardBankProAdapter extends RecyclerView.Adapter<CreditCardBankViewHolderPro> {
    @Nullable

    /* renamed from: a  reason: from kotlin metadata */
    private Function1<? super CreditCard, Unit> onItemClickListener;

    /* renamed from: b  reason: from kotlin metadata */
    private final List<CreditCard> dataList;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ CreditCard f3816g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CreditCardBankProAdapter f3817h;

        a(CreditCard creditCard, CreditCardBankProAdapter creditCardBankProAdapter, CreditCardBankViewHolderPro creditCardBankViewHolderPro) {
            this.f3816g = creditCard;
            this.f3817h = creditCardBankProAdapter;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Function1<CreditCard, Unit> h2;
            if (!this.f3816g.cardCanUse || (h2 = this.f3817h.h()) == null) {
                return;
            }
            h2.invoke(this.f3816g);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CreditCardBankProAdapter(@Nullable List<? extends CreditCard> list) {
        this.dataList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CreditCard> list = this.dataList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Nullable
    public final Function1<CreditCard, Unit> h() {
        return this.onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NotNull CreditCardBankViewHolderPro holder, int position) {
        CreditCard creditCard;
        List<CreditCard> list = this.dataList;
        if (list == null || (creditCard = list.get(position)) == null) {
            return;
        }
        holder.b(creditCard);
        holder.c(creditCard.cardCanUse);
        holder.e(creditCard);
        holder.f(new a(creditCard, this, holder));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CreditCardBankViewHolderPro onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_cashier_sdk_item_credit_card_bank_dialog, (ViewGroup) null, false);
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        Context context = parent.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "parent.context");
        return new CreditCardBankViewHolderPro(itemView, context);
    }

    public final void n(@Nullable Function1<? super CreditCard, Unit> function1) {
        this.onItemClickListener = function1;
    }
}
