package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.PlanRowEntity;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\u0015\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0004\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0012\u00a8\u0006\u0017"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/PlanPaymentRateAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jd/lib/cashier/sdk/pay/adapter/PlanRayRateViewHolder;", "", "getItemCount", "()I", "Landroid/view/ViewGroup;", "parent", "viewType", JshopConst.JSHOP_PROMOTIO_H, "(Landroid/view/ViewGroup;I)Lcom/jd/lib/cashier/sdk/pay/adapter/PlanRayRateViewHolder;", "holder", "position", "", com.jingdong.jdsdk.a.a.a, "(Lcom/jd/lib/cashier/sdk/pay/adapter/PlanRayRateViewHolder;I)V", "", "Lcom/jd/lib/cashier/sdk/core/model/PlanRowEntity;", "Ljava/util/List;", "dataList", "<init>", "(Ljava/util/List;)V", "b", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class PlanPaymentRateAdapter extends RecyclerView.Adapter<PlanRayRateViewHolder> {

    /* renamed from: b  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: a  reason: from kotlin metadata */
    private final List<PlanRowEntity> dataList;

    /* renamed from: com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter$a  reason: from kotlin metadata */
    /* loaded from: classes14.dex */
    public static final class Companion {
        private Companion() {
        }

        /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
            java.lang.NullPointerException
            */
        public static final /* synthetic */ java.util.List a(com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter.Companion r0, java.util.List r1) {
            /*
                r0.b(r1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter.Companion.a(com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter$a, java.util.List):java.util.List");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        private final List<PlanRowEntity> b(List<? extends PlanRowEntity> list) {
            PlanRowEntity.TYPE type;
            int size = list.size();
            int size2 = list.size();
            for (int i2 = 0; i2 < size2; i2++) {
                PlanRowEntity planRowEntity = (PlanRowEntity) list.get(i2);
                if (i2 == 0) {
                    type = PlanRowEntity.TYPE.HEADER;
                } else if (i2 == size - 1) {
                    type = PlanRowEntity.TYPE.TAIL;
                } else {
                    type = PlanRowEntity.TYPE.CELL;
                }
                planRowEntity.type = type;
            }
            return list;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter.a.a(com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter$a, java.util.List):java.util.List
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    /* JADX WARN: Multi-variable type inference failed */
    public PlanPaymentRateAdapter(@org.jetbrains.annotations.NotNull java.util.List<? extends com.jd.lib.cashier.sdk.core.model.PlanRowEntity> r2) {
        /*
            r1 = this;
            r1.<init>()
            r1.dataList = r2
            com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter$a r0 = com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter.INSTANCE
            com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter.Companion.a(r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter.<init>(java.util.List):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NotNull PlanRayRateViewHolder holder, int position) {
        if (position < 0 || position >= this.dataList.size()) {
            return;
        }
        PlanRowEntity planRowEntity = this.dataList.get(position);
        holder.b(planRowEntity);
        holder.c(planRowEntity);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public PlanRayRateViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_cashier_sdk_dialog_plan_rate_item, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        return new PlanRayRateViewHolder(itemView);
    }
}
