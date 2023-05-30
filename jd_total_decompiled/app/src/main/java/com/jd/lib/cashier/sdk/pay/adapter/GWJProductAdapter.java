package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinSkuInfo;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0010\u0010\u0017\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u0010\u00a2\u0006\u0004\b\u0018\u0010\u0016J\u001f\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fR,\u0010\u0017\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0019"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/GWJProductAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jd/lib/cashier/sdk/pay/adapter/GWJProductViewHolder;", "Landroid/view/ViewGroup;", "parent", "", "viewType", JshopConst.JSHOP_PROMOTIO_H, "(Landroid/view/ViewGroup;I)Lcom/jd/lib/cashier/sdk/pay/adapter/GWJProductViewHolder;", "getItemCount", "()I", "holder", "position", "", com.jingdong.jdsdk.a.a.a, "(Lcom/jd/lib/cashier/sdk/pay/adapter/GWJProductViewHolder;I)V", "", "Lcom/jd/lib/cashier/sdk/pay/bean/GouWuJinSkuInfo;", "Ljava/util/List;", "getSkuInfoList", "()Ljava/util/List;", "setSkuInfoList", "(Ljava/util/List;)V", "skuInfoList", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class GWJProductAdapter extends RecyclerView.Adapter<GWJProductViewHolder> {
    @Nullable

    /* renamed from: a  reason: from kotlin metadata */
    private List<? extends GouWuJinSkuInfo> skuInfoList;

    public GWJProductAdapter(@Nullable List<? extends GouWuJinSkuInfo> list) {
        this.skuInfoList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NotNull GWJProductViewHolder holder, int position) {
        GouWuJinSkuInfo gouWuJinSkuInfo;
        List<? extends GouWuJinSkuInfo> list = this.skuInfoList;
        if (list == null || (gouWuJinSkuInfo = list.get(position)) == null) {
            return;
        }
        String str = gouWuJinSkuInfo.imgUrl;
        Intrinsics.checkExpressionValueIsNotNull(str, "it.imgUrl");
        holder.b(str);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<? extends GouWuJinSkuInfo> list = this.skuInfoList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public GWJProductViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_cashier_sdk_payment_gwj_picture_item, (ViewGroup) null, false);
        Intrinsics.checkExpressionValueIsNotNull(contentView, "contentView");
        return new GWJProductViewHolder(contentView);
    }
}
