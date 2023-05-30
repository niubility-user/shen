package com.jd.lib.cashier.sdk.pay.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinSkuInfo;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinWalletInfo;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0010\u0010\u0017\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u0010\u00a2\u0006\u0004\b\u0018\u0010\u0016J\u001f\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fR,\u0010\u0017\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0019"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/GWJAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jd/lib/cashier/sdk/pay/adapter/GWJViewHolder;", "Landroid/view/ViewGroup;", "parent", "", "viewType", JshopConst.JSHOP_PROMOTIO_H, "(Landroid/view/ViewGroup;I)Lcom/jd/lib/cashier/sdk/pay/adapter/GWJViewHolder;", "getItemCount", "()I", "viewHolder", "position", "", com.jingdong.jdsdk.a.a.a, "(Lcom/jd/lib/cashier/sdk/pay/adapter/GWJViewHolder;I)V", "", "Lcom/jd/lib/cashier/sdk/pay/bean/GouWuJinWalletInfo;", "Ljava/util/List;", "getWalletInfos", "()Ljava/util/List;", "setWalletInfos", "(Ljava/util/List;)V", "walletInfos", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class GWJAdapter extends RecyclerView.Adapter<GWJViewHolder> {
    @Nullable

    /* renamed from: a  reason: from kotlin metadata */
    private List<? extends GouWuJinWalletInfo> walletInfos;

    public GWJAdapter(@Nullable List<? extends GouWuJinWalletInfo> list) {
        this.walletInfos = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NotNull GWJViewHolder viewHolder, int position) {
        GouWuJinWalletInfo gouWuJinWalletInfo;
        List<? extends GouWuJinWalletInfo> list = this.walletInfos;
        if (list == null || (gouWuJinWalletInfo = list.get(position)) == null) {
            return;
        }
        viewHolder.l(gouWuJinWalletInfo.activityName, viewHolder.getGwjName());
        viewHolder.l(gouWuJinWalletInfo.activityTip, viewHolder.getGwjCanUsePrice());
        viewHolder.l(gouWuJinWalletInfo.statusDesc, viewHolder.getGwjAmountText());
        View gwjLine = viewHolder.getGwjLine();
        if (gwjLine != null) {
            gwjLine.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
        }
        if (gouWuJinWalletInfo.enable) {
            TextView gwjAmount = viewHolder.getGwjAmount();
            if (gwjAmount != null) {
                gwjAmount.setVisibility(0);
            }
            viewHolder.l(gouWuJinWalletInfo.baseTotalAmt, viewHolder.getGwjAmount());
            View disableLayout = viewHolder.getDisableLayout();
            if (disableLayout != null) {
                disableLayout.setVisibility(8);
            }
            TextView gwjName = viewHolder.getGwjName();
            if (gwjName != null) {
                gwjName.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
            }
            TextView gwjCanUsePrice = viewHolder.getGwjCanUsePrice();
            if (gwjCanUsePrice != null) {
                gwjCanUsePrice.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
            }
            TextView gwjAmountText = viewHolder.getGwjAmountText();
            if (gwjAmountText != null) {
                gwjAmountText.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
            }
            TextView gwjAmount2 = viewHolder.getGwjAmount();
            if (gwjAmount2 != null) {
                gwjAmount2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19));
            }
            m0.a(viewHolder.getGwjAmount(), (byte) 3);
        } else {
            if (!TextUtils.isEmpty(gouWuJinWalletInfo.disableDesc)) {
                View disableLayout2 = viewHolder.getDisableLayout();
                if (disableLayout2 != null) {
                    disableLayout2.setVisibility(0);
                }
                TextView gwjDisableDesc = viewHolder.getGwjDisableDesc();
                if (gwjDisableDesc != null) {
                    gwjDisableDesc.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
                }
                viewHolder.l(gouWuJinWalletInfo.disableDesc, viewHolder.getGwjDisableDesc());
            } else {
                View disableLayout3 = viewHolder.getDisableLayout();
                if (disableLayout3 != null) {
                    disableLayout3.setVisibility(8);
                }
            }
            TextView gwjAmount3 = viewHolder.getGwjAmount();
            if (gwjAmount3 != null) {
                gwjAmount3.setVisibility(8);
            }
            TextView gwjName2 = viewHolder.getGwjName();
            if (gwjName2 != null) {
                gwjName2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            }
            TextView gwjCanUsePrice2 = viewHolder.getGwjCanUsePrice();
            if (gwjCanUsePrice2 != null) {
                gwjCanUsePrice2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            }
            TextView gwjAmountText2 = viewHolder.getGwjAmountText();
            if (gwjAmountText2 != null) {
                gwjAmountText2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            }
            TextView gwjAmount4 = viewHolder.getGwjAmount();
            if (gwjAmount4 != null) {
                gwjAmount4.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            }
        }
        List<GouWuJinSkuInfo> list2 = gouWuJinWalletInfo.skuInfoList;
        if (!(list2 == null || list2.isEmpty())) {
            RecyclerView gwjRecyclerView = viewHolder.getGwjRecyclerView();
            if (gwjRecyclerView != null) {
                gwjRecyclerView.setVisibility(0);
            }
            RecyclerView gwjRecyclerView2 = viewHolder.getGwjRecyclerView();
            if (gwjRecyclerView2 != null) {
                gwjRecyclerView2.setLayoutManager(new LinearLayoutManager(gwjRecyclerView2.getContext(), 0, false));
                gwjRecyclerView2.setAdapter(new GWJProductAdapter(gouWuJinWalletInfo.skuInfoList));
                return;
            }
            return;
        }
        RecyclerView gwjRecyclerView3 = viewHolder.getGwjRecyclerView();
        if (gwjRecyclerView3 != null) {
            gwjRecyclerView3.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<? extends GouWuJinWalletInfo> list = this.walletInfos;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public GWJViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_cashier_sdk_payment_gwj_item_view, (ViewGroup) null, false);
        Intrinsics.checkExpressionValueIsNotNull(contentView, "contentView");
        return new GWJViewHolder(contentView);
    }
}
