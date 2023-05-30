package com.jd.lib.productdetail.tradein.selectdevice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInSelectDeviceCateAdapter extends RecyclerView.Adapter<a> {
    public final List<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> a;
    public final View.OnClickListener b;

    /* loaded from: classes16.dex */
    public static final class a extends RecyclerView.ViewHolder {
        public final TextView a;

        public a(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tradein_select_device_cate_item_name);
        }
    }

    public TradeInSelectDeviceCateAdapter(List<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> list, View.OnClickListener onClickListener) {
        this.a = list;
        this.b = onClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(View view) {
        if (view.getTag() instanceof TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem) {
            List<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> list = this.a;
            if (list != null) {
                Iterator<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> it = list.iterator();
                while (it.hasNext()) {
                    it.next().selected = false;
                }
            }
            ((TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem) view.getTag()).selected = true;
            View.OnClickListener onClickListener = this.b;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            notifyDataSetChanged();
        }
    }

    @NonNull
    public a a(@NonNull ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_device_cate_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> list = this.a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem cateItem;
        List<TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem> list = this.a;
        if (list == null || (cateItem = list.get(i2)) == null) {
            return;
        }
        aVar.a.setText(cateItem.categoryName);
        aVar.a.setSelected(cateItem.selected);
        aVar.itemView.setTag(cateItem);
        aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.selectdevice.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInSelectDeviceCateAdapter.this.h(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
