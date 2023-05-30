package com.jd.lib.productdetail.tradein.time;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.time.TradeInSelectWishTimesResp;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeInSelectWishTimeDateAdapter extends RecyclerView.Adapter<a> {
    public final List<TradeInSelectWishTimesResp.Data.PromiseDateItem> a;
    public Action1<TradeInSelectWishTimesResp.Data.PromiseDateItem> b;

    /* loaded from: classes16.dex */
    public static final class a extends RecyclerView.ViewHolder {
        public final TextView a;

        public a(@NonNull View view) {
            super(view);
            TextView textView = (TextView) view.findViewById(R.id.tradein_select_wish_time_label_big);
            this.a = textView;
            FontsUtil.changeTextFont(textView, 4099);
        }
    }

    public TradeInSelectWishTimeDateAdapter(List<TradeInSelectWishTimesResp.Data.PromiseDateItem> list) {
        this.a = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(TradeInSelectWishTimesResp.Data.PromiseDateItem promiseDateItem, View view) {
        Action1<TradeInSelectWishTimesResp.Data.PromiseDateItem> action1 = this.b;
        if (action1 != null) {
            action1.call(promiseDateItem);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    public int h() {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            if (this.a.get(i2).selected == 1) {
                return i2;
            }
        }
        return 0;
    }

    @NonNull
    public a l(@NonNull ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_time_big, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        final TradeInSelectWishTimesResp.Data.PromiseDateItem promiseDateItem = this.a.get(i2);
        aVar.a.setText(promiseDateItem.displayText);
        aVar.a.setSelected(promiseDateItem.selected == 1);
        aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.time.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInSelectWishTimeDateAdapter.this.n(promiseDateItem, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return l(viewGroup);
    }
}
