package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.h.h.g;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010%\u001a\u00020\u0010\u0012\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00190!\u00a2\u0006\u0004\b&\u0010'J\u001f\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fR\"\u0010\u0017\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R6\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r\u0018\u00010\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00190!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#\u00a8\u0006("}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/WholePaymentAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jd/lib/cashier/sdk/pay/adapter/WholePaymentViewHolder;", "Landroid/view/ViewGroup;", "parent", "", "viewType", "m", "(Landroid/view/ViewGroup;I)Lcom/jd/lib/cashier/sdk/pay/adapter/WholePaymentViewHolder;", "getItemCount", "()I", "holder", "position", "", NotifyType.LIGHTS, "(Lcom/jd/lib/cashier/sdk/pay/adapter/WholePaymentViewHolder;I)V", "Landroidx/fragment/app/FragmentActivity;", com.jingdong.jdsdk.a.a.a, "Landroidx/fragment/app/FragmentActivity;", "getActivityContext", "()Landroidx/fragment/app/FragmentActivity;", "setActivityContext", "(Landroidx/fragment/app/FragmentActivity;)V", "activityContext", "Lkotlin/Function2;", "Lcom/jd/lib/cashier/sdk/h/g/x;", "b", "Lkotlin/jvm/functions/Function2;", JshopConst.JSHOP_PROMOTIO_H, "()Lkotlin/jvm/functions/Function2;", PersonalConstants.ICON_STYLE_N, "(Lkotlin/jvm/functions/Function2;)V", "onPaymentItemClickListener", "", "c", "Ljava/util/List;", "dataList", AnnoConst.Constructor_Context, "<init>", "(Landroidx/fragment/app/FragmentActivity;Ljava/util/List;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class WholePaymentAdapter extends RecyclerView.Adapter<WholePaymentViewHolder> {
    @NotNull

    /* renamed from: a  reason: from kotlin metadata */
    private FragmentActivity activityContext;
    @Nullable

    /* renamed from: b  reason: from kotlin metadata */
    private Function2<? super x, ? super Integer, Unit> onPaymentItemClickListener;

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private final List<x> dataList;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ x f3848g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ WholePaymentAdapter f3849h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f3850i;

        a(x xVar, WholePaymentAdapter wholePaymentAdapter, WholePaymentViewHolder wholePaymentViewHolder, int i2) {
            this.f3848g = xVar;
            this.f3849h = wholePaymentAdapter;
            this.f3850i = i2;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Function2<x, Integer, Unit> h2;
            if (!g.g(this.f3848g.a().status) || (h2 = this.f3849h.h()) == null) {
                return;
            }
            h2.invoke(this.f3848g, Integer.valueOf(this.f3850i));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WholePaymentAdapter(@NotNull FragmentActivity fragmentActivity, @NotNull List<? extends x> list) {
        this.dataList = list;
        this.activityContext = fragmentActivity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }

    @Nullable
    public final Function2<x, Integer, Unit> h() {
        return this.onPaymentItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NotNull WholePaymentViewHolder holder, int position) {
        x xVar = this.dataList.get(position);
        holder.b(this.activityContext, xVar, position);
        holder.c(new a(xVar, this, holder, position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public WholePaymentViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_cashier_sdk_regulator_item_cashier_whole_payment, (ViewGroup) null, false);
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        return new WholePaymentViewHolder(itemView);
    }

    public final void n(@Nullable Function2<? super x, ? super Integer, Unit> function2) {
        this.onPaymentItemClickListener = function2;
    }
}
