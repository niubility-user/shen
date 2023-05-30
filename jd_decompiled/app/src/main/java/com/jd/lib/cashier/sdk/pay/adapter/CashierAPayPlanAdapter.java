package com.jd.lib.cashier.sdk.pay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 82\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u001d\u0012\u0006\u0010(\u001a\u00020%\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\u00a2\u0006\u0004\b6\u00107J\u001f\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001b\u001a\u00020\u000b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\u00a2\u0006\u0004\b\u001b\u0010\u001cR$\u0010$\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0016\u0010(\u001a\u00020%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R$\u0010.\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010\u0013R\"\u0010\u0015\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u0010\u0017R\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105\u00a8\u00069"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/CashierAPayPlanAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jd/lib/cashier/sdk/pay/adapter/CashierAPayPlanViewHolder;", "Landroid/view/ViewGroup;", "parent", "", "viewType", "m", "(Landroid/view/ViewGroup;I)Lcom/jd/lib/cashier/sdk/pay/adapter/CashierAPayPlanViewHolder;", "holder", "position", "", NotifyType.LIGHTS, "(Lcom/jd/lib/cashier/sdk/pay/adapter/CashierAPayPlanViewHolder;I)V", "getItemCount", "()I", "", "mxHighlight", PersonalConstants.ICON_STYLE_N, "(Ljava/lang/String;)V", "", "viewNeedPadding", "o", "(Z)V", "", "Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;", "dataList", "q", "(Ljava/util/List;)V", "Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;", com.jingdong.jdsdk.a.a.a, "Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;", "getOnItemClickListener", "()Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;", "p", "(Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;)V", "onItemClickListener", "Landroid/content/Context;", "d", "Landroid/content/Context;", "activity", "b", "Ljava/lang/String;", "getMianXiHighlight", "()Ljava/lang/String;", "setMianXiHighlight", "mianXiHighlight", "c", "Z", "getViewNeedPadding", "()Z", "setViewNeedPadding", com.jingdong.app.mall.e.a, "Ljava/util/List;", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "g", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CashierAPayPlanAdapter extends RecyclerView.Adapter<CashierAPayPlanViewHolder> {
    @NotNull

    /* renamed from: f */
    private static final String f3807f;

    /* renamed from: g  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @Nullable

    /* renamed from: a */
    private OnPlanViewClickListener onItemClickListener;
    @Nullable

    /* renamed from: b  reason: from kotlin metadata */
    private String mianXiHighlight;

    /* renamed from: c  reason: from kotlin metadata */
    private boolean viewNeedPadding = true;

    /* renamed from: d  reason: from kotlin metadata */
    private Context activity;

    /* renamed from: e */
    private List<? extends IPlanItemViewEntity> dataList;

    /* renamed from: com.jd.lib.cashier.sdk.pay.adapter.CashierAPayPlanAdapter$a  reason: from kotlin metadata */
    /* loaded from: classes14.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String a() {
            return CashierAPayPlanAdapter.f3807f;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        String simpleName = CashierAPayPlanAdapter.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "CashierAPayPlanAdapter::class.java.simpleName");
        f3807f = simpleName;
    }

    public CashierAPayPlanAdapter(@NotNull Context context, @NotNull List<? extends IPlanItemViewEntity> list) {
        this.activity = context;
        this.dataList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l */
    public void onBindViewHolder(@NotNull CashierAPayPlanViewHolder cashierAPayPlanViewHolder, int i2) {
        int size = this.dataList.size();
        if (i2 >= 0 && size > i2) {
            cashierAPayPlanViewHolder.c(this.activity, this.dataList.get(i2), this.mianXiHighlight, this.onItemClickListener, this.viewNeedPadding);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: m */
    public CashierAPayPlanViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.activity).inflate(R.layout.lib_cashier_sdk_item_a_pay_plan_view, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        return new CashierAPayPlanViewHolder(itemView);
    }

    public final void n(@Nullable String str) {
        this.mianXiHighlight = str;
    }

    public final void o(boolean viewNeedPadding) {
        this.viewNeedPadding = viewNeedPadding;
    }

    public final void p(@Nullable OnPlanViewClickListener onPlanViewClickListener) {
        this.onItemClickListener = onPlanViewClickListener;
    }

    public final void q(@NotNull List<? extends IPlanItemViewEntity> dataList) {
        this.dataList = dataList;
    }
}
