package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.h.h.g;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCyberMoneyChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCyberMoneyCouponItemEvent;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u001d\u001a\u00020\u001a\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0019\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u00168\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001d\u001a\u00020\u001a8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001c\u00a8\u0006 "}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/DigitalBankAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jd/lib/cashier/sdk/pay/adapter/DigitalBankViewHolder;", "Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyBankCard;", "digitalMoneyBankCard", "", "m", "(Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyBankCard;)V", "Landroid/view/ViewGroup;", "parent", "", "viewType", PersonalConstants.ICON_STYLE_N, "(Landroid/view/ViewGroup;I)Lcom/jd/lib/cashier/sdk/pay/adapter/DigitalBankViewHolder;", "getItemCount", "()I", "holder", "position", NotifyType.LIGHTS, "(Lcom/jd/lib/cashier/sdk/pay/adapter/DigitalBankViewHolder;I)V", JshopConst.JSHOP_PROMOTIO_H, "()Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyBankCard;", "", "b", "Ljava/util/List;", "dataList", "Landroidx/fragment/app/FragmentActivity;", com.jingdong.jdsdk.a.a.a, "Landroidx/fragment/app/FragmentActivity;", "activity", "<init>", "(Landroidx/fragment/app/FragmentActivity;Ljava/util/List;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class DigitalBankAdapter extends RecyclerView.Adapter<DigitalBankViewHolder> {

    /* renamed from: a  reason: from kotlin metadata */
    private final FragmentActivity activity;

    /* renamed from: b  reason: from kotlin metadata */
    private final List<DigitalMoneyBankCard> dataList;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ DigitalMoneyBankCard f3826g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ DigitalBankAdapter f3827h;

        a(DigitalMoneyBankCard digitalMoneyBankCard, DigitalBankAdapter digitalBankAdapter, DigitalBankViewHolder digitalBankViewHolder) {
            this.f3826g = digitalMoneyBankCard;
            this.f3827h = digitalBankAdapter;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (g.g(this.f3826g.status)) {
                this.f3827h.m(this.f3826g);
                com.jd.lib.cashier.sdk.b.i.e.f("CLICK_CYBER_MONEY_COUPON", new ClickCyberMoneyCouponItemEvent(this.f3826g));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Landroid/view/View;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Landroid/view/View;)V", "com/jd/lib/cashier/sdk/pay/adapter/DigitalBankAdapter$onBindViewHolder$1$2", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class b extends Lambda implements Function1<View, Unit> {
        final /* synthetic */ DigitalMoneyBankCard $currentItemData;
        final /* synthetic */ DigitalBankViewHolder $holder$inlined;
        final /* synthetic */ DigitalBankAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(DigitalMoneyBankCard digitalMoneyBankCard, DigitalBankAdapter digitalBankAdapter, DigitalBankViewHolder digitalBankViewHolder) {
            super(1);
            this.$currentItemData = digitalMoneyBankCard;
            this.this$0 = digitalBankAdapter;
            this.$holder$inlined = digitalBankViewHolder;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View view) {
            if (g.g(this.$currentItemData.status)) {
                this.this$0.m(this.$currentItemData);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DigitalBankAdapter(@NotNull FragmentActivity fragmentActivity, @NotNull List<? extends DigitalMoneyBankCard> list) {
        this.activity = fragmentActivity;
        this.dataList = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void m(DigitalMoneyBankCard digitalMoneyBankCard) {
        if ((!Intrinsics.areEqual(h(), digitalMoneyBankCard)) == true) {
            Iterator<T> it = this.dataList.iterator();
            while (it.hasNext()) {
                ((DigitalMoneyBankCard) it.next()).selected = false;
            }
            if (digitalMoneyBankCard != null) {
                digitalMoneyBankCard.selected = true;
            }
            notifyDataSetChanged();
            com.jd.lib.cashier.sdk.b.i.e.f("CLICK_CYBER_MONEY_CHANNEL", new ClickCyberMoneyChannelItemEvent(digitalMoneyBankCard != null ? digitalMoneyBankCard.selectedCoupon : null));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }

    @Nullable
    public final DigitalMoneyBankCard h() {
        for (DigitalMoneyBankCard digitalMoneyBankCard : this.dataList) {
            if (digitalMoneyBankCard.selected) {
                return digitalMoneyBankCard;
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NotNull DigitalBankViewHolder holder, int position) {
        int size = this.dataList.size();
        if (position >= 0 && size > position) {
            DigitalMoneyBankCard digitalMoneyBankCard = this.dataList.get(position);
            holder.b(this.activity, digitalMoneyBankCard, new a(digitalMoneyBankCard, this, holder));
            holder.c(Intrinsics.areEqual("1", digitalMoneyBankCard.status));
            holder.f(new b(digitalMoneyBankCard, this, holder));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public DigitalBankViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_cashier_sdk_item_digital_money_bank_dialog, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        return new DigitalBankViewHolder(itemView);
    }
}
