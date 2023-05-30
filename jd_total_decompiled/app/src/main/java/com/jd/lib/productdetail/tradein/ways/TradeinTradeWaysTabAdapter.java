package com.jd.lib.productdetail.tradein.ways;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysResp;
import java.util.Iterator;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeinTradeWaysTabAdapter extends RecyclerView.Adapter<a> {
    public List<TradeInTradeWaysResp.Data.TradeModeItem> a;
    public Action1<TradeInTradeWaysResp.Data.TradeModeItem> b;

    /* loaded from: classes16.dex */
    public static final class a extends RecyclerView.ViewHolder {
        public final TextView a;

        public a(@NonNull View view) {
            super(view);
            TextView textView = (TextView) view.findViewById(R.id.tradein_select_device_cate_tag_item_name);
            this.a = textView;
            textView.setBackgroundResource(com.jd.lib.productdetail.core.R.drawable.lib_pd_common_item_background_corners_40_light);
            textView.setTextColor(textView.getResources().getColorStateList(com.jd.lib.productdetail.core.R.color.lib_pd_common_item_textcolor_light));
        }
    }

    public TradeinTradeWaysTabAdapter(List<TradeInTradeWaysResp.Data.TradeModeItem> list) {
        this.a = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(TradeInTradeWaysResp.Data.TradeModeItem tradeModeItem, View view) {
        if (tradeModeItem.selected == 1) {
            return;
        }
        Iterator<TradeInTradeWaysResp.Data.TradeModeItem> it = this.a.iterator();
        while (it.hasNext()) {
            TradeInTradeWaysResp.Data.TradeModeItem next = it.next();
            next.selected = next == tradeModeItem ? 1 : 0;
        }
        notifyDataSetChanged();
        Action1<TradeInTradeWaysResp.Data.TradeModeItem> action1 = this.b;
        if (action1 != null) {
            action1.call(tradeModeItem);
        }
    }

    @NonNull
    public a a(@NonNull ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_device_cate_tag_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<TradeInTradeWaysResp.Data.TradeModeItem> list = this.a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        final TradeInTradeWaysResp.Data.TradeModeItem tradeModeItem = this.a.get(i2);
        TextView textView = aVar.a;
        textView.setText(tradeModeItem.tradeModeName);
        textView.setSelected(tradeModeItem.selected == 1);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.ways.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeinTradeWaysTabAdapter.this.h(tradeModeItem, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
