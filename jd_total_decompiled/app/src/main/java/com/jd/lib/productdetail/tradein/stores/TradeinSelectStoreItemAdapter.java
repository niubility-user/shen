package com.jd.lib.productdetail.tradein.stores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeinSelectStoreItemAdapter extends RecyclerView.Adapter<a> {
    public final List<TradeInSelectStoreResp.Data.StoreTagItem.StoreInfoItem> a;
    public Action1<TradeInSelectStoreResp.Data.StoreTagItem.StoreInfoItem> b;

    /* loaded from: classes16.dex */
    public static final class a extends RecyclerView.ViewHolder {
        public final TextView a;
        public final TextView b;

        /* renamed from: c  reason: collision with root package name */
        public final TextView f5555c;
        public final TextView d;

        /* renamed from: e  reason: collision with root package name */
        public final ImageView f5556e;

        public a(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tradein_select_store_addr_title);
            this.b = (TextView) view.findViewById(R.id.tradein_select_store_addr_distance);
            this.f5555c = (TextView) view.findViewById(R.id.tradein_select_store_addr_info);
            this.d = (TextView) view.findViewById(R.id.tradein_select_store_open_time);
            this.f5556e = (ImageView) view.findViewById(R.id.tradein_select_store_addr_select_flag);
        }
    }

    public TradeinSelectStoreItemAdapter(List<TradeInSelectStoreResp.Data.StoreTagItem.StoreInfoItem> list) {
        this.a = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(TradeInSelectStoreResp.Data.StoreTagItem.StoreInfoItem storeInfoItem, View view) {
        Action1<TradeInSelectStoreResp.Data.StoreTagItem.StoreInfoItem> action1 = this.b;
        if (action1 != null) {
            action1.call(storeInfoItem);
        }
    }

    @NonNull
    public a a(@NonNull ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_store_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        final TradeInSelectStoreResp.Data.StoreTagItem.StoreInfoItem storeInfoItem = this.a.get(i2);
        aVar.a.setText(storeInfoItem.storeName);
        aVar.b.setText(storeInfoItem.distance);
        aVar.f5555c.setText(String.format("\u5730\u5740\uff1a%s", storeInfoItem.storeAddress));
        aVar.d.setText(storeInfoItem.openTime);
        if (storeInfoItem.selected == 1) {
            aVar.f5556e.setImageResource(R.drawable.tradein_address_selected_icon);
        } else {
            aVar.f5556e.setImageResource(R.drawable.tradein_address_unselect_icon);
        }
        aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.stores.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeinSelectStoreItemAdapter.this.h(storeInfoItem, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
