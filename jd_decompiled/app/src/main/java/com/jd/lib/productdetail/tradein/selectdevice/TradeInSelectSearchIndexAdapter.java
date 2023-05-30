package com.jd.lib.productdetail.tradein.selectdevice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import java.util.ArrayList;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeInSelectSearchIndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public boolean a = false;
    public List<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    public Action1<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> f5538c;

    /* loaded from: classes16.dex */
    public static class a extends RecyclerView.ViewHolder {
        public TextView a;

        public a(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tradein_select_device_search_index_footer_item_text);
        }
    }

    /* loaded from: classes16.dex */
    public static class b extends RecyclerView.ViewHolder {
        public TextView a;

        public b(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tradein_select_device_search_index_item_text);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i2, View view) {
        List<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> list;
        Action1<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> action1 = this.f5538c;
        if (action1 == null || (list = this.b) == null || list.size() <= i2) {
            return;
        }
        action1.call(this.b.get(i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size() + (this.a ? 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return i2 == this.b.size() ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i2) {
        if (viewHolder instanceof b) {
            b bVar = (b) viewHolder;
            List<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> list = this.b;
            if (list != null && list.size() > i2) {
                bVar.a.setText(this.b.get(i2).inquiryName);
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.selectdevice.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TradeInSelectSearchIndexAdapter.this.a(i2, view);
                }
            });
            return;
        }
        a aVar = (a) viewHolder;
        if (this.a) {
            aVar.a.setText("\u52a0\u8f7d\u4e2d...");
        } else {
            aVar.a.setText((CharSequence) null);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_device_search_index_item, viewGroup, false));
        }
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_device_search_index_footer_item, viewGroup, false));
    }
}
