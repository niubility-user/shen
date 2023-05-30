package com.jd.lib.productdetail.tradein.h;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreFragment;

/* loaded from: classes16.dex */
public class b extends RecyclerView.ItemDecoration {
    public b(TradeInSelectStoreFragment tradeInSelectStoreFragment) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        rect.bottom = PDUtils.dip2px(8.0f);
    }
}
