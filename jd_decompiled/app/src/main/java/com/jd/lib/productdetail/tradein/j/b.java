package com.jd.lib.productdetail.tradein.j;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.ways.TradeInRenewsWaysFragment;
import java.util.ArrayList;

/* loaded from: classes16.dex */
public class b extends RecyclerView.Adapter<TradeInRenewsWaysFragment.a> {
    public final /* synthetic */ ArrayList a;
    public final /* synthetic */ int b;

    /* renamed from: c */
    public final /* synthetic */ int f5380c;
    public final /* synthetic */ TradeInRenewsWaysFragment d;

    public b(TradeInRenewsWaysFragment tradeInRenewsWaysFragment, ArrayList arrayList, int i2, int i3) {
        this.d = tradeInRenewsWaysFragment;
        this.a = arrayList;
        this.b = i2;
        this.f5380c = i3;
    }

    public void a(TradeInResultData.BarterText.BarterOperateEvent.Data.ReplacementModeList replacementModeList, View view) {
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo;
        int i2;
        TradeInRenewsWaysFragment tradeInRenewsWaysFragment = this.d;
        TradeInViewModel tradeInViewModel = tradeInRenewsWaysFragment.f5607i;
        if (tradeInViewModel == null || (tradeInWareCardInfo = tradeInRenewsWaysFragment.f5606h) == null || replacementModeList == null) {
            return;
        }
        boolean z = false;
        int i3 = tradeInViewModel.f5256h;
        tradeInViewModel.f5257i = i3;
        if (tradeInWareCardInfo.cardType == 4 && (i2 = replacementModeList.tradeTypeRefreshTarget) != 0 && i2 != -1 && i3 != i2) {
            tradeInViewModel.f5256h = i2;
            z = true;
        }
        tradeInWareCardInfo.setCurrentTradeInMode(tradeInRenewsWaysFragment.getContext(), replacementModeList);
        PdBaseProtocolLiveData.Result<TradeInResultData> value = tradeInViewModel.u.getValue();
        if (z && value != null) {
            value.mStatus = PdBaseProtocolLiveData.Result.DataStatus.DIRTY;
        }
        tradeInViewModel.u.setValue(value);
        tradeInRenewsWaysFragment.getParentFragmentManager().popBackStack();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: h */
    public void onBindViewHolder(@NonNull TradeInRenewsWaysFragment.a aVar, int i2) {
        final TradeInResultData.BarterText.BarterOperateEvent.Data.ReplacementModeList replacementModeList = (TradeInResultData.BarterText.BarterOperateEvent.Data.ReplacementModeList) this.a.get(i2);
        aVar.f5608c.setSelected(false);
        if (replacementModeList.replacementMode == this.b) {
            int i3 = this.f5380c;
            if (i3 != 0) {
                if (replacementModeList.subReplacementMode == i3) {
                    aVar.f5608c.setSelected(true);
                }
            } else {
                aVar.f5608c.setSelected(true);
            }
        }
        aVar.a.setText(replacementModeList.t1);
        aVar.b.setText(replacementModeList.t2);
        aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.j.c
            {
                b.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                b.this.a(replacementModeList, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public TradeInRenewsWaysFragment.a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new TradeInRenewsWaysFragment.a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_renew_ways_item, viewGroup, false));
    }
}
