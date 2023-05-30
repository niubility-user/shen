package com.jd.lib.productdetail.tradein.selectdevice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInSelectDeviceBrandAdapter extends RecyclerView.Adapter<a> {
    public final List<TradeInSelectDeviceData.Data.CategoriesInfo.CateItem> a;
    public View.OnClickListener b;

    /* renamed from: c  reason: collision with root package name */
    public TradeInSelectDeviceData.Data.CategoriesInfo.CateItem f5497c;

    /* loaded from: classes16.dex */
    public static final class a extends RecyclerView.ViewHolder {
        public final TextView a;

        public a(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tradein_select_device_brand_item_name);
        }
    }

    public TradeInSelectDeviceBrandAdapter(List<TradeInSelectDeviceData.Data.CategoriesInfo.CateItem> list, View.OnClickListener onClickListener) {
        this.a = list;
        this.b = onClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(View view) {
        if (view.getTag() instanceof TradeInSelectDeviceData.Data.CategoriesInfo.CateItem) {
            Object tag = view.getTag();
            TradeInSelectDeviceData.Data.CategoriesInfo.CateItem cateItem = this.f5497c;
            if (tag != cateItem) {
                if (cateItem != null) {
                    cateItem.selected = false;
                }
                TradeInSelectDeviceData.Data.CategoriesInfo.CateItem cateItem2 = (TradeInSelectDeviceData.Data.CategoriesInfo.CateItem) view.getTag();
                this.f5497c = cateItem2;
                cateItem2.selected = true;
                View.OnClickListener onClickListener = this.b;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                notifyDataSetChanged();
            }
        }
    }

    @NonNull
    public a a(@NonNull ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_device_brand_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        TradeInSelectDeviceData.Data.CategoriesInfo.CateItem cateItem = this.a.get(i2);
        if (cateItem != null) {
            if (cateItem.selected) {
                this.f5497c = cateItem;
            }
            aVar.a.setText(cateItem.categoryName);
            if (cateItem.selected) {
                aVar.a.setBackgroundColor(-1);
                aVar.a.setTypeface(null, 1);
            } else {
                aVar.a.setBackgroundColor(-592138);
                aVar.a.setTypeface(null, 0);
            }
            aVar.itemView.setTag(cateItem);
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.selectdevice.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TradeInSelectDeviceBrandAdapter.this.h(view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
