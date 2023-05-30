package com.jd.lib.productdetail.tradein.stores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp;
import java.util.Iterator;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeinSelectStoreTabAdapter extends RecyclerView.Adapter<a> {
    public final List<TradeInSelectStoreResp.Data.StoreTagItem> a;
    public Action1<TradeInSelectStoreResp.Data.StoreTagItem> b;

    /* loaded from: classes16.dex */
    public static final class a extends RecyclerView.ViewHolder {
        public final TextView a;

        public a(@NonNull View view) {
            super(view);
            TextView textView = (TextView) view.findViewById(R.id.tradein_select_device_cate_tag_item_name);
            this.a = textView;
            textView.setBackgroundResource(com.jd.lib.productdetail.core.R.drawable.lib_pd_common_item_background_corners_40_light);
            textView.setTextColor(ContextCompat.getColorStateList(view.getContext(), com.jd.lib.productdetail.core.R.color.lib_pd_common_item_textcolor_light));
        }
    }

    public TradeinSelectStoreTabAdapter(List<TradeInSelectStoreResp.Data.StoreTagItem> list) {
        this.a = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(TradeInSelectStoreResp.Data.StoreTagItem storeTagItem, View view) {
        if (storeTagItem.selected == 1) {
            return;
        }
        Iterator<TradeInSelectStoreResp.Data.StoreTagItem> it = this.a.iterator();
        while (it.hasNext()) {
            TradeInSelectStoreResp.Data.StoreTagItem next = it.next();
            next.selected = next == storeTagItem ? 1 : 0;
        }
        notifyDataSetChanged();
        Action1<TradeInSelectStoreResp.Data.StoreTagItem> action1 = this.b;
        if (action1 != null) {
            action1.call(storeTagItem);
        }
    }

    @NonNull
    public a a(@NonNull ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_device_cate_tag_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        final TradeInSelectStoreResp.Data.StoreTagItem storeTagItem = this.a.get(i2);
        aVar.a.setText(String.format("%s(%s)", storeTagItem.tagName, Integer.valueOf(storeTagItem.storeInfos.size())));
        aVar.a.setSelected(storeTagItem.selected == 1);
        aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.stores.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeinSelectStoreTabAdapter.this.h(storeTagItem, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
