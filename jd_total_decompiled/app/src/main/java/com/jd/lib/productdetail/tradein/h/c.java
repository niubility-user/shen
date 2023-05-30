package com.jd.lib.productdetail.tradein.h;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreFragment;
import com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp;
import com.jd.lib.productdetail.tradein.stores.TradeinSelectStoreTabAdapter;
import java.util.Iterator;

/* loaded from: classes16.dex */
public class c extends RecyclerView.OnScrollListener {
    public final /* synthetic */ LinearLayoutManager a;
    public final /* synthetic */ TradeInSelectStoreFragment b;

    public c(TradeInSelectStoreFragment tradeInSelectStoreFragment, LinearLayoutManager linearLayoutManager) {
        this.b = tradeInSelectStoreFragment;
        this.a = linearLayoutManager;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
        TradeInSelectStoreResp.Data.StoreTagItem storeTagItem;
        if (i2 == 0) {
            RecyclerView.Adapter adapter = this.b.f5554n.getAdapter();
            if (adapter instanceof TradeinSelectStoreTabAdapter) {
                Iterator<TradeInSelectStoreResp.Data.StoreTagItem> it = ((TradeinSelectStoreTabAdapter) adapter).a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        storeTagItem = null;
                        break;
                    }
                    storeTagItem = it.next();
                    if (storeTagItem.selected == 1) {
                        break;
                    }
                }
                if (storeTagItem != null) {
                    this.b.r.put(storeTagItem.tagId, this.a.findFirstVisibleItemPosition());
                }
            }
        }
    }
}
