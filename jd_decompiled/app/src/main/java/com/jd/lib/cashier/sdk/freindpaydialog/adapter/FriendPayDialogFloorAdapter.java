package com.jd.lib.cashier.sdk.freindpaydialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.d.a.e.a;
import com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity;
import com.jd.lib.cashier.sdk.freindpaydialog.viewholder.FriendPayDialogDefaultViewHolder;
import com.jd.lib.cashier.sdk.freindpaydialog.viewholder.FriendPayDialogPriceInfoViewHolder;
import com.jd.lib.cashier.sdk.freindpaydialog.viewholder.FriendPayDialogProductListViewHolder;
import com.jd.lib.cashier.sdk.freindpaydialog.viewholder.FriendPayDialogTipInfoViewHolder;
import com.jd.lib.cashier.sdk.g.g.b;
import com.jd.lib.cashier.sdk.g.g.d;
import java.util.List;

/* loaded from: classes14.dex */
public class FriendPayDialogFloorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context a;
    public List<a> b;

    /* renamed from: c */
    private boolean f3432c = false;

    public FriendPayDialogFloorAdapter(Context context, List<a> list) {
        this.a = context;
        this.b = list;
    }

    private void h() {
        List<a> list = this.b;
        if (list == null || list.size() == 0) {
            return;
        }
        this.f3432c = !this.f3432c;
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (this.b.get(i2).getItemType() == 1003) {
                b bVar = (b) this.b.get(i2);
                if (bVar.f3477g) {
                    bVar.f3476f = true;
                } else {
                    bVar.f3476f = this.f3432c;
                }
                bVar.f3479i = this.f3432c;
                this.b.set(i2, bVar);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<a> list = this.b;
        if (list == null || list.size() == 0) {
            return 0;
        }
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        List<a> list = this.b;
        if (list == null || list.size() == 0) {
            return 0;
        }
        return this.b.get(i2).getItemType();
    }

    public void l() {
        h();
        notifyDataSetChanged();
    }

    public void m(boolean z, View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z) {
            ((ViewGroup.MarginLayoutParams) layoutParams).height = -2;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = -1;
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
            ((ViewGroup.MarginLayoutParams) layoutParams).height = 0;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = 0;
        }
        view.setLayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        List<a> list = this.b;
        if (list == null || i2 > list.size() - 1) {
            return;
        }
        if (viewHolder instanceof FriendPayDialogPriceInfoViewHolder) {
            ((FriendPayDialogPriceInfoViewHolder) viewHolder).b((FriendPayDialogActivity) this.a, (com.jd.lib.cashier.sdk.g.g.a) this.b.get(i2));
        } else if (viewHolder instanceof FriendPayDialogTipInfoViewHolder) {
            ((FriendPayDialogTipInfoViewHolder) viewHolder).b((FriendPayDialogActivity) this.a, (d) this.b.get(i2));
        } else if (viewHolder instanceof FriendPayDialogProductListViewHolder) {
            if (!((b) this.b.get(i2)).f3476f) {
                m(false, viewHolder.itemView);
                return;
            }
            m(true, viewHolder.itemView);
            ((FriendPayDialogProductListViewHolder) viewHolder).c((FriendPayDialogActivity) this.a, this, (b) this.b.get(i2));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 1001) {
            return new FriendPayDialogPriceInfoViewHolder(LayoutInflater.from(this.a).inflate(R.layout.lib_cashier_sdk_friend_pay_dialog_price_info_floor, viewGroup, false));
        }
        if (i2 == 1002) {
            return new FriendPayDialogTipInfoViewHolder(LayoutInflater.from(this.a).inflate(R.layout.lib_cashier_sdk_friend_pay_tip_info_floor, viewGroup, false));
        }
        if (i2 == 1003) {
            return new FriendPayDialogProductListViewHolder(LayoutInflater.from(this.a).inflate(R.layout.lib_cashier_sdk_friend_pay_dialog_product_floor, viewGroup, false));
        }
        return new FriendPayDialogDefaultViewHolder(LayoutInflater.from(this.a).inflate(R.layout.lib_cashier_sdk_friend_pay_dialog_empty, viewGroup, false));
    }
}
