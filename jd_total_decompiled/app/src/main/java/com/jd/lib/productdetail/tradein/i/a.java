package com.jd.lib.productdetail.tradein.i;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.time.TradeInSelectWishTimeFragment;
import com.jd.lib.productdetail.tradein.time.TradeInSelectWishTimeGroupAdapter;
import com.jd.lib.productdetail.tradein.time.TradeInSelectWishTimesResp;
import java.util.Iterator;

/* loaded from: classes16.dex */
public class a extends RecyclerView.OnScrollListener {
    public final /* synthetic */ TradeInSelectWishTimeFragment a;

    public a(TradeInSelectWishTimeFragment tradeInSelectWishTimeFragment) {
        this.a = tradeInSelectWishTimeFragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
        TradeInSelectWishTimesResp.Data.PromiseDateItem promiseDateItem;
        if (i2 == 0) {
            RecyclerView.LayoutManager layoutManager = this.a.f5585n.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                RecyclerView.Adapter adapter = this.a.f5584m.getAdapter();
                if (adapter instanceof TradeInSelectWishTimeGroupAdapter) {
                    Iterator<TradeInSelectWishTimesResp.Data.PromiseDateItem> it = ((TradeInSelectWishTimeGroupAdapter) adapter).a.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            promiseDateItem = null;
                            break;
                        }
                        promiseDateItem = it.next();
                        if (promiseDateItem.selected == 1) {
                            break;
                        }
                    }
                    if (promiseDateItem != null) {
                        this.a.v.put(promiseDateItem.recycleDate, Integer.valueOf(linearLayoutManager.findFirstVisibleItemPosition()));
                    }
                }
            }
        }
    }
}
