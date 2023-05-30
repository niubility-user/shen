package com.jd.lib.cashier.sdk.pay.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010$\u001a\u00020\t\u00a2\u0006\u0004\b%\u0010&J!\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0007\u0010\bR\u001b\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u000f\u0010\u0012R\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00048\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0010\u001a\u0004\b\n\u0010\u0012R\u001b\u0010\u001a\u001a\u0004\u0018\u00010\u00048\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0012R\u001b\u0010\u001b\u001a\u0004\u0018\u00010\t8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\u000b\u001a\u0004\b\u0018\u0010\rR\u001b\u0010\u001e\u001a\u0004\u0018\u00010\u00048\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001c\u0010\u0010\u001a\u0004\b\u001d\u0010\u0012R\u001b\u0010#\u001a\u0004\u0018\u00010\u001f8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0019\u0010 \u001a\u0004\b!\u0010\"\u00a8\u0006'"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/GWJViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "", "text", "Landroid/widget/TextView;", "view", "", NotifyType.LIGHTS, "(Ljava/lang/String;Landroid/widget/TextView;)V", "Landroid/view/View;", "g", "Landroid/view/View;", "i", "()Landroid/view/View;", "gwjLine", "f", "Landroid/widget/TextView;", JshopConst.JSHOP_PROMOTIO_H, "()Landroid/widget/TextView;", "gwjDisableDesc", "c", "gwjAmountText", "b", "gwjCanUsePrice", "d", com.jingdong.app.mall.e.a, "gwjAmount", "disableLayout", com.jingdong.jdsdk.a.a.a, "j", "gwjName", "Landroidx/recyclerview/widget/RecyclerView;", "Landroidx/recyclerview/widget/RecyclerView;", "k", "()Landroidx/recyclerview/widget/RecyclerView;", "gwjRecyclerView", "contentView", "<init>", "(Landroid/view/View;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class GWJViewHolder extends RecyclerView.ViewHolder {
    @Nullable

    /* renamed from: a  reason: from kotlin metadata */
    private final TextView gwjName;
    @Nullable

    /* renamed from: b  reason: from kotlin metadata */
    private final TextView gwjCanUsePrice;
    @Nullable

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private final TextView gwjAmountText;
    @Nullable

    /* renamed from: d  reason: from kotlin metadata */
    private final TextView gwjAmount;
    @Nullable

    /* renamed from: e  reason: collision with root package name and from kotlin metadata */
    private final RecyclerView gwjRecyclerView;
    @Nullable

    /* renamed from: f  reason: collision with root package name and from kotlin metadata */
    private final TextView gwjDisableDesc;
    @Nullable

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private final View gwjLine;
    @Nullable

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private final View disableLayout;

    public GWJViewHolder(@NotNull View view) {
        super(view);
        this.gwjName = (TextView) view.findViewById(R.id.gwj_name);
        this.gwjCanUsePrice = (TextView) view.findViewById(R.id.gwj_can_use_price);
        this.gwjAmountText = (TextView) view.findViewById(R.id.gwj_amount_text);
        this.gwjAmount = (TextView) view.findViewById(R.id.gwj_amount);
        this.gwjRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_gwj_product_list);
        this.gwjDisableDesc = (TextView) view.findViewById(R.id.gwj_disable_desc);
        this.gwjLine = view.findViewById(R.id.gwj_line);
        this.disableLayout = view.findViewById(R.id.disable_desc_view);
    }

    @Nullable
    /* renamed from: d  reason: from getter */
    public final View getDisableLayout() {
        return this.disableLayout;
    }

    @Nullable
    /* renamed from: e  reason: from getter */
    public final TextView getGwjAmount() {
        return this.gwjAmount;
    }

    @Nullable
    /* renamed from: f  reason: from getter */
    public final TextView getGwjAmountText() {
        return this.gwjAmountText;
    }

    @Nullable
    /* renamed from: g  reason: from getter */
    public final TextView getGwjCanUsePrice() {
        return this.gwjCanUsePrice;
    }

    @Nullable
    /* renamed from: h  reason: from getter */
    public final TextView getGwjDisableDesc() {
        return this.gwjDisableDesc;
    }

    @Nullable
    /* renamed from: i  reason: from getter */
    public final View getGwjLine() {
        return this.gwjLine;
    }

    @Nullable
    /* renamed from: j  reason: from getter */
    public final TextView getGwjName() {
        return this.gwjName;
    }

    @Nullable
    /* renamed from: k  reason: from getter */
    public final RecyclerView getGwjRecyclerView() {
        return this.gwjRecyclerView;
    }

    public final void l(@Nullable String text, @Nullable TextView view) {
        if (TextUtils.isEmpty(text)) {
            if (view != null) {
                view.setVisibility(8);
                return;
            }
            return;
        }
        if (view != null) {
            view.setVisibility(0);
        }
        if (view != null) {
            view.setText(text);
        }
    }
}
