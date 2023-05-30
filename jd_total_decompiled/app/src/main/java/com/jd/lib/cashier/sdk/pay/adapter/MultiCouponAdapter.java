package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.h.h.i;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010$\u001a\u00020!\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u00a2\u0006\u0004\b%\u0010&J\u0017\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R6\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0016\u0010$\u001a\u00020!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#\u00a8\u0006'"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/MultiCouponAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "", "position", "getItemViewType", "(I)I", "Landroid/view/ViewGroup;", "parent", "viewType", "onCreateViewHolder", "(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getItemCount", "()I", "holder", "", "onBindViewHolder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V", "", "Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "c", "Ljava/util/List;", "dataList", "Lkotlin/Function2;", "Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "", com.jingdong.jdsdk.a.a.a, "Lkotlin/jvm/functions/Function2;", NotifyType.LIGHTS, "()Lkotlin/jvm/functions/Function2;", "m", "(Lkotlin/jvm/functions/Function2;)V", "onItemClickListener", "Landroidx/fragment/app/FragmentActivity;", "b", "Landroidx/fragment/app/FragmentActivity;", "activity", "<init>", "(Landroidx/fragment/app/FragmentActivity;Ljava/util/List;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class MultiCouponAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Nullable

    /* renamed from: a  reason: from kotlin metadata */
    private Function2<? super CouponEntity, ? super Boolean, Unit> onItemClickListener;

    /* renamed from: b  reason: from kotlin metadata */
    private final FragmentActivity activity;

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private final List<com.jd.lib.cashier.sdk.pay.dialog.e> dataList;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Landroid/view/View;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Landroid/view/View;)V", "com/jd/lib/cashier/sdk/pay/adapter/MultiCouponAdapter$onBindViewHolder$1$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class a extends Lambda implements Function1<View, Unit> {
        final /* synthetic */ CouponEntity $couponEntity;
        final /* synthetic */ RecyclerView.ViewHolder $holder$inlined;
        final /* synthetic */ int $position$inlined;
        final /* synthetic */ boolean $tempSelected;
        final /* synthetic */ MultiCouponAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(CouponEntity couponEntity, boolean z, MultiCouponAdapter multiCouponAdapter, RecyclerView.ViewHolder viewHolder, int i2) {
            super(1);
            this.$couponEntity = couponEntity;
            this.$tempSelected = z;
            this.this$0 = multiCouponAdapter;
            this.$holder$inlined = viewHolder;
            this.$position$inlined = i2;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View view) {
            Function2<CouponEntity, Boolean, Unit> l2;
            int i2 = this.$position$inlined;
            if (i2 < 0 || i2 >= this.this$0.dataList.size() || (l2 = this.this$0.l()) == null) {
                return;
            }
            CouponEntity couponEntity = this.$couponEntity;
            if (couponEntity == null) {
                couponEntity = new CouponEntity();
            }
            l2.invoke(couponEntity, Boolean.valueOf(!this.$tempSelected));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/i;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/i;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class b extends Lambda implements Function1<i, Unit> {
        final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.e $entity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
            super(1);
            this.$entity = eVar;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(i iVar) {
            invoke2(iVar);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull i iVar) {
            com.jd.lib.cashier.sdk.h.e.a.d().v(MultiCouponAdapter.this.activity, iVar.b(), iVar.a(), iVar.e(), this.$entity.getPayMarketingUUID());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MultiCouponAdapter(@NotNull FragmentActivity fragmentActivity, @NotNull List<? extends com.jd.lib.cashier.sdk.pay.dialog.e> list) {
        this.activity = fragmentActivity;
        this.dataList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.dataList.get(position).getViewType();
    }

    @Nullable
    public final Function2<CouponEntity, Boolean, Unit> l() {
        return this.onItemClickListener;
    }

    public final void m(@Nullable Function2<? super CouponEntity, ? super Boolean, Unit> function2) {
        this.onItemClickListener = function2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        Boolean selected;
        if (position < 0 || position >= this.dataList.size()) {
            return;
        }
        com.jd.lib.cashier.sdk.pay.dialog.e eVar = this.dataList.get(position);
        if (holder instanceof d) {
            CouponEntity couponEntity = (CouponEntity) (!(eVar instanceof CouponEntity) ? null : eVar);
            boolean booleanValue = (couponEntity == null || (selected = couponEntity.getSelected()) == null) ? false : selected.booleanValue();
            d dVar = (d) holder;
            dVar.c(eVar.getCouponTypeName());
            dVar.f(eVar.getTitleName());
            dVar.e(eVar.getSubtitle());
            dVar.d(eVar.getUsingScene());
            dVar.k(position, getItemCount());
            dVar.h(booleanValue);
            dVar.j();
            dVar.i(new a(couponEntity, booleanValue, this, holder, position));
            dVar.b(couponEntity != null ? couponEntity.getStackableTag() : null);
            dVar.g();
        } else if (holder instanceof e) {
            e eVar2 = (e) holder;
            eVar2.b(eVar.getCouponTypeName());
            eVar2.e(eVar.getTitleName());
            eVar2.d(eVar.getSubtitle());
            eVar2.c(eVar.getUsingScene());
            eVar2.h(position, getItemCount());
            eVar2.g();
            eVar2.f();
        }
        com.jd.lib.cashier.sdk.h.h.c.c(this.activity, new b(eVar));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        if (viewType != 1) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_cashier_sdk_item_multi_coupon_disabled, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(pare\u2026_disabled, parent, false)");
            return new e(inflate);
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_cashier_sdk_item_multi_coupon_available, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        return new d(itemView);
    }
}
