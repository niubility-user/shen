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
public class TradeInSelectWishTimeItemAdapter extends RecyclerView.Adapter<a> {
    public final List<TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem> a;
    public Action1<TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem> b;

    /* loaded from: classes16.dex */
    public static final class a extends RecyclerView.ViewHolder {
        public final TextView a;

        public a(@NonNull View view) {
            super(view);
            TextView textView = (TextView) view.findViewById(R.id.tradein_select_time_tag_small);
            this.a = textView;
            FontsUtil.changeTextFont(textView, 4099);
        }
    }

    public TradeInSelectWishTimeItemAdapter(List<TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem> list) {
        this.a = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem timeMomentItem, View view) {
        Action1<TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem> action1 = this.b;
        if (action1 != null) {
            action1.call(timeMomentItem);
        }
    }

    @NonNull
    public a a(@NonNull ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_time_small, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        final TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem timeMomentItem = this.a.get(i2);
        aVar.a.setText(String.format("%s-%s", timeMomentItem.startTime, timeMomentItem.endTime));
        aVar.a.setSelected(timeMomentItem.selected == 1);
        aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.time.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInSelectWishTimeItemAdapter.this.l(timeMomentItem, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
